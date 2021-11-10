package co.edu.unbosque.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table (name = "usuario")
public class Usuario {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id ;
		
		@Column(name = "clave", nullable = false, length = 15)
		private String clave;
		
		@Column(name = "usuario", nullable = false, length = 15)
		private String usuario;
		
		@Column(name = "rol", nullable = false, length = 15)
		private String rol;
		
		@Column(name = "nombre", nullable = false, length = 60)
		private String nombre;
		
		@Column(name = "apellido", nullable = false, length = 60)
		private String apellido;
		
		@Column(name = "edad", nullable = false, length = 3)
		private Integer edad;
		
		@Column(name = "fecha_nacimiento", nullable = false, length = 11)
		private Date fecha_nacimiento;
	
	
	
	public Usuario () {
		
	}
	
	

	public Usuario(long id, String usuario,String clave, int edad, Date fecha_nacimiento, String nombre, String apellido, String rol) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.edad = edad;
		this.fecha_nacimiento = fecha_nacimiento;
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
	
	
	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public void setId(long id) {
		this.id = id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
