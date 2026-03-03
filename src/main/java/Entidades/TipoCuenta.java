package Entidades;


public class TipoCuenta {
	
	private char CodTipoCuenta;
	private String Descripcion;
	
	public TipoCuenta()
	{
		CodTipoCuenta = 'x';
		Descripcion = "";
	}
	
	public TipoCuenta(char codTipoCuenta, String descripcion) {
		CodTipoCuenta = codTipoCuenta;
		Descripcion = descripcion;
	}
	
	public char getCodTipoCuenta() {
		return CodTipoCuenta;
	}

	public void setCodTipoCuenta(char codTipoCuenta) {
		CodTipoCuenta = codTipoCuenta;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
