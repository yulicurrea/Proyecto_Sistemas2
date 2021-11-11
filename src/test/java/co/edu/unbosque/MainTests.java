package co.edu.unbosque;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepo;

@SpringBootTest
class MainTests {
	
	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	

	@Test
	void crearUsuarioTest() {
		
		Usuario usu = new Usuario();
		Date nacimiento = new Date("12/08/1998");
		
		usu.setId((long) 1);
		usu.setNombre("Juan");
		usu.setApellido("Perez");
		usu.setFechaNacimiento(nacimiento);
		usu.setEdad(25);
		usu.setUsuario("JuanP");
		usu.setClave("Juan123");
		usu.setRol("Usuario");
		
		Usuario retorno = repo.save(usu);
		
		assertTrue(retorno.getClave().equalsIgnoreCase(usu.getClave()));
		
	}

}
