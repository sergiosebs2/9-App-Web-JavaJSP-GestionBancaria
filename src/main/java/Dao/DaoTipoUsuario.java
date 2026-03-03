package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DaoInterfaz.IDaoTipoUsuario;
import Entidades.TipoUsuario;

public class DaoTipoUsuario implements IDaoTipoUsuario {

	public TipoUsuario obtenerPorId(char IdSolicitado) {
		TipoUsuario tipoUsuario = new TipoUsuario();
        Connection cn = null;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT IdTipoUsuario, Descripcion FROM tipousuarios WHERE IdTipoUsuario = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, String.valueOf(IdSolicitado));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                tipoUsuario.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
                tipoUsuario.setDescripcion(rs.getString("Descripcion"));
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
        return tipoUsuario;
	}
	
}
