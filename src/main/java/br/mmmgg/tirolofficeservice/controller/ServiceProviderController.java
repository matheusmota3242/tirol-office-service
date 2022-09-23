package br.mmmgg.tirolofficeservice.controller;

import static br.mmmgg.tirolofficeservice.util.LogUtil.*;


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
import br.mmmgg.tirolofficeservice.model.ServiceProvider;
import br.mmmgg.tirolofficeservice.service.impl.ServiceProviderImpl;

@RestController
@RequestMapping("service-providers")
public class ServiceProviderController {

	@Autowired
	private ServiceProviderImpl service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceProviderController.class);
	
	@PostMapping
	public ServiceProvider save(@RequestBody @Valid ServiceProvider serviceProvider) {
		LOGGER.info(SAVE_ENTRY_POINT, serviceProvider);
		try {
			serviceProvider = service.save(serviceProvider); 
		} catch (ValidationException e) {
			LOGGER.error(SAVE_ERROR, serviceProvider);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getDescription(), e.getCause());
		}
		LOGGER.info(SAVE_EXIT_POINT, serviceProvider);
		return serviceProvider;
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<ServiceProvider> getAll() {
		LOGGER.info(GET_ALL_ENTRY_POINT);
		List<ServiceProvider> serviceProviders = service.getAll();
		LOGGER.info(GET_ALL_EXIT_POINT, serviceProviders);
		return serviceProviders;
	}
	
	@GetMapping("{id}")
	public ServiceProvider getById(@PathVariable Integer id) {
		LOGGER.info(GET_BY_ID_ENTRY_POINT, id);
		ServiceProvider serviceProvider = null;
		try {
			serviceProvider = service.getById(id);	
		} catch (ValidationException e) {
			LOGGER.error(INEXISTENT_REGISTER_ERROR);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getDescription(), e.getCause());
		}
		return serviceProvider;
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
