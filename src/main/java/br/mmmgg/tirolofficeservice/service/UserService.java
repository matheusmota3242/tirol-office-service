package br.mmmgg.tirolofficeservice.service;

import java.util.List;

import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.model.User;

public interface UserService {

	User saveUser(User user);

	Role saveRole(Role role);

	User getUser(String username);

	List<User> getUsers();
}
