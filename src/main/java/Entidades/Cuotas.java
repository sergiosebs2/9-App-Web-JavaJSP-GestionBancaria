package Entidades;

import java.sql.Date;

public class Cuotas {
	
	private int codigoCuota;
	private int codPrestamo;
	private int numeroCuota;
	private double montoCuota;
	private Date fechaPago;
	//ESTADO -> false: no pago - true:pago
	private boolean estado;
	
	public Cuotas() {		
	}
	public int getCodigoCuota() {
		return codigoCuota;
	}
	public void setCodigoCuota(int codigoCuota) {
		this.codigoCuota = codigoCuota;
	}
	public int getCodPrestamo() {
		return codPrestamo;
	}
	public void setCodPrestamo(int codPrestamo) {
		this.codPrestamo = codPrestamo;
	}
	public int getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Cuotas(int codigoCuota, int codPrestamo, int numeroCuota, double montoCuota, Date fechaPago,
			boolean estado) {
		super();
		this.codigoCuota = codigoCuota;
		this.codPrestamo = codPrestamo;
		this.numeroCuota = numeroCuota;
		this.montoCuota = montoCuota;
		this.fechaPago = fechaPago;
		this.estado = estado;
	}
	
	
	
	
}
