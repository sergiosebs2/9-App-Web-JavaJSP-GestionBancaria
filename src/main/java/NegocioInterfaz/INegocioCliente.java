package NegocioInterfaz;

import java.util.ArrayList;

import Entidades.Cliente;

public interface INegocioCliente {
	public Cliente buscarClientePorCodigo(String codigoCliente);
	public boolean Agregar(Cliente cliente, int iDusuario);
	public boolean Modificar(Cliente cliente);
    public boolean Eliminar(int CodigoCliente);
	public ArrayList<Cliente> Listar();
	public boolean existeDni(String dni);
	public Cliente buscarClientePorUsuario(String nombreUsuario);
	public boolean existeCuil(String cuil);

}
