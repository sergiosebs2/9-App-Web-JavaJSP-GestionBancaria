package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DaoInterfaz.IDaoTipoCuenta;
import Entidades.TipoCuenta;

public class DaoTipoCuenta implements IDaoTipoCuenta{

	public TipoCuenta obtenerPorCod(char codSolicitado) {
		TipoCuenta tipoCuenta = new TipoCuenta();
        Connection cn = null;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT CodTipoCuenta, Descripcion FROM tipocuentas WHERE CodTipoCuenta = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, String.valueOf(codSolicitado));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
            	tipoCuenta.setCodTipoCuenta(rs.getString("CodTipoCuenta").charAt(0));
                tipoCuenta.setDescripcion(rs.getString("Descripcion"));
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
        return tipoCuenta;
	}
	
	public ArrayList<TipoCuenta> obtenerTiposCuenta() {
		ArrayList<TipoCuenta> lTipoCuentas = new ArrayList<TipoCuenta>();
		Connection cn = null;
		
		try{
		   cn = Conexion.getConexion().getSQLConexion();
		   String query = "SELECT * FROM tipocuentas";
		   PreparedStatement pst = cn.prepareStatement(query);
		   ResultSet rs = pst.executeQuery(query);
		   
		   while(rs.next()){
		      TipoCuenta tipos = new TipoCuenta();
		      tipos.setCodTipoCuenta(rs.getString("CodTipoCuenta").charAt(0));
		      tipos.setDescripcion(rs.getString("Descripcion"));
		      lTipoCuentas.add(tipos);
		    }
		}
		catch (Exception e){
		 e.printStackTrace();
	}
		finally {
	    	try {
				cn.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lTipoCuentas;
	}
	
}
