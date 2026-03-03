package Entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Prestamo {
	private int codPrestamo;
	private Cliente clienteAsociado;
	private CuentaBancaria cuentaAsociada;
	private Date fechaSolicitado;
	private BigDecimal importePagar;
	private BigDecimal importeSolicitado;
	private int plazoMeses;
	private BigDecimal pagoMensual;
	private int cuotasTotales;
	// 0 = Saldado | 1 = Vigente
	private boolean deuda;
	// 0 = Prestamo rechazado/pendiente | 1 = Prestamo Aceptado
	private boolean estado;
	
	public Prestamo()
	{
		codPrestamo = 0;
		clienteAsociado = new Cliente();
		cuentaAsociada = new CuentaBancaria();
		fechaSolicitado = null;
		importePagar = new BigDecimal(0);
		importeSolicitado = new BigDecimal(0);
		plazoMeses = 0;
		pagoMensual = new BigDecimal(0);
		cuotasTotales = 0;
		deuda = false;
		estado = false;
	}

	public int getCodPrestamo() {
		return codPrestamo;
	}

	public void setCodPrestamo(int codPrestamo) {
		this.codPrestamo = codPrestamo;
	}

	public Cliente getClienteAsociado() {
		return clienteAsociado;
	}

	public void setClienteAsociado(Cliente clienteAsociado) {
		this.clienteAsociado = clienteAsociado;
	}

	public CuentaBancaria getCuentaAsociada() {
		return cuentaAsociada;
	}

	public void setCuentaAsociada(CuentaBancaria cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}

	public Date getFechaSolicitado() {
		return fechaSolicitado;
	}

	public void setFechaSolicitado(Date fechaSolicitado) {
		this.fechaSolicitado = fechaSolicitado;
	}

	public BigDecimal getImportePagar() {
		return importePagar;
	}

	public void setImportePagar(BigDecimal importePagar) {
		this.importePagar = importePagar;
	}

	public BigDecimal getImporteSolicitado() {
		return importeSolicitado;
	}

	public void setImporteSolicitado(BigDecimal importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	public int getPlazoMeses() {
		return plazoMeses;
	}

	public void setPlazoMeses(int plazoMeses) {
		this.plazoMeses = plazoMeses;
	}

	public BigDecimal getPagoMensual() {
		return pagoMensual;
	}

	public void setPagoMensual(BigDecimal pagoMensual) {
		this.pagoMensual = pagoMensual;
	}

	public int getCuotasTotales() {
		return cuotasTotales;
	}

	public void setCuotasTotales(int cuotasTotales) {
		this.cuotasTotales = cuotasTotales;
	}

	public boolean isDeuda() {
		return deuda;
	}

	public void setDeuda(boolean deuda) {
		this.deuda = deuda;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
