<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="NegocioInterfaz.INegocioPrestamo"%>
<%@ page import="Negocio.NegocioPrestamo"%>
<%@ page import="Entidades.Prestamo"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Prestamos</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable({
            "pageLength": 5, // Muestra 5 registros por página
            "lengthMenu": [5, 10, 25, 50], // Opciones para cambiar cantidad de registros
           });
	});
</script>



</head>
<body>
    <header>
        <jsp:include page="AdminNavBar.jsp"></jsp:include>
    </header>


    <main class="container mt-4">
    	<h1 class="mb-4">Prestamos</h1>
    	<div class="card p-4">
    	
    	
    		<form method="post" action="servletAdminClientes">		
        		<label for="txtNroCuenta" class="form-label">Buscar préstamos pendientes por número de cuenta:</label>
        		<input type="number" id="txtNroCuenta" name="txtNroCuenta" class="form-control" min="1" step="1" pattern="\d+" title="Ingrese un número entero positivo">
        		<button type="submit" name="btnBuscarP" class="btn btn-primary mt-2">Buscar</button><br>
			<%
			    String mensaje = (String) request.getAttribute("mensaje");
			    if (mensaje != null) {
			        boolean esExito = mensaje.toLowerCase().contains("exito");
			%>
			    <div class="alert <%= esExito ? "alert-success" : "alert-danger" %> mt-2">
			        <%= mensaje %>
			    </div>
			<% } %>
        		<br><label for="txtMostrarPrestamos" class="form-label">Mostrar Prestamos:</label><br>
        		<button type="submit" name="btnPendiente" class="btn btn-primary mt-2">Pendientes</button><br>
        		<button type="submit" name="btnAceptado" class="btn btn-primary mt-2">Aceptados</button><br>
        		<button type="submit" name="btnRechazado" class="btn btn-primary mt-2">Rechazados</button><br>
        		<button type="submit" name="btnSaldado" class="btn btn-primary mt-2">Saldados</button><br>
    		</form>
            <br>
            
            <% 
                 ArrayList<Prestamo> listaPrestamos = null;
                 if(request.getAttribute("listaP") != null)
                 {
                	 listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaP");
                 }
            
            %>
            
            <%
    String origenLista = (String) request.getAttribute("origenLista");
%>
                     
    		<table class="table table-bordered table-hover" id="table_id">
        		<thead class="table-light">
            	<tr>
                	<th>Codigo prestamo</th>
                	<th>Codigo cliente</th>
                	<th>Nro Cuenta</th>
                    <th>Fecha</th>
                	<th>Importe a pagar</th>
                	<th>Importe solicitado</th>
                    <th>Plazo en meses</th>
                    <th>Pago mensual</th>
                    <th>Cuotas</th>
                    <th>Deuda</th>
                    <th>Estado</th>
                    <th>Opciones</th>
                    
                    
            	</tr>
        		</thead>       		
        		
        		<tbody>
        		
        		<%
        		     if(listaPrestamos != null){
        		     for(Prestamo p : listaPrestamos){ %>         		
        		   <tr>
            		<form action="servletAdminClientes" method="post">
                		<td><%=p.getCodPrestamo() %> <input type="hidden" name="codPrestamo" value="<%=p.getCodPrestamo()%>"></td>
                		<td><%=p.getClienteAsociado().getCodCliente() %></td>
                		<td><%=p.getCuentaAsociada().getNroCuenta() %></td>
                		<td><%=p.getFechaSolicitado() %></td>
                		<td><%=p.getImportePagar() %></td>
                		<td><%=p.getImporteSolicitado() %></td>
                        <td><%=p.getPlazoMeses() %></td>
                        <td><%=p.getPagoMensual() %></td>
                        <td><%=p.getCuotasTotales() %></td>
                        <td><%=p.isDeuda() ? "Sí" : "No"%></td>
                        <td><%=p.isEstado() ? "Aceptado" : "Pendiente/Rechazado"%></td>
                        <td>
<% if ("pendientes".equals(origenLista) || "buscar".equals(origenLista)) { %>
    <input type="submit" name="btnAceptarP" class="btn btn-success btn-sm" value="Aceptar" onclick="confirmarAccion(event, 'aceptar')"><br>
    <input type="submit" name="btnRechazarP" class="btn btn-danger btn-sm" value="Rechazar" onclick="confirmarAccion(event, 'rechazar')">
<% } else { %>
   
<% } %>
</td>
                                               
                      </form> 
            		</tr>
            		
            		
            		<% } 
            		}%>
        		</tbody>
        		       		
    		</table>		
 		</div>
	</main>
	
<script>
    function confirmarAccion(event, tipo) {
        let mensaje = tipo === 'aceptar'
            ? "¿Está seguro de aceptar el préstamo?"
            : "¿Está seguro de rechazar el préstamo?";
        
        if (!confirm(mensaje)) {
            event.preventDefault(); // Evita que se envíe el formulario si el usuario hace clic en "Cancelar"
        }
    }
</script>

</body>
</html>