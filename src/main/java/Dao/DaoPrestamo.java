package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Statement;

import DaoInterfaz.IDaoPrestamo;
import Entidades.Cliente;
import Entidades.CuentaBancaria;
import Entidades.Prestamo;

public class DaoPrestamo implements IDaoPrestamo{

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPendientes() {
		ArrayList<Prestamo> lPrestamos = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, " +
	                   "NroCuentaAsociado, Fecha, " +
	                   "ImportePagar, ImportePedido, " +
	                   "PlazoMeses, PagoMensual, " +
	                   "CuotasTotales, Deuda, Estado " +
	                   "FROM prestamos WHERE Estado = 0 AND Deuda = 1";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamos.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamos;
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosPendientesPorNroCuenta(int nroCuenta) {
		ArrayList<Prestamo> lPrestamosCuenta = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, " +
	                   "NroCuentaAsociado, Fecha, " +
	                   "ImportePagar, ImportePedido, " +
	                   "PlazoMeses, PagoMensual, " +
	                   "CuotasTotales, Deuda, Estado " +
	                   "FROM prestamos WHERE NroCuentaAsociado = ? AND Estado = 0 AND Deuda = 1";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         pst.setInt(1, nroCuenta);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamosCuenta.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamosCuenta;
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosAceptados() {
		ArrayList<Prestamo> lPrestamosA = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, " +
	                   "NroCuentaAsociado, Fecha, " +
	                   "ImportePagar, ImportePedido, " +
	                   "PlazoMeses, PagoMensual, " +
	                   "CuotasTotales, Deuda, Estado " +
	                   "FROM prestamos WHERE Estado = 1 AND Deuda = 1";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamosA.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamosA;
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosRechazados() {
		ArrayList<Prestamo> lPrestamosR = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, " +
	                   "NroCuentaAsociado, Fecha, " +
	                   "ImportePagar, ImportePedido, " +
	                   "PlazoMeses, PagoMensual, " +
	                   "CuotasTotales, Deuda, Estado " +
	                   "FROM prestamos WHERE Estado = 0 AND Deuda = 0";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamosR.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamosR;
	}

	@Override
	public boolean rechazarPrestamo(int codPrestamo) {
		Connection cn = null;
	    boolean rechazo = false;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE prestamos SET Deuda = 0 WHERE CodPrestamo = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, codPrestamo);

	        int filasAfectadas = pst.executeUpdate();

	        if (filasAfectadas > 0) {
	            cn.commit(); 
	            rechazo = true;
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

	    return rechazo;
	}

	@Override
	public boolean aceptarPrestamo(int codPrestamo) {
		Connection cn = null;
	    boolean aceptacion = false;
	    
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        
	        CallableStatement cst = cn.prepareCall("{CALL aceptarPrestamo(?)}");
	        cst.setInt(1, codPrestamo);

	        int filasAfectadas = cst.executeUpdate();

	        if (filasAfectadas > 0) {
	            cn.commit(); 
	            aceptacion = true;
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
		return aceptacion;
	}
	
	public int agregarPrestamo(Prestamo pres){		
		
		    Connection cn = null;
		    int resultado = 0;
		    
		    try {
		        cn = Conexion.getConexion().getSQLConexion();
		        String query = "INSERT INTO prestamos (CodCliente, NroCuentaAsociado, ImportePagar, ImportePedido, "
		                     + "PlazoMeses, PagoMensual, CuotasTotales) "
		                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
		        
		        PreparedStatement pst = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		        
		        
		        pst.setInt(1, pres.getClienteAsociado().getCodCliente());
		        pst.setInt(2, pres.getCuentaAsociada().getNroCuenta());
		        pst.setBigDecimal(3, pres.getImportePagar());
		        pst.setBigDecimal(4, pres.getImporteSolicitado());
		        pst.setInt(5, pres.getPlazoMeses());
		        pst.setBigDecimal(6, pres.getPagoMensual());
		        pst.setInt(7, pres.getCuotasTotales());
		        
		        
		        int filasAfectadas = pst.executeUpdate();
		        
		        if (filasAfectadas > 0) {
		        	cn.commit();
		            ResultSet rs = pst.getGeneratedKeys();
		            if (rs.next()) {
		                pres.setCodPrestamo(rs.getInt(1));
		            }
		            resultado = 1;
		        }
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (cn != null) cn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return resultado;
		
	}

	@Override
	public ArrayList<Prestamo> obtenerPrestamosSaldados() {
		ArrayList<Prestamo> lPrestamosS = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, " +
	                   "NroCuentaAsociado, Fecha, " +
	                   "ImportePagar, ImportePedido, " +
	                   "PlazoMeses, PagoMensual, " +
	                   "CuotasTotales, Deuda, Estado " +
	                   "FROM prestamos WHERE Estado = 1 AND Deuda = 0";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamosS.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamosS;
	}

	public boolean saldarPrestamo(int codPrestamo) {
		Connection cn = null;
	    boolean saldado = false;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "UPDATE prestamos SET Deuda = 0 WHERE CodPrestamo = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, codPrestamo);

	        int filasAfectadas = pst.executeUpdate();

	        if (filasAfectadas > 0) {
	            cn.commit(); 
	            saldado = true;
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

	    return saldado;
	}
	
	public ArrayList<Prestamo> obtenerPrestamosAceptadosPorNroCuenta(int nroCuenta)
	{
		ArrayList<Prestamo> lPrestamosA = new ArrayList<>();
		Connection cn = null;
	  
	    try {
	    	
	    	 cn = Conexion.getConexion().getSQLConexion();
	    	 String query = "SELECT CodPrestamo, CodCliente, NroCuentaAsociado, Fecha, ImportePagar, ImportePedido, PlazoMeses, PagoMensual, CuotasTotales, Deuda, Estado FROM prestamos WHERE Estado = 1 AND Deuda = 1 AND NroCuentaAsociado = ?";
	    	 
	         PreparedStatement pst = cn.prepareStatement(query);
	         pst.setInt(1, nroCuenta);
	         ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Prestamo p = new Prestamo();
	            p.setCodPrestamo(rs.getInt("CodPrestamo"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente")); 
	            p.setClienteAsociado(cliente);
	            
	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado")); 
	            p.setCuentaAsociada(cuenta);
	            
	            p.setFechaSolicitado(rs.getDate("Fecha"));
	            p.setImportePagar(rs.getBigDecimal("ImportePagar"));
	            p.setImporteSolicitado(rs.getBigDecimal("ImportePedido"));
	            p.setPlazoMeses(rs.getInt("PlazoMeses"));
	            p.setPagoMensual(rs.getBigDecimal("PagoMensual"));
	            p.setCuotasTotales(rs.getInt("CuotasTotales"));
	            p.setDeuda(rs.getBoolean("Deuda"));
	            p.setEstado(rs.getBoolean("Estado"));
	            
	            lPrestamosA.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally {
	    	try {
	    		if (cn != null)
                    cn.close();
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    }

	    return lPrestamosA;
	}
}
