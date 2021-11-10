package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;



@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository personaResporitory;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public Usuario create(Usuario persona) {

		Usuario usu = new Usuario();
		usu.setClave(bCryptPasswordEncoder.encode(usu.getClave()));
		return personaResporitory.save(persona);
	}

	public List<Usuario> getAllPersonas() {
		return personaResporitory.findAll();
	}

	public void deleteById(Long id) {
		personaResporitory.deleteById(id);
	}

	public void delete(Usuario entity) {
		personaResporitory.delete(entity);
	}

	public Optional<Usuario> findById(Long id) {
		return personaResporitory.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = personaResporitory.findByUsuario(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));

		UserDetails userDet = new User(us.getUsuario(), us.getClave(), roles);

		return userDet;
	}

}
