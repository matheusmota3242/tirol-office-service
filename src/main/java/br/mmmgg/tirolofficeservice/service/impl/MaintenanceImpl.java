package br.mmmgg.tirolofficeservice.service.impl;

import static br.mmmgg.tirolofficeservice.util.ErrorMessageUtil.INEXISTENT_REGISTER;
import static br.mmmgg.tirolofficeservice.util.ErrorMessageUtil.REMOVE_REGISTER;
import static br.mmmgg.tirolofficeservice.util.ErrorMessageUtil.SAVE_REGISTER;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Maintenance;
import br.mmmgg.tirolofficeservice.repository.MaintenanceRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class MaintenanceImpl implements IService<Maintenance> {

	@Autowired
	private MaintenanceRepository repository;
	
	@Autowired
	private EquipmentImpl equipmentService;
	
	@Autowired
	private ServiceProviderImpl serviceProviderService;
	
	@Override
	public Maintenance save(Maintenance entity) throws ValidationException {
		try {
			if (hasValidProperties(entity))
				return repository.save(entity);
			else
				throw new IllegalArgumentException("A referência de pelo menos um provedor de serviço e um equipamento é mandatória.");
		} catch (Exception e) {			
			throw new ValidationException(e, SAVE_REGISTER);
		}

	}

	@Override
	public Maintenance getById(Integer id) throws ValidationException {
		return repository.findById(id).orElseThrow(() -> new ValidationException(INEXISTENT_REGISTER));
	}

	@Override
	public List<Maintenance> getAll() {
		return repository.findAll();
	}

	@Override
	public void removeById(Integer id) throws ValidationException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException(e, REMOVE_REGISTER);
		}
	}
	
	public Maintenance setHasOccured(Integer id, boolean hasOccured) throws ValidationException {
		try {
			Maintenance maintenance = getById(id);
			if (LocalDateTime.now().isAfter(maintenance.getDateTime())) {
				maintenance.setHasOccured(hasOccured);
				return save(maintenance);
			} else {
				throw new IllegalStateException("Não é possível alterar o status de ocorrência de uma manutenção antes da data/hora marcada.");
			}
		} catch (Exception e) {
			throw new ValidationException(e, "Erro ao tentar modificar status de ocorrência da manutnção.");
		}
	}
	
	private boolean hasValidProperties(Maintenance maintenance) {
		boolean validEquipment = maintenance.getEquipment() != null
				&& maintenance.getEquipment().getId() != null
				&& equipmentService.findById(maintenance.getEquipment().getId()).isPresent();
		
		boolean validServiceProvider = maintenance.getServiceProvider() != null
				&& maintenance.getServiceProvider().getId() != null
				&& serviceProviderService.findById(maintenance.getServiceProvider().getId()).isPresent();
		
		return validEquipment && validServiceProvider;
	}

}
