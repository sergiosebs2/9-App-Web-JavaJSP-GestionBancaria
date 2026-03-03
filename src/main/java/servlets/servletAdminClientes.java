package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Negocio.NegocioCliente;
import Negocio.NegocioCuentaBancaria;
import Negocio.NegocioInforme;
import Negocio.NegocioLocalidad;
import Negocio.NegocioNacionalidad;
import Negocio.NegocioPrestamo;
import Negocio.NegocioProvincia;
import Negocio.NegocioTipoCuenta;
import Negocio.NegocioUsuario;
import NegocioInterfaz.INegocioCuentaBancaria;
import NegocioInterfaz.INegocioInformes;
import NegocioInterfaz.INegocioPrestamo;
import NegocioInterfaz.INegocioTipoCuenta;
import NegocioInterfaz.INegocioCliente;
import Entidades.Cliente;
import Entidades.CuentaBancaria;
import Entidades.Informe;
import Entidades.Localidad;
import Entidades.Nacionalidad;
import Entidades.Prestamo;
import Entidades.Provincia;
import Entidades.TipoCuenta;
import Entidades.Usuario;
import Excepciones.noExisteClienteException;
import Excepciones.clienteBajaException;

@WebServlet("/servletAdminClientes")
public class servletAdminClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public servletAdminClientes() { 
        super();
    }

    NegocioCliente negocio = new NegocioCliente();
    INegocioCuentaBancaria cuentaBancaria = new NegocioCuentaBancaria();
    INegocioTipoCuenta tiposCuenta = new NegocioTipoCuenta();
    INegocioInformes informeNegocio = new NegocioInforme();
    NegocioUsuario negUsuario = new NegocioUsuario();
    ArrayList<Usuario> usuarios;
    Usuario usuarioA;
    INegocioPrestamo negPrestamo = new NegocioPrestamo();
    /*noExisteClienteException exc1 = new noExisteClienteException();
    clienteBajaException exc2 = new clienteBajaException();*/

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param = request.getParameter("param");
		String accion = request.getParameter("accion");
		
		
		if ("cargarLocalidades".equals(accion)) {
            int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));
            ArrayList<Localidad> localidades = new NegocioLocalidad().obtenerLocalidadesPorProvincia(idProvincia);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            for (Localidad l : localidades) {
                out.println("<option value='" + l.getIdLocalidad() + "'>" + l.getDescripcion() + "</option>");
            }
            return;
        }

		if ("1".equals(param) || "Agregar".equals(accion)) {
			cargarNacionalidades(request);
			cargarProvincias(request);
			
			request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
		}
		
		cargarTipoCuentas(request);
	    
		String codigoCliente = request.getParameter("codigoCliente");
		
		if(request.getParameter("btnBuscarClienteAEliminar") != null) 
		{
			buscarCliente(request, response, codigoCliente);
		}
		if(request.getParameter("btnConsultarCuentaBancarias") != null)
		{
			consultarCuentasCliente(request, response);
		}
		if(request.getParameter("btnAsignarCuenta") != null)
		{
			asignarCuentaBancaria(request, response);
		}
		if(request.getParameter("btnFiltrarporUsuario") != null)
		{
			cargarUsuariosYRedirigir(request, response);
		}
		if(request.getParameter("btnFiltrarporCliente") != null)
		{
			cargarUsuariosYRedirigir(request, response);
		}
		if(request.getParameter("btnBuscarTodo") != null)
		{
			cargarUsuariosYRedirigir(request, response);
		}	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String accion = request.getParameter("accion");
		 String param = request.getParameter("param");
		 
		 if ("Agregar".equals(accion)|| "1".equals(param))
		 {
			 agregarCliente(request, response);
		 }		 
		 
		 if ("eliminar".equals(accion))
		 {
			 eliminarCliente(request, response);
		 }
		        
		 if (request.getParameter("btnBuscar") != null)
		 {
		    String codigoCliente = request.getParameter("codigoCliente");
		    String tipoSeleccionado = request.getParameter("tipoDeCuentas");
		    String estadoSeleccionado = request.getParameter("estadoCuentas");
		    buscarCuentasFiltradas(request, response, codigoCliente, tipoSeleccionado, estadoSeleccionado);
		 }
		 
		 if (request.getParameter("btnGenerarInforme") != null)
		 {
			 generarInforme(request, response);
		 }
		    
		 if (request.getParameter("btnEditar") != null)
		 {
			 editarCuenta(request, response);
		 }
		  
		 if (request.getParameter("usuarioSeleccionado")!= null)
		 {
			 cargarUsuario(request, response);
		 } 
		  
		 if (request.getParameter("nuevaContrasena")!= null)
		 {
			 cambiarContrasena(request, response);
		 } 
		 
		 if(request.getParameter("btnPendiente") != null)
		    {
		    	listarPrestamosPendientes(request, response);
		    }
		    
		    if(request.getParameter("btnAceptado") != null)
		    {
		    	listarPrestamosAceptados(request, response);
		    }
		    
		    if(request.getParameter("btnRechazado") != null)
		    {
		    	listarPrestamosRechazados(request, response);
		    }
		    
		    if(request.getParameter("btnSaldado") != null)
		    {
		    	listarPrestamosSaldados(request, response);
		    }
		    
		    if (request.getParameter("btnBuscarP") != null) {
				   
			    listarPrestamosDelCliente(request, response);
			  }
			  	 
		    
		    if(request.getParameter("btnAceptarP")!=null) {
		    	aceptarPrestamos(request,response);
		    }
		    
		    if(request.getParameter("btnRechazarP")!=null) {
		    	rechazarPrestamos(request,response);
		    }
	}
	
	private void listarPrestamosDelCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Validacion para campo vacio
	    if (request.getParameter("txtNroCuenta") == null || request.getParameter("txtNroCuenta").trim().isEmpty()) {
	        request.setAttribute("mensaje", "ERROR: Ingrese un Número de Cuenta");
	        request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	        return;
	    }

	    try {
	        int nroCuenta = Integer.parseInt(request.getParameter("txtNroCuenta"));

	        // Validacion numero negativo o cero
	        if (nroCuenta <= 0) {
	            request.setAttribute("mensaje", "ERROR: El número de cuenta debe ser un número entero positivo");
	            request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	            return;
	        }

	        // Traer prestamos
	        ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosPendientesPorNroCuenta(nroCuenta);

	        if (lista.isEmpty()) {
	            request.setAttribute("mensaje", "ERROR: No se encontraron préstamos pendientes asociados al número de cuenta ingresado.");
	        } else {
	            request.setAttribute("listaP", lista);
	            request.setAttribute("origenLista", "buscar");
	        }

	        request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);

	    } catch (NumberFormatException ex) {
	        request.setAttribute("mensaje", "ERROR: Formato inválido. Ingrese solo números enteros positivos.");
	        request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	    }
	}
	private void listarPrestamosPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosPendientes();
		request.setAttribute("listaP", lista);
		request.setAttribute("origenLista", "pendientes");
		request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	}
	private void listarPrestamosAceptados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosAceptados();
		  request.setAttribute("listaP", lista);
		  request.setAttribute("origenLista", "aceptados");
		  request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	}
	private void listarPrestamosRechazados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosRechazados();
		  request.setAttribute("listaP", lista);
		  request.setAttribute("origenLista", "rechazados");
		  request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	}
	
	private void listarPrestamosSaldados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosSaldados();
		  request.setAttribute("listaP", lista);
		  request.setAttribute("origenLista", "saldados");
		  request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);
	}
	
	private void aceptarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		
			int cod = Integer.parseInt(request.getParameter("codPrestamo").toString());
			negPrestamo.aceptarPrestamo(cod);
			request.setAttribute("mensaje", "Prestamo aceptado con exito");
			
			  ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosPendientes();
			  request.setAttribute("listaP", lista);
			  request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);			
				
	}
	
	private void rechazarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{				
			int cod = Integer.parseInt(request.getParameter("codPrestamo").toString());
			negPrestamo.rechazarPrestamo(cod);
			request.setAttribute("mensaje", "Prestamo rechazado con exito");
			
			  ArrayList<Prestamo> lista = negPrestamo.obtenerPrestamosPendientes();
			  request.setAttribute("listaP", lista);
			  request.getRequestDispatcher("/AdminPrestamos.jsp").forward(request, response);							
	}
	
	private void consultarCuentasCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			String codigoCliente = request.getParameter("txtCodigoCliente");
		    INegocioCliente negocioCliente = new NegocioCliente();
		    
		    try {
		        Cliente cliente = negocioCliente.buscarClientePorCodigo(codigoCliente);

		        if (cliente == null) {
		        	throw new noExisteClienteException();
		        }

		        if (!cliente.isEstado()) {
		        	throw new clienteBajaException();
		        }
		        
		        // Cliente existe y está activo
		        INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
		        ArrayList<CuentaBancaria> cuentasBancarias = negocioCuentaBancaria.buscarCuentasBancariasPorClienteAsignado(codigoCliente);
		        int cantCuentasBancarias = cuentasBancarias.size();

		        request.setAttribute("codigoCliente", codigoCliente);
		        request.setAttribute("cuentasBancarias", cuentasBancarias);
		        request.setAttribute("cantCuentasBancarias", cantCuentasBancarias);
		        boolean permitidoAsignar = cantCuentasBancarias < 3;
		        request.setAttribute("permitidoAsignar", permitidoAsignar);
		    	
		    	} catch (noExisteClienteException | clienteBajaException ex) {
		            request.setAttribute("mensajeErrorCliente", ex.getMessage());
		       }
		request.getRequestDispatcher("/AdminAsignarCuentas.jsp").forward(request, response);
	}
	
	private void asignarCuentaBancaria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String tipoCuentaBancaria = request.getParameter("tipoCuentaBancaria");
		String codigoCliente = request.getParameter("txtCodigoCliente");
		ArrayList<CuentaBancaria> cuentasBancarias;
		
		INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
		if(negocioCuentaBancaria.asignarCuenta(Integer.parseInt(codigoCliente), tipoCuentaBancaria.charAt(0)))
		{
			cuentasBancarias = negocioCuentaBancaria.buscarCuentasBancariasPorClienteAsignado(codigoCliente);
			request.setAttribute("cuentasBancarias", cuentasBancarias);
		}
		else {
			request.setAttribute("errorAsignacion", "No se pudo asignar la cuenta.");
		}
		
		request.getRequestDispatcher("/AdminAsignarCuentas.jsp").forward(request, response);
	}
	
	private void editarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String numeroCuenta = request.getParameter("nroCuenta");
		int nroCuenta = Integer.parseInt(numeroCuenta);
		String estadoCuenta = request.getParameter("estadoActual");
		boolean estadoActual = Boolean.parseBoolean(estadoCuenta);
		boolean cambio = false;
		
		INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
		
		if (estadoActual) {
			negocioCuentaBancaria.darBajaCuenta(nroCuenta);
			cambio = true;
			request.setAttribute("confirmacionEditarEstado", "Cuenta bancaria dada de baja con éxito");
		} else {
			negocioCuentaBancaria.darAltaCuenta(nroCuenta);
			cambio = true;
			request.setAttribute("confirmacionEditarEstado", "Cuenta bancaria reestablecida con éxito");
		}
		
		request.setAttribute("cambio", cambio);
		request.getRequestDispatcher("/AdminCuentas.jsp").forward(request, response);
	}
	
	private void generarInforme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Date fechaInicial = Date.valueOf(request.getParameter("dtpFechaInicial"));
		Date fechaFinal = Date.valueOf(request.getParameter("dtpFechaFinal"));

		Informe informe = informeNegocio.generarInforme(fechaInicial, fechaFinal);
		ArrayList<Cliente> clientes;
		
		request.setAttribute("fechaInicio", informe.getFechaInicio());
		request.setAttribute("fechaFinal", informe.getFechaFinal());
		request.setAttribute("cuentasDadasAlta", informe.getCuentasDadasAlta());
		request.setAttribute("cuentasAltaCajaAhorro", informe.getCuentasAltaCajaAhorro());
		request.setAttribute("cuentasAltaCorriente", informe.getCuentasAltaCorriente());
    	request.setAttribute("cuentasDadasBaja", informe.getCuentasBajas());
		request.setAttribute("cuentasBajaCajaAhorro", informe.getCuentasBajaCajaAhorro());
		request.setAttribute("cuentasBajaCorriente", informe.getCuentasBajaCorriente());
    	request.setAttribute("clientesNuevos", informe.getClientesNuevos());
    	clientes = informeNegocio.obtenerTablaNuevos(fechaInicial, fechaFinal);
		request.setAttribute("clientesNuevosLista", clientes);
    	request.setAttribute("prestamosActivos", informe.getPrestamosActivos());
    	request.setAttribute("prestamosAtrasados", informe.getPrestamosAtrasados());
    	request.setAttribute("prestamosSaldados", informe.getPrestamosSaldados());
    	request.setAttribute("cumplibilidad", informe.getCumplibilidadPrestamos());
    	request.setAttribute("saldoPromedio", informe.getSaldoPromedioPorCuenta().setScale(2, RoundingMode.HALF_UP));
    	request.setAttribute("saldoBanco", informe.getSaldoTotalBanco());
    	request.setAttribute("transferencias", informe.getTransferenciasRealizadas());
    	request.setAttribute("movimientos", informe.getMovimientosTotales());

    	
    	request.getRequestDispatcher("/AdminInformes.jsp").forward(request, response);
	}
	
	private void buscarCliente(HttpServletRequest request, HttpServletResponse response, String codigoCliente) throws ServletException, IOException
	{
		try {
	        Cliente cliente = negocio.buscarClientePorCodigo(codigoCliente);

	        if (cliente == null) {
	            throw new noExisteClienteException();
	        }

	        if (!cliente.isEstado()) {
	            throw new clienteBajaException();
	        }

	        request.setAttribute("cliente", cliente);

	    } catch (noExisteClienteException | clienteBajaException ex) {
	        request.setAttribute("mensajeNoEncontrado", ex.getMessage());
	    }
		request.getRequestDispatcher("/AdminEliminarCliente.jsp").forward(request, response);
	}
	
	private void buscarCuentasFiltradas(HttpServletRequest request, HttpServletResponse response,
			String codigoCliente, String tipoCuenta, String estado) throws ServletException, IOException {
		
	    INegocioCliente negocioCliente = new NegocioCliente();
	    Cliente cliente = negocioCliente.buscarClientePorCodigo(codigoCliente);
	    
	    if (cliente == null) {
	    	request.setAttribute("noCuentasBancarias", 2);
	    	request.getRequestDispatcher("/AdminCuentas.jsp").forward(request, response);
	    }
	    else if (!cliente.isEstado()) {
	    	request.setAttribute("noCuentasBancarias", 3);
	    	request.getRequestDispatcher("/AdminCuentas.jsp").forward(request, response);
	    }

		ArrayList<CuentaBancaria> cuentas = cuentaBancaria.buscarCuentasFiltradas(codigoCliente, tipoCuenta, estado);
		if(cuentas != null && !cuentas.isEmpty()) {
			request.setAttribute("cuentas", cuentas);
			request.setAttribute("codigoCliente", codigoCliente);
			request.setAttribute("tipoDeCuentas", tipoCuenta);
			request.setAttribute("estadoCuentas", estado);
		} else request.setAttribute("noCuentasBancarias", 1);
		
		request.getRequestDispatcher("/AdminCuentas.jsp").forward(request, response);
	}
	
	private void cargarTipoCuentas(HttpServletRequest request) {
		 ArrayList<TipoCuenta> lTipos = tiposCuenta.obtenerTiposCuenta();
		    request.setAttribute("tiposCuenta", lTipos);
	}
	
	private void cargarNacionalidades(HttpServletRequest request) {
        NegocioNacionalidad negocio = new NegocioNacionalidad();
        ArrayList<Nacionalidad> nacionalidades = negocio.obtenerNacionalidades();

        StringBuilder opcionesNacionalidades = new StringBuilder();
        for (Nacionalidad n : nacionalidades) {
            opcionesNacionalidades.append("<option value='")
                .append(n.getIdNacionalidad())
                .append("'>")
                .append(n.getDescripcion())
                .append("</option>");
        }
        request.setAttribute("nacionalidades", opcionesNacionalidades.toString());
    }

    private void cargarProvincias(HttpServletRequest request) {
        NegocioProvincia negocio = new NegocioProvincia();
        ArrayList<Provincia> provincias = negocio.obtenerProvincias();

        StringBuilder opcionesProvincias = new StringBuilder();
        for (Provincia p : provincias) {
            opcionesProvincias.append("<option value='")
                .append(p.getIdProvincia())
                .append("'>")
                .append(p.getDescripcion())
                .append("</option>");
        }
        request.setAttribute("provincias", opcionesProvincias.toString());
    }
    
    private void cargarLocalidades(HttpServletRequest request, int idProvincia) {
        NegocioLocalidad negocio = new NegocioLocalidad();
        ArrayList<Localidad> localidades = negocio.obtenerLocalidadesPorProvincia(idProvincia);

        StringBuilder opcionesLocalidades = new StringBuilder();
        for (Localidad l : localidades) {
            opcionesLocalidades.append("<option value='")
                .append(l.getIdLocalidad())
                .append("'>")
                .append(l.getDescripcion())
                .append("</option>");
        }

        request.setAttribute("localidades", opcionesLocalidades.toString());
    }
    
    private Usuario crearObjUsuario(HttpServletRequest request) {
        Usuario u = new Usuario();
        u.setNombreUsuario(request.getParameter("txtUsuario"));
        u.setContrasena(request.getParameter("txtContrasenia"));
        return u;
    }

    private Cliente crearObjCliente(HttpServletRequest request) {
        Cliente c = new Cliente();
        c.setDni(request.getParameter("txtDni"));
        c.setCuil(request.getParameter("txtCuil"));
        c.setNombre(request.getParameter("txtNombre"));
        c.setApellido(request.getParameter("txtApellido"));
        c.setSexo(request.getParameter("rdoSexo").charAt(0));
        c.setDireccion(request.getParameter("txtDireccion"));
        c.setFechaNacimiento(java.sql.Date.valueOf(request.getParameter("txtfechaNacimiento")));
        c.setTelefono(request.getParameter("txtTelefono"));
        c.setCorreoElectronico(request.getParameter("txtCorreo"));

        Nacionalidad nac = new Nacionalidad();
        nac.setIdNacionalidad(Integer.parseInt(request.getParameter("ddlnacionalidad")));
        c.setNacionalidad(nac);

        Provincia prov = new Provincia();
        prov.setIdProvincia(Integer.parseInt(request.getParameter("ddlprovincia")));
        c.setProvincia(prov);

        Localidad loc = new Localidad();
        loc.setIdLocalidad(Integer.parseInt(request.getParameter("ddllocalidad")));
        c.setLocalidad(loc);

        return c;
    }
    
    private boolean validarCampos(HttpServletRequest request) {
        String[] campos = {
            "txtDni", "txtCuil", "txtNombre", "txtApellido", "rdoSexo",
            "txtDireccion", "txtfechaNacimiento", "txtTelefono", "txtCorreo",
            "txtUsuario", "txtContrasenia", "txtconfirmarContrasenia",
            "ddlnacionalidad", "ddlprovincia", "ddllocalidad"
        };

        for (String campo : campos) {
            String valor = request.getParameter(campo);
            if (valor == null || valor.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean validarContrasenia(HttpServletRequest request) {
        String pass = request.getParameter("txtContrasenia");
        String confirmar = request.getParameter("txtconfirmarContrasenia");
        return (pass != null && confirmar != null && !pass.equals(confirmar));
    }
    
    private void cargarSelectYSeleccion(HttpServletRequest request) {

        cargarNacionalidades(request);
        cargarProvincias(request);
        int idProvincia = Integer.parseInt(request.getParameter("ddlprovincia"));
        cargarLocalidades(request, idProvincia);
        request.setAttribute("provinciaSeleccionada", idProvincia);
    }
	
    /*private void mostrarTodosUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	ArrayList<Usuario> usuarios = negUsuario.Listar();            
            request.setAttribute("listaU", usuarios);
            request.getRequestDispatcher("AdminUsuarios.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.getStackTrace();
          
        }
    }
    
    private void enviarDatosCliente(HttpServletRequest request, HttpServletResponse response, String codigoCliente) throws ServletException, IOException {
        Cliente cliente = negocio.buscarClientePorCodigo(codigoCliente);

        if (cliente != null && cliente.isEstado()) {
            request.setAttribute("idUsuario", cliente.getUsuario().getIdUsuario());
            request.setAttribute("nombre", cliente.getNombre());
            request.setAttribute("apellido", cliente.getApellido());

            request.getRequestDispatcher("/ClienteNavBar.jsp").forward(request, response);
        } else {
            
            request.setAttribute("mensajeError", "Cliente no encontrado.");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }*/
    
    private void cargarUsuariosYRedirigir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		   if (usuarios==null)			  
				usuarios = new ArrayList<Usuario>();
    	if(request.getParameter("btnFiltrarporUsuario") != null)
		  {
    		if(usuarios != null)
    			usuarios.clear();
    		
			String filtroUsuario = request.getParameter("idUsuario");
			if(filtroUsuario != null && !filtroUsuario.isEmpty())
			{	
				int id = Integer.parseInt(filtroUsuario);			
				usuarios.add(negUsuario.ListarporID(id));				
			}
		  }
		if(request.getParameter("btnFiltrarporCliente") != null)
		  {
			if(usuarios != null)
				usuarios.clear();
			
			String filtroUsuario = request.getParameter("cliente");
			if(filtroUsuario != null && !filtroUsuario.isEmpty())
			{	
				int id = Integer.parseInt(filtroUsuario);			
				usuarios.add(negUsuario.listadoPorCliente(id));				
			}
		  }
		if(request.getParameter("btnBuscarTodo") != null)
		  {		
			if(usuarios != null)
				usuarios.clear();
			usuarios = negUsuario.Listar();
		  }
		if(usuarios != null &&  !usuarios.isEmpty()&& usuarios.getFirst().getIdUsuario()==0)
		{
			request.getRequestDispatcher("AdminUsuarios.jsp").forward(request, response);
		}
		else {
			request.setAttribute("listaU", usuarios);   	    
	    	request.getRequestDispatcher("AdminUsuarios.jsp").forward(request, response);
		}
		    	 
    }
    
    private void cambiarContrasena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	int confirmar = 0;
    	String nueva = request.getParameter("nuevaContrasena");
    	String confirmarNueva = request.getParameter("confNuevaContrasena");
    	
    	 if (nueva != null && !nueva.isEmpty() && confirmarNueva != null && !confirmarNueva.isEmpty()) {
    		 if (nueva.equals(confirmarNueva)) {
    			 confirmar = negUsuario.cambiarContrasena(usuarioA.getIdUsuario(), nueva);
    	     } else { confirmar = 0; }
    	 }
    	 
    	 request.setAttribute("confirmacion", confirmar);
    	 
    	  request.setAttribute("Ufiltrado", usuarioA);
    	  ArrayList<Usuario> lista = negUsuario.Listar(); 
    	  request.setAttribute("listaU", lista); 
    	
    	request.getRequestDispatcher("AdminUsuarios.jsp").forward(request, response);
    }
    
    private void cargarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {	    	
    	int id = Integer.parseInt(request.getParameter("usuarioSeleccionado"));    	
    	usuarioA = negUsuario.ListarporID(id);
    	request.setAttribute("Ufiltrado", usuarioA);
    	cargarUsuariosYRedirigir(request, response);
    }
    
    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String dni = request.getParameter("txtDni");
		 String cuil = request.getParameter("txtCuil");
		 String usuarioNombre = request.getParameter("txtUsuario");
		 
		 if(!negocio.existeDni(dni) && !negocio.existeCuil(cuil) && !negUsuario.existeNombreUsuario(usuarioNombre))
		 {			 
		     try {       
	        
	            if (validarCampos(request)) {
	            request.setAttribute("mensaje", "Error: Todos los campos son obligatorios.");
	            cargarSelectYSeleccion(request);
	            request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
	            return;
	            }

	            if (validarContrasenia(request)) {
	            request.setAttribute("mensaje", "Error: Las contraseñas no coinciden.");
	            cargarSelectYSeleccion(request);
	            request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
	            return;
	            }
	        
	               Usuario u = crearObjUsuario(request);			        
	               int idGenerado = negUsuario.Agregar(u);

	               if (idGenerado > 0) {
	            	        	
	        	      Cliente c = crearObjCliente(request);
	                  boolean insertado = negocio.Agregar(c, idGenerado);

	                  if (insertado) {
	                      request.setAttribute("mensaje", "Exito: Cliente agregado correctamente.");
	                      INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
	                      INegocioCliente negocioCliente = new NegocioCliente();
	                      negocioCuentaBancaria.asignarCuenta(negocioCliente.buscarClientePorUsuario(u.getNombreUsuario()).getCodCliente(), 'A');
	                      } 
	                  else {
	                      request.setAttribute("mensaje", "Error: No se pudo agregar el cliente."); }
	            
	               } else {
	                 request.setAttribute("mensaje", "Error: No se pudo crear el usuario."); }

	               cargarSelectYSeleccion(request);
	               request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
	        
		       } catch(Exception ex) {
			     ex.printStackTrace();
			   request.setAttribute("mensaje", "Error: Ocurrió un error al intentar guardar los datos."+ ex.getMessage());
			   cargarSelectYSeleccion(request);
			   request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
			   return;}			 		 
		 
	    } else {
	    	request.setAttribute("mensaje", "Error: El DNI, CUIL o usuario ya existe.");
	    	cargarSelectYSeleccion(request);
			request.getRequestDispatcher("/AdminAgregarCliente.jsp").forward(request, response);
	    	return;}
		 
	 }
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            int codigoCliente = Integer.parseInt(request.getParameter("codigoCliente"));
	            boolean eliminado = negocio.Eliminar(codigoCliente);

	            if (eliminado) {
	                request.setAttribute("mensajeEliminacion", "Cliente eliminado exitosamente.");
	            } else {
	                request.setAttribute("mensajeEliminacion", "No se pudo eliminar el cliente.");
	            }
	            request.getRequestDispatcher("/AdminEliminarCliente.jsp").forward(request, response);
	        } catch (NumberFormatException e) {
	            request.setAttribute("mensaje", "Código de cliente inválido.");
	            request.getRequestDispatcher("/AdminEliminarCliente.jsp").forward(request, response);
	        }
	 }
}


