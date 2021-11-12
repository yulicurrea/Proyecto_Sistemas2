package co.edu.unbosque.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Login;
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

	public boolean login(Login user) {
		boolean aux = false;
		for (int i = 0; i < personaResporitory.findAll().size(); i++) {
			if (personaResporitory.findAll().get(i).getUsuario().equals(user.getUsuario())) {
				if (user.getClave().equals(personaResporitory.findAll().get(i).getClave())) {
					aux = true;
					break;
				} else {
					aux = false;
				}
			}
		}
		return aux;
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
	public boolean validacionId(Usuario login) {
		boolean aux = false;
		for (int i = 0; i < personaResporitory.findAll().size(); i++) {
			if (personaResporitory.findAll().get(i).getId() == login.getId()) {
					aux = true;
					break;
			}
		}
		return aux;
	}
	public boolean validacionUser(Usuario login) {
		boolean aux = false;
		for (int i = 0; i < personaResporitory.findAll().size(); i++) {
			if (personaResporitory.findAll().get(i).getUsuario().equals(login.getUsuario())) {
					aux = true;
					break;
			}
		}
		return aux;
	}

}
