package DaoInterfaz;

import java.sql.Date;
import java.util.ArrayList;

import Entidades.Cliente;
import Entidades.Informe;

public interface IDaoInforme {
	
	Informe obtenerInforme(Date fechaDesde, Date fechaHasta);
	public ArrayList<Cliente> obtenerTablaClientesNuevos(Date fechaDesde, Date fechaHasta);
	
}
