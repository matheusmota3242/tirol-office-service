package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.ServiceUnit;
import br.mmmgg.tirolofficeservice.repository.ServiceUnitRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class ServiceUnitImpl implements IService<ServiceUnit> {

    @Autowired
    private ServiceUnitRepository repository;

	@Override
	public ServiceUnit save(ServiceUnit entity) {
		return repository.save(entity);
	}

	@Override
	public ServiceUnit getById(Integer id) throws NoSuchMethodException {
		return repository.findById(id).orElseThrow(NoSuchMethodException::new);
	}

	@Override
	public List<ServiceUnit> getAll() {
		return repository.findAll();
	}

	@Override
	public void removeById(Integer id) {
		repository.deleteById(id);
	}


    
}