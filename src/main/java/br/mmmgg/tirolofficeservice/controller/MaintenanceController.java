package br.mmmgg.tirolofficeservice.controller;

import static br.mmmgg.tirolofficeservice.util.LogUtil.GET_ALL_ENTRY_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.GET_ALL_EXIT_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.GET_BY_ID_ENTRY_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.INEXISTENT_REGISTER_ERROR;
import static br.mmmgg.tirolofficeservice.util.LogUtil.REMOVE_BY_ID_ENTRY_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.REMOVE_BY_ID_EXIT_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.REMOVE_ERROR;
import static br.mmmgg.tirolofficeservice.util.LogUtil.SAVE_ENTRY_POINT;
import static br.mmmgg.tirolofficeservice.util.LogUtil.SAVE_ERROR;
import static br.mmmgg.tirolofficeservice.util.LogUtil.SAVE_EXIT_POINT;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mmmgg.tirolofficeservice.ValidationException;
import br.mmmgg.tirolofficeservice.model.Maintenance;
import br.mmmgg.tirolofficeservice.service.impl.MaintenanceImpl;

@RestController
@RequestMapping("maintenances")
public class MaintenanceController {

	@Autowired
	private MaintenanceImpl service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceController.class);
	
	@PostMapping
	public Maintenance save(@RequestBody @Valid Maintenance maintenance) {
		LOGGER.info(SAVE_ENTRY_POINT, maintenance);
		try {
			maintenance = service.save(maintenance); 
		} catch (ValidationException e) {
			LOGGER.error(SAVE_ERROR, maintenance);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getDescription(), e.getCause());
		}
		LOGGER.info(SAVE_EXIT_POINT, maintenance);
		return maintenance;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Maintenance> getAll() {
		LOGGER.info(GET_ALL_ENTRY_POINT);
		List<Maintenance> Maintenances = service.getAll();
		LOGGER.info(GET_ALL_EXIT_POINT, Maintenances);
		return Maintenances;
	}
	
	@GetMapping("{id}")
	public Maintenance getById(@PathVariable Integer id) {
		LOGGER.info(GET_BY_ID_ENTRY_POINT, id);
		Maintenance Maintenance = null;
		try {
			Maintenance = service.getById(id);	
		} catch (ValidationException e) {
			LOGGER.error(INEXISTENT_REGISTER_ERROR);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getDescription(), e.getCause());
		}
		return Maintenance;
	}
	
	@DeleteMapping("{id}")
	public void removeById(@PathVariable Integer id) {
		LOGGER.info(REMOVE_BY_ID_ENTRY_POINT, id);
		try {
			service.removeById(id);
		} catch (ValidationException e) {
			LOGGER.error(REMOVE_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getDescription(), e.getCause());
		}
		LOGGER.info(REMOVE_BY_ID_EXIT_POINT, id);
	}
}
