package co.edu.unbosque.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	@Query(value = "select count(*) from categoria a, concepto b where a.id=b.id_categoria and a.id=:id",nativeQuery = true)
	long validarUsoCategoria(@Param("id") long id);
}
