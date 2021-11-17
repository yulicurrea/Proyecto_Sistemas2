package co.edu.unbosque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Presupuesto;
import co.edu.unbosque.repository.PresupuestoRepository;

@Service
public class PresupuestoService {

	@Autowired
	private PresupuestoRepository presupuestoResporitory;

	public Presupuesto create(Presupuesto presup) {
		presup.setAnio(presup.getAnio());
		return  presupuestoResporitory.save(presup);
	}

	public List<Presupuesto> getAllPresupuesto() {
		return presupuestoResporitory.findAll();
	}

	public void delete(Presupuesto presupuesto) {
		presupuestoResporitory.delete(presupuesto);
	}

	public void deleteById(Long id) {
		presupuestoResporitory.deleteById(id);
	}

	public Presupuesto findById(Long id) {
		return presupuestoResporitory.findById(id).orElse(null);
	}

	public Presupuesto getPreseupuestoPorId(Long id) {
		return presupuestoResporitory.getById(id);
	}

}
