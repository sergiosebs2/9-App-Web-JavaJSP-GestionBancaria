	package Dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DaoInterfaz.IDaoInforme;
import Entidades.Cliente;
import Entidades.Informe;

public class DaoInforme implements IDaoInforme{
	
	public Informe obtenerInforme(Date fechaDesde, Date fechaHasta) {
		
		Informe inf = new Informe();
		inf.setFechaInicio(fechaDesde);
        inf.setFechaFinal(fechaHasta);
        
        inf.setCuentasDadasAlta(obtenerCuentasDadasAlta(fechaDesde, fechaHasta));
        setCuentasAltaTipos(inf, fechaDesde, fechaHasta);
        setCuentasBajaTipos(inf, fechaDesde, fechaHasta);
        inf.setCuentasBajas(obtenerCuentasDadasBaja(fechaDesde, fechaHasta));
        inf.setClientesNuevos(obtenerClientesNuevos(fechaDesde, fechaHasta));
        inf.setPrestamosActivos(obtenerPrestamosActivos(fechaDesde, fechaHasta));
        inf.setPrestamosSaldados(obtenerPrestamosSaldados(fechaDesde, fechaHasta));
        inf.setPrestamosAtrasados(obtenerPrestamosAtrasados(fechaDesde, fechaHasta));
        inf.setCumplibilidadPrestamos(obtenerCumplibilidadPrestamos(fechaDesde, fechaHasta));
        inf.setSaldoPromedioPorCuenta(obtenerPromedioSaldos(fechaHasta));
        inf.setSaldoTotalBanco(obtenerSaldoTotalBanco(fechaHasta));
        inf.setTransferenciasRealizadas(obtenerTransferencias(fechaDesde, fechaHasta));
        inf.setMovimientosTotales(obtenerMovimientosTotales(fechaDesde, fechaHasta));
        
		return inf;
	}
	
