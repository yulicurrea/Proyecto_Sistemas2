package co.edu.unbosque.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.model.ConceptoVis;

public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
	
	@Query(value = "Select c.id,a.categoria, c.concepto from concepto c, categoria a Where c.id_categoria=a.id",nativeQuery = true)
	public List<ConceptoVis> obtenerConceptos();
	//public  List<Concepto>findByidAndconceptoAndid_categoria(long id,String concepto,long id_categoria);
	//public List<Categoria>findByid(long id);

	
}
