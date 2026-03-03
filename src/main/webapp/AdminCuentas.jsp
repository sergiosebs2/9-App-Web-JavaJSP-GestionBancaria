<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entidades.CuentaBancaria" import="Entidades.TipoCuenta" import="java.util.ArrayList" import="NegocioInterfaz.INegocioTipoCuenta" import="Negocio.NegocioTipoCuenta"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminCuentas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="Estilos.css" type="text/css"  />

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>

</head>
<body>
<jsp:include page="AdminNavBar.jsp"></jsp:include>
<form action="servletAdminClientes" method="post">
<div class="container mt-5">
		<h2 class="mb-5 text-center"> Cuentas bancarias</h2>
		
		
		<div class="row justify-content-center mt-2">
			<div class="col-md-2">
				<label for="cliente" class="form-label mt-1">Buscar cuentas asociadas a:</label>
			</div>
			<div class="col-md-3">
				 <input type="text" id="cliente" class="form-control" name="codigoCliente" placeholder="Ingresar código del cliente" >
			</div>
		</div>

		<div class="row justify-content-center mt-2">
			<div class="col-md-2">
				<label for="cliente" class="form-label mt-1">Buscar cuentas de tipo:</label>
			</div>
			<div class="col-md-3">
				<select class="form-select w-100" id="tipoCuenta" name="tipoDeCuentas" style="width: 262px;">
	      			<option value="">Seleccione</option>
	      			<option value="A">Caja de ahorro</option>
	      			<option value="C">Cuenta corriente</option>
	  	 		</select>
	  		</div>
		</div>

		<div class="row justify-content-center mt-2">
			<div class="col-md-2">
				<label for="cliente" class="form-label mt-1">Buscar por estado:</label>		
			</div>
			<div class="col-md-3">
				<select class="form-select w-100" id="estadoCuenta" name="estadoCuentas" style="width: 263px;">
		    	  <option value="">Activas / Desactivadas</option>
		    	  <%
					String estadoCuentas = request.getParameter("estadoCuentas");
					if (estadoCuentas == null) estadoCuentas = "";
					%>
					<option value="1" <%= "1".equals(estadoCuentas) ? "selected" : "" %>>Activas</option>
					<option value="0" <%= "0".equals(estadoCuentas) ? "selected" : "" %>>Desactivadas</option>
		    	</select>
			</div>
		</div>

		<div class="row justify-content-center mt-3">
			<input type="submit" class="btn btn-primary mt-2 w-25" name="btnBuscar" value="BuscarCuenta">
		</div>

		<br>
</div>
</form>


<div class="row justify-content-center mt-4">
	<div class="col-md-5 text-center">
		<%
		if (request.getAttribute("noCuentasBancarias") != null) { 
		    int cant = (int) request.getAttribute("noCuentasBancarias");
		    
		    if (cant == 1) { %>
			    <p class="text-danger fs-5 fw-bold text-center mb-4 p-3 border border-danger rounded bg-light w-100 shadow-sm">
			    No se encuentran cuentas bancarias con los filtros pedidos.
			    </p>
			<% } else if(cant == 2) { %>
				<p class="text-danger fs-5 fw-bold text-center mb-4 p-3 border border-danger rounded bg-light w-100 shadow-sm">
			    El cliente no existe.
			    </p>
			<% } else if(cant == 3) { %>
				<p class="text-danger fs-5 fw-bold text-center mb-4 p-3 border border-danger rounded bg-light w-100 shadow-sm">
			    El cliente está dado de baja.
			    </p>
			<% } 
		}%>
	</div>
</div>


<div class="row justify-content-center mt-5">
 <div class="col-md-6">
 	    <table id="table_id" class="display">
        <thead class="table-light">
            <tr>
                <th>Cuenta N°</th>
                <th>Tipo de cuenta</th>
                <th>Cliente asociado</th>
                <th>CBU</th>
                <th>Saldo</th>
                <th>Fecha de alta</th>
                <th>Estado</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <% 
        	ArrayList<CuentaBancaria> cuentas = (ArrayList<CuentaBancaria>) request.getAttribute("cuentas");
        	
        	if (cuentas != null && !cuentas.isEmpty()) {
        		for (CuentaBancaria cuenta : cuentas) {
        %>
             <tr>
                <td><%= cuenta.getNroCuenta() %> </td>
                <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
                <td><%= cuenta.getCliente().getNombre() %></td>
                <td><%= cuenta.getCBU() %></td>
                <td><%= cuenta.getSaldo() %></td>
                <td><%= cuenta.getFecha_alta() %></td>
                <td>
                	<input type="checkbox" name="estado" id="chkEstado"
	         		<%= cuenta.isEstado() ? "checked" : "" %> disabled />
	         	</td>
                <td>
	                <form action="servletAdminClientes" method="post" onsubmit="return confirmarCambio('<%= cuenta.getNroCuenta() %>')">
	   					<input type="hidden" name="estadoActual" value="<%= cuenta.isEstado() %>" />
	   					<input type="hidden" name="nroCuenta" value="<%= cuenta.getNroCuenta() %>" />
	   					<input type="submit" class="btn btn-warning" name="btnEditar" value="Cambiar estado">
	   				</form>
   				</td>
            </tr>
        <% 
        		}
        	} else {
        %>
        	<tr>
        	 	<td colspan="8" class="text-center">No hay cuentas para mostrar.</td>
        	</tr>
        <%
        	}
        %>
        </tbody>
       </table>
       <br>

       
       <% 
	    if (request.getAttribute("cambio") != null) { 
		    boolean cant = (boolean) request.getAttribute("cambio"); 
		    if (cant) { 
		%>
		    <label class="text-success fw-bold fs-4 d-block text-center mb-4 p-3 border border-success rounded bg-light">
		    	${confirmacionEditarEstado}
		    </label>
		<% } else { %>
		    <p class="text-danger fs-5 fw-bold text-center mb-4 p-3 border border-danger rounded bg-light w-100 shadow-sm">
			    ERROR: No se pudo cambiar el estado de la cuenta.
			</p>
		<% }
		} %>
 </div>
</div> 

<script>
    function confirmarCambio(nroCuenta) {
    	return confirm("¿Estás seguro de que deseas cambiar el estado de la cuenta N° " + nroCuenta + "?");
    }
</script>
</body>
</html>