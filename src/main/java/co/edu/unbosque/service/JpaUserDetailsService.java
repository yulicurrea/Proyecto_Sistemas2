package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = dao.findByUsuario(username);

		List<Usuario> lista = dao.findAll();

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		String prefix = "ROLE_";

		// TODO agregar el rol
		// roles.add(new SimpleGrantedAuthority(prefix + usuario.getRole().getName()));
		roles.add(new SimpleGrantedAuthority(prefix + usuario.getRol().toUpperCase()));

		return new User(username, usuario.getClave(), true, true, true, true, roles);
	}

	public Collection<? extends GrantedAuthority> getAuthorities(String userName) {
		Usuario usuario = dao.findByUsuario(userName);

		List<GrantedAuthority> roles = new ArrayList<>();
		String prefix = "ROLE_";

		// TODO agregar el rol
		// roles.add(new SimpleGrantedAuthority(prefix + usuario.getRole().getName()));
		roles.add(new SimpleGrantedAuthority(prefix + "USER"));
		return roles;
	}

}
