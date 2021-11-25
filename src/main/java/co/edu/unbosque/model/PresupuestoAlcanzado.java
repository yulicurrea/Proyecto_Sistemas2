package co.edu.unbosque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "presupuesto_alcanzado")
public class PresupuestoAlcanzado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "id_presupuesto", nullable = false)
	private long id_presupuesto;
	@Column(name = "mes", nullable = false)
	private String mes;
	@Column(name = "valor", nullable = false)
	private double valor;

	public PresupuestoAlcanzado(long id, long id_presupuesto, String mes, double valor) {
		super();
		this.id = id;
		this.id_presupuesto = id_presupuesto;
		this.mes = mes;
		this.valor = valor;
	}

	public PresupuestoAlcanzado() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_presupuesto() {
		return id_presupuesto;
	}

	public void setId_presupuesto(long id_presupuesto) {
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