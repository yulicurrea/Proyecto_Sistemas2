package co.edu.unbosque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;

@Service
public class UsuarioDetailsService implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findUser(username);
		if(usuario == null) {
			
			throw new UsernameNotFoundException("El usuario no existe");
		}
		return usuario;
	}
	

}
