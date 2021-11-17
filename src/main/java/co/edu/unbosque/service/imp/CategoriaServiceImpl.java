package co.edu.unbosque.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Categoria;
import co.edu.unbosque.repository.CategoriaRepository;
import co.edu.unbosque.service.api.CategoriaServiceAPI;
import co.edu.unbosque.utils.GenericServiceImpl;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria, Long> implements CategoriaServiceAPI {

	@Autowired
	private CategoriaRepository categoriaDaoAPI;

	@Override
	public CrudRepository<Categoria, Long> getDao() {
		return categoriaDaoAPI;
	}

}
