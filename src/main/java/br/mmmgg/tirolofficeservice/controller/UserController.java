package br.mmmgg.tirolofficeservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import br.mmmgg.tirolofficeservice.model.User;
import br.mmmgg.tirolofficeservice.service.impl.UserImpl;
import br.mmmgg.tirolofficeservice.util.LogUtil;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserImpl service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public List<User> getAll() {
		LOGGER.info(LogUtil.GET_ALL_ENTRY_POINT);
		List<User> users = service.getAll();
		LOGGER.info(LogUtil.GET_ALL_EXIT_POINT, users);
		return users;
	}
	
	@PostMapping
	public User save(@RequestBody @Valid User user) throws IllegalArgumentException, IOException {
		LOGGER.info(LogUtil.SAVE_ENTRY_POINT, user);
		user = service.save(user);
		LOGGER.info(LogUtil.SAVE_EXIT_POINT, user);
		return user;
	}
	
	@GetMapping("{id}")
	public User getById(@PathVariable Integer id) {
		LOGGER.info(LogUtil.GET_ALL_ENTRY_POINT);
		User user = null;
		try {
			user = service.getById(id);
		} catch (NoSuchElementException e) {
			LOGGER.error(LogUtil.INEXISTENT_REGISTER_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"There isn't a register that contains the passed 'id' value");
		}
		LOGGER.info(LogUtil.GET_BY_ID_EXIT_POINT, id);
		return user;
	}
	
	@DeleteMapping("{id}")
	public void removeById(@PathVariable Integer id) {
		LOGGER.info(LogUtil.REMOVE_BY_ID_ENTRY_POINT, id);
		try {
			service.removeById(id);
		} catch (IllegalArgumentException e) {
			LOGGER.info(LogUtil.INEXISTENT_REGISTER_ERROR, id);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"There isn't a register that contains the passed 'id' value");
		}
		LOGGER.info(LogUtil.REMOVE_BY_ID_EXIT_POINT, id);
		
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		service.refreshToken(request, response);
	}

}
