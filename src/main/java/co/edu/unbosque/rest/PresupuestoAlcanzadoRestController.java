package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.PresupuestoAlcanzado;
import co.edu.unbosque.repository.PresupuestoAlcanzadoRepository;
import co.edu.unbosque.service.api.PresupuestoAlcanzadoServiceAPI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/presupuestoAlcanzado")
public class PresupuestoAlcanzadoRestController {

	@Autowired
	private PresupuestoAlcanzadoServiceAPI presupuestoAlcanzadoServiceAPI;

	@Autowired
	private PresupuestoAlcanzadoRepository presupuestoAlcanzadoRepository;

	@GetMapping(value = "/getAll")
	public List<PresupuestoAlcanzado> getAll() {
		return presupuestoAlcanzadoServiceAPI.getAll();
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

}
