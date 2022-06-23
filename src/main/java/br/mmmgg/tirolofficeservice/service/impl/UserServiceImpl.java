package br.mmmgg.tirolofficeservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.mmmgg.tirolofficeservice.model.Role;
import br.mmmgg.tirolofficeservice.model.User;
import br.mmmgg.tirolofficeservice.repository.RoleRepository;
import br.mmmgg.tirolofficeservice.repository.UserRepository;
import br.mmmgg.tirolofficeservice.service.UserService;
import br.mmmgg.tirolofficeservice.util.PropertiesUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final String BEARER_PREFIX = "Bearer ";

	@Override
	public User saveUser(User user) {
		LOGGER.info("Saving new user {} to the database.", user.getEmail());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
		LOGGER.info("Listando todos os usuários do banco de dados.");
		return userRepository.findAll();
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith(BEARER_PREFIX)) {
			
			try {
				String refreshToken  = authorizationHeader.substring(BEARER_PREFIX.length());
				Algorithm algorithm = Algorithm.HMAC256(PropertiesUtil.loadProperties("application.properties").getProperty("jwt.secret").getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				String username = decodedJWT.getSubject();
				User user = getUser(username);
				String accessToken = JWT.create()
					.withSubject(user.getEmail())
					.withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
					.withIssuer(request.getRequestURL().toString())
					.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
					.sign(algorithm);
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception e) {
				LOGGER.error("Erro ao tentar recarregar o token: {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh token inexistente.");
		}
	}

}
