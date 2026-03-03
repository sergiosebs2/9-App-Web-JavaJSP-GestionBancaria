package Negocio;
import Entidades.Movimiento;

import java.math.BigDecimal;
import java.util.ArrayList;

import Dao.DaoMovimientos;
import NegocioInterfaz.INegocioMovimientos;


public class NegocioMovimiento implements INegocioMovimientos {

	 private DaoMovimientos daoMovimientos = new DaoMovimientos();

	    public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int nroCuenta) {
	        return daoMovimientos.obtenerMovimientosPorCuenta(nroCuenta);
	    }
	    
	    public ArrayList<Movimiento> obtenerMovimientosPorTipo(int nroCuenta, String tipomovimiento) {
			
			 return daoMovimientos.obtenerMovimientosPorTipo(nroCuenta, tipomovimiento);
		}
	    
	    public boolean crearMovimiento(String tipoMovimiento, int numeroCuenta, String detalle, BigDecimal importe)
	    {
	    	return daoMovimientos.crearMovimiento(tipoMovimiento, numeroCuenta, detalle, importe);
	    }
}