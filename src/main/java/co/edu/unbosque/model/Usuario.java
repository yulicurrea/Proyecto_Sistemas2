package co.edu.unbosque.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "clave", nullable = false, length = 15)
	private String clave;

	@Column(name = "edad", nullable = false, length = 3)
	private int edad;

	@Column(name = "fecha_nacimiento", nullable = false, length = 11)
	private Date fechaNacimiento;

	@Column(name = "nombre", nullable = false, length = 60)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 60)
	private String apellido;

	@Column(name = "rol", nullable = false, length = 5)
	private String rol;

	public Usuario() {

	}

	public Usuario(long id, String clave, int edad, Date fechaNacimiento, String nombre, String apellido, String rol) {
		super();
		this.id = id;
		this.clave = clave;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
