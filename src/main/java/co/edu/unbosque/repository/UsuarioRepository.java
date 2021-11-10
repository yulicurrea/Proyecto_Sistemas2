package co.edu.unbosque.repository;
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unbosque.model.Usuario;

@Repository("UsuarioRepository")
@EnableJpaRepositories
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario,Serializable>{

	public Usuario findOneByusuario(String usuario);
}
