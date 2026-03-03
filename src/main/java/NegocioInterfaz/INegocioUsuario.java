package NegocioInterfaz;

import java.util.ArrayList;

import Entidades.Usuario;

public interface INegocioUsuario {
	public boolean inicioSesion(String usuario, String contrasena, char tipoUsuario);
	public Usuario obtenerPorNombre(String nombreUsuario);
	public int Agregar(Usuario usuario);
	public ArrayList<Usuario> Listar();
	public boolean existeNombreUsuario(String nombreUsuario);
	public Usuario ListarporID(int num);
	public int cambiarContrasena(int id, String contra);
	public Usuario listadoPorCliente(int num);
}
