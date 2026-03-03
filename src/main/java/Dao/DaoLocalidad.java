package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DaoInterfaz.IDaoLocalidad;
import Entidades.Localidad;

public class DaoLocalidad implements IDaoLocalidad{

	@Override
	public ArrayList<Localidad> obtenerLocalidadesPorProvincia(int id) {
		ArrayList<Localidad> lLocalidades = new ArrayList<Localidad>();
		Connection cn = null;
		
		try{
		   cn = Conexion.getConexion().getSQLConexion();
		   String query = "SELECT L.IdLocalidad, L.Descripcion FROM localidades L " +
	                 "INNER JOIN provincias P ON L.IdProvincia = P.IdProvincia " +
	                 "WHERE L.IdProvincia = ?";
		   PreparedStatement pst = cn.prepareStatement(query);
	           pst.setInt(1, id);
		   ResultSet rs = pst.executeQuery();
		   
		   while(rs.next()){
			    Localidad l = new Localidad();
		            l.setIdLocalidad(rs.getInt("IdLocalidad"));
			    l.setDescripcion(rs.getString("Descripcion"));
			    lLocalidades.add(l);
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
		return lLocalidades;
	}

}
