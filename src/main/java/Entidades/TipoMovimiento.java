package Entidades;

public class TipoMovimiento {
	private String codTipoMovimiento;
	private String descripcion;
	
	public TipoMovimiento()
	{
		codTipoMovimiento = "";
		descripcion = "";
	}

	public String getCodTipoMovimiento() {
		return codTipoMovimiento;
	}

	public void setCodTipoMovimiento(String codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
