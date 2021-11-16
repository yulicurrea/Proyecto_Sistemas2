package co.edu.unbosque.model;

import java.util.Date;

import javax.persistence.Id;

public class Presupuesto {

	@Id
	private long id;
	private String categoria;
	private String concepto;
	private Date anio;
	private double ppto_asignado;
	private double porce_ppto_alcanzado;
	private double ppto_alcanzado;
	private double ppto_restante;

	public Presupuesto(long id, String categoria, String concepto, Date anio, double ppto_asignado,
			double porce_ppto_alcanzado, double ppto_alcanzado, double ppto_restante) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.concepto = concepto;
		this.anio = anio;
		this.ppto_asignado = ppto_asignado;
		this.porce_ppto_alcanzado = porce_ppto_alcanzado;
		this.ppto_alcanzado = ppto_alcanzado;
		this.ppto_restante = ppto_restante;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getAnio() {
		return anio;
	}

	public void setAnio(Date anio) {
		this.anio = anio;
	}

	public double getPpto_asignado() {
		return ppto_asignado;
	}

	public void setPpto_asignado(double ppto_asignado) {
		this.ppto_asignado = ppto_asignado;
	}

	public double getPorce_ppto_alcanzado() {
		return porce_ppto_alcanzado;
	}

	public void setPorce_ppto_alcanzado(double porce_ppto_alcanzado) {
		this.porce_ppto_alcanzado = porce_ppto_alcanzado;
	}

	public double getPpto_alcanzado() {
		return ppto_alcanzado;
	}

	public void setPpto_alcanzado(double ppto_alcanzado) {
		this.ppto_alcanzado = ppto_alcanzado;
	}

	public double getPpto_restante() {
		return ppto_restante;
	}

	public void setPpto_restante(double ppto_restante) {
		this.ppto_restante = ppto_restante;
	}

}
