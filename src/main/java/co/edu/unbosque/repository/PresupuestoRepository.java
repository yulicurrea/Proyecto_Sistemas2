package co.edu.unbosque.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.model.Presupuesto;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long>{
	
	@Query(value = "select count(*) from presupuesto where id_concepto=:id and anio=:anio_pre",nativeQuery = true)
	long validarAsignacionPresupuesto(@Param("id") long id,@Param("anio_pre") int anio_pre);
	
	

}
