package co.edu.unbosque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.PresupuestoDashboardGraficoTotales;

public interface DashboardGraficoPptoTotalesRepository
		extends JpaRepository<PresupuestoDashboardGraficoTotales, String> {

	@Query(value = "SELECT a.categoria, SUM(p.ppto_asignado) total_ppto_asignado, SUM(p.ppto_alcanzado) total_ppto_alcanzado,SUM(p.ppto_restante) total_ppto_restante FROM presupuesto p, concepto c, categoria a WHERE p.id_concepto=c.id AND c.id_categoria=a.id AND a.id=:id_cat GROUP BY a.categoria", nativeQuery = true)
	public List<PresupuestoDashboardGraficoTotales> obtenerDatosDashPresupuestoTotales(@Param("id_cat") long id_cat);

}
