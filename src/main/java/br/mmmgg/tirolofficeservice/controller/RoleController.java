package br.mmmgg.tirolofficeservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.service.impl.RoleImpl;
import br.mmmgg.tirolofficeservice.util.LogUtil;

@RestController
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private RoleImpl service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@PostMapping
	public Role save(@Valid @RequestBody Role role) {
		LOGGER.info(LogUtil.SAVE_ENTRY_POINT, role);
		try {
			role = service.save(role);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.SAVE_ERROR, role);
		}
		
		LOGGER.info(LogUtil.SAVE_EXIT_POINT, role);
		return role;
	}
	
	@GetMapping
	public List<Role> getAll() {
		LOGGER.info(LogUtil.GET_ALL_ENTRY_POINT);
		List<Role> roles = service.getAll();
		LOGGER.info(LogUtil.GET_ALL_EXIT_POINT, roles);
		return roles;
	}
	
	@GetMapping("{id}")
	public Role getById(Integer id) {
		LOGGER.info(LogUtil.GET_BY_ID_ENTRY_POINT, id);
		Role role = null;
		try {
			role = service.getById(id);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.INEXISTENT_REGISTER_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					e.getDescription());
		}
		LOGGER.info(LogUtil.GET_BY_ID_EXIT_POINT, role);
		return role;
	}
	
	@DeleteMapping("{id}")
	public void removeById(Integer id) {
		LOGGER.info(LogUtil.REMOVE_BY_ID_ENTRY_POINT, id);
		try {
			service.removeById(id);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.REMOVE_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					e.getDescription());
		}
		LOGGER.info(LogUtil.REMOVE_BY_ID_EXIT_POINT, id);
	}
}
