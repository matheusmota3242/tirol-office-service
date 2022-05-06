package br.mmmgg.tirolofficeservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mmmgg.tirolofficeservice.model.User;
import br.mmmgg.tirolofficeservice.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl service;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(service.getUsers());
	}
	
	@PostMapping
	public User saveUser(@RequestBody @Valid User user) {
		LOGGER.info("Endpoint entry: {}", "saveUser");
		return service.saveUser(user);
	}
}
