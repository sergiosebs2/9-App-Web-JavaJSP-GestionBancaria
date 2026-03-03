package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DaoInterfaz.IDaoCuotas;
import Entidades.Cuotas;

public class DaoCuotas implements IDaoCuotas {
	
	public ArrayList<Cuotas> obtenerCuotasDelPrestamo (int idPrestamo){
		
		ArrayList<Cuotas> cuentas = new ArrayList<Cuotas>();
		Connection cn = null;
		
		try {			
			cn = Conexion.getConexion().getSQLConexion();
			String query = "SELECT CodCuota, NumeroCuota, MontoCuota, EstadoPago FROM Cuotas WHERE CodPrestamo = ?";
			
			PreparedStatement pst = cn.prepareStatement(query);
			pst.setInt(1, idPrestamo);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
											
				Cuotas cuota = new Cuotas();
				cuota.setCodigoCuota(rs.getInt("CodCuota"));
				cuota.setNumeroCuota(rs.getInt("NumeroCuota"));
				cuota.setMontoCuota(rs.getDouble("MontoCuota"));
				cuota.setEstado(rs.getBoolean("EstadoPago"));		
				
				cuentas.add(cuota);
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
	
	public int pagarCuota(int idprestamo, int idCuota) {
	    Connection cn = null;
	    PreparedStatement pst = null;
	    int filasAfectadas = 0;

	    try {
	       
	        cn = Conexion.getConexion().getSQLConexion();
	        if (cn == null) {
	            System.err.println("Error: No se pudo establecer conexión con la base de datos");
	            return 0;
	        }     	        
	        String query = "UPDATE Cuotas SET EstadoPago = TRUE, FechaPago = NOW() WHERE CodPrestamo = ? AND NumeroCuota = ? AND EstadoPago = FALSE";
	        
	        pst = cn.prepareStatement(query);
	        
	       
	        pst.setInt(1, idprestamo);
	        pst.setInt(2, idCuota);

	      
	        filasAfectadas = pst.executeUpdate();
	        cn.commit();

	    } catch (SQLException e) {
	       
	        try {
	            if (cn != null) cn.rollback();
	        } catch (SQLException ex) {
	            System.err.println("Error al hacer rollback:");
	            ex.printStackTrace();
	        }
	        System.err.println("Error al procesar pago de cuota:");
	        e.printStackTrace();
	    } finally {
	        
	        try {
	            if (pst != null) pst.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar PreparedStatement:");
	            e.printStackTrace();
	        }
	        try {
	            if (cn != null) cn.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar Connection:");
	            e.printStackTrace();
	        }
	    }

	    return filasAfectadas;
	}
}
