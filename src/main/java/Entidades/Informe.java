package Entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class Informe {
	private int cuentasDadasAlta;
	private int cuentasAltaCajaAhorro;
	private int cuentasAltaCorriente;
	private int cuentasBajas;
	private int cuentasBajaCajaAhorro;
	private int cuentasBajaCorriente;
	private int clientesNuevos;
	private int prestamosActivos;
	private int prestamosAtrasados;
	private int prestamosSaldados;
	private double cumplibilidadPrestamos;
	private BigDecimal saldoPromedioPorCuenta;
	private BigDecimal saldoTotalBanco;
	private int transferenciasRealizadas;
	private int movimientosTotales;
	private Date fechaInicio;
	private Date fechaFinal;

	public Informe() {
		cuentasDadasAlta = 0;
		cuentasAltaCajaAhorro = 0;
		cuentasAltaCorriente = 0;
		cuentasBajas = 0;
		cuentasBajaCajaAhorro = 0;
		cuentasBajaCorriente = 0;
		clientesNuevos = 0;
		prestamosActivos = 0;
		prestamosAtrasados = 0;
		prestamosSaldados = 0;
		cumplibilidadPrestamos = 0;
		saldoPromedioPorCuenta = new BigDecimal("0");
		saldoTotalBanco = new BigDecimal("0");
		transferenciasRealizadas = 0;
		movimientosTotales = 0;
		fechaInicio = null;
		fechaFinal = null;
	}

	public int getCuentasDadasAlta() {
		return cuentasDadasAlta;
	}

	public void setCuentasDadasAlta(int cuentasDadasAlta) {
		this.cuentasDadasAlta = cuentasDadasAlta;
	}

	public int getCuentasAltaCajaAhorro() {
		return cuentasAltaCajaAhorro;
	}

	public void setCuentasAltaCajaAhorro(int cuentasAltaCajaAhorro) {
		this.cuentasAltaCajaAhorro = cuentasAltaCajaAhorro;
	}

	public int getCuentasAltaCorriente() {
		return cuentasAltaCorriente;
	}

	public void setCuentasAltaCorriente(int cuentasAltaCorriente) {
		this.cuentasAltaCorriente = cuentasAltaCorriente;
	}

	public int getCuentasBajas() {
		return cuentasBajas;
	}

	public void setCuentasBajas(int cuentasBajas) {
		this.cuentasBajas = cuentasBajas;
	}

	public int getCuentasBajaCajaAhorro() {
		return cuentasBajaCajaAhorro;
	}

	public void setCuentasBajaCajaAhorro(int cuentasBajaCajaAhorro) {
		this.cuentasBajaCajaAhorro = cuentasBajaCajaAhorro;
	}

	public int getCuentasBajaCorriente() {
		return cuentasBajaCorriente;
	}

	public void setCuentasBajaCorriente(int cuentasBajaCorriente) {
		this.cuentasBajaCorriente = cuentasBajaCorriente;
	}

	public int getClientesNuevos() {
		return clientesNuevos;
	}

	public void setClientesNuevos(int clientesNuevos) {
		this.clientesNuevos = clientesNuevos;
	}

	public int getPrestamosActivos() {
		return prestamosActivos;
	}

	public void setPrestamosActivos(int prestamosActivos) {
		this.prestamosActivos = prestamosActivos;
	}

	public int getPrestamosAtrasados() {
		return prestamosAtrasados;
	}

	public void setPrestamosAtrasados(int prestamosAtrasados) {
		this.prestamosAtrasados = prestamosAtrasados;
	}

	public int getPrestamosSaldados() {
		return prestamosSaldados;
	}

	public void setPrestamosSaldados(int prestamosSaldados) {
		this.prestamosSaldados = prestamosSaldados;
	}

	public double getCumplibilidadPrestamos() {
		return cumplibilidadPrestamos;
	}

	public void setCumplibilidadPrestamos(double cumplibilidadPrestamos) {
		this.cumplibilidadPrestamos = cumplibilidadPrestamos;
	}

	public BigDecimal getSaldoPromedioPorCuenta() {
		return saldoPromedioPorCuenta;
	}

	public void setSaldoPromedioPorCuenta(BigDecimal saldoPromedioPorCuenta) {
		this.saldoPromedioPorCuenta = saldoPromedioPorCuenta;
	}

	public BigDecimal getSaldoTotalBanco() {
		return saldoTotalBanco;
	}

	public void setSaldoTotalBanco(BigDecimal saldoTotalBanco) {
		this.saldoTotalBanco = saldoTotalBanco;
	}

	public int getTransferenciasRealizadas() {
		return transferenciasRealizadas;
	}

	public void setTransferenciasRealizadas(int transferenciasRealizadas) {
		this.transferenciasRealizadas = transferenciasRealizadas;
	}

	public int getMovimientosTotales() {
		return movimientosTotales;
	}

	public void setMovimientosTotales(int movimientosTotales) {
		this.movimientosTotales = movimientosTotales;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

}
