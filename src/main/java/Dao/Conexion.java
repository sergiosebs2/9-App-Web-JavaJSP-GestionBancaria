package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String DB_NAME = "tpintegrador"; 
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "rootser";

    private static Conexion instancia;

    private Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getConexion() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getSQLConexion() {
        try {
            Connection nuevaConexion = DriverManager.getConnection(URL, USER, PASSWORD);
            nuevaConexion.setAutoCommit(false);
            return nuevaConexion;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}