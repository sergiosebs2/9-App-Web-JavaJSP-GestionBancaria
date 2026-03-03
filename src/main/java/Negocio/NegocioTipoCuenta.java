package Negocio;

import java.util.ArrayList;

import Dao.DaoTipoCuenta;
import DaoInterfaz.IDaoTipoCuenta;
import Entidades.TipoCuenta;
import NegocioInterfaz.INegocioTipoCuenta;

public class NegocioTipoCuenta implements INegocioTipoCuenta{

	private IDaoTipoCuenta daoTipoCuenta = new DaoTipoCuenta();
	
	public TipoCuenta obtenerPorCod(char codSolicitado) {
		return daoTipoCuenta.obtenerPorCod(codSolicitado);
	}
	
	public ArrayList<TipoCuenta> obtenerTiposCuenta(){
		return daoTipoCuenta.obtenerTiposCuenta();
	}

}
