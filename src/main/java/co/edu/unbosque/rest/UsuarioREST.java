package co.edu.unbosque.rest;

import java.net.URI;
import java.util.List;

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

	// POST, PUT> Reciben datos en tipo JSON, entonces debo usar @RequestBody

	// GET, DELETE no reciben datos tipo JSON. Yo debo mandarlos por ruta o URL
	// 1) url/variable/variable2. Las obtengo con @Pathvariable
	// 2) url?var1=nombre&var2=apellido. Las obtengo con @PathParam

	@PostMapping
	private ResponseEntity<Usuario> guardar(@RequestBody Usuario persona) {
		Usuario temporal = personaService.create(persona);
		try {
			return ResponseEntity.created(new URI("/api/user/" + temporal.getId())).body(temporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	@PostMapping("login/")
	private ResponseEntity<Boolean> login(@RequestBody Login user) {
		try {
			return ResponseEntity.ok(personaService.login(user) == true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	*/

	// @Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping
	private ResponseEntity<List<Usuario>> listarTodasLasPersona() {
		return ResponseEntity.ok(personaService.getAllPersonas());
	}

	@DeleteMapping(value = "delete/{id}")
	private ResponseEntity<Boolean> eliminarPersona(@PathVariable("id") Long id) {
		personaService.deleteById(id);
		return ResponseEntity.ok((personaService.findById(id) == null));

	}

	@GetMapping(value = "{id}")
	private ResponseEntity<Usuario> listarPersonasPorID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(personaService.findById(id));
	}

}
