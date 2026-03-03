package Excepciones;

public class clienteBajaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public clienteBajaException() {
		
	}

	@Override
	public String getMessage() {
		return "El cliente está dado de baja";
	}
}
