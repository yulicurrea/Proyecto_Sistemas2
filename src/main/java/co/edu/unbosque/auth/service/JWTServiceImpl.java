package co.edu.unbosque.auth.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import co.edu.unbosque.service.JpaUserDetailsService;
import co.edu.unbosque.util.Bean;
import co.edu.unbosque.util.CommonVar;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {

	@SuppressWarnings("deprecation")
	@Override
	public String create(Authentication auth) throws IOException {
		String username = ((User) auth.getPrincipal()).getUsername();

		Map<String, Object> claims = new HashMap<>();
		claims.put("role", "ROLE");
		claims.put("companyId", 1);

		return Jwts.builder().setSubject(username).addClaims(claims)
				.signWith(SignatureAlgorithm.HS512, CommonVar.SALT.getBytes()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + CommonVar.DATE_TOKEN_EXPIRATION)).compact();

	}

	@Override
	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(CommonVar.SALT.getBytes()).parseClaimsJws(resolve(token)).getBody();
	}

	@Override
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException {

		String userName = getClaims(token).getSubject();
		JpaUserDetailsService dao = (JpaUserDetailsService) Bean.getBean(JpaUserDetailsService.class);
		return dao.getAuthorities(userName);
	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(CommonVar.TOKEN_PREFIX)) {
			return token.replace(CommonVar.TOKEN_PREFIX, "");
		} else {
			return null;
		}
	}

}
