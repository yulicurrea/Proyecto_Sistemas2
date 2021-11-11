package co.edu.unbosque.model;

public class Login {

	private String usuario;
	private String clave;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
}
