package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Negocio.*;
import NegocioInterfaz.INegocioCuentaBancaria;
import NegocioInterfaz.INegocioCuotas;
import Entidades.*;
import NegocioInterfaz.INegocioMovimientos;
import NegocioInterfaz.INegocioPrestamo;

/**
 * Servlet implementation class servletsClientes
 */
@WebServlet("/servletsClientes")
public class servletsClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Cliente cliente = new Cliente();
       CuentaBancaria cuenta;       
       INegocioCuentaBancaria negcuentaBancaria = new NegocioCuentaBancaria();
       INegocioMovimientos movimiento = new NegocioMovimiento();
       INegocioPrestamo negPrestamo = new NegocioPrestamo();
       INegocioCuotas negCuotas = new NegocioCuotas();
       INegocioMovimientos negMovimientos = new NegocioMovimiento();

   
    public servletsClientes() {
        super();      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if (request.getParameter("btnSeleccionCuenta") != null) {
        	cargarSeleccionCuenta(request, response);
        }
        
        if (request.getParameter("btnCalcularCuota") != null)  {
        	calcularCuota(request, response);
        }
        
        if (request.getParameter("btnSolicitarPrestamo") != null)  {
        	agregarPrestamo(request, response);
        }
        
        if (request.getParameter("btnCargarPrestamos") != null)  {
        	cargarPrestamosActuales(request, response);
        }
        
        if (request.getParameter("btnVerHistorial") != null) {
            mostrarHistorial(request, response);
        }
        if (request.getParameter("btnGeneraCuotas") != null) {
            cargarCuotas(request, response);
        }
        
        if (request.getParameter("btnPagarCuota") != null) {
            pagarPrestamo(request, response);
        }
        
        if (request.getParameter("btnHistorialFiltrado") != null) {
    	    String tipoMovimiento = request.getParameter("tipoMovimiento");

    	    if (tipoMovimiento == null || tipoMovimiento.isEmpty()) {
    	        mostrarHistorial(request, response);
    	    } else {
    	        mostrarHistorialFiltrado(request, response, tipoMovimiento); 
    	    }
    	}
        
        if(request.getParameter("btnTransferir") != null)
        {
        	realizarTransferencia(request, response);
        }
	}
	
	private void realizarTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		INegocioCuentaBancaria negocioCuentaBancaria = new NegocioCuentaBancaria();
		CuentaBancaria cuentaSeleccionada = (CuentaBancaria)request.getSession().getAttribute("cuentaElegida");
		CuentaBancaria cuentaOrigen = negocioCuentaBancaria.obtenerCuentaPorCbu(cuentaSeleccionada.getCBU());
		BigDecimal monto = new BigDecimal(request.getParameter("txtMonto"));
		String cbuDestino = request.getParameter("txtCbuDestino");
		String detalle = request.getParameter("txtDetalle");
		
		if(negocioCuentaBancaria.transferir(cbuDestino, cuentaOrigen, monto, detalle))
		{
			request.setAttribute("transferenciaRealizada", true);
			cuentaOrigen = negocioCuentaBancaria.obtenerCuentaPorCbu(cuentaSeleccionada.getCBU());
			request.setAttribute("saldoRestante", cuentaOrigen.getSaldo());
		}
		else 
		{
			request.setAttribute("transferenciaRealizada", false);
		}
		
		request.getRequestDispatcher("/ClienteTransferencias.jsp").forward(request, response);
	}
	
	private void cargarSeleccionCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
    if(request.getParameter("numeroCuenta") != null)
    {
        int idCuenta = Integer.parseInt(request.getParameter("numeroCuenta"));
        cuenta = negcuentaBancaria.ObtenerporID(idCuenta);

        // 🔹 CAMBIO: ahora se guarda la cuenta en sesión con dos nombres
        request.getSession().setAttribute("cuentaElegida", cuenta);
        request.getSession().setAttribute("cuentaSesion", cuenta); // <-- agregado

        int nroCuenta = cuenta.getNroCuenta();
        ArrayList<Movimiento> movimientos = movimiento.obtenerMovimientosPorCuenta(nroCuenta);
        request.setAttribute("listaMovimientos", movimientos);
        request.getRequestDispatcher("/ClienteDashboard.jsp").forward(request, response);
        return;
    }

    request.getRequestDispatcher("/ClienteDashboard.jsp").forward(request, response);
}
	
	
		private void mostrarHistorial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CuentaBancaria cuentaElegida = (CuentaBancaria) request.getSession().getAttribute("cuentaElegida");

        int nroCuenta = cuentaElegida.getNroCuenta();
        ArrayList<Movimiento> movimientos = movimiento.obtenerMovimientosPorCuenta(nroCuenta);
        request.setAttribute("listaMovimientos", movimientos);
        request.getRequestDispatcher("ClienteHistorial.jsp").forward(request, response);
		}

		private void mostrarHistorialFiltrado(HttpServletRequest request, HttpServletResponse response, String tipoMovimiento) throws ServletException, IOException {
         CuentaBancaria cuentaElegida = (CuentaBancaria) request.getSession().getAttribute("cuentaElegida");
        int nroCuenta = cuentaElegida.getNroCuenta();
        ArrayList<Movimiento> movimientosFiltrados = movimiento.obtenerMovimientosPorTipo(nroCuenta, tipoMovimiento);


        request.setAttribute("listaMovimientos", movimientosFiltrados);
        request.setAttribute("tipoMovimientoSeleccionado", tipoMovimiento);
        request.getRequestDispatcher("ClienteHistorial.jsp").forward(request, response);
		}
	
	
	private void calcularCuota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if((request.getParameter("plazoMeses") != null) && (request.getParameter("montoDeseado") != null))
		  {
			int cuota = Integer.parseInt(request.getParameter("plazoMeses"));  	
			double montoPedido = Double.parseDouble(request.getParameter("montoDeseado"));  
			double valorCuota =(double)( montoPedido * 1.20 ) / cuota;
			HttpSession session = request.getSession();
	        session.setAttribute("montoCuota", valorCuota);
	        session.setAttribute("totalPedido", montoPedido);
	        session.setAttribute("cuotas", cuota);
		  }
		request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
	}
	

	
