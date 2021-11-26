package co.edu.unbosque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.PresupuestoAlcanzadoVis;

public interface PresupuestoAlcanzadoVisRepository extends JpaRepository<PresupuestoAlcanzadoVis, Long> {

	@Query(value = "SELECT a.id, t.categoria, c.concepto, p.anio, a.mes, a.valor FROM presupuesto p, presupuesto_alcanzado a, concepto c, categoria t Where a.id_presupuesto = p.id and p.id_concepto = c.id and c.id_categoria = t.id and a.id_presupuesto =:id", nativeQuery = true)
	List<PresupuestoAlcanzadoVis> listaPresupuestoAlcanzados(@Param("id") long id);

}
