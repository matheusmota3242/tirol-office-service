package br.mmmgg.tirolofficeservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.model.User;
import br.mmmgg.tirolofficeservice.repository.RoleRepository;
import br.mmmgg.tirolofficeservice.repository.UserRepository;
import br.mmmgg.tirolofficeservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		LOGGER.info("Saving new user {} to the database.", user.getEmail());
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		LOGGER.info("Saving new role {} to the database.", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public User getUser(String username) {
		LOGGER.info("Getting user {} from database.", username);
		return userRepository.findByEmail(username)
				.orElseThrow(() -> new NoSuchElementException("Não há usuário com esse e-mail."));
	}

	@Override
	public List<User> getUsers() {
		LOGGER.info("Getting all users from database.");
		return userRepository.findAll();
	}

}
