package co.edu.unbosque.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.unbosque.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	
}
