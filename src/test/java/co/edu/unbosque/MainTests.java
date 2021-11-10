package co.edu.unbosque;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.repository.UsuarioRepo;

@SpringBootTest
class MainTests {
	
	@Autowired
	private UsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void crearUsuarioTest() {
		
		Usuario usu = new Usuario();
		Date nacimiento = new Date("12/08/1998");
		
		usu.setId((long) 2);
		usu.setNombre("Sandra");
		usu.setApellido("Pacheco");
		usu.setFecha_nacimiento(nacimiento);
		usu.setEdad(25);
		usu.setUsuario("SandraP");
		usu.setClave(bCryptPasswordEncoder.encode("123"));
		usu.setRol("Usuario");
		
		Usuario retorno = repo.save(usu);
		
		assertTrue(retorno.getClave().equalsIgnoreCase(usu.getClave()));
		
	}

}
