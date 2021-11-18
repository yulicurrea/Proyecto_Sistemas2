package co.edu.unbosque.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;

public interface JWTService {

	/**
	 * Crea un token que contiene el nombre del usuario y sus credenciales
	 * 
	 * @param auth Objeto que contiene la representacion del usuario logueado
	 * @return Cadena que representa el token
	 * @throws IOException si ocurre algun error al crear token o extraer los datos
	 *                     del usuario
	 */
	public String create(Authentication auth) throws IOException;

	/**
	 * Valida si tiene un token valido
	 * 
	 * @param token Cadena que representa el token a validar
	 * @return true si el token es valido, false en caso contrario
	 */
	public boolean validate(String token);

	/**
	 * Obtiene las reclamaciones contenidas en el token recibido como parametro
	 * 
	 * @param token Cadena que representa el token
	 * @return Reclamaciones
	 */
	public Claims getClaims(String token);

	/**
	 * Obtiene el usuario contenido en el token recibido como parametro
	 * 
	 * @param token Cadena que representa el token
	 * @return Cadena que representa el usuario
	 */
	public String getUsername(String token);

	/**
	 * Obtiene la coleccion de permisos contenidos en el token
	 * 
	 * @param token Cadena que representa el token
	 * @return Coleccion de permisos
	 * @throws IOException
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException;

	/**
	 * Limpia el token para retornar la informacion relevante contenida en el, sin
	 * encabezado
	 * 
	 * @param token Cadena que representa el token
	 * @return Token sin encabezado
	 */
	public String resolve(String token);

}
