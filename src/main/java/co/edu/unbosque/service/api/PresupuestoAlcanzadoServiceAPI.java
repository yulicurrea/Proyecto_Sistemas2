package co.edu.unbosque.service.api;

import co.edu.unbosque.model.PresupuestoAlcanzado;
import co.edu.unbosque.model.dto.PresupuestoDTO;
import co.edu.unbosque.utils.GenericServiceAPI;

public interface PresupuestoAlcanzadoServiceAPI extends GenericServiceAPI<PresupuestoAlcanzado, Long> {

	public PresupuestoDTO obtenerIdPresupuesto(Long idPresupuesto);

}
