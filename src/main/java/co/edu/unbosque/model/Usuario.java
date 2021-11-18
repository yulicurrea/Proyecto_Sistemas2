package co.edu.unbosque.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String clave;
	private Integer edad;

	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	private String nombre;
	private String apellido;
	private String rol;
	private String usuario;

	public Usuario() {

	}

	public Usuario(long id, String clave, int edad, Date fechaNacimiento, String nombre, String apellido, String rol,
			String usuario) {
		super();
		this.id = id;
		this.clave = clave;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
		this.usuario = usuario;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@JsonIgnore
	public String getClave() {
		return clave;
	}

	@JsonProperty
	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}