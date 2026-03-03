package DaoInterfaz;

import java.util.ArrayList;

import Entidades.Usuario;

public interface IDaoUsuario {
	public Usuario obtenerPorNombre(String nombreUsuario);
	public int Agregar(Usuario usuario);
	public ArrayList<Usuario> Listar();
	public boolean existeNombreUsuario(String nombreUsuario);
	public Usuario listarID(int num);
	public int cambioContrasena(int id, String nueva);
	public Usuario listarPorCliente(int num);
}
