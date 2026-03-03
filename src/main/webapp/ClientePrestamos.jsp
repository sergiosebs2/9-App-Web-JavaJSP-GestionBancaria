<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>
    <%@ page import="Entidades.*"%>
    <%@ page import="java.text.SimpleDateFormat"%>
    <%@ page import= "java.math.BigDecimal"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ClientePrestamos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
    	<jsp:include page="ClienteNavBar.jsp"></jsp:include>
	</header>
	<main class="container mt-3">
	
	<% 	ArrayList<CuentaBancaria> cuentas;	
	BigDecimal saldoenCuentaActualizada;	
	double cuota =0;%>
	
	<h3 class="mb-3 text-center">Sección Prestamos</h3>
		
		<div class="row">
		<div class="col-md-6">		
		
		 <div class="card card-body bg-light">
		 <form method="post" action="servletsClientes">
                    <h5 class="fw-semibold fst-italic mb-3">Solicitá un nuevo prestamo </h5>                    
                    
                <label id="cbuDestino" class="form-label">Monto deseado</label>
               <% Double valor = (Double) session.getAttribute("totalPedido");%>
    			<input type="text" name="montoDeseado"  class="form-control mb-2" value="<%= valor != null ? valor : "" %>" required  placeholder="Ingrese el monto deseado">
				
				<select class="form-select mb-2"  name="plazoMeses">
				<%Integer cuotas = (Integer) session.getAttribute("cuotas");%>
				    <option value="3" <%= (cuotas != null && cuotas == 3) ? "selected" : "" %>>3 cuotas</option>
				    <option value="6" <%= (cuotas != null && cuotas == 6) ? "selected" : "" %>>6 cuotas</option>
				    <option value="12" <%= (cuotas != null && cuotas == 12) ? "selected" : "" %>>12 cuotas</option>
				    <option value="18" <%= (cuotas != null && cuotas == 18) ? "selected" : "" %>>18 cuotas</option>
				    <option value="24" <%= (cuotas != null && cuotas == 24) ? "selected" : "" %>>24 cuotas</option>
				    </select>
				<input type="submit" name="btnCalcularCuota" class="btn btn-primary py-0 w-100 mb-4 mt-3" value="Calcular valor de las cuotas">
		</form>
		<hr class="border border-secondary opacity-50"> 

			<form method="post" action="servletsClientes">
		<label id="cbuDestino" class="form-label">Valor de la cuota</label>
		<% if(session.getAttribute("montoCuota") != null)
			{cuota = (double)(session.getAttribute("montoCuota")) ;}%>
		<input type="text" class="form-control mb-2" id="cuota" value="<%= cuota %>" readonly>
				
      	<label for="CuentaDestino" class="form-label">Cuenta a depositar</label>
		<% CuentaBancaria cuenta = (CuentaBancaria)session.getAttribute("cuentaElegida"); %>
		<input type="text" class="form-control mb-5" value="<%= cuenta != null ? cuenta.toString() : "No seleccionada" %>" readonly>
		       		
		       		 
      	<div class="d-inline-flex align-items-center gap-2">
      	<button type="button" class="btn btn-outline-primary py-3" data-bs-toggle="modal" data-bs-target="#confirmarCambioModal"> Solicitar préstamo</button>
      	
      	<div class="modal fade" id="confirmarCambioModal" tabindex="-1" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h5 class="modal-title">¿Confirmar solicitud?</h5>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <div class="modal-body">
		               ¿Estás seguro de solicitar este préstamo? Esta acción no se puede deshacer.
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		               <input type="submit" class="btn btn-outline-primary" name="btnSolicitarPrestamo" value=" Sí, solicitar">		                
		            </div>
		        </div>		
		    </div>
		</div>
        
        <%	if(request.getAttribute("prestamoAgregado") != null) {			
		%>
		<div class="alert alert-success mt-3" role="alert">
            <i class="bi bi-check-circle-fill"></i> ¡Solicitud de Prestamo enviada! Una vez aprobado, podras verlo reflejado en Historial"
        </div><%
		}
		%>
        
		</div>
		</form></div>
		</div>	
		
		<div class="col-md-6">
		
		 <div class="card card-body bg-light">
		 <form method="post" action="servletsClientes ">
                <input type="submit" name="btnCargarPrestamos" class="btn btn-outline-secondary w-100 py-2" value="Pagar cuotas de tu prestamo existente">
                
                <%
                if(request.getSession().getAttribute("prestamosActuales")!= null){ %> 
                    <h6 class="fw-semibold fst-italic mb-3 mt-4">Seleccionar prestamo a pagar</h6>
                    
				<select name="seleccionPrestamo" class="form-select">
			    <%   
			        ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) request.getSession().getAttribute("prestamosActuales");
			        //String cuentaSeleccionada = request.getParameter("numeroCuenta");
			        
			        if(prestamos != null && !prestamos.isEmpty()) {
			            for(Prestamo pres : prestamos) { 
			            	String importe = "Monto de $ " + String.valueOf(pres.getImporteSolicitado()) + " || Solicitado el ";
			            	String mostrarvalorCuota = " || Cuotas de $ " + String.valueOf(pres.getPagoMensual());
			            	String valorOption = String.valueOf(pres.getCodPrestamo());
			            	String fechaPedido = new SimpleDateFormat("dd-MM-yyyy").format(pres.getFechaSolicitado());
			                %>
			                <option value="<%= valorOption %>"
			                <%= (session.getAttribute("IdprestamoSeleccionado") != null && 
							         session.getAttribute("IdprestamoSeleccionado").equals(valorOption)) ? "selected" : "" %>>
		                    
							   <%= importe + fechaPedido + mostrarvalorCuota %>
							</option>
                
                
			    <% 	}	} else {
			    %>
			            <option disabled selected>No hay prestamos vigentes</option>
			    <% 
			        } 
			    %>
			</select>
			
			
			<h6 class="fw-semibold fst-italic mb-3 mt-4">Seleccionar cuenta con la que desea abonar</h6>
                    
				<select name="seleccionCuentaPago" class="form-select">
			    <% 
			    ArrayList<CuentaBancaria> cuentaPago=null;
				if(request.getSession().getAttribute("cuentasBancarias") != null)
			        cuentaPago = (ArrayList<CuentaBancaria>) session.getAttribute("cuentasBancarias");
				if(cuentaPago != null && !cuentaPago.isEmpty()) {
			            for(CuentaBancaria itemCuenta : cuentaPago) { 
			            	String valorOption = String.valueOf(itemCuenta.getNroCuenta());
			            	String datosCuenta = String.valueOf(itemCuenta.getTipoCuenta().getDescripcion()) + " - CBU " + String.valueOf(itemCuenta.getCBU()) + "  || Saldo actual: $ " + itemCuenta.getSaldo();
			            	
			                %>
			                <option value="<%= valorOption %>">
                    		<%= datosCuenta %>
                </option>
                
                
			    <% 
			            }
			        } else {
			    %>
			            <option disabled selected>No hay cuentas vigentes</option>
			    <% 
			        } 
			    %>
			</select>			
                  <input type="submit" class="btn btn-secondary mt-4 w-100 py-1" value="Confirmar y continuar a pagar" name="btnGeneraCuotas">
			
                   <% }%>
                   
                   </form>
			<form method="post" action="servletsClientes ">
                   <%if(session.getAttribute("listadoCuotas")!= null ) { %> 
            	<h6 class="fw-semibold fst-italic mb-3 mt-4">Seleccionar una cuota de la lista</h6>
                    
				<select name="seleccionCuota" class="form-select">
			    <% 
			    double valorCuota = 0.0;
		        double saldoact = 0.0;
		        double saldoFinal = 0.0;
			    ArrayList<Cuotas> listaCuotas = null;		
			    if(session.getAttribute("listadoCuotas") != null)
			    	listaCuotas = (ArrayList<Cuotas>) session.getAttribute("listadoCuotas");
			   
			    
				if(listaCuotas != null && !listaCuotas.isEmpty()) {
			            for(Cuotas itemCuota : listaCuotas) { 
			            	if(!itemCuota.isEstado())
			            	{
			            		String valorOption = String.valueOf(itemCuota.getNumeroCuota());
				            	String datosCuenta = "Cuota Nro " + String.valueOf(itemCuota.getNumeroCuota()) +  " - Valor $ " + String.valueOf(itemCuota.getMontoCuota());
				            	valorCuota = itemCuota.getMontoCuota();
				                %>
				                <option value="<%= valorOption %>">
	                    		<%= datosCuenta %>
			            	<%}%>
			            	
                </option>                
                
			    <% 
			            }
			        } else {
			    %>
			            <option disabled selected>No hay cuotas pendientes de pago</option>
			    <% 
			        } 
				if(session.getAttribute("saldoAntesdeDescontar") != null)
				{
					saldoact = (double) (session.getAttribute("saldoAntesdeDescontar"));
				}	
				saldoFinal = saldoact - valorCuota;
			    %>
			</select>		
			    <input type="text" class="form-control mb-2 mt-2" id="cuotas" value="Nuevo saldo en cuenta: $ <%= saldoFinal %> " readonly>
				<input type="hidden" name="saldoActualizado" value="<%= saldoFinal %>">
			
                   
		<button type="button"  class="btn btn-secondary mt-4 w-100 py-1" data-bs-toggle="modal" data-bs-target="#confirmarPagoModal"> Pagar Cuota</button>                 
                   
                   <div class="modal fade" id="confirmarPagoModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">¿Confirmar pago?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de querer pagar esta cuota?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>                
                <input type="submit" class="btn btn-primary" value="Si, confirmar Pago" name="btnPagarCuota">
                
            </div>
        </div>
    </div>
</div>
			          <%}
                   String mensaje="";
                   if(request.getAttribute("mensajeExito")!= null) {
                       mensaje = (String) session.getAttribute("mensajeExito");
					%>
					    <div class="alert alert-success mt-3" role="alert">
					        <i class="bi bi-check-circle-fill"></i> <%= mensaje %>
					    </div>
					<%
					    }
					%>
               
                	   </form>  
               </div>
                   
               
    		
   
   
		
		</div>
	</div>
	
	
			
	</main>
	

</body>
</html>