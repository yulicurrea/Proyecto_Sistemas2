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

import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.service.ConceptoService;

@RestController
@RequestMapping("/api/concepto/")
public class ConceptoREST {

	@Autowired
	private ConceptoService conceptoService;

	// POST, PUT> Reciben datos en tipo JSON, entonces debo usar @RequestBody

	// GET, DELETE no reciben datos tipo JSON. Yo debo mandarlos por ruta o URL
	// 1) url/variable/variable2. Las obtengo con @Pathvariable
	// 2) url?var1=nombre&var2=apellido. Las obtengo con @PathParam

	@PostMapping
	private ResponseEntity<Concepto> guardar(@RequestBody Concepto concepto) {
		Concepto concepTemporal = conceptoService.create(concepto);
		try {
			return ResponseEntity.created(new URI("/api/concepto/" + concepTemporal.getId())).body(concepTemporal);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * @GetMapping("/{id}") public Concepto getConcepto(@PathVariable Long id) {
	 * return conceptoService.getPersonaPorId(id); }
	 */

	@DeleteMapping(value = "delete/{id}")
	private ResponseEntity<Boolean> eliminarConcepto(@PathVariable("id") Long id) {
		conceptoService.deleteById(id);
		return ResponseEntity.ok((conceptoService.findById(id) == null));
	}

	@GetMapping(value = "{id}")
	private ResponseEntity<Concepto> listarConceptoPorID(@PathVariable("id") Long id) {
		return ResponseEntity.ok(conceptoService.findById(id));
	}

}
