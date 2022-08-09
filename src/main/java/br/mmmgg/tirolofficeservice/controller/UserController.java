package br.mmmgg.tirolofficeservice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import br.mmmgg.tirolofficeservice.model.User;
import br.mmmgg.tirolofficeservice.service.impl.UserImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserImpl service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public List<User> getAll() {
		LOGGER.info("Endpoint entry: {}", "getAll");
		return service.getAll();
	}
	
	@PostMapping
	public User save(@RequestBody @Valid User user) throws IllegalArgumentException, IOException {
		LOGGER.info("Endpoint entry: {} {}", "save", user);
		return service.save(user);
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		service.refreshToken(request, response);
	}

}
