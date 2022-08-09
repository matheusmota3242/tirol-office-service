package br.mmmgg.tirolofficeservice.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
	
	private static final String BEARER_PREFIX = "Bearer ";
	
	public static int ACCESS_TOKEN_TIMEOUT_MILLIS = 3600 * 1000;

	public static int REFRESH_TOKEN_TIMEOUT_MILLIS = 2 * 3600 * 1000;

	public static boolean formattedCorrectly(String authorizationHeader) {
		return authorizationHeader != null && !authorizationHeader.isBlank()
				&& authorizationHeader.startsWith(BEARER_PREFIX);
	}
	
	public static DecodedJWT getDecodedJWT(String authorizationHeader) throws IllegalArgumentException, IOException {
		try {
			String refreshToken  = authorizationHeader.substring(BEARER_PREFIX.length());
			Algorithm algorithm = Algorithm.HMAC256(PropertiesUtil.loadProperties("application.properties").getProperty("jwt.secret").getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			return verifier.verify(refreshToken);
		} catch (IllegalArgumentException | IOException e) {
			LOGGER.error("Error while trying to get decoded JWT. Error: {}", e);
			throw e;
		}
		
	}
	
}
