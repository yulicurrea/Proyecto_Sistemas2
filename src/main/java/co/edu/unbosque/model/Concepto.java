package co.edu.unbosque.model;

import javax.persistence.Id;

public class Concepto {

	@Id
	private long id;
	private String descrip;

	public Concepto(long id, String descrip) {
		super();
		this.id = id;
		this.descrip = descrip;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

}