private void agregarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{	
		HttpSession session = request.getSession();
		if (session.getAttribute("clienteSesion") != null) {
			    cliente = (Cliente) session.getAttribute("clienteSesion");
		}
		Prestamo prestamo = new Prestamo();
		prestamo.setClienteAsociado(cliente);			
		
		CuentaBancaria cuenta = (CuentaBancaria)session.getAttribute("cuentaElegida");
		prestamo.setCuentaAsociada(cuenta);
		
		double importeSolicitado = (double)session.getAttribute("totalPedido");
		BigDecimal BDtotalpedido = new BigDecimal(String.valueOf(importeSolicitado));
		prestamo.setImporteSolicitado(BDtotalpedido);
		
		int plazoMeses = (int)session.getAttribute("cuotas");
		prestamo.setPlazoMeses(plazoMeses);
		
		double pagoMensual = (double)session.getAttribute("montoCuota");
		BigDecimal BDpagoMensual = new BigDecimal(String.valueOf(pagoMensual));
		prestamo.setPagoMensual(BDpagoMensual);
		
		int cuotasTotales = (int)session.getAttribute("cuotas");
		prestamo.setCuotasTotales(cuotasTotales);
		
		float importeAaPagar = (float) pagoMensual * plazoMeses;
		BigDecimal BDimporteAaPagar = new BigDecimal(String.valueOf(importeAaPagar));
		prestamo.setImportePagar(BDimporteAaPagar);
		
		int confirmar = negPrestamo.agregarNuevoPrestamo(prestamo);
		
		request.setAttribute("prestamoAgregado", confirmar);
		request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
	}
	
	private void cargarPrestamosActuales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if (request.getSession().getAttribute("clienteSesion") != null) {
			    cliente = (Cliente) request.getSession().getAttribute("clienteSesion");
		}
		
		int idCuentaActual = cuenta.getNroCuenta();
		ArrayList<Prestamo> prestamosCuenta = new ArrayList<Prestamo>();
		ArrayList<Prestamo> todosLosPrestamosVigentes = negPrestamo.obtenerPrestamosAceptados();
		for(Prestamo prestamo : todosLosPrestamosVigentes)
		{
			if(prestamo.getCuentaAsociada().getNroCuenta()==idCuentaActual && prestamo.isDeuda() && prestamo.isEstado())
				prestamosCuenta.add(prestamo);
		}
		request.getSession().setAttribute("prestamosActuales", prestamosCuenta);
		request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
		
	}
	
	private void cargarCuotas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		    String cuentaId = request.getParameter("seleccionCuentaPago");
		    int idCuenta = Integer.parseInt(cuentaId);
	        request.getSession().setAttribute("idCuentaPago", idCuenta);
		    int prestamoID = Integer.parseInt(request.getParameter("seleccionPrestamo"));
		    

		    if (prestamoID != 0 && cuentaId != null) {
	            int cuentaPago = Integer.parseInt(cuentaId);
	            request.getSession().setAttribute("IdprestamoSeleccionado", prestamoID);
	            
	            CuentaBancaria cuentaAaCobrar = negcuentaBancaria.ObtenerporID(cuentaPago);		            
	            double saldoAntesdeDescontar = cuentaAaCobrar.getSaldo().doubleValue();
			
			request.getSession().setAttribute("IdprestamoSeleccionado", prestamoID);
			request.getSession().setAttribute("cuentaPagoCuota", cuentaAaCobrar);
						
			ArrayList <Cuotas> cuotas = new ArrayList<Cuotas>();
			cuotas = negCuotas.listarCuotasPorIdPrestamo(prestamoID);	
			if(saldoAntesdeDescontar < cuotas.get(0).getMontoCuota())
			{
				request.setAttribute("mensajeEror", "El saldo es menor al monto a abonar");
			}
			
			request.getSession().setAttribute("saldoAntesdeDescontar", saldoAntesdeDescontar);
			request.getSession().setAttribute("listadoCuotas", cuotas);
			
		}
		request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
		
	}
	
	private void pagarPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{	//marcar cuota paga
		int resultado =0;
		//generar movimiento - solo pagar estado = 0 - marcar cuota como 1 - restar saldo en cuenta
		int cuotaElegida = Integer.parseInt(request.getParameter("seleccionCuota"));
		 int prestamoID = (int) request.getSession().getAttribute("IdprestamoSeleccionado");
	    resultado = negCuotas.pagarCuotaSeleccionada(prestamoID, cuotaElegida);
	    
	    
	    //actualizar saldo
	   
	    double saldoFinal = Double.parseDouble(request.getParameter("saldoActualizado"));
	    BigDecimal saldoFinalBD = BigDecimal.valueOf(saldoFinal);
	    Integer idCuentaiNT = (Integer) request.getSession().getAttribute("idCuentaPago");
	    int idCuenta = idCuentaiNT;
	    String mensaje="";
	    if(negcuentaBancaria.descontarCuota(saldoFinalBD, idCuenta))
	    {
	    	if(resultado == 1)
	    		mensaje= "Pago de cuota exitoso!";
            	request.getSession().setAttribute("mensajeExito", mensaje);
            	request.setAttribute("mensajeExito", mensaje);
	        	CuentaBancaria cuenta = (CuentaBancaria)request.getSession().getAttribute("cuentaPagoCuota");
	        	cuenta.setSaldo(saldoFinalBD);
	        	String tipo= "PP";
	        	String texto = "Pago de cuota";
	        	
	        	ArrayList <Cuotas> cuotas = (ArrayList <Cuotas>)request.getSession().getAttribute("listadoCuotas");
	        	BigDecimal mov = BigDecimal.valueOf(cuotas.get(0).getMontoCuota()*(-1));
	        	negMovimientos.crearMovimiento(tipo, idCuenta, texto, mov);
	        	
	        	INegocioCuotas negocioCuotas = new NegocioCuotas();
	        	if(negocioCuotas.cuotasSaldadas(prestamoID))
	        	{
	        		INegocioPrestamo negocioPrestamo = new NegocioPrestamo();
	        		negocioPrestamo.saldarPrestamo(prestamoID);
	        		request.getSession().setAttribute("prestamosActuales", negocioPrestamo.obtenerPrestamosAceptadosPorNroCuenta(idCuentaiNT));
	        	}
	        	
	        	request.getSession().setAttribute("listadoCuotas", negocioCuotas.listarCuotasPorIdPrestamo(prestamoID));
	    } 
	    
		request.getRequestDispatcher("/ClientePrestamos.jsp").forward(request, response);
		}
}


