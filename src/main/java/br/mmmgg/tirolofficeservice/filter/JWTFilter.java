package br.mmmgg.tirolofficeservice.filter;

import static br.mmmgg.tirolofficeservice.util.JWTUtil.formattedCorrectly;
import static br.mmmgg.tirolofficeservice.util.JWTUtil.getDecodedJWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	private Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);

	private static final String BEARER_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().equals("/login") || request.getServletPath().equals("/users/token/refresh")) {
			filterChain.doFilter(request, response);
		} else {
			String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if (formattedCorrectly(authHeader)) {
				try {
					DecodedJWT decodedJWT = getDecodedJWT(authHeader);
					String username = decodedJWT.getSubject();
					List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
					Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
					roles.forEach(role -> {
						authorities.add(new SimpleGrantedAuthority(role));
					});
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
				} catch (Exception e) {
					LOGGER.error("Error logging in: {}", e.getMessage());
					response.setHeader("error", e.getMessage());
					response.setStatus(HttpStatus.FORBIDDEN.value());
					Map<String, String> error = new HashMap<>();
					error.put("error_message", e.getMessage());
					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		}	
	};
}
