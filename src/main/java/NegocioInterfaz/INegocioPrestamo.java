package NegocioInterfaz;

import java.util.ArrayList;

import Entidades.Prestamo;

public interface INegocioPrestamo {

	public ArrayList<Prestamo> obtenerPrestamosPendientes();
	public ArrayList<Prestamo> obtenerPrestamosPendientesPorNroCuenta(int nroCuenta);
	public ArrayList<Prestamo> obtenerPrestamosAceptados();
	public ArrayList<Prestamo> obtenerPrestamosRechazados();
	public boolean rechazarPrestamo(int codPrestamo);
	public boolean aceptarPrestamo(int codPrestamo);
	public int agregarNuevoPrestamo(Prestamo prestamo);
	public ArrayList<Prestamo> obtenerPrestamosSaldados();
	public boolean saldarPrestamo(int idPrestamo);
	public ArrayList<Prestamo> obtenerPrestamosAceptadosPorNroCuenta(int nroCuenta);
}
