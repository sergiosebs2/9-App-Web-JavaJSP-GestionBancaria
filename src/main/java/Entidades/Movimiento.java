package Entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Movimiento {
	private int codMovimiento;
	private TipoMovimiento tipoMovimiento;
	private CuentaBancaria cuentaBancariaAsociada;
	private Date fecha;
	private String detalle;
	private BigDecimal importe;
	
	public Movimiento()
	{
		codMovimiento = 0;
		tipoMovimiento = new TipoMovimiento();
		cuentaBancariaAsociada = new CuentaBancaria();
		fecha = null;
		detalle = "";
		importe = new BigDecimal("0");
	}

	public int getCodMovimiento() {
		return codMovimiento;
	}

	public void setCodMovimiento(int codMovimiento) {
		this.codMovimiento = codMovimiento;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public CuentaBancaria getCuentaBancariaAsociada() {
		return cuentaBancariaAsociada;
	}

	public void setCuentaBancariaAsociada(CuentaBancaria cuentaBancariaAsociada) {
		this.cuentaBancariaAsociada = cuentaBancariaAsociada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
}
