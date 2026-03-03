package Negocio;

import java.sql.Date;
import java.util.ArrayList;

import Entidades.Cliente;
import Entidades.Informe;
import NegocioInterfaz.INegocioInformes;
import DaoInterfaz.IDaoInforme;
import Dao.DaoInforme;

public class NegocioInforme implements INegocioInformes {

	private IDaoInforme informeDao = new DaoInforme();

	public Informe generarInforme(Date desde, Date hasta) {
		try {
				return informeDao.obtenerInforme(desde, hasta);
	        } catch (Exception ex) {
	            throw new RuntimeException("Error generando informe", ex);
	        }
	    }
	
	public ArrayList<Cliente> obtenerTablaNuevos(Date fechaDesde, Date fechaHasta) {
		try {
			return informeDao.obtenerTablaClientesNuevos(fechaDesde, fechaHasta);
        } catch (Exception ex) {
            throw new RuntimeException("Error generando los clientes nuevos", ex);
        }
	}
}
