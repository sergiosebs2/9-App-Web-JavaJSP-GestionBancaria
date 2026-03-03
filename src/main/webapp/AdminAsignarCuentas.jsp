<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entidades.CuentaBancaria" import="Entidades.TipoCuenta" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Asignar Cuentas</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
</head>

<body>
	<jsp:include page="AdminNavBar.jsp"></jsp:include>
	<div class="container mt-5">
		<h2 class="mb-5 text-center"> Asignar Cuentas Bancarias</h2>
		
		<form action="servletAdminClientes" method="get">
		<div class="row justify-content-center mt-2">
			<div class="col-md-4">
				
		    	<label for="txtCodigoCliente" class="form-label">Ingrese Código Cliente:</label>
		    	<input required type="text" class="form-control" name="txtCodigoCliente" value=<%= request.getAttribute("cantCuentasBancarias") != null ? request.getAttribute("codigoCliente") : "" %>>
		    </div>
		    <div class="col-md-2 d-flex align-items-end">
			    <input type="submit" class="btn btn-primary w-100" name="btnConsultarCuentaBancarias" value="Consultar Disponibilidad">
			</div>	
			
			<div class="row justify-content-center mt-4">
				<div class="col-md-5 text-center">
				
				<%
					
					String mensajeErrorCliente = (String) request.getAttribute("mensajeErrorCliente");
					if (mensajeErrorCliente != null) {
					%>
						
						<div class="alert alert-danger" role="alert">
							<%= mensajeErrorCliente %>
						</div>

					<%
					
					} else {
						
						Object cantObj = request.getAttribute("cantCuentasBancarias");
						if (cantObj != null) {
							if (cantObj instanceof Integer) {
								int cant = (Integer) cantObj;
					%>
								<p class="fs-3"><%= "Tiene " + cant + " cuenta/s asignada/s." %></p>
								<% if (cant >= 3) { %>
									<p class="text-danger fs-5 fw-bold">El cliente alcanzó el máximo de 3 cuentas bancarias.</p>
								<% } %>
					<%
							} else if (cantObj instanceof String) {
					%>
								<p class="fs-4 text-warning"><%= cantObj %></p>
					<%
							}
						}
					%>
						
		    	<hr class="border border-2 border-secondary opacity-75 my-4">
		    	</div>
			</div>
			
			<div class="row justify-content-center mt-2">
				<div class="col-md-2">
					<label for="tipoCuentaBancaria" class="form-label mt-1">Tipo de cuenta a Asignar:</label>		
				</div>
				<div class="col-md-3">
					<select class="form-select w-100" name="tipoCuentaBancaria" style="width: 263px;">
			    	  <option value="A">Caja de Ahorro</option>
			    	  <option value="C">Cuenta Corriente</option>
			    	</select>
				</div>
			</div>
			
			<div class="row justify-content-center mt-4">
				<div class="col-md-6">
				<%
				boolean permitidoAsignar = false;
				if(request.getAttribute("permitidoAsignar") != null)
				{ 
					permitidoAsignar = (boolean)request.getAttribute("permitidoAsignar");%>
					<input type="submit" 
			           class="btn <%= permitidoAsignar ? "btn-success" : "btn-danger" %> w-100" 
			           name="btnAsignarCuenta" 
			           value="Asignar Nueva Cuenta" 
			           <%= !permitidoAsignar ? "disabled" : "" %>>
				<%} %>
		    	</div>
		    </div>
		    
		        <% 
		        
		        	ArrayList<CuentaBancaria> cuentas = (ArrayList<CuentaBancaria>)request.getAttribute("cuentasBancarias");
		        	
		        	if (cuentas != null && !cuentas.isEmpty()) {%>
		        		
		        		<div class="col-md-6">
				 	    <table class="table mt-3 text-center">
				        <thead class="table-light">
				            <tr>
				                <th>ID Cuenta bancaria asignada</th>
				                <th>CBU</th>
				            </tr>
				        </thead>
		        		
		        	<%for (CuentaBancaria cuenta : cuentas) {
		        
		         %>
		         
			        <tbody>
		             <tr>
		             	<td><%= cuenta.getNroCuenta() %> </td>
			            <td><%= cuenta.getCBU() %></td>
		            </tr>
		        	<% 
		        		}
		        	}	else {
		        	 %>
		        	 <tr>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 	<td>&nbsp;</td>
		        	 </tr>
		        	 </tbody>
		       		</table>
     
 				</div>
		        	 <%
		        	 	}
						}
		        	  %>

		</div>
		
		</form>
	</div>	
</body>
</html>