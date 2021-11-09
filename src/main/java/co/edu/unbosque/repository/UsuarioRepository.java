package co.edu.unbosque.repository;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unbosque.model.Usuario;

@Repository("UsuarioRepository")
@EnableJpaRepositories
public interface UsuarioRepository extends JpaRepository<Usuario,Serializable>{

	//@Query("SELECT u FROM usuario u WHERE u.usuario = ?1")
	public Usuario findOneByusuario(String usuario);
}
