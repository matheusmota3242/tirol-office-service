package br.mmmgg.tirolofficeservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Department;
import br.mmmgg.tirolofficeservice.service.impl.DepartmentImpl;
import br.mmmgg.tirolofficeservice.util.LogUtil;

@RestController
@RequestMapping("departments")
public class DepartmentController {

	@Autowired
	private DepartmentImpl service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping
	public Department save(@RequestBody @Valid Department department) {
		LOGGER.info(LogUtil.SAVE_ENTRY_POINT, department);
		try {
			department = service.save(department);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.SAVE_ERROR, department);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getDescription(), e.getCause());
		}
		LOGGER.info(LogUtil.SAVE_EXIT_POINT, department);
		return department;
	}
	
	@GetMapping
	public List<Department> getAll() {
		LOGGER.info(LogUtil.GET_ALL_ENTRY_POINT);
		List<Department> departments = service.getAll();
		LOGGER.info(LogUtil.GET_ALL_EXIT_POINT, departments);
		return departments;
	}
	
	@GetMapping("{id}")
	public Department getById(@PathVariable Integer id) {
		LOGGER.info(LogUtil.GET_BY_ID_ENTRY_POINT, id);
		Department department = null;
		try {
			department = service.getById(id);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.INEXISTENT_REGISTER_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					e.getDescription());
		} 
		LOGGER.info(LogUtil.GET_BY_ID_EXIT_POINT, department);
		return department; 
	}
	
	@DeleteMapping("{id}")
	public void removeById(@PathVariable Integer id) {
		LOGGER.info(LogUtil.REMOVE_BY_ID_ENTRY_POINT, id);
		try {
			service.removeById(id);
		} catch (ValidationException e) {
			LOGGER.error(LogUtil.INEXISTENT_REGISTER_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					e.getDescription());
		}
		LOGGER.info(LogUtil.REMOVE_BY_ID_EXIT_POINT, id);
	}
}
