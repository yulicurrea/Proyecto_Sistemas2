package co.edu.unbosque.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.auth.service.JWTService;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.util.CommonVar;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;

		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username != null && password != null) {
			logger.info("Username desde request parameter: " + username + "   :" + password);
		} else {

			Usuario user = null;

			try {
				user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

				username = user.getUsuario();
				password = user.getClave();

				logger.info("Username desde request parameter (raw): " + username + "   :" + password);
			} catch (JsonParseException e) {
				logger.info("JsonParseException");
			} catch (JsonMappingException e) {
				logger.info("JsonMappingException");
			} catch (IOException e) {
				logger.info("IOException");
			}
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.create(authResult);

		response.addHeader(CommonVar.HEADER_STRING, CommonVar.TOKEN_PREFIX + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");

		response.addHeader("Access-Control-Allow-Origin", "*");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("error", failed.getMessage().replaceAll("\\s", "_").toUpperCase());

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);// OK // 401 No autorizado //403 Acceso prohibido
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");

	}

}
