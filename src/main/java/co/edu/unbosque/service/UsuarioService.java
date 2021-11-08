package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository personaResporitory;

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

	public Usuario guardarUsuario(Usuario usuario) {

		return personaResporitory.save(usuario);
	}
	
	public Usuario encotrarUsuarioClave(String usuario,String password) {
		//Usuario user = personaResporitory.findByIdClave(usuario, password);
		//return user
		return null;
	}

}
