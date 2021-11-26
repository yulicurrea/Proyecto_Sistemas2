package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.PresupuestoAlcanzado;
import co.edu.unbosque.model.PresupuestoAlcanzadoVis;
import co.edu.unbosque.repository.PresupuestoAlcanzadoRepository;
import co.edu.unbosque.repository.PresupuestoAlcanzadoVisRepository;
import co.edu.unbosque.service.api.PresupuestoAlcanzadoServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/presupuestoAlcanzado")
public class PresupuestoAlcanzadoRestController {

	@Autowired
	private PresupuestoAlcanzadoServiceAPI presupuestoAlcanzadoServiceAPI;

	@Autowired
	private PresupuestoAlcanzadoRepository presupuestoAlcanzadoRepository;

	@Autowired
	private PresupuestoAlcanzadoVisRepository presupuestoAlcanzadoVisRepository;

	@GetMapping(value = "/getAll")
	public List<PresupuestoAlcanzado> getAll() {
		return presupuestoAlcanzadoServiceAPI.getAll();
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<PresupuestoAlcanzado> getPresupuestoById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		PresupuestoAlcanzado presupuesto = presupuestoAlcanzadoServiceAPI.get(id);
		if (presupuesto == null) {
			new ResourceNotFoundException("Record not found for <Presupuesto>" + id);
		}
		return ResponseEntity.ok().body(presupuesto);

	}

	@DeleteMapping(value = "/deletePresupuesto/{id}")
	public ResponseEntity<PresupuestoAlcanzado> delete(@PathVariable(value = "id") Long id) {
		PresupuestoAlcanzado presupuesto = presupuestoAlcanzadoServiceAPI.get(id);
		if (presupuesto != null) {
			presupuestoAlcanzadoServiceAPI.delete(id);
		} else {
			return new ResponseEntity<PresupuestoAlcanzado>(presupuesto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<PresupuestoAlcanzado>(presupuesto, HttpStatus.OK);

	}

	@PostMapping(value = "/savePresupuestoAlcanzado")
	public ResponseEntity<PresupuestoAlcanzado> save(@RequestBody PresupuestoAlcanzado presupuestoAlcanzado) {
		PresupuestoAlcanzado obj = new PresupuestoAlcanzado(-1, -1, "", 0);
		if (validarPresupuestoAlcanzado(presupuestoAlcanzado.getId_presupuesto(), presupuestoAlcanzado.getMes()) == 0) {
			obj = presupuestoAlcanzadoServiceAPI.save(presupuestoAlcanzado);
		}
		return new ResponseEntity<PresupuestoAlcanzado>(obj, HttpStatus.OK);
	}

	public long validarPresupuestoAlcanzado(long id, String mes) {
		return presupuestoAlcanzadoRepository.idPresupuesto(id, mes);
	}

	@GetMapping(value = "/obtenerLista/{id}")
	public List<PresupuestoAlcanzadoVis> getListaPresupuesto(@PathVariable(value = "id") Long id) {
		return presupuestoAlcanzadoVisRepository.listaPresupuestoAlcanzados(id);
	}

}
