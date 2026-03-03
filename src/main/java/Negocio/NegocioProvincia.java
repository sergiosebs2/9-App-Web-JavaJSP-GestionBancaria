package Negocio;

import java.util.ArrayList;

import Dao.DaoProvincia;
import DaoInterfaz.IDaoProvincia;
import Entidades.Provincia;
import NegocioInterfaz.INegocioProvincia;

public class NegocioProvincia implements INegocioProvincia{

	IDaoProvincia daoProvincia = new DaoProvincia();
	
	@Override
	public ArrayList<Provincia> obtenerProvincias() {
		return daoProvincia.obtenerProvincias();
	}

}
