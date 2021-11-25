package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.PresupuestoAlcanzado;

public interface PresupuestoAlcanzadoRepository extends JpaRepository<PresupuestoAlcanzado, Long> {

	@Query(value = "SELECT count(*) FROM public.presupuesto_alcanzado Where id_presupuesto=:id and mes=:mes_preAlcan", nativeQuery = true)
	long idPresupuesto(@Param("id") long id, @Param("mes_preAlcan") String mes_preAlcan);

}
