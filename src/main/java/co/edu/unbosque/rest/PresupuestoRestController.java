package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.model.PresupuestoVis;
import co.edu.unbosque.repository.PresupuestoVisRepository;
import co.edu.unbosque.service.api.PresupuestoServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

@RestController
@RequestMapping("/presupuestos")
public class PresupuestoRestController {

	@Autowired
	private PresupuestoVisRepository presupuestoVisRepository;
	
	@Autowired
	private PresupuestoServiceAPI presupuestoServiceAPI;

	@GetMapping(value = "/getAll")
	public List<Presupuesto> getAll() {
		return presupuestoServiceAPI.getAll();
	}

	@GetMapping(value = "/savePresupuesto")
	public ResponseEntity<Presupuesto> save(@RequestBody Presupuesto presupuesto) {
		Presupuesto obj = presupuestoServiceAPI.save(presupuesto);
		return new ResponseEntity<Presupuesto>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<Presupuesto> getPresupuestoById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Presupuesto presupuesto = presupuestoServiceAPI.get(id);
		if (presupuesto == null) {
			new ResourceNotFoundException("Record not found for <Presupuesto>" + id);
		}
		return ResponseEntity.ok().body(presupuesto);

	}

	@DeleteMapping(value = "/deletePresupuesto/{id}")
	public ResponseEntity<Presupuesto> delete(@PathVariable(value = "id") Long id) {
		Presupuesto presupuesto = presupuestoServiceAPI.get(id);
		if (presupuesto != null) {
			presupuestoServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Presupuesto>(presupuesto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Presupuesto>(presupuesto, HttpStatus.OK);

	}
	
	@GetMapping(value = "/obtenerPre")
	public List<PresupuestoVis> obtenerPre() {
		return presupuestoVisRepository.obtenerPresupuesto();
	}
}
