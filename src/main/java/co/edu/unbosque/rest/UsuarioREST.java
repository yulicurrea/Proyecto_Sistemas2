package co.edu.unbosque.rest;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.service.UsuarioService;

@RestController
@RequestMapping("/api/user/")
public class UsuarioREST {

	@Autowired
	private UsuarioService personaService = new UsuarioService();
	
	public static final Logger logger = LoggerFactory.getLogger(UsuarioREST.class);
	
	UsuarioREST(){
		
	}
	
	public UsuarioREST(UsuarioService personaService){
		
		this.personaService = personaService;
		
	}
	
	@PostMapping("guardar")
	private ResponseEntity<Usuario> guardar(@RequestBody Usuario persona) {
		Usuario temporal = personaService.create(persona);

		try {
			return ResponseEntity.created(new URI("/api/persona" + temporal.getId())).body(temporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("listar")
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

	
		@CrossOrigin
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public ResponseEntity<?> createUser(@RequestBody Usuario usu) {
			if (personaService.findUser(usu.getNombre()) != null) {
				logger.error("el nombre de usuario ya existe" + usu.getNombre());
				return new ResponseEntity<Object>(
						new Error("El usuario con el nombre de usuario " + usu.getNombre() + "ya existe "),
						HttpStatus.CONFLICT);
			}
			usu.setRol("USER");
			
			return new ResponseEntity<Usuario>(personaService.guardarUsuario(usu), HttpStatus.CREATED);
		}
	
	@CrossOrigin
	@RequestMapping("login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}
	
	
}
