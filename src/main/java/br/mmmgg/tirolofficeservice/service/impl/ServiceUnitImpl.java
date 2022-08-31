package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.ServiceUnit;
import br.mmmgg.tirolofficeservice.repository.ServiceUnitRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class ServiceUnitImpl implements IService<ServiceUnit> {

    @Autowired
    private ServiceUnitRepository repository;

	@Override
	public ServiceUnit save(ServiceUnit entity) throws ValidationException {
		return repository.save(entity);
	}

	@Override
	public ServiceUnit getById(Integer id) throws ValidationException {
		return repository.findById(id).orElseThrow(() -> new ValidationException(null, "Não há registro que contenha o 'id' passado."));
	}

	@Override
	public List<ServiceUnit> getAll() {
		return repository.findAll();
	}

	@Override
	public void removeById(Integer id) throws ValidationException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException(null, "Não há registro que contenha o 'id' passado.");
		}
		
	}


    
}