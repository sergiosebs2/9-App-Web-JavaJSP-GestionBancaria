package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import DaoInterfaz.IDaoCliente;

import Entidades.Cliente;
import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Provincia;
import Entidades.TipoUsuario;
import Entidades.Usuario;



public class DaoCliente implements IDaoCliente{

    public Cliente obtenerClientePorCodigo(String codigo) {
        Cliente cliente = null;
        Connection cn = null;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT CodCliente, c.IdUsuario AS IdUsuario, c.IdNacionalidad AS IdNacionalidad, n.Descripcion AS Nacionalidad, \r\n"
            		+ " c.IdProvincia AS IdProvincia, p.Descripcion AS Provincia, c.IdLocalidad AS IdLocalidad, l.Descripcion AS Descripcion, DNI, CUIL, Nombre, Apellido, Sexo, \r\n"
            		+ " Direccion, FechaNacimiento, Telefono, CorreoElectronico, c.Estado AS EstadoCliente, u.IdTipoUsuario AS IdTipoUsuario, \r\n"
            		+ " tu.Descripcion AS TipoUsuario, u.NombreUsuario AS NombreUsuario, u.Contrasena AS Contrasena, u.Estado AS EstadoUsuario\r\n"
            		+ " FROM clientes c\r\n"
            		+ " INNER JOIN usuarios u ON c.IdUsuario = u.IdUsuario\r\n"
            		+ " INNER JOIN tipousuarios tu ON tu.IdTipoUsuario = u.IdTipoUsuario\r\n"
            		+ " INNER JOIN nacionalidades n ON n.IdNacionalidad = c.IdNacionalidad\r\n"
            		+ " INNER JOIN provincias p ON p.IdProvincia = c.IdProvincia\r\n"
            		+ " INNER JOIN localidades l ON l.IdLocalidad = c.IdLocalidad\r\n"
            		+ " WHERE CodCliente = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(codigo));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("CodCliente"));
                cliente.setDni(rs.getString("DNI"));
                cliente.setCuil(rs.getString("CUIL"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setSexo(rs.getString("Sexo").charAt(0));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setCorreoElectronico(rs.getString("CorreoElectronico"));
                cliente.setEstado(rs.getBoolean("EstadoCliente"));
                
                TipoUsuario tipoUsuarioAux = new TipoUsuario();
                tipoUsuarioAux.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
                tipoUsuarioAux.setDescripcion(rs.getString("TipoUsuario"));
                
                Usuario usuarioAux = new Usuario();
                usuarioAux.setTipoUsuario(tipoUsuarioAux);
                usuarioAux.setIdUsuario(rs.getInt("IdUsuario"));
                usuarioAux.setNombreUsuario(rs.getString("NombreUsuario"));
                usuarioAux.setContrasena(rs.getString("Contrasena"));
                usuarioAux.setEstado(rs.getBoolean("EstadoUsuario"));
                cliente.setUsuario(usuarioAux);
                
                Nacionalidad nacionalidadAux = new Nacionalidad();
                nacionalidadAux.setIdNacionalidad(rs.getInt("IdNacionalidad"));
                nacionalidadAux.setDescripcion(rs.getString("Nacionalidad"));
                cliente.setNacionalidad(nacionalidadAux);
                
                Provincia provinciaAux = new Provincia();
                provinciaAux.setIdProvincia(rs.getInt("IdProvincia"));
                provinciaAux.setDescripcion(rs.getString("Provincia"));
                cliente.setProvincia(provinciaAux);
                
                Localidad localidadAux = new Localidad();
                localidadAux.setProvincia(provinciaAux);
                localidadAux.setIdLocalidad(rs.getInt("IdLocalidad"));
                localidadAux.setDescripcion(rs.getString("Localidad"));
                cliente.setLocalidad(localidadAux);
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
        return cliente;
    }

	
	public boolean Agregar(Cliente cliente, int iDusuario) {
		Connection cn = null;
		int filas=0;

        try {
        	
            cn = Conexion.getConexion().getSQLConexion();
            String query = "INSERT INTO clientes(IdUsuario, IdNacionalidad, IdProvincia, IdLocalidad, DNI, CUIL, Nombre, Apellido, Sexo, Direccion, FechaNacimiento, Telefono, CorreoElectronico) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(query);
                    
            	pst.setInt(1, iDusuario);
            	pst.setInt(2, cliente.getNacionalidad().getIdNacionalidad());
            	pst.setInt(3, cliente.getProvincia().getIdProvincia());
            	pst.setInt(4, cliente.getLocalidad().getIdLocalidad());
            	pst.setString(5, cliente.getDni());
            	pst.setString(6, cliente.getCuil());
            	pst.setString(7, cliente.getNombre());
            	pst.setString(8, cliente.getApellido());
            	pst.setString(9, String.valueOf(cliente.getSexo()));  
            	pst.setString(10, cliente.getDireccion());  
            	pst.setDate(11, (java.sql.Date)cliente.getFechaNacimiento());  
            	pst.setString(12, cliente.getTelefono());  
            	pst.setString(13, cliente.getCorreoElectronico());
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

	
	public boolean Modificar(Cliente cliente) {
		
		return false;
	}

	
	public boolean Eliminar(int CodigoCliente) {
		
		Connection cn = null;
	    boolean eliminado = false;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        CallableStatement cs = cn.prepareCall("{ call EliminarCliente(?) }");
	        cs.setInt(1, CodigoCliente);

	        cs.executeUpdate();

	        cn.commit();
	        eliminado = true;

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

	    return eliminado;
	}


	public ArrayList<Cliente> Listar() {
		
		return null;
	}


	@Override
	public boolean existeDni(String dni) {
		boolean existe = false;
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT COUNT(*) FROM clientes WHERE DNI = ? AND Estado = 1";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setString(1, dni);
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


	
	public int obtenerIdUsuarioPorNombreUsuario(String usuario) {
		int idUsuario = 0;
	    Connection cn = null;
	    
	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT IdUsuario FROM usuarios WHERE NombreUsuario = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setString(1, usuario);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            idUsuario = rs.getInt("IdUsuario");
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
	    return idUsuario;
		
	}
	


	
	public Cliente obtenerClientePorIdUsuario(int idUsuario) {
		Cliente cliente = null;
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT CodCliente, c.IdUsuario AS IdUsuario, c.IdNacionalidad AS IdNacionalidad, n.Descripcion AS Nacionalidad, \r\n"
	                + " c.IdProvincia AS IdProvincia, p.Descripcion AS Provincia, c.IdLocalidad AS IdLocalidad, l.Descripcion AS Localidad, DNI, CUIL, Nombre, Apellido, Sexo, \r\n"
	                + " Direccion, FechaNacimiento, Telefono, CorreoElectronico, c.Estado AS EstadoCliente, u.IdTipoUsuario AS IdTipoUsuario, \r\n"
	                + " tu.Descripcion AS TipoUsuario, u.NombreUsuario AS NombreUsuario, u.Contrasena AS Contrasena, u.Estado AS EstadoUsuario\r\n"
	                + " FROM clientes c\r\n"
	                + " INNER JOIN usuarios u ON c.IdUsuario = u.IdUsuario\r\n"
	                + " INNER JOIN tipousuarios tu ON tu.IdTipoUsuario = u.IdTipoUsuario\r\n"
	                + " INNER JOIN nacionalidades n ON n.IdNacionalidad = c.IdNacionalidad\r\n"
	                + " INNER JOIN provincias p ON p.IdProvincia = c.IdProvincia\r\n"
	                + " INNER JOIN localidades l ON l.IdLocalidad = c.IdLocalidad\r\n"
	                + " WHERE c.IdUsuario = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, idUsuario);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente();
	            cliente.setCodCliente(rs.getInt("CodCliente"));
	            cliente.setDni(rs.getString("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));
	            cliente.setSexo(rs.getString("Sexo").charAt(0));
	            cliente.setDireccion(rs.getString("Direccion"));
	            cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            cliente.setTelefono(rs.getString("Telefono"));
	            cliente.setCorreoElectronico(rs.getString("CorreoElectronico"));
	            cliente.setEstado(rs.getBoolean("EstadoCliente"));
	            
	            TipoUsuario tipoUsuarioAux = new TipoUsuario();
	            tipoUsuarioAux.setIdTipoUsuario(rs.getString("IdTipoUsuario").charAt(0));
	            tipoUsuarioAux.setDescripcion(rs.getString("TipoUsuario"));
	            
	            Usuario usuarioAux = new Usuario();
	            usuarioAux.setTipoUsuario(tipoUsuarioAux);
	            usuarioAux.setIdUsuario(rs.getInt("IdUsuario"));
	            usuarioAux.setNombreUsuario(rs.getString("NombreUsuario"));
	            usuarioAux.setContrasena(rs.getString("Contrasena"));
	            usuarioAux.setEstado(rs.getBoolean("EstadoUsuario"));
	            cliente.setUsuario(usuarioAux);
	            
	            Nacionalidad nacionalidadAux = new Nacionalidad();
	            nacionalidadAux.setIdNacionalidad(rs.getInt("IdNacionalidad"));
	            nacionalidadAux.setDescripcion(rs.getString("Nacionalidad"));
	            cliente.setNacionalidad(nacionalidadAux);
	            
	            Provincia provinciaAux = new Provincia();
	            provinciaAux.setIdProvincia(rs.getInt("IdProvincia"));
	            provinciaAux.setDescripcion(rs.getString("Provincia"));
	            cliente.setProvincia(provinciaAux);
	            
	            Localidad localidadAux = new Localidad();
	            localidadAux.setProvincia(provinciaAux);
	            localidadAux.setIdLocalidad(rs.getInt("IdLocalidad"));
	            localidadAux.setDescripcion(rs.getString("Localidad"));
	            cliente.setLocalidad(localidadAux);
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

	    return cliente;
	}


	@Override
	public boolean existeCuil(String cuil) {
		boolean existe = false;
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT COUNT(*) FROM clientes WHERE CUIL = ? AND Estado = 1";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setString(1, cuil);
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
}