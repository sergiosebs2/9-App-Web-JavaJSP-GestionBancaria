package Entidades;

import java.math.BigDecimal;
import java.sql.Date;

public class CuentaBancaria {
	private int nroCuenta;
	private TipoCuenta tipoCuenta;
	private Cliente cliente;
	private String CBU;
	private BigDecimal saldo;
	private Date fecha_alta;
	private boolean estado;
	
	public CuentaBancaria(int nroCuenta, TipoCuenta tipoCuenta, Cliente cliente, String cbu, BigDecimal saldo, Date fecha_alta,
			boolean estado) {
		super();
		this.nroCuenta = nroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.cliente = cliente;
		this.CBU = cbu;
		this.saldo = saldo;
		this.fecha_alta = fecha_alta;
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return tipoCuenta.getDescripcion() + " CBU: " + CBU;
	}

	public CuentaBancaria ()
	{
		nroCuenta = 0;
		tipoCuenta = new TipoCuenta();
		cliente = new Cliente();
		CBU = "";
		saldo = new BigDecimal("0");
		fecha_alta = null;
		estado = false;
	}

	public int getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
}

