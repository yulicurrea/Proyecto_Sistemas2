package co.edu.unbosque.model;

import javax.persistence.Id;

public class Categoria {

	@Id
	private long id;
	private String titulo;

	public Categoria(long id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
