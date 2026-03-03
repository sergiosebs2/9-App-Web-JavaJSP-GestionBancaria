package DaoInterfaz;

import java.util.ArrayList;

import Entidades.Cuotas;

public interface IDaoCuotas {
	public ArrayList<Cuotas> obtenerCuotasDelPrestamo (int idPrestamo);
	public int pagarCuota (int idprestamo, int idCuota);


}
