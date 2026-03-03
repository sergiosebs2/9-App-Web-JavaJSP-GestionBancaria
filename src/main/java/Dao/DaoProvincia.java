package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DaoInterfaz.IDaoProvincia;
import Entidades.Provincia;

public class DaoProvincia implements IDaoProvincia{

	@Override
	public ArrayList<Provincia> obtenerProvincias() {
		ArrayList<Provincia> lProvincias = new ArrayList<Provincia>();
		Connection cn = null;
		
		try{
		   cn = Conexion.getConexion().getSQLConexion();
		   String query = "SELECT * FROM provincias";
		   Statement st = cn.createStatement();
		   ResultSet rs = st.executeQuery(query);
		   while(rs.next()){
		      Provincia p = new Provincia();
		      p.setIdProvincia(rs.getInt("IdProvincia"));
		      p.setDescripcion(rs.getString("Descripcion"));
		      lProvincias.add(p);
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
		return lProvincias;
	}

}
