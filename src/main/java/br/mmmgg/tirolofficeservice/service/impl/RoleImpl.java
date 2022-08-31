package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.repository.RoleRepository;
import br.mmmgg.tirolofficeservice.service.IService;
import br.mmmgg.tirolofficeservice.util.ErrorMessageUtil;

@Service
public class RoleImpl implements IService<Role> {
	
	@Autowired
	private RoleRepository repository;
	
	@Override
	public Role save(Role role) throws ValidationException {
		try {
			return repository.save(role);
		} catch (Exception e) {
			throw new ValidationException(e, ErrorMessageUtil.SAVE_REGISTER);
		}
	}

	@Override
	public List<Role> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Role getById(Integer id) throws ValidationException {
		return repository.findById(id).orElseThrow(() -> new ValidationException(ErrorMessageUtil.INEXISTENT_REGISTER));
	}
	
	
	public void removeById(Integer id) throws ValidationException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ValidationException(e, ErrorMessageUtil.REMOVE_REGISTER);
		}
		
	}
	

}
