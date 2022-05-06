package br.mmmgg.tirolofficeservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.mmmgg.tirolofficeservice.security.JWTUtil;
import br.mmmgg.tirolofficeservice.security.MyUserDetailsService;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	private static final String BEARER_PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith(BEARER_PREFIX)) {
			String jwt = authHeader.substring(BEARER_PREFIX.length());
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	};
}
