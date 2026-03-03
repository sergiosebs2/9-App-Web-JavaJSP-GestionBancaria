package NegocioInterfaz;
import java.util.ArrayList;

import Entidades.*;

public interface INegocioCuotas {
	public ArrayList<Cuotas>listarCuotasPorIdPrestamo (int num);
	public int pagarCuotaSeleccionada (int idprestamo, int idCuota);
	public boolean cuotasSaldadas(int idPrestamo);
}
