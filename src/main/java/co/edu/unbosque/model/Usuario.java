package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "usuario")
@Scope("session")
public class Usuario implements UserDetails {

	public static enum Role{ USER }
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "usuario", nullable = false, length = 15)
	private String usuario;

	@JsonProperty(access = Access.WRITE_ONLY)
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

	public Usuario(long id, String usuario,String clave, int edad, Date fechaNacimiento, String nombre, String apellido, String rol) {
		super();
		this.id = id;
		this.usuario = usuario;
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
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(rol));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return clave;
	}

	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String toString() {
		return "User [id=" + id + ", username=" + usuario + ", password=" + clave + ", role=" + rol +
				 ",]";
	}

}
