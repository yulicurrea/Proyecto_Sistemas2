package co.edu.unbosque.model.dto;

import java.util.List;

import co.edu.unbosque.model.PresupuestoVis;

public class PresupuestoDTO {

	private List<PresupuestoVis> datos;
	private double totalPresupuesto;
	private double totalPorcentajeEjecucion;
	private double totalEjecutado;
	private double totalFaltante;
	
	

	public PresupuestoDTO() {		
		this.totalPresupuesto = 0;
		this.totalPorcentajeEjecucion = 0;
		this.totalEjecutado = 0;
		this.totalFaltante = 0;
	}

	public List<PresupuestoVis> getDatos() {
		return datos;
	}

	public void setDatos(List<PresupuestoVis> datos) {
		this.datos = datos;
	}

	public double getTotalPresupuesto() {
		return totalPresupuesto;
	}

	public void setTotalPresupuesto(double totalPresupuesto) {
		this.totalPresupuesto = totalPresupuesto;
	}

	public double getTotalPorcentajeEjecucion() {
		return totalPorcentajeEjecucion;
	}

	public void setTotalPorcentajeEjecucion(double totalPorcentajeEjecucion) {
		this.totalPorcentajeEjecucion = totalPorcentajeEjecucion;
	}

	public double getTotalEjecutado() {
		return totalEjecutado;
	}

	public void setTotalEjecutado(double totalEjecutado) {
		this.totalEjecutado = totalEjecutado;
	}

	public double getTotalFaltante() {
		return totalFaltante;
	}

	public void setTotalFaltante(double totalFaltante) {
		this.totalFaltante = totalFaltante;
	}

}
