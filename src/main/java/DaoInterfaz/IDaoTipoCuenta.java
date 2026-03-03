package DaoInterfaz;

import java.util.ArrayList;

import Entidades.TipoCuenta;

public interface IDaoTipoCuenta {
	public TipoCuenta obtenerPorCod(char codSolicitado);
	public ArrayList<TipoCuenta> obtenerTiposCuenta();
}
