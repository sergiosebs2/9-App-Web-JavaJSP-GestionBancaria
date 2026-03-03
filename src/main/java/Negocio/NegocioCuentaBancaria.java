package Negocio;

import NegocioInterfaz.INegocioCuentaBancaria;
import NegocioInterfaz.INegocioMovimientos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import Dao.DaoCuentaBancaria;
import DaoInterfaz.IDaoCuentaBancaria;
import Entidades.CuentaBancaria;

public class NegocioCuentaBancaria implements INegocioCuentaBancaria{
	
	private IDaoCuentaBancaria cuentaDao = new DaoCuentaBancaria();
	
	public boolean darBajaCuenta(int numeroCuenta)
	{
		return cuentaDao.darBajaCuenta(numeroCuenta);
	}
	
	public ArrayList<CuentaBancaria> buscarCuentasBancariasPorClienteAsignado(String codCliente)
	{
		if (codCliente == null) {
			return new ArrayList<>();
		}
		
		return cuentaDao.obtenerCuentaPorCliente(codCliente);
	}
	
	public ArrayList<CuentaBancaria> buscarCuentasBancariasPorTipo(char tipo)
	{
		return cuentaDao.obtenerCuentasPorTipo(tipo);
	}

	
	public ArrayList<CuentaBancaria> buscarCuentasFiltradas(String codigoCliente, String tipoCuenta, String estado) {
		
		 return cuentaDao.buscarCuentasFiltradas(codigoCliente, tipoCuenta, estado);
	}
	
	public boolean asignarCuenta(int codigoCliente, char tipoDeCuenta)
	{
		String cbu = generarCbuUnico();
		
		return cuentaDao.asignarCuenta(codigoCliente, tipoDeCuenta, cbu);
	}
	
	private String generarCbuUnico()
	{
		ArrayList<String> cbuExistentes = cuentaDao.obtenerCbuExistentes();
		
		String cbuUnico;
		
		do
		{
			cbuUnico = generarCBU();
	    } while (cbuExistentes.contains(cbuUnico));
		
		return cbuUnico;
	}
	
	private String generarCBU() {
		
	    StringBuilder cbu = new StringBuilder();
	    Random random = new Random();

	    for (int i = 0; i < 22; i++) 
	    {
	        cbu.append(random.nextInt(10));
	    }

	    return cbu.toString();
	}
	
	public boolean darAltaCuenta(int numeroCuenta)
	{
		return cuentaDao.darAltaCuenta(numeroCuenta);
	}
	
	public CuentaBancaria ObtenerporID(int id)
	{
		return cuentaDao.ObtenerCuentaporID(id);
	}
	
	public boolean descontarCuota( BigDecimal monto, int numeroCuenta)
	{
		return cuentaDao.modificarSaldoEnCuenta(monto, numeroCuenta);
	}

	public CuentaBancaria obtenerCuentaPorCbu(String cbu) {
		return cuentaDao.obtenerCuentaPorCbu(cbu);
	}
	
	public boolean transferir(String cbuDestino, CuentaBancaria cuentaOrigen, BigDecimal montoTransferido, String detalle)
	{
		CuentaBancaria cuentaDestino = obtenerCuentaPorCbu(cbuDestino);
		
		if(montoTransferido.compareTo(BigDecimal.ONE) < 0)
		{
			return false;
		}
		
		if(cuentaOrigen.getSaldo().compareTo(montoTransferido) < 0)
		{
			return false;
		}
		
		if(cuentaOrigen.getNroCuenta() == cuentaDestino.getNroCuenta())
		{
			return false;
		}
		
		BigDecimal nuevoSaldoOrigen = cuentaOrigen.getSaldo().subtract(montoTransferido);
		if(!cuentaDao.modificarSaldoEnCuenta(nuevoSaldoOrigen, cuentaOrigen.getNroCuenta()))
		{
			return false;
		}
		
		BigDecimal nuevoSaldoDestino = cuentaDestino.getSaldo().add(montoTransferido);
		if(!cuentaDao.modificarSaldoEnCuenta(nuevoSaldoDestino, cuentaDestino.getNroCuenta()))
		{
			return false;
		}
		
		INegocioMovimientos negocioMovimientos = new NegocioMovimiento();
		//movimiento de la cuenta de origen que se pasa en negativo.
		if(!negocioMovimientos.crearMovimiento("TT", cuentaOrigen.getNroCuenta(), detalle, montoTransferido.negate()))
		{
			return false;
		}
		
		//movimiento de la cuenta destinataria.
		if(!negocioMovimientos.crearMovimiento("TT", cuentaDestino.getNroCuenta(), detalle, montoTransferido))
		{
			return false;
		}
		
		return true;
	}
	
}
