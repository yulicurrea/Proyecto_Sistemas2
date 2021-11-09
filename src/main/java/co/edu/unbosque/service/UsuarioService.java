package co.edu.unbosque.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@Component
@Service
public class UsuarioService{

	@Autowired
	private UsuarioRepository personaResporitory;

	public UsuarioService() {

	}

	public UsuarioService(UsuarioRepository personaResporitory) {

		this.personaResporitory = personaResporitory;
	}

	public Usuario create(Usuario persona) {
		return personaResporitory.save(persona);
	}

	public List<Usuario> getAllPersonas() {
		return personaResporitory.findAll();
	}

	public void delete(Usuario persona) {
		personaResporitory.delete(persona);
	}

	public Optional<Usuario> findById(Long id) {
		return personaResporitory.findById(id);
	}
	
	public Usuario findUser(String usuario) {
		return personaResporitory.findOneByusuario(usuario);
	}

	public Usuario guardarUsuario(Usuario usuario) {

		return personaResporitory.saveAndFlush(usuario);
	}

	
}
