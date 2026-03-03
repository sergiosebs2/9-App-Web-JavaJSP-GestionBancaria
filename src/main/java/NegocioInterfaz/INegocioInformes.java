package NegocioInterfaz;

import java.sql.Date;
import java.util.ArrayList;

import Entidades.Cliente;
import Entidades.Informe;

public interface INegocioInformes {
	Informe generarInforme(Date desde, Date hasta);
	public ArrayList<Cliente> obtenerTablaNuevos(Date fechaDesde, Date fechaHasta);
}
