package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DaoInterfaz.IDaoNacionalidad;
import Entidades.Nacionalidad;

public class DaoNacionalidad implements IDaoNacionalidad{

	@Override
	public ArrayList<Nacionalidad> obtenerNacionalidades() {
		ArrayList<Nacionalidad> lNacionalidades = new ArrayList<Nacionalidad>();
		Connection cn = null;
		
		try{
		   cn = Conexion.getConexion().getSQLConexion();
		   String query = "SELECT * FROM nacionalidades";
		   Statement st = cn.createStatement();
		   ResultSet rs = st.executeQuery(query);
		   while(rs.next()){
		      Nacionalidad n = new Nacionalidad();
		      n.setIdNacionalidad(rs.getInt("IdNacionalidad"));
		      n.setDescripcion(rs.getString("Descripcion"));
		      lNacionalidades.add(n);
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
		return lNacionalidades;
	}

}
