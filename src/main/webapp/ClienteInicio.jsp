<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*"%>
    <%@ page import="Entidades.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Selección de Cuenta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-body-tertiary">
			<div class="container-fluid">
				<div class="d-flex ms-auto align-items-center">
					<span class="me-3 fw-bold">
					<%= session.getAttribute("nombre") != null ? session.getAttribute("nombre") : "" %>
					<%= session.getAttribute("apellido") != null ? session.getAttribute("apellido") : "" %>
					</span>
					<a href="Login.jsp" class="btn btn-danger">Salir</a>
				</div>
			</div>
		</nav>
	</header>

	<div class="container text-center">
	    <div class="row align-items-center">
	    	<div class="col-md-6 offset-md-3">
	    		<h2 class="mt-5 mb-5 text-center">Elija una cuenta</h2>
	    	<form method="post" action="servletsClientes">
	    		
			<select name="numeroCuenta" class="form-select">
    <% 
        ArrayList<CuentaBancaria> cuentas = (ArrayList<CuentaBancaria>) session.getAttribute("cuentasBancarias");
        String cuentaSeleccionada = request.getParameter("numeroCuenta");
        
        if(cuentas != null && !cuentas.isEmpty()) {
            for(CuentaBancaria cuenta : cuentas) { 
            	String valorCuenta = String.valueOf(cuenta.getNroCuenta());
                %>
                            <option value="<%= valorCuenta %>"
                                <%= (cuentaSeleccionada != null && cuentaSeleccionada.equals(valorCuenta)) ? "selected" : "" %>>
                                <%=  cuenta.getTipoCuenta().getDescripcion() + " Número: " + valorCuenta + " - " + " CBU " + cuenta.getCBU()%>
                            </option>
    <% 
            }
        } else {
    %>
            <option disabled selected>No hay cuentas disponibles</option>
    <% 
        } 
    %>
</select>

<input type="submit" class="btn btn-secondary w-100 py-1 d-flex mt-3 text-center" name="btnSeleccionCuenta" value="Continuar">

</form>
	
	    		
	    	</div>
	    </div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.min.js" integrity="sha384-7qAoOXltbVP82dhxHAUje59V5r2YsVfBafyUDxEdApLPmcdhBPg1DKg1ERo0BZlK" crossorigin="anonymous"></script>
</body>
</html>