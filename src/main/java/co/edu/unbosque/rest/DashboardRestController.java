package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.PresupuestoDashboard;
import co.edu.unbosque.repository.DashboardRepository;

@RestController
@RequestMapping("api/dashboard")
public class DashboardRestController {

	@Autowired
	private DashboardRepository dashboardRepository;

	@GetMapping(value = "/datosDashPpto/{id}")
	public List<PresupuestoDashboard> obtenerDatosPresupuestoDash(@PathVariable Long id) {
		return dashboardRepository.obtenerDatosDashPresupuesto(id);
	}
}
