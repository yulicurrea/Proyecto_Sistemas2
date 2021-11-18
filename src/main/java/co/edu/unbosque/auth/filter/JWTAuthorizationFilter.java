package co.edu.unbosque.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import co.edu.unbosque.auth.service.JWTService;
import co.edu.unbosque.util.CommonVar;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);

		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(CommonVar.HEADER_STRING);
		boolean requiere = requiresAuthentication(header);
		if (requiere) {
			chain.doFilter(request, response);
		} else {

			UsernamePasswordAuthenticationToken authentication = null;

			if (jwtService.validate(header)) {
				authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null,
						jwtService.getAuthorities(header));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(request, response);
			} else {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				response.setStatus(401);
				response.addHeader("Access-Control-Allow-Origin", "*");

			}

		}
	}

	protected boolean requiresAuthentication(String header) {
		return (header == null || !header.startsWith(CommonVar.TOKEN_PREFIX));
	}

}