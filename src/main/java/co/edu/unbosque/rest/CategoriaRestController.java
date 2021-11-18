package co.edu.unbosque.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> main
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.service.api.CategoriaServiceAPI;
import co.edu.unbosque.utils.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/categorias")
public class CategoriaRestController {

	@Autowired
	private CategoriaServiceAPI categoriaServiceAPI;

	@GetMapping(value = "/getAll")
	public List<Categoria> getAll() {
		return categoriaServiceAPI.getAll();
	}

	@GetMapping(value = "/saveCategoria")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria obj = categoriaServiceAPI.save(categoria);
		return new ResponseEntity<Categoria>(obj, HttpStatus.OK);
	}

	@GetMapping(value = "/findRecord/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Categoria categoria = categoriaServiceAPI.get(id);
		if (categoria == null) {
			new ResourceNotFoundException("Record not found for <Categoria>" + id);
		}
		return ResponseEntity.ok().body(categoria);

	}

	@DeleteMapping(value = "/deleteCategoria/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable(value = "id") Long id) {
		Categoria categoria = categoriaServiceAPI.get(id);
		if (categoria != null) {
			categoriaServiceAPI.delete(id);
		} else {
			return new ResponseEntity<Categoria>(categoria, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);

	}
}
