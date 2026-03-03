package Negocio;

import java.util.ArrayList;

import Dao.DaoNacionalidad;
import DaoInterfaz.IDaoNacionalidad;
import Entidades.Nacionalidad;
import NegocioInterfaz.INegocioNacionalidad;

public class NegocioNacionalidad implements INegocioNacionalidad{

	IDaoNacionalidad daoNacionalidad = new DaoNacionalidad();
	
	@Override
	public ArrayList<Nacionalidad> obtenerNacionalidades() {
		return daoNacionalidad.obtenerNacionalidades();
	}

	
}
