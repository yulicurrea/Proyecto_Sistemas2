package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.Concepto;

public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
	
	@Query(value = "select count(*) from presupuesto a, concepto b where b.id=a.id_concepto and b.id=:id",nativeQuery = true)
	long validarUsoConcepto(@Param("id") long id);
	
}
