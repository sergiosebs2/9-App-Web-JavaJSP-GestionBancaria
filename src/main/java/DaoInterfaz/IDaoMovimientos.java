package DaoInterfaz;

import java.math.BigDecimal;
import java.util.ArrayList;

import Entidades.Movimiento;


public interface IDaoMovimientos {
	public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int nroCuenta);
	public ArrayList<Movimiento> obtenerMovimientosPorTipo(int nroCuenta, String codTipoMovimiento);
	public boolean crearMovimiento(String tipoMovimiento, int numeroCuenta, String detalle, BigDecimal importe);
}
