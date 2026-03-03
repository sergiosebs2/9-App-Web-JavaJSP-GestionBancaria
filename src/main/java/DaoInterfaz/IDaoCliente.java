package DaoInterfaz;

import java.util.ArrayList;

import Entidades.Cliente;

public interface IDaoCliente {
	public Cliente obtenerClientePorCodigo(String codigo);
	public boolean Agregar(Cliente cliente, int IdUsuario);
	public boolean Modificar(Cliente cliente);
    public boolean Eliminar(int CodigoCliente);
	public ArrayList<Cliente> Listar();
	public boolean existeDni(String dni);
	public int obtenerIdUsuarioPorNombreUsuario(String usuario);
	public Cliente obtenerClientePorIdUsuario(int idUsuario);
	public boolean existeCuil(String cuil);
}
