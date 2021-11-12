package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long>{

}
