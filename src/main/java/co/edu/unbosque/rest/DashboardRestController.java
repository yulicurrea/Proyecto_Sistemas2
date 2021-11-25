package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.PresupuestoDashBoardPorcentaje;
import co.edu.unbosque.model.PresupuestoDashboardGrafico;
import co.edu.unbosque.model.PresupuestoDashboardGraficoTotales;
import co.edu.unbosque.repository.DashboardGraficoPptoRepository;
import co.edu.unbosque.repository.DashboardGraficoPptoTotalesRepository;
import co.edu.unbosque.repository.PresupuestoDashBoardPorcentajeRepository;

@RestController
@RequestMapping("api/dashboard")
public class DashboardRestController {

	@Autowired
	private DashboardGraficoPptoRepository dashboardGraficoPptoRepository;
	
	@Autowired
	private DashboardGraficoPptoTotalesRepository dashboardGraficoPptoTotalesRepository;
	
	@Autowired
	private PresupuestoDashBoardPorcentajeRepository boardPorcentajeRepository;

	@GetMapping(value = "/datosDashPpto/{id}")
	public List<PresupuestoDashboardGrafico> obtenerDatosPresupuestoDash(@PathVariable Long id) {
		return dashboardGraficoPptoRepository.obtenerDatosDashPresupuesto(id);
	}
	
	@GetMapping(value = "/datosDashPptoTotal/{id}")
	public List<PresupuestoDashboardGraficoTotales> obtenerDatosPresupuestoDashTotales(@PathVariable Long id) {
		return dashboardGraficoPptoTotalesRepository.obtenerDatosDashPresupuestoTotales(id);
	}
	
	@GetMapping(value = "/datosDashPorcentaje/{id}")
	public List<PresupuestoDashBoardPorcentaje> obtenerDatosBoardPorcentajes(@PathVariable Long id){
		return boardPorcentajeRepository.obtenerDatosDashPresupuestoPorcentaje(id);
	}
}
