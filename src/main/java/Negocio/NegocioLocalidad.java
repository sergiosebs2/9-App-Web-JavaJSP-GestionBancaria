package Negocio;

import java.util.ArrayList;

import Dao.DaoLocalidad;
import DaoInterfaz.IDaoLocalidad;
import Entidades.Localidad;
import NegocioInterfaz.INegocioLocalidad;

public class NegocioLocalidad implements INegocioLocalidad{

	IDaoLocalidad daoLocalidad = new DaoLocalidad();
	
	@Override
	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int id) {
		return daoLocalidad.obtenerLocalidadesPorProvincia(id);
	}

}