	private int obtenerCuentasDadasAlta(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int cuentas = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT COUNT(*)\r\n"
        			+ "FROM cuentas\r\n"
        			+ "WHERE Fecha_alta BETWEEN ? AND ? AND ESTADO > 0;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			cuentas = (rs.getInt(1));
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
	
	private Informe setCuentasBajaTipos(Informe informe, Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT \r\n"
        			+ "SUM(CASE WHEN tc.CodTipoCuenta = 'A'   THEN 1 ELSE 0 END) AS 'Caja de ahorro',\r\n"
        			+ "SUM(CASE WHEN tc.CodTipoCuenta = 'C' THEN 1 ELSE 0 END) AS 'Cuenta corriente'\r\n"
        			+ "FROM cuentas c INNER JOIN tipocuentas tc ON c.CodTipoCuenta = tc.CodTipoCuenta\r\n"
        			+ "WHERE c.Fecha_alta BETWEEN ? AND ? AND c.Estado = 0;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			informe.setCuentasBajaCajaAhorro(rs.getInt(1));
       			informe.setCuentasBajaCorriente(rs.getInt(2));
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
		
		return informe;
		
	}
	
	private Informe setCuentasAltaTipos(Informe informe, Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT \r\n"
        			+ "SUM(CASE WHEN tc.CodTipoCuenta = 'A'   THEN 1 ELSE 0 END) AS 'Caja de ahorro',\r\n"
        			+ "SUM(CASE WHEN tc.CodTipoCuenta = 'C' THEN 1 ELSE 0 END) AS 'Cuenta corriente'\r\n"
        			+ "FROM cuentas c INNER JOIN tipocuentas tc ON c.CodTipoCuenta = tc.CodTipoCuenta\r\n"
        			+ "WHERE c.Fecha_alta BETWEEN ? AND ? AND c.Estado > 0;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			informe.setCuentasAltaCajaAhorro(rs.getInt(1));
       			informe.setCuentasAltaCorriente(rs.getInt(2));
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
		
		return informe;
		
	}
	
	private int obtenerCuentasDadasBaja(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int cuentasBajas = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT COUNT(*)\r\n"
        			+ "FROM cuentas\r\n"
        			+ "WHERE Fecha_alta BETWEEN ? AND ? AND ESTADO = 0;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			cuentasBajas = (rs.getInt(1));;
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
		
		return cuentasBajas;
	}
	
	private int obtenerClientesNuevos(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int clientesNuevos = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT COUNT(*) FROM clientes\r\n"
        			+ "WHERE FechaDadoAlta BETWEEN ? AND ? AND ESTADO = 1";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			clientesNuevos = (rs.getInt(1));;
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
		
		return clientesNuevos;
	}
	
	public ArrayList<Cliente> obtenerTablaClientesNuevos(Date fechaDesde, Date fechaHasta) {
	    ArrayList<Cliente> clientes = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT codCliente, Nombre, Apellido, FechaDadoAlta FROM clientes WHERE fechaDadoAlta BETWEEN ? AND ? AND estado = 1";

	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setDate(1, fechaDesde);
	        pst.setDate(2, fechaHasta);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Cliente c = new Cliente();
	            c.setCodCliente(rs.getInt("codCliente"));
	            c.setNombre(rs.getString("Nombre"));
	            c.setApellido(rs.getString("Apellido"));
	            c.setFechaDadoAlta(rs.getTimestamp("FechaDadoAlta").toLocalDateTime());
	            clientes.add(c);
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

	    return clientes;
	}
	
	private int obtenerPrestamosActivos(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int prestamos = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	CallableStatement cst = cn.prepareCall("{CALL prestamosActivos(?, ?)}");
       		cst.setDate(1, fechaDesde);
       		cst.setDate(2, fechaHasta);
       		
       		ResultSet rs = cst.executeQuery();
       		
       		if (rs.next()) {
       			prestamos = rs.getInt(1);
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
		
		return prestamos;
	}
	
	private int obtenerPrestamosSaldados(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int prestamosSaldados = 0;
		
	    try {
	    	cn = Conexion.getConexion().getSQLConexion();
	    	String query = "SELECT COUNT(*)\r\n"
	    			+ "FROM ( SELECT P.CodPrestamo, MAX(M.Fecha) AS FechaUltimoPago FROM Prestamos P\r\n"
	    			+ "JOIN Movimientos M ON P.NroCuentaAsociado = M.NroCuentaAsociado\r\n"
	    			+ "WHERE P.Deuda = 0 AND M.CodTipoMovimiento = 'PP'\r\n"
	    			+ "GROUP BY P.CodPrestamo) Ult WHERE FechaUltimoPago BETWEEN ? AND ?;";
	    			
	   		PreparedStatement pst = cn.prepareStatement(query);
	   		pst.setDate(1, fechaDesde);
	   		pst.setDate(2, fechaHasta);
	   		ResultSet rs = pst.executeQuery();
	   		
	   		if (rs.next()) {
	   			prestamosSaldados = (rs.getInt(1));;
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
		
		return prestamosSaldados;
	}
	
	private int obtenerPrestamosAtrasados(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int atrasados = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	CallableStatement cst = cn.prepareCall("{CALL PrestamosAtrasados(?, ?)}");
       		cst.setDate(1, fechaDesde);
       		cst.setDate(2, fechaHasta);
       		
       		ResultSet rs = cst.executeQuery();
       		
       		if (rs.next()) {
       			atrasados = (rs.getInt(1));;
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
		
		return atrasados;
	}
	
	public double obtenerCumplibilidadPrestamos(Date fechaDesde, Date fechaHasta) {
		
		double cumplibilidad = 0;
		double cumplibilidadResta = 0;
		double cumplibilidadDivision = 0;
		int activos = obtenerPrestamosActivos(fechaDesde, fechaHasta);
		int atrasados = obtenerPrestamosAtrasados(fechaDesde, fechaHasta);
		
		cumplibilidadResta = (activos - atrasados);
		cumplibilidadDivision = (cumplibilidadResta / activos);
		cumplibilidad = cumplibilidadDivision * 100;
		
		return cumplibilidad;
	}
	
	private BigDecimal obtenerPromedioSaldos(Date fechaHasta){
		
		Connection cn = null;
		BigDecimal atrasados = new BigDecimal(0);
		
	    try {
	    	cn = Conexion.getConexion().getSQLConexion();
	    	String query = "SELECT AVG( c.Saldo - IFNULL((SELECT SUM(m.Importe) FROM Movimientos m \r\n"
	    			+ "WHERE m.NroCuentaAsociado = c.NroCuenta AND m.Fecha > ? ), 0))\r\n"
	    			+ "AS SaldoPromedioCuentas FROM Cuentas c;";
	    			
	   		PreparedStatement pst = cn.prepareStatement(query);
	   		pst.setDate(1, fechaHasta);
	   		ResultSet rs = pst.executeQuery();
	   		
	   		if (rs.next()) {
	   			atrasados = (rs.getBigDecimal(1));;
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
		
		return atrasados;
	}
	
	private BigDecimal obtenerSaldoTotalBanco(Date fechaHasta){
		
		Connection cn = null;
		BigDecimal saldoBanco = new BigDecimal(0);
		
	    try {
	    	cn = Conexion.getConexion().getSQLConexion();
	    	String query = "SELECT SUM(c.Saldo - IFNULL((SELECT SUM(m.Importe)\r\n"
	    			+ "FROM Movimientos m WHERE m.NroCuentaAsociado = c.NroCuenta AND m.Fecha > ? ), 0))\r\n"
	    			+ "AS SaldoTotalBanco FROM Cuentas c;";
	    			
	   		PreparedStatement pst = cn.prepareStatement(query);
	   		pst.setDate(1, fechaHasta);
	   		ResultSet rs = pst.executeQuery();
	   		
	   		if (rs.next()) {
	   			saldoBanco = (rs.getBigDecimal(1));;
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
		
		return saldoBanco;
	}
	
	private int obtenerTransferencias(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int transferencias = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT COUNT(*) from movimientos\r\n"
        			+ "WHERE CodTipoMovimiento = 'TT' \r\n"
        			+ "AND Fecha BETWEEN ? AND ?;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			transferencias = (rs.getInt(1));
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
		
		return transferencias;
	}
	
	private int obtenerMovimientosTotales(Date fechaDesde, Date fechaHasta) {
		
		Connection cn = null;
		int movimientos = 0;
		
        try {
        	cn = Conexion.getConexion().getSQLConexion();
        	String query = "SELECT COUNT(*) from movimientos WHERE Fecha BETWEEN ? AND ?;";
        			
       		PreparedStatement pst = cn.prepareStatement(query);
       		pst.setDate(1, fechaDesde);
       		pst.setDate(2, fechaHasta);
       		ResultSet rs = pst.executeQuery();
       		
       		if (rs.next()) {
       			movimientos = (rs.getInt(1));
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
		
		return movimientos;
	}
	
}
