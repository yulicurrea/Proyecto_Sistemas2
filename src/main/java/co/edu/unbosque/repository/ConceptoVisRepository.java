package co.edu.unbosque.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.model.ConceptoVis;

public interface ConceptoVisRepository extends JpaRepository<ConceptoVis, Long> {
	
	@Query(value = "Select c.id,a.categoria, c.concepto from concepto c, categoria a Where c.id_categoria=a.id",nativeQuery = true)
	public List<ConceptoVis> obtenerConceptos();

	
}
