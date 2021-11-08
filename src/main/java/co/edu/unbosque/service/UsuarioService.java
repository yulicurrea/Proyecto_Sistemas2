package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@Component
public class UsuarioService implements IUsuarioService<Usuario> {

	@Autowired
	private UsuarioRepository personaResporitory;
	private UsuarioService usu;

	
	public UsuarioService(UsuarioRepository personaResporitory) {

		this.personaResporitory =  personaResporitory;
	}
	
	@Transactional
	public Usuario create(Usuario persona) {
		return personaResporitory.save(persona);
	}

	@Transactional
	public List<Usuario> getAllPersonas() {
		return personaResporitory.findAll();
	}

	@Transactional
	public void delete(Usuario persona) {
		personaResporitory.delete(persona);
	}

	@Transactional
	public Optional<Usuario> findById(Long id) {
		return personaResporitory.findById(id);
	}

	@Transactional
	public Usuario guardarUsuario(Usuario usuario) {

		return personaResporitory.save(usuario);
	}

	@Transactional
	public Usuario encotrarUsuarioClave(String usuario, String password) {
		Usuario user = usu.findByIdClave(usuario, password);
		return user;
	}

	@Override
	public Usuario findByIdClave(String nombre, String clave) {
		// TODO Auto-generated method stub
		return null;
	}

}
