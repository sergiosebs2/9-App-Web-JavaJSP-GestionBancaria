package DaoInterfaz;

import java.util.ArrayList;

import Entidades.Prestamo;

public interface IDaoPrestamo {

	public ArrayList<Prestamo> obtenerPrestamosPendientes();
	public ArrayList<Prestamo> obtenerPrestamosPendientesPorNroCuenta(int nroCuenta);
	public ArrayList<Prestamo> obtenerPrestamosAceptados();
	public ArrayList<Prestamo> obtenerPrestamosRechazados();
	public boolean rechazarPrestamo(int codPrestamo);
	public boolean aceptarPrestamo(int codPrestamo);
	public int agregarPrestamo(Prestamo pres);
	public ArrayList<Prestamo> obtenerPrestamosSaldados();
	public boolean saldarPrestamo(int codPrestamo);
	public ArrayList<Prestamo> obtenerPrestamosAceptadosPorNroCuenta(int nroCuenta);
}
