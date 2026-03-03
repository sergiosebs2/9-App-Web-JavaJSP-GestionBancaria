package DaoInterfaz;

import java.util.ArrayList;

import Entidades.Localidad;

public interface IDaoLocalidad {

	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int id);
}
