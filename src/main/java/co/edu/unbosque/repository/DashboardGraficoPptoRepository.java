package co.edu.unbosque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.PresupuestoDashboardGrafico;

public interface DashboardGraficoPptoRepository extends JpaRepository<PresupuestoDashboardGrafico, Long> {

	@Query(value = "SELECT p.id, c.concepto, p.ppto_asignado, p.ppto_alcanzado  FROM presupuesto p, concepto c, categoria a WHERE p.id_concepto=c.id AND c.id_categoria=a.id AND a.id=:id_cat", nativeQuery = true)
	public List<PresupuestoDashboardGrafico> obtenerDatosDashPresupuesto(@Param("id_cat") long id_cat);

}
