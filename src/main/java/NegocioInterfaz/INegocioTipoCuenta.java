package NegocioInterfaz;

import java.util.ArrayList;

import Entidades.TipoCuenta;

public interface INegocioTipoCuenta {
	public TipoCuenta obtenerPorCod(char codSolicitado);
	public ArrayList<TipoCuenta> obtenerTiposCuenta();
}
