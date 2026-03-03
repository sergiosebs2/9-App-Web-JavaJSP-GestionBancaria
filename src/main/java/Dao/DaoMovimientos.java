package Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DaoInterfaz.IDaoMovimientos;
import Entidades.CuentaBancaria;
import Entidades.Movimiento;
import Entidades.TipoMovimiento;

public class DaoMovimientos implements IDaoMovimientos{
	 
	public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int nroCuenta) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        Connection cn = null;

        try {
            cn = Conexion.getConexion().getSQLConexion();

            String query = "SELECT m.CodMovimiento, m.CodTipoMovimiento, m.NroCuentaAsociado, m.Fecha, m.Detalle, m.Importe, " +
                           "tm.Descripcion AS TipoDescripcion " +
                           "FROM movimientos m " +
                           "JOIN tipomovimientos tm ON m.CodTipoMovimiento = tm.CodTipoMovimiento " +
                           "WHERE m.NroCuentaAsociado = ? " +
                           "ORDER BY m.Fecha DESC";

            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, nroCuenta);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Movimiento mov = new Movimiento();
                mov.setCodMovimiento(rs.getInt("CodMovimiento"));

               
                TipoMovimiento tipoMov = new TipoMovimiento();
                tipoMov.setCodTipoMovimiento(rs.getString("CodTipoMovimiento"));
                tipoMov.setDescripcion(rs.getString("TipoDescripcion"));
                mov.setTipoMovimiento(tipoMov);

                
                CuentaBancaria cuenta = new CuentaBancaria();
                cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado"));
                mov.setCuentaBancariaAsociada(cuenta);
                mov.setFecha(rs.getDate("Fecha"));
                mov.setDetalle(rs.getString("Detalle"));
                mov.setImporte(rs.getBigDecimal("Importe"));
                movimientos.add(mov);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) cn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return movimientos;
    }
	
	public ArrayList<Movimiento> obtenerMovimientosPorTipo(int nroCuenta, String codTipoMovimiento) {
		ArrayList<Movimiento> movimientos = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();

	        String query = "SELECT m.CodMovimiento, m.CodTipoMovimiento, m.NroCuentaAsociado, m.Fecha, m.Detalle, m.Importe, " +
	                       "tm.Descripcion AS TipoDescripcion " +
	                       "FROM movimientos m " +
	                       "JOIN tipomovimientos tm ON m.CodTipoMovimiento = tm.CodTipoMovimiento " +
	                       "WHERE m.NroCuentaAsociado = ? AND m.CodTipoMovimiento = ? " +
	                       "ORDER BY m.Fecha DESC";

	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, nroCuenta);
	        pst.setString(2, codTipoMovimiento);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Movimiento mov = new Movimiento();
	            mov.setCodMovimiento(rs.getInt("CodMovimiento"));

	            TipoMovimiento tipoMov = new TipoMovimiento();
	            tipoMov.setCodTipoMovimiento(rs.getString("CodTipoMovimiento"));
	            tipoMov.setDescripcion(rs.getString("TipoDescripcion"));
	            mov.setTipoMovimiento(tipoMov);

	            CuentaBancaria cuenta = new CuentaBancaria();
	            cuenta.setNroCuenta(rs.getInt("NroCuentaAsociado"));
	            mov.setCuentaBancariaAsociada(cuenta);
	            mov.setFecha(rs.getDate("Fecha"));
	            mov.setDetalle(rs.getString("Detalle"));
	            mov.setImporte(rs.getBigDecimal("Importe"));
	            movimientos.add(mov); 
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (cn != null) cn.close();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }

	    return movimientos;
	
	}
	public boolean crearMovimiento(String tipoMovimiento, int numeroCuenta, String detalle, BigDecimal importe)
	{
		Connection cn = null;
		int filas = 0;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO movimientos (CodTipoMovimiento, NroCuentaAsociado, Detalle, Importe) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(query);
                    
            	pst.setString(1, tipoMovimiento);
            	pst.setInt(2, numeroCuenta);
            	pst.setString(3, detalle);
            	pst.setBigDecimal(4, importe);
            	filas = pst.executeUpdate();
    	        cn.commit();
            	          

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
        
        return filas>0;
	}
}