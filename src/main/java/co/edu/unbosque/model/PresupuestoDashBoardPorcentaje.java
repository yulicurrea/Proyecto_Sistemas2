package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class PresupuestoDashBoardPorcentaje {

	@Id
	private Long id;
	private String concepto;
	private double porce_ppto_alcanzado;

	public PresupuestoDashBoardPorcentaje() {
		super();
	}

	public PresupuestoDashBoardPorcentaje(Long id, String concepto, double porce_ppto_alcanzado) {
		super();
		this.id = id;
		this.concepto = concepto;
		this.porce_ppto_alcanzado = porce_ppto_alcanzado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getPorce_ppto_alcanzado() {
		return porce_ppto_alcanzado;
	}

	public void setPorce_ppto_alcanzado(double porce_ppto_alcanzado) {
		this.porce_ppto_alcanzado = porce_ppto_alcanzado;
	}

	

}
