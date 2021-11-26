package co.edu.unbosque.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "presupuesto")
public class Presupuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "id_concepto", nullable = false)
	private long id_concepto;
	@Column(name = "anio", nullable = false)
	private int anio;
	@Column(name = "ppto_asignado", nullable = false)
	private double ppto_asignado;
	@Column(name = "porce_ppto_alcanzado", nullable = false)
	private double porce_ppto_alcanzado;
	@Column(name = "ppto_alcanzado")
	private double ppto_alcanzado;
	@Column(name = "ppto_restante", nullable = false)
	private double ppto_restante;
	
	
	
	public Presupuesto(long id, long id_concepto, int anio, double ppto_asignado, double porce_ppto_alcanzado,
			double ppto_alcanzado, double ppto_restante) {
		super();
		this.id = id;
		this.id_concepto = id_concepto;
		this.anio = anio;
		this.ppto_asignado = ppto_asignado;
		this.porce_ppto_alcanzado = porce_ppto_alcanzado;
		this.ppto_alcanzado = ppto_alcanzado;
		this.ppto_restante = ppto_restante;
		
	}
	public Presupuesto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_concepto() {
		return id_concepto;
	}
	public void setId_concepto(long id_concepto) {
		this.id_concepto = id_concepto;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
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