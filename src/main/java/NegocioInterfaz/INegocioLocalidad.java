package NegocioInterfaz;

import java.util.ArrayList;

import Entidades.Localidad;

public interface INegocioLocalidad {

	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int id);
}
