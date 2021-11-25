package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PresupuestoDashboardGrafico {

	@Id
	private long id;
	private String concepto;
	private double ppto_asignado;
	private double ppto_alcanzado;

	public PresupuestoDashboardGrafico() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getPpto_asignado() {
		return ppto_asignado;
	}

	public void setPpto_asignado(double ppto_asignado) {
		this.ppto_asignado = ppto_asignado;
	}

	public double getPpto_alcanzado() {
		return ppto_alcanzado;
	}

	public void setPpto_alcanzado(double ppto_alcanzado) {
		this.ppto_alcanzado = ppto_alcanzado;
	}

}