package Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DaoInterfaz.IDaoUsuario;
import Entidades.*;
import Negocio.*;
import NegocioInterfaz.*;


public class DaoUsuario implements IDaoUsuario{

	public int Agregar(Usuario usuario) {
	    Connection cn = null;
	    int idGenerado = 0;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "INSERT INTO usuarios(IdTipoUsuario, NombreUsuario, Contrasena) VALUES (?, ?, ?)";
	        PreparedStatement pst = cn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        pst.setString(1, "C");
	        pst.setString(2, usuario.getNombreUsuario());
	        pst.setString(3, usuario.getContrasena());

	        int filas = pst.executeUpdate();
	        if (filas > 0) {
	            ResultSet rs = pst.getGeneratedKeys();
	            if (rs.next()) {
	                idGenerado = rs.getInt(1);
	                usuario.setIdUsuario(idGenerado);
	            }
	            cn.commit();
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

	    return idGenerado;
	}
        
        

	public ArrayList<Usuario> Listar() {
			
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			Connection cn = null;
			try{
				cn = Conexion.getConexion().getSQLConexion();
	            String query = "SELECT U.IdUsuario, U.IdTipoUsuario, T.Descripcion, C.CodCliente, U.NombreUsuario, U.Contrasena, U.Estado\r\n"
	            		+ "FROM usuarios U\r\n"
	            		+ "INNER JOIN tipousuarios T ON U.IdTipoUsuario = T.IdTipoUsuario\r\n"
	            		+ "INNER JOIN clientes C ON C.IdUsuario = U.IdUsuario\r\n"
	            		+ "WHERE U.Estado  = 1;";
	            PreparedStatement pst = cn.prepareStatement(query);		       
				ResultSet rs = pst.executeQuery();
				
				
				while(rs.next()){
					
					Usuario us = new Usuario();
					us.setIdUsuario(rs.getInt("IdUsuario"));
				    TipoUsuario tipo = new TipoUsuario();
				    tipo.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
				    tipo.setDescripcion(rs.getString("Descripcion")); 			   
				    us.setTipoUsuario(tipo);
				    us.setClienteAsociado(rs.getInt("CodCliente"));
				    us.setNombreUsuario(rs.getString("NombreUsuario"));
				    us.setContrasena(rs.getString("Contrasena"));
				    us.setEstado(rs.getBoolean("Estado"));				
					lista.add(us);			}
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			
				 try {
			            if (cn != null)
			                cn.close();
			        } catch (Exception e2) {
			            e2.printStackTrace();
			        }
			}		
			return lista;
		}

	public Usuario obtenerPorNombre(String nombreUsuario) {
		Usuario usuarioSolicitado = new Usuario();
        Connection cn = null;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT IdUsuario, IdTipoUsuario, NombreUsuario, Contrasena, Estado FROM usuarios WHERE NombreUsuario = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, nombreUsuario);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
            	usuarioSolicitado.setIdUsuario(rs.getInt("IdUsuario"));
            	usuarioSolicitado.setNombreUsuario(nombreUsuario);
            	usuarioSolicitado.setContrasena(rs.getString("Contrasena"));
            	usuarioSolicitado.setEstado(rs.getBoolean("Estado"));
            	
            	INegocioTipoUsuario negocioTipoUsuario = new NegocioTipoUsuario();
            	usuarioSolicitado.setTipoUsuario(negocioTipoUsuario.obtenerPorId(rs.getString("IdTipoUsuario").charAt(0)));
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
        return usuarioSolicitado;
	}

	@Override
	public boolean existeNombreUsuario(String nombreUsuario) {
		boolean existe = false;
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT COUNT(*) FROM usuarios WHERE NombreUsuario = ? AND Estado = 1";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setString(1, nombreUsuario);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            int cantidad = rs.getInt(1);
	            existe = cantidad > 0;
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

		return existe;
	}
	
	public Usuario listarID(int num) {
		Usuario us = new Usuario();
        Connection cn = null;

        try {
           cn = Conexion.getConexion().getSQLConexion();
           String query = "SELECT U.IdUsuario, U.IdTipoUsuario, T.Descripcion , C.CodCliente, U.NombreUsuario, U.Contrasena, U.Estado\r\n"
           		+ "FROM usuarios U \r\n"
           		+ "INNER JOIN tipousuarios T ON U.IdTipoUsuario = T.IdTipoUsuario\r\n"
           		+ "INNER JOIN  clientes C ON C.IdUsuario = U.IdUsuario\r\n"
           		+ "WHERE U.IdUsuario = ? AND U.Estado = 1";
        		   PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {            	
				us.setIdUsuario(rs.getInt("IdUsuario"));
			    TipoUsuario tipo = new TipoUsuario();
			    tipo.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
			    tipo.setDescripcion(rs.getString("Descripcion")); 			   
			    us.setTipoUsuario(tipo);			  
			    us.setNombreUsuario(rs.getString("NombreUsuario"));
			    us.setContrasena(rs.getString("Contrasena"));
			    us.setEstado(rs.getBoolean("Estado"));
			    us.setClienteAsociado(rs.getInt("CodCliente"));           }

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
        return us;	
}
	
	public Usuario listarPorCliente(int num) {
		Usuario us = new Usuario();
        Connection cn = null;

        try {
           cn = Conexion.getConexion().getSQLConexion();
           String query = "SELECT U.IdUsuario, U.IdTipoUsuario, T.Descripcion , C.CodCliente, U.NombreUsuario, U.Contrasena, U.Estado\r\n"
           		+ "FROM usuarios U\r\n"
           		+ "INNER JOIN tipousuarios T ON U.IdTipoUsuario = T.IdTipoUsuario\r\n"
           		+ "INNER JOIN  clientes C ON C.IdUsuario = U.IdUsuario\r\n"
           		+ "WHERE C.CodCliente = ? AND U.Estado = 1";
        		   PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, num);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {            	
				us.setIdUsuario(rs.getInt("IdUsuario"));
			    TipoUsuario tipo = new TipoUsuario();
			    tipo.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
			    tipo.setDescripcion(rs.getString("Descripcion")); 			   
			    us.setTipoUsuario(tipo);			  
			    us.setNombreUsuario(rs.getString("NombreUsuario"));
			    us.setContrasena(rs.getString("Contrasena"));
			    us.setEstado(rs.getBoolean("Estado"));
			    us.setClienteAsociado(rs.getInt("CodCliente"));           }

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
        return us;	
}
	
	public int cambioContrasena(int id, String nueva)
	{		
        Connection cn = null;
        int filasAfectadas =0;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            cn.setAutoCommit(true);
            String query = "UPDATE Usuarios SET Contrasena = ? WHERE IdUsuario = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, nueva);
            pst.setInt(2, id);
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
        return filasAfectadas;	
	}
	
}
