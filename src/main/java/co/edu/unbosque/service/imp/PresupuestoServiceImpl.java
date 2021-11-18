package co.edu.unbosque.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.repository.PresupuestoRepository;
import co.edu.unbosque.service.api.PresupuestoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class PresupuestoServiceImpl extends GenericServiceImpl<Presupuesto, Long> implements PresupuestoServiceAPI {

	@Autowired
	private PresupuestoRepository presupuestoDaoAPI;

	@Override
	public CrudRepository<Presupuesto, Long> getDao() {
		return presupuestoDaoAPI;
	}

}
