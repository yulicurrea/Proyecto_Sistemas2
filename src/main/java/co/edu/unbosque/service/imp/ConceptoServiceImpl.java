package co.edu.unbosque.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Concepto;
import co.edu.unbosque.model.ConceptoVis;
import co.edu.unbosque.repository.ConceptoRepository;
import co.edu.unbosque.service.api.ConceptoServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class ConceptoServiceImpl extends GenericServiceImpl<Concepto, Long> implements ConceptoServiceAPI {

	@Autowired
	private ConceptoRepository conceptoDaoAPI;

	@Override
	public CrudRepository<Concepto, Long> getDao() {
		return conceptoDaoAPI;
	}
	public List<ConceptoVis> obtenerTodo() {
		List<ConceptoVis>concepto =conceptoDaoAPI.obtenerConceptos();
		return concepto;
		
	}
}
