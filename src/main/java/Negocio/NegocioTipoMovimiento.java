package Negocio;

import Dao.DaoTipoMovimiento;
import DaoInterfaz.IDaoTipoMovimiento;
import Entidades.TipoMovimiento;
import NegocioInterfaz.INegocioTipoMovimiento;

public class NegocioTipoMovimiento implements INegocioTipoMovimiento{
	private IDaoTipoMovimiento daoTipoMovimiento = new DaoTipoMovimiento();
	public TipoMovimiento obtenerPorCod(String codigoSolicitado) {
		return daoTipoMovimiento.obtenerPorCod(codigoSolicitado);
	}

}
