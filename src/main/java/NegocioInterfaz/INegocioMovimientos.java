package NegocioInterfaz;
import java.math.BigDecimal;
import java.util.ArrayList;

import Entidades.Movimiento;

public interface INegocioMovimientos {
	ArrayList<Movimiento> obtenerMovimientosPorCuenta(int nroCuenta);
	ArrayList<Movimiento> obtenerMovimientosPorTipo(int nroCuenta, String tipomovimiento);
	public boolean crearMovimiento(String tipoMovimiento, int numeroCuenta, String detalle, BigDecimal importe);
}
