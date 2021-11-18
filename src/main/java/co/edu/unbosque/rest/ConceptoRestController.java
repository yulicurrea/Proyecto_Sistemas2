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

import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.model.ConceptoVis;
import co.edu.unbosque.repository.ConceptoVisRepository;
import co.edu.unbosque.service.api.ConceptoServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;

@RestController
@RequestMapping("/conceptos")
public class ConceptoRestController {

	@Autowired
	private ConceptoServiceAPI conceptoServiceAPI;

	@Autowired
	private ConceptoVisRepository conceptoVisRepository;

	@GetMapping(value = "/getAll")
	public List<Concepto> getAll() {
		return conceptoServiceAPI.getAll();
	}

	@GetMapping(value = "/saveConcepto")
	public ResponseEntity<Concepto> save(@RequestBody Concepto concepto) {
		Concepto obj = conceptoServiceAPI.save(concepto);
		return new ResponseEntity<Concepto>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<Concepto> getConceptoById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Concepto concepto = conceptoServiceAPI.get(id);
		if (concepto == null) {
			new ResourceNotFoundException("Record not found for <Concepto>" + id);
		}
		return ResponseEntity.ok().body(concepto);

	}

	@DeleteMapping(value = "/deleteConcepto/{id}")
	public ResponseEntity<Concepto> delete(@PathVariable(value = "id") Long id) {
		Concepto concepto = conceptoServiceAPI.get(id);
		if (concepto != null) {
			conceptoServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Concepto>(concepto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Concepto>(concepto, HttpStatus.OK);

	}

	@GetMapping(value = "/obtener")
	public List<ConceptoVis> obtener() {
		return conceptoVisRepository.obtenerConceptos();
	}

}
