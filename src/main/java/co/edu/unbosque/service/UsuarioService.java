package co.edu.unbosque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepository;
import co.edu.unbosque.util.Utils;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UsuarioRepository personaResporitory;

	public Usuario create(Usuario persona) {

		persona.setClave(encoder.encode(persona.getClave()));

		persona.setEdad(Utils.obtenerEdad(persona.getFechaNacimiento()));

		return personaResporitory.save(persona);
	}

	public List<Usuario> getAllPersonas() {
		return personaResporitory.findAll();
	}

	public void delete(Usuario persona) {
		personaResporitory.delete(persona);
	}

	public void deleteById(Long id) {
		personaResporitory.deleteById(id);
	}

	public Usuario findById(Long id) {
		return personaResporitory.findById(id).orElse(null);
	}

	public Usuario getPersonaPorId(Long id) {
		return personaResporitory.getById(id);
	}

	public Usuario findByUsuario(String username) {
		return personaResporitory.findByUsuario(username);
	}
}
