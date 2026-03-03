package NegocioInterfaz;

import Entidades.TipoMovimiento;

public interface INegocioTipoMovimiento {
	public TipoMovimiento obtenerPorCod(String codigoSolicitado);
}
