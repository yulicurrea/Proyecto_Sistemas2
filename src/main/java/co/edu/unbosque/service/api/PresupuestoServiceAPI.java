package co.edu.unbosque.service.api;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.model.dto.PresupuestoDTO;
import co.edu.unbosque.utils.GenericServiceAPI;

public interface PresupuestoServiceAPI extends GenericServiceAPI<Presupuesto, Long> {

	public PresupuestoDTO obtenerPresupuestoPorCategoria(Long idCategoria);

}
