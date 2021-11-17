package co.edu.unbosque.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.service.CategoriaService;
import co.edu.unbosque.service.PresupuestoService;

@RestController
@RequestMapping("/api/presupuesto/")
public class PresupuestoREST {

	@Autowired
	private PresupuestoService presupService;

	// POST, PUT> Reciben datos en tipo JSON, entonces debo usar @RequestBody

	// GET, DELETE no reciben datos tipo JSON. Yo debo mandarlos por ruta o URL
	// 1) url/variable/variable2. Las obtengo con @Pathvariable
	// 2) url?var1=nombre&var2=apellido. Las obtengo con @PathParam

	@PostMapping
	private ResponseEntity<Presupuesto> guardar(@RequestBody Presupuesto presup) {
		Presupuesto presuTemporal = presupService.create(presup);
		try {
			return ResponseEntity.created(new URI("/api/presupuesto/" + presuTemporal.getId())).body(presuTemporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * @GetMapping("/{id}") public Presupuesto getPresupuesto(@PathVariable Long id) {
	 * return presupService.getPresupuestoPorId(id); }
	 */

	@DeleteMapping(value = "delete/{id}")
	private ResponseEntity<Boolean> eliminarPresupuesto(@PathVariable("id") Long id) {
		presupService.deleteById(id);
		return ResponseEntity.ok((presupService.findById(id) == null));
	}

	@GetMapping(value = "{id}")
	private ResponseEntity<Presupuesto> listarPresupuestoPorID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(presupService.findById(id));
	}

}
