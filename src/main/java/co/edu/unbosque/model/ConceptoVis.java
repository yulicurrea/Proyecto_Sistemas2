package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConceptoVis {
	@Id
	private int id;
	private String categoria;

	private String concepto;

	public ConceptoVis(int id, String categoria, String concepto) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.concepto = concepto;
	}
	public ConceptoVis() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	

}