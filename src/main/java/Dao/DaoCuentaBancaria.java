package Dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoInterfaz.IDaoCuentaBancaria;
import NegocioInterfaz.INegocioCliente;
import NegocioInterfaz.INegocioTipoCuenta;
import Entidades.CuentaBancaria;
import Entidades.TipoCuenta;
import Negocio.NegocioCliente;
import Negocio.NegocioTipoCuenta;
import Entidades.Cliente;

public class DaoCuentaBancaria implements IDaoCuentaBancaria{
	
	public boolean darBajaCuenta(int numeroCuenta)
	{
		Connection cn = null;
	    boolean eliminado = false;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE cuentas SET Estado = 0 WHERE NroCuenta = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, numeroCuenta);

	        int filasAfectadas = pst.executeUpdate();

	        if (filasAfectadas > 0) {
	            cn.commit(); 
	            eliminado = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (cn != null) cn.rollback(); 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (cn != null) cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return eliminado;
	}
	
	public ArrayList<CuentaBancaria> obtenerCuentaPorCliente(String CodigoCliente) {
		ArrayList<CuentaBancaria> lCuentas = new ArrayList<CuentaBancaria>();
		Connection cn = null;
		
		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT NroCuenta, CodTipoCuenta, CodCliente, CBU, Saldo, Fecha_alta, Estado FROM cuentas WHERE CodCliente = ?";
			
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(CodigoCliente));
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				INegocioTipoCuenta negocioTipoCuenta = new NegocioTipoCuenta();
				INegocioCliente clienteNegocio = new NegocioCliente();
				
				CuentaBancaria cuentaBancaria = new CuentaBancaria();
				cuentaBancaria.setNroCuenta(rs.getInt("NroCuenta"));
				cuentaBancaria.setCBU(rs.getString("CBU"));
				cuentaBancaria.setSaldo(rs.getBigDecimal("Saldo"));
				cuentaBancaria.setFecha_alta(rs.getDate("Fecha_alta"));
				cuentaBancaria.setEstado(rs.getBoolean("Estado"));
				
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta = negocioTipoCuenta.obtenerPorCod(rs.getString("CodTipoCuenta").charAt(0));
				cuentaBancaria.setTipoCuenta(tipoCuenta);
				
				Cliente cliente = new Cliente();
				cliente = clienteNegocio.buscarClientePorCodigo(CodigoCliente);
				cuentaBancaria.setCliente(cliente);
				
				lCuentas.add(cuentaBancaria);
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		return lCuentas;
	}
	
	public ArrayList<CuentaBancaria> obtenerCuentasPorTipo(char tipo) {
		ArrayList<CuentaBancaria> lCuentas = new ArrayList<CuentaBancaria>();
		Connection cn = null;
		
		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT NroCuenta, CodTipoCuenta, CodCliente, CBU, Saldo, Fecha_alta, Estado FROM cuentas WHERE CodTipoCuenta = ?";
			
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setString(1, String.valueOf(tipo));
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				INegocioTipoCuenta negocioTipoCuenta = new NegocioTipoCuenta();
				INegocioCliente clienteNegocio = new NegocioCliente();
				
				CuentaBancaria cuentaBancaria = new CuentaBancaria();
				cuentaBancaria.setNroCuenta(rs.getInt("NroCuenta"));
				cuentaBancaria.setCBU(rs.getString("CBU"));
				cuentaBancaria.setSaldo(rs.getBigDecimal("Saldo"));
				cuentaBancaria.setFecha_alta(rs.getDate("Fecha_alta"));
				cuentaBancaria.setEstado(rs.getBoolean("Estado"));
				
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta = negocioTipoCuenta.obtenerPorCod(rs.getString("CodTipoCuenta").charAt(0));
				cuentaBancaria.setTipoCuenta(tipoCuenta);
				
				Cliente cliente = new Cliente();
				cliente = clienteNegocio.buscarClientePorCodigo(rs.getString("CodCliente"));
				cuentaBancaria.setCliente(cliente);
				
				lCuentas.add(cuentaBancaria);
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		return lCuentas;
	}

	@Override
	public ArrayList<CuentaBancaria> buscarCuentasFiltradas(String codigoCliente, String tipoCuenta, String estado) {
		  ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
		    Connection cn = null;

		    try {
		        cn = Conexion.getConexion().getSQLConexion();
		        CallableStatement cst = cn.prepareCall("{CALL FiltrarCuentas(?, ?, ?)}");

		       
		        if (codigoCliente != null && !codigoCliente.isEmpty()) {
		            cst.setInt(1, Integer.parseInt(codigoCliente));
		        } else {
		            cst.setNull(1, java.sql.Types.INTEGER);
		        }

		        if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
		            cst.setString(2, tipoCuenta);
		        } else {
		            cst.setNull(2, java.sql.Types.CHAR);
		        }

		        if (estado != null && !estado.isEmpty()) {
		            cst.setBoolean(3, estado.equals("1"));
		        } else {
		            cst.setNull(3, java.sql.Types.BOOLEAN);
		        }

		        ResultSet rs = cst.executeQuery();

		        INegocioTipoCuenta negocioTipoCuenta = new NegocioTipoCuenta();
		        INegocioCliente clienteNegocio = new NegocioCliente();

		        while (rs.next()) {
		            CuentaBancaria cuenta = new CuentaBancaria();
		            cuenta.setNroCuenta(rs.getInt("NroCuenta"));
		            cuenta.setCBU(rs.getString("CBU"));
		            cuenta.setSaldo(rs.getBigDecimal("Saldo"));
		            cuenta.setFecha_alta(rs.getDate("Fecha_alta"));
		            cuenta.setEstado(rs.getBoolean("Estado"));

		            TipoCuenta tipo = negocioTipoCuenta.obtenerPorCod(rs.getString("CodTipoCuenta").charAt(0));
		            cuenta.setTipoCuenta(tipo);

		            Cliente cliente = clienteNegocio.buscarClientePorCodigo(rs.getString("CodCliente"));
		            cuenta.setCliente(cliente);

		            cuentas.add(cuenta);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (cn != null)
		                cn.close();
		        } catch (Exception e2) {
		            e2.printStackTrace();
		        }
		    }

		    return cuentas;
		
	}

	@Override
	public boolean asignarCuenta(int codigoCliente, char tipoDeCuenta, String cbu) {
		Connection cn = null;
		int nroCuentaGenerado = 0;
		int filas=0;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO cuentas(CodTipoCuenta, CodCliente, CBU) VALUES (?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    
            	pst.setString(1, String.valueOf(tipoDeCuenta));
            	pst.setInt(2, codigoCliente);
            	pst.setString(3, cbu);
    	        pst.executeUpdate();
            	
    	    ResultSet rs = pst.getGeneratedKeys();
    	        if (rs.next()) {
    	            nroCuentaGenerado = rs.getInt(1);
    	        }
    	    
            String query2 = "INSERT INTO movimientos(CodTipoMovimiento, NroCuentaAsociado, Detalle, Importe) VALUES ('AC', ?, 'Alta de cuenta', 10000);";
            PreparedStatement pst2 = cn.prepareStatement(query2);
            
                pst2.setInt(1, nroCuentaGenerado);
                
        	    filas = pst2.executeUpdate();
        	    cn.commit();

        } catch (Exception e) {
        	try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        return filas>0;
	}

	@Override
	public ArrayList<String> obtenerCbuExistentes() {
		ArrayList<String> lCbus = new ArrayList<String>();
		Connection cn = null;
		
		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT CBU FROM cuentas";
			
			PreparedStatement pst = cn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				lCbus.add(rs.getString("CBU"));
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		return lCbus;
	}
	
	public boolean darAltaCuenta(int numeroCuenta)
	{
		Connection cn = null;
	    boolean agregado = false;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE cuentas SET Estado = 1 WHERE NroCuenta = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, numeroCuenta);

	        int filasAfectadas = pst.executeUpdate();

	        if (filasAfectadas > 0) {
	            cn.commit(); 
	            agregado = true;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (cn != null) cn.rollback(); 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    } finally {
	        try {
	            if (cn != null) cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    return agregado;
	}
	
	public CuentaBancaria ObtenerCuentaporID(int id)
	{
		
		CuentaBancaria cuentaBancaria = new CuentaBancaria();
		Connection cn = null;
		
		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT NroCuenta, CodTipoCuenta, CodCliente, CBU, Saldo, Fecha_alta, Estado FROM cuentas WHERE NroCuenta = ?";
			
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				
				INegocioTipoCuenta negocioTipoCuenta = new NegocioTipoCuenta();
				INegocioCliente clienteNegocio = new NegocioCliente();
				
				cuentaBancaria = new CuentaBancaria();
				cuentaBancaria.setNroCuenta(rs.getInt("NroCuenta"));
				cuentaBancaria.setCBU(rs.getString("CBU"));
				cuentaBancaria.setSaldo(rs.getBigDecimal("Saldo"));
				cuentaBancaria.setFecha_alta(rs.getDate("Fecha_alta"));
				cuentaBancaria.setEstado(rs.getBoolean("Estado"));
				
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta = negocioTipoCuenta.obtenerPorCod(rs.getString("CodTipoCuenta").charAt(0));
				cuentaBancaria.setTipoCuenta(tipoCuenta);
				
				Cliente cliente = new Cliente();
				cliente = clienteNegocio.buscarClientePorCodigo(rs.getString("CodCliente"));
				cuentaBancaria.setCliente(cliente);			
				
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		return cuentaBancaria;
	}
		
	public CuentaBancaria obtenerCuentaPorCbu(String cbu)
	{
		CuentaBancaria cuentaBancaria = new CuentaBancaria();
		Connection cn = null;
		
		try {
			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT NroCuenta, CodTipoCuenta, CodCliente, CBU, Saldo, Fecha_alta, Estado FROM cuentas WHERE CBU = ?";
			
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setString(1, cbu);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				
				INegocioTipoCuenta negocioTipoCuenta = new NegocioTipoCuenta();
				INegocioCliente clienteNegocio = new NegocioCliente();
				
				cuentaBancaria = new CuentaBancaria();
				cuentaBancaria.setNroCuenta(rs.getInt("NroCuenta"));
				cuentaBancaria.setCBU(rs.getString("CBU"));
				cuentaBancaria.setSaldo(rs.getBigDecimal("Saldo"));
				cuentaBancaria.setFecha_alta(rs.getDate("Fecha_alta"));
				cuentaBancaria.setEstado(rs.getBoolean("Estado"));
				
				TipoCuenta tipoCuenta = new TipoCuenta();
				tipoCuenta = negocioTipoCuenta.obtenerPorCod(rs.getString("CodTipoCuenta").charAt(0));
				cuentaBancaria.setTipoCuenta(tipoCuenta);
				
				Cliente cliente = new Cliente();
				cliente = clienteNegocio.buscarClientePorCodigo(rs.getString("CodCliente"));
				cuentaBancaria.setCliente(cliente);			
				
			}
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		
		return cuentaBancaria;
	}
	
	public boolean modificarSaldoEnCuenta(BigDecimal monto, int numeroCuenta)
	{
		//UPDATE cuentas SET Saldo = ? WHERE NroCuenta = ?
		Connection cn = null;
        int filasAfectadas = 0;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            cn.setAutoCommit(true);
            String query = "UPDATE cuentas SET Saldo = ? WHERE NroCuenta = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setBigDecimal(1, monto);
            pst.setInt(2, numeroCuenta);
            filasAfectadas = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null)
                    cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return filasAfectadas > 0;	
	}
}

