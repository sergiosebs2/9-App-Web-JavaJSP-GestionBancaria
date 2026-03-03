package Excepciones;

public class noExisteClienteException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public noExisteClienteException() {
		
	}

	@Override
	public String getMessage() {
		return "El cliente no existe";
	}
}
