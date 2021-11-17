package co.edu.unbosque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.repository.ConceptoRepository;

@Service
public class ConceptoService {

	@Autowired
	private ConceptoRepository conceptoResporitory;

	public Concepto create(Concepto concepto) {
		concepto.setDescrip(concepto.getDescrip());
		return conceptoResporitory.save(concepto);
	}

	public List<Concepto> getAllCategorias() {
		return conceptoResporitory.findAll();
	}

	public void delete(Concepto concepto) {
		conceptoResporitory.delete(concepto);
	}

	public void deleteById(Long id) {
		conceptoResporitory.deleteById(id);
	}

	public Concepto findById(Long id) {
		return conceptoResporitory.findById(id).orElse(null);
	}

	public Concepto getPersonaPorId(Long id) {
		return conceptoResporitory.getById(id);
	}

}
