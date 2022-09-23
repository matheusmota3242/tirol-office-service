package br.mmmgg.tirolofficeservice.service.impl;

import static br.mmmgg.tirolofficeservice.util.ErrorMessageUtil.*;

import java.util.List;
import java.util.Optional;

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
	
	public boolean hasValidProperties(Maintenance maintenance) {
		boolean validEquipment = maintenance.getEquipment() != null
				&& maintenance.getEquipment().getId() != null
				&& equipmentService.findById(maintenance.getEquipment().getId()).isPresent();
		
		boolean validServiceProvider = maintenance.getServiceProvider() != null
				&& maintenance.getServiceProvider().getId() != null
				&& serviceProviderService.findById(maintenance.getServiceProvider().getId()).isPresent();
		
		return validEquipment && validServiceProvider;
	}

}
