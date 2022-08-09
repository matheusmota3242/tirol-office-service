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

import br.mmmgg.tirolofficeservice.model.ServiceUnit;
import br.mmmgg.tirolofficeservice.service.impl.ServiceUnitImpl;
import br.mmmgg.tirolofficeservice.util.LogUtil;

@RestController
@RequestMapping("service-units")
public class ServiceUnitController {
    
    @Autowired
    private ServiceUnitImpl service;
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUnitController.class);

    @PostMapping
    public ServiceUnit save(@Valid @RequestBody ServiceUnit serviceUnit) {
    	LOGGER.info(LogUtil.SAVE_ENTRY_POINT, serviceUnit);
    	serviceUnit = service.save(serviceUnit);
    	LOGGER.info(LogUtil.SAVE_EXIT_POINT, serviceUnit);
        return serviceUnit;
    }
    
    @GetMapping
    public List<ServiceUnit> getAll() {
    	LOGGER.info(LogUtil.GET_ALL_ENTRY_POINT);
    	List<ServiceUnit> serviceUnits = service.getAll();
    	LOGGER.info(LogUtil.GET_ALL_EXIT_POINT, serviceUnits);
    	return serviceUnits;
    }
    
	@GetMapping("{id}")
	public ServiceUnit getById(@PathVariable Integer id) {
		LOGGER.info(LogUtil.GET_BY_ID_ENTRY_POINT, id);
		ServiceUnit serviceUnit = null;
		try {
			serviceUnit = service.getById(id);
		} catch (NoSuchMethodException e) {
			LOGGER.error(LogUtil.INEXISTENT_REGISTER, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"There isn't a register that contains the passed 'id' value");
		}
		LOGGER.info(LogUtil.GET_BY_ID_EXIT_POINT, serviceUnit);
		return serviceUnit;
	}
    
    @DeleteMapping("{id}")
    public void removeById(@PathVariable Integer id) {
    	LOGGER.info(LogUtil.REMOVE_BY_ID_ENTRY_POINT, id);
    	service.removeById(id);
    	LOGGER.info(LogUtil.REMOVE_BY_ID_EXIT_POINT, id);
    }
}
