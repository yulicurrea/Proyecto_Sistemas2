package co.edu.unbosque.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PresupuestoAlcanzadoVis {

	@Id
	private int id;
	private int id_presupuesto;
	private String mes;
	private double valor;

	public PresupuestoAlcanzadoVis() {

	}

	public PresupuestoAlcanzadoVis(int id, int id_presupuesto, String mes, double valor) {
		super();
		this.id = id;
		this.id_presupuesto = id_presupuesto;
		this.mes = mes;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_presupuesto() {
		return id_presupuesto;
	}

	public void setId_presupuesto(int id_presupuesto) {
		this.id_presupuesto = id_presupuesto;
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