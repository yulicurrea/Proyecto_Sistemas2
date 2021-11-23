package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PresupuestoDashboardGraficoTotales {

	@Id
	private String categoria;
	private double total_ppto_asignado;
	private double total_ppto_alcanzado;
	private double total_ppto_restante;

	public PresupuestoDashboardGraficoTotales() {

	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getTotal_ppto_asignado() {
		return total_ppto_asignado;
	}

	public void setTotal_ppto_asignado(double total_ppto_asignado) {
		this.total_ppto_asignado = total_ppto_asignado;
	}

	public double getTotal_ppto_alcanzado() {
		return total_ppto_alcanzado;
	}

	public void setTotal_ppto_alcanzado(double total_ppto_alcanzado) {
		this.total_ppto_alcanzado = total_ppto_alcanzado;
	}

	public double getTotal_ppto_restante() {
		return total_ppto_restante;
	}

	public void setTotal_ppto_restante(double total_ppto_restante) {
		this.total_ppto_restante = total_ppto_restante;
	}


}