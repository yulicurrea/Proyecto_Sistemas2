package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PresupuestoAlcanzadoVis {

	@Id
	private int id;
	private String categoria;
	private String concepto;
	private String anio;
	private String mes;
	private double valor;

	public PresupuestoAlcanzadoVis() {

	}

	public PresupuestoAlcanzadoVis(int id, String categoria, String concepto, String anio, String mes, double valor) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.concepto = concepto;
		this.anio = anio;
		this.mes = mes;
		this.valor = valor;
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

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}