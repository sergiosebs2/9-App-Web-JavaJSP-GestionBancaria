package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DaoInterfaz.IDaoTipoMovimiento;
import Entidades.TipoMovimiento;

public class DaoTipoMovimiento implements IDaoTipoMovimiento{

	public TipoMovimiento obtenerPorCod(String codigoSolicitado) {
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
        Connection cn = null;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT CodTipoMovimiento, Descripcion FROM tipomovimientos WHERE CodTipoMovimiento = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, codigoSolicitado);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
            	tipoMovimiento.setCodTipoMovimiento(rs.getString("CodTipoMovimiento"));
                tipoMovimiento.setDescripcion(rs.getString("Descripcion"));
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
        return tipoMovimiento;
	}
	
}
