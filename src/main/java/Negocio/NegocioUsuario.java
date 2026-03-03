package Negocio;

import java.util.ArrayList;

import Dao.DaoUsuario;
import Entidades.Usuario;
import NegocioInterfaz.INegocioUsuario;
import DaoInterfaz.IDaoUsuario;

public class NegocioUsuario implements INegocioUsuario {
	
	 private IDaoUsuario usuarioDao = new DaoUsuario();
	 
	public int Agregar(Usuario usuario) {
		if (usuario == null ) {
            System.out.println("Faltan completar campos");
            return 0;
		}
		
		int idGenerado = usuarioDao.Agregar(usuario);
		
		if (idGenerado > 0) {
	        return idGenerado;
	    } else {
	        System.out.println("Error al crear usuario");
	        return 0;
	    }
	}
	
	
public ArrayList<Usuario> Listar() {
		
		return usuarioDao.Listar();
	}
	public Usuario ListarporID(int num)
	{
		return usuarioDao.listarID(num);
	}

	
	public Usuario obtenerPorNombre(String nombreUsuario) {
		return usuarioDao.obtenerPorNombre(nombreUsuario);
	}
	
	public boolean inicioSesion(String usuario, String contrasena, char tipoUsuarioSeleccionado)
	{
		Usuario usuarioSolicitado = obtenerPorNombre(usuario);
		
		if(usuarioSolicitado.getIdUsuario() < 1)
		{
			return false;
		}
		
		if(!usuarioSolicitado.getContrasena().equals(contrasena))
		{
			return false;
		}
		
		if(usuarioSolicitado.getTipoUsuario().getIdTipoUsuario() != tipoUsuarioSeleccionado)
		{
			return false;
		}
		
		return true;
	}



	@Override
	public boolean existeNombreUsuario(String nombreUsuario) {
		return usuarioDao.existeNombreUsuario(nombreUsuario);
	}
	public int cambiarContrasena(int id, String contra)
	{
		return usuarioDao.cambioContrasena(id, contra);
	}
	
	public Usuario listadoPorCliente(int num)
	{
		return usuarioDao.listarPorCliente(num);
	}
}
