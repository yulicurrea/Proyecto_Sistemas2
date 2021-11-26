package co.edu.unbosque.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.PresupuestoAlcanzado;
import co.edu.unbosque.repository.PresupuestoAlcanzadoRepository;
import co.edu.unbosque.service.api.PresupuestoAlcanzadoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class PresupuestoAlcanzadoServiceImpl extends GenericServiceImpl<PresupuestoAlcanzado, Long>
		implements PresupuestoAlcanzadoServiceAPI {

	@Autowired
	private PresupuestoAlcanzadoRepository presupuestoAlcanzadoDaoAPI;

	@Override
	public CrudRepository<PresupuestoAlcanzado, Long> getDao() {
		return presupuestoAlcanzadoDaoAPI;
	}

}
