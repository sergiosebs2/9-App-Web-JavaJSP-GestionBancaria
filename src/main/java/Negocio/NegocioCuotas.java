package Negocio;

import java.util.ArrayList;

import Dao.DaoCuotas;
import DaoInterfaz.IDaoCuotas;
import Entidades.Cuotas;
import NegocioInterfaz.INegocioCuotas;

public class NegocioCuotas implements INegocioCuotas{
	IDaoCuotas dao = new DaoCuotas();
	
	public ArrayList<Cuotas>listarCuotasPorIdPrestamo (int num)
	{
		return dao.obtenerCuotasDelPrestamo(num);
	}
	
	public boolean cuotasSaldadas(int idPrestamo)
	{
		ArrayList<Cuotas> cuotas = dao.obtenerCuotasDelPrestamo(idPrestamo);
		
		for (Cuotas itemCuota : cuotas) {
			if (!itemCuota.isEstado())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public int pagarCuotaSeleccionada (int idprestamo, int idCuota)
	{
		return dao.pagarCuota(idprestamo, idCuota);
	}
}
