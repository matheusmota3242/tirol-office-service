package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.ServiceProvider;
import br.mmmgg.tirolofficeservice.repository.ServiceProviderRepository;
import br.mmmgg.tirolofficeservice.service.IService;
import static br.mmmgg.tirolofficeservice.util.ErrorMessageUtil.*;

@Service
public class ServiceProviderImpl implements IService<ServiceProvider> {

	@Autowired
	private ServiceProviderRepository repository;
	
	@Override
	public ServiceProvider save(ServiceProvider entity) throws ValidationException {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new ValidationException(e, SAVE_REGISTER);
		}
	}

	@Override
	public ServiceProvider getById(Integer id) throws ValidationException {
		return findById(id).orElseThrow(() -> new ValidationException(INEXISTENT_REGISTER));
	}

	@Override
	public List<ServiceProvider> getAll() {
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
	
	public Optional<ServiceProvider> findById(Integer id) {
		return repository.findById(id);
	}

}
