package Negocio;

import java.util.ArrayList;

import Dao.DaoPrestamo;
import Entidades.Prestamo;
import NegocioInterfaz.INegocioPrestamo;
import DaoInterfaz.IDaoPrestamo;

public class NegocioPrestamo implements INegocioPrestamo{

	IDaoPrestamo dao = new DaoPrestamo();

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPendientes() {
		return dao.obtenerPrestamosPendientes();
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPendientesPorNroCuenta(int nroCuenta) {
		return dao.obtenerPrestamosPendientesPorNroCuenta(nroCuenta);
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosAceptados() {
		return dao.obtenerPrestamosAceptados();
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosRechazados() {
		return dao.obtenerPrestamosRechazados();
	}

	@Override
	public boolean rechazarPrestamo(int codPrestamo) {
		return dao.rechazarPrestamo(codPrestamo);
	}

	@Override
	public boolean aceptarPrestamo(int codPrestamo) {
		return dao.aceptarPrestamo(codPrestamo);
	}
	
	public int agregarNuevoPrestamo(Prestamo prestamo)
	{
		return dao.agregarPrestamo(prestamo);
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosSaldados() {
		return dao.obtenerPrestamosSaldados();
	}
	
	public boolean saldarPrestamo(int idPrestamo)
	{
		return dao.saldarPrestamo(idPrestamo);
	}

	public ArrayList<Prestamo> obtenerPrestamosAceptadosPorNroCuenta(int nroCuenta)
	{
		return dao.obtenerPrestamosAceptadosPorNroCuenta(nroCuenta);
	}
}
