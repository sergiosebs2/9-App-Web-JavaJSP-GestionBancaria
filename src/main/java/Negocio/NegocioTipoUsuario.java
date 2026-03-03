package Negocio;

import Dao.DaoTipoUsuario;
import DaoInterfaz.IDaoTipoUsuario;
import Entidades.TipoUsuario;
import NegocioInterfaz.INegocioTipoUsuario;

public class NegocioTipoUsuario implements INegocioTipoUsuario{
	
	private IDaoTipoUsuario daoTipoUsuario = new DaoTipoUsuario();
	
	public TipoUsuario obtenerPorId(char idSolicitado) {
		return daoTipoUsuario.obtenerPorId(idSolicitado);
	}

}
