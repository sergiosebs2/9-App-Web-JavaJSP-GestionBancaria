package Entidades;

public class TipoUsuario {
	private char IdTipoUsuario;
	private String Descripcion;
	
	public TipoUsuario()
	{
		IdTipoUsuario = 'x';
		Descripcion = "Invalido";
	}
	
	public char getIdTipoUsuario() {
		return IdTipoUsuario;
	}
	public void setIdTipoUsuario(char idTipoUsuario) {
		IdTipoUsuario = idTipoUsuario;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}
