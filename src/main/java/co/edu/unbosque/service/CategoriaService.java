package co.edu.unbosque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaResporitory;

	public Categoria create(Categoria cat) {
		cat.setTitulo(cat.getTitulo());
		return categoriaResporitory.save(cat);
	}

	public List<Categoria> getAllCategorias() {
		return categoriaResporitory.findAll();
	}

	public void delete(Categoria categoria) {
		categoriaResporitory.delete(categoria);
	}

	public void deleteById(Long id) {
		categoriaResporitory.deleteById(id);
	}

	public Categoria findById(Long id) {
		return categoriaResporitory.findById(id).orElse(null);
	}

	public Categoria getPersonaPorId(Long id) {
		return categoriaResporitory.getById(id);
	}

}
