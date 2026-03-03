package DaoInterfaz;

import java.math.BigDecimal;
import java.util.ArrayList;

import Entidades.CuentaBancaria;

public interface IDaoCuentaBancaria {
	public ArrayList<CuentaBancaria> obtenerCuentaPorCliente(String CodigoCliente);
	public ArrayList<CuentaBancaria> obtenerCuentasPorTipo(char tipoCuenta);
	public ArrayList<CuentaBancaria> buscarCuentasFiltradas(String codigoCliente, String tipoCuenta, String estado);
	public boolean darBajaCuenta(int numeroCuenta);
	public boolean asignarCuenta(int codigoCliente, char tipoDeCuenta, String cbu);
	public ArrayList<String> obtenerCbuExistentes();
	public boolean darAltaCuenta(int numeroCuenta);
	public CuentaBancaria ObtenerCuentaporID(int id);
	public CuentaBancaria obtenerCuentaPorCbu(String cbu);
	public boolean modificarSaldoEnCuenta(BigDecimal monto, int numeroCuenta);
}
