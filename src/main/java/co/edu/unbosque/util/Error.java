package co.edu.unbosque.util;

public class Error {

	private String mesajeError;

    public Error(String mesajeError){
        this.mesajeError = mesajeError;
    }

    public String getErrorMessage() {
        return mesajeError;
    }
}
