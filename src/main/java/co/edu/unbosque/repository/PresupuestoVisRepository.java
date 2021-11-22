package co.edu.unbosque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.unbosque.model.PresupuestoVis;

public interface PresupuestoVisRepository extends JpaRepository<PresupuestoVis, Long> {

	@Query(value = "select p.id, a.categoria, c.concepto,p.anio,p.ppto_asignado,p.porce_ppto_alcanzado, p.ppto_alcanzado, p.ppto_restante from presupuesto p,concepto c, categoria a Where p.id_concepto=c.id and c.id_categoria=a.id order by p.anio, a.categoria, c.concepto", nativeQuery = true)
	public List<PresupuestoVis> obtenerPresupuesto();
	
	@Query(value = "Select p.id, a.categoria, c.concepto,p.anio,p.ppto_asignado,p.porce_ppto_alcanzado, p.ppto_alcanzado, p.ppto_restante from presupuesto p,concepto c, categoria a Where p.id_concepto=c.id and c.id_categoria=a.id AND a.id = ?1", nativeQuery = true)
	public List<PresupuestoVis> obtenerPresupuestoPorCategoria(Long idCategoria);

}
