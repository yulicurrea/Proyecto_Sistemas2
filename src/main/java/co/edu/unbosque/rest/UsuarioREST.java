package co.edu.unbosque.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import co.edu.unbosque.model.Login;
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
			return ResponseEntity.created(new URI("/api/user/" + temporal.getId())).body(temporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@PostMapping("login/")
	private ResponseEntity<Boolean> login(@RequestBody Login user) {
		try {
			return ResponseEntity.ok(personaService.login(user) == true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("validarId/")
	private ResponseEntity<Boolean> validarId(@RequestBody Usuario persona) {
		return ResponseEntity.ok(personaService.validacionId(persona));
	}

	@PostMapping("validarUsuario/")
	private ResponseEntity<Boolean> validarUsuario(@RequestBody Usuario persona) {
		return ResponseEntity.ok(personaService.validacionUser(persona));
	}

	@GetMapping
	private ResponseEntity<List<Usuario>> listarTodasLasPersona() {
		return ResponseEntity.ok(personaService.getAllPersonas());
	}

	@DeleteMapping(value = "delete/{id}")
	private ResponseEntity<Boolean> eliminarPersona(@PathVariable("id") Long id) {
		personaService.deleteById(id);
		return ResponseEntity.ok(!(personaService.findById(id) != null));

	}

	@PostMapping("editar/{id}")
	private ResponseEntity<Usuario> editarPersona(@RequestBody Usuario user, @PathVariable("id") Long id) {
		Usuario editar = new Usuario();
		if (personaService.findById(id) != null) {
			editar.setId(user.getId());
			editar.setNombre(user.getNombre());
			editar.setApellido(user.getApellido());
			editar.setUsuario(user.getUsuario());	
			editar.setRol(user.getRol());
			editar.setClave(user.getClave());
			editar.setFechaNacimiento(user.getFechaNacimiento());
			personaService.deleteById(id);
			personaService.create(editar);
			
		}
		try {
			return ResponseEntity.created(new URI("/api/user/editar/" + editar.getId())).body(editar);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	
	}

	@GetMapping(value = "{id}")
	private ResponseEntity<Optional<Usuario>> listarPersonasPorID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(personaService.findById(id));
	}

}
