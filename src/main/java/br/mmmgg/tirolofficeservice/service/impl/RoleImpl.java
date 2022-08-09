package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.repository.RoleRepository;
import br.mmmgg.tirolofficeservice.service.IService;

@Service
public class RoleImpl implements IService<Role> {
	
	@Autowired
	private RoleRepository repository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleImpl.class);

	@Override
	public Role save(Role role) {
		LOGGER.info("Saving new role {}", role.getName());
		return repository.save(role);
	}

	@Override
	public List<Role> getAll() {
		LOGGER.info("Listing all roles");
		return repository.findAll();
	}
	
	@Override
	public Role getById(Integer id) throws NoSuchElementException {
		LOGGER.info("Getting role with id {}", id);
		return repository.findById(id).orElseThrow();
	}
	
	
	public void removeById(Integer id) {
		LOGGER.info("Removing role with id {}", id);
		repository.deleteById(id);
	}
	

}
