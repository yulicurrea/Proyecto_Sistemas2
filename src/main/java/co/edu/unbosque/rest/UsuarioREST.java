package co.edu.unbosque.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.service.UsuarioService;

@RestController
@RequestMapping("/api/user/")
public class UsuarioREST {

	@Autowired
	private UsuarioService personaService;

	@PostMapping
	private ResponseEntity<Usuario> guardar(@RequestBody Usuario persona) {
		Usuario temporal = personaService.create(persona);

		try {
			return ResponseEntity.created(new URI("/api/persona" + temporal.getId())).body(temporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping
	private ResponseEntity<List<Usuario>> listarTodasLasPersona() {
		return ResponseEntity.ok(personaService.getAllPersonas());
	}

	@DeleteMapping
	private ResponseEntity<Void> eliminarPersona(@RequestBody Usuario persona) {
		personaService.delete(persona);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "{id}")
	private ResponseEntity<Optional<Usuario>> listarPersonasPorID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(personaService.findById(id));
	}

	@PostMapping("login")
	public Usuario loginUsuario(@RequestBody Usuario usuario) throws Exception {

		String usu = usuario.getNombre();
		String clave = usuario.getClave();
		Usuario usuarioObj = null;
		if (usu != null && clave != null) {

			usuarioObj = personaService.encotrarUsuarioClave(usu, clave);

		}
		if (usuarioObj == null) {

			throw new Exception("Credenciales invalidas");
		}

		return usuarioObj;
	}

}
