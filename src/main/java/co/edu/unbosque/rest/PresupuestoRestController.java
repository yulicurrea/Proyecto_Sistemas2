package co.edu.unbosque.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.model.PresupuestoVis;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.dto.PresupuestoDTO;
import co.edu.unbosque.repository.PresupuestoRepository;
import co.edu.unbosque.repository.PresupuestoVisRepository;
import co.edu.unbosque.service.SessionService;
import co.edu.unbosque.service.UsuarioService;
import co.edu.unbosque.service.api.PresupuestoServiceAPI;
import co.edu.unbosque.util.PDFGenerator;
import co.edu.unbosque.utils.ResourceNotFoundException;


@RestController
@RequestMapping("api/presupuestos")
public class PresupuestoRestController {

	@Autowired
	private PresupuestoServiceAPI presupuestoServiceAPI;

	@Autowired
	private PresupuestoVisRepository presupuestoVisRepository;

	@Autowired
	private PresupuestoRepository presupuestoRepository;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping(value = "/getAll")
	public List<Presupuesto> getAll() {
		return presupuestoServiceAPI.getAll();
	}

	@PostMapping(value = "/savePresupuesto")
	public ResponseEntity<Presupuesto> save(@RequestBody Presupuesto presupuesto) {
		Presupuesto obj = new Presupuesto(-1,-1, 0, 0, 0, 0, 0);
		if(validarPresupuesto(presupuesto.getId_concepto(),presupuesto.getAnio())==0) {
			obj= presupuestoServiceAPI.save(presupuesto);
		}
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
	public long validarPresupuesto(long l,int anio) {
		return presupuestoRepository.validarAsignacionPresupuesto(l,anio);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(value = "/obtenerPre/{idCategoria}")
	public PresupuestoDTO obtenerPre(@PathVariable Long idCategoria) {
		return presupuestoServiceAPI.obtenerPresupuestoPorCategoria(idCategoria);
	}
	
	
	@GetMapping("/PDF")
    public ResponseEntity<InputStreamResource> customerReport() throws IOException {
        String username =  sessionService.getUserName();
        
        Usuario usuario = usuarioService.findByUsuario(username);
        
        PresupuestoDTO ingresos = presupuestoServiceAPI.obtenerPresupuestoPorCategoria((long) 1);
        PresupuestoDTO egresos = presupuestoServiceAPI.obtenerPresupuestoPorCategoria((long) 2);
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(ingresos, egresos, usuario);
        
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Presupuesto.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
}
