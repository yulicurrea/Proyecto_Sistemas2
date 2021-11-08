package co.edu.unbosque.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import co.edu.unbosque.model.Usuario;



@Service
public interface IUsuarioService<U> {
	
	public U create(U persona);
	public List<U> getAllPersonas();
	public void delete(U persona);
	public Optional<U> findById(Long id);
	public U guardarUsuario(U usuario);
	public U encotrarUsuarioClave(String usuario,String password);
	public U findByIdClave(String nombre,String clave);

}
