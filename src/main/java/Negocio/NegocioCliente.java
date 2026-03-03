
package Negocio;
import Entidades.Cliente;

import java.util.ArrayList;

import Dao.DaoCliente;
import NegocioInterfaz.INegocioCliente;
import DaoInterfaz.IDaoCliente;

public class NegocioCliente implements INegocioCliente {

    private IDaoCliente clienteDao = new DaoCliente();

	
    public Cliente buscarClientePorCodigo(String codigoCliente) {
            if (codigoCliente == null ) {
            System.out.println("Código de cliente inválido: " + codigoCliente);
            return null;
        }
        
        return clienteDao.obtenerClientePorCodigo(codigoCliente);
    }
    
    public Cliente buscarClientePorUsuario(String Usuario) {
        if (Usuario == null) 
        {
        	return null;
        }
        
        int idUsuario = clienteDao.obtenerIdUsuarioPorNombreUsuario(Usuario);
        
        return clienteDao.obtenerClientePorIdUsuario(idUsuario);
    }

	
    public boolean Agregar(Cliente cliente, int iDusuario) {
		if (cliente != null ) {
			return clienteDao.Agregar(cliente, iDusuario); 
		} else {
			return false;
		}
	}

	
	public boolean Modificar(Cliente cliente) {
		
		return false;
	}

	//Hacer validaciones
	public boolean Eliminar(int CodigoCliente) {
		
		 return clienteDao.Eliminar(CodigoCliente);
		
	}

	
	public ArrayList<Cliente> Listar() {
		
		return null;
	}


	@Override
	public boolean existeDni(String dni) {
		return clienteDao.existeDni(dni);
	}

	@Override
	public boolean existeCuil(String cuil) {
		return clienteDao.existeCuil(cuil);
	}
}