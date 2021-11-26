package co.edu.unbosque.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.model.PresupuestoVis;
import co.edu.unbosque.model.dto.PresupuestoDTO;
import co.edu.unbosque.repository.PresupuestoRepository;
import co.edu.unbosque.repository.PresupuestoVisRepository;
import co.edu.unbosque.service.api.PresupuestoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class PresupuestoServiceImpl extends GenericServiceImpl<Presupuesto, Long> implements PresupuestoServiceAPI {

	@Autowired
	private PresupuestoRepository presupuestoDaoAPI;
	
	@Autowired
	private PresupuestoVisRepository presupuestoVisDAO;

	@Override
	public CrudRepository<Presupuesto, Long> getDao() {
		return presupuestoDaoAPI;
	}

	@Override
	public PresupuestoDTO obtenerPresupuestoPorCategoria(Long idCategoria) {
		PresupuestoDTO modelo = new PresupuestoDTO();		
		modelo.setDatos(presupuestoVisDAO.obtenerPresupuestoPorCategoria(idCategoria));
		
		double totalPtoAsignado = 0;
		double totalEjecutado = 0;
		long totalFaltante = 0;
		double totalPorcenteEjecucion = 0;
		
		for (PresupuestoVis registro : modelo.getDatos()) {
			totalPtoAsignado += registro.getPpto_asignado();
			totalEjecutado += registro.getPpto_alcanzado();
			totalFaltante += registro.getPpto_restante();
		}
		totalPorcenteEjecucion = (totalEjecutado / totalPtoAsignado) * 100;
		
		modelo.setTotalPresupuesto(totalPtoAsignado);
		modelo.setTotalEjecutado(totalEjecutado);
		modelo.setTotalFaltante(totalFaltante);
		modelo.setTotalPorcentajeEjecucion(totalPorcenteEjecucion);
		
		return modelo;
	}

}
