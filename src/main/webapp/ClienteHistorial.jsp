<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Entidades.CuentaBancaria" %>
    <%@ page import="Entidades.Movimiento" %>
    <%@ page import="java.util.ArrayList" %>
    
<!DOCTYPE html>
<html>
<head>
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

	<!-- DataTables JS -->
	<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	<meta charset="UTF-8">
	<title>ClienteHistorial</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<script>
  $(document).ready(function() {
    $('#TablaMovimientos').DataTable({
    	searching: false,       
        lengthChange: false,     
        pageLength: 5   });
      
  });
</script>
</head>
<body>

	<header>
    	<jsp:include page="ClienteNavBar.jsp"></jsp:include>
	</header>
	<main class="container mt-5">
    <h1 class="mb-4">Historial de Movimientos</h1>
    
	<form method="post" action="servletsClientes" class="row g-3">

    <div class="col-md-4">
        <label for="tipoMovimiento" class="form-label">Filtrar por tipo de movimiento</label>
        <select name="tipoMovimiento" id="tipoMovimiento" class="form-select">
            <option value="">Todos</option>
            <option value="TT">Transferencia</option>
            <option value="PP">Pago de prestamo</option>
            <option value="AP">Alta de prestamo</option>
            <option value="AC">Alta de cuenta</option>
            
        </select>
    </div>

    <div class="col-md-2 align-self-end">
        <button type="submit" name="btnHistorialFiltrado" value="filtrar" class="btn btn-primary">Filtrar</button>
    </div>
</form>
    
    <div class="table-responsive mt-4">
      <table  class="table table-bordered table-hover" id="TablaMovimientos">
      
     
      
        <thead >
          <tr>
            <th scope="col">Fecha</th>
            <th scope="col">Detalle / Concepto</th>
            <th scope="col">Importe</th>
            <th scope="col">Tipo de movimiento</th>
          </tr>
        </thead>
        <tbody>
       
         <%
                        ArrayList<Movimiento> listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("listaMovimientos");
                        if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
                            for (Movimiento mov : listaMovimientos) {
                    %>
                                <tr>
                                    <td><%= mov.getFecha() %></td>
                                    <td><%= mov.getDetalle() %></td>
                                    <td class="<%= mov.getImporte().compareTo(new java.math.BigDecimal("0")) > 0 ? "text-success" : "text-danger" %>">
                                        <%= (mov.getImporte().compareTo(new java.math.BigDecimal("0")) > 0 ? "+" : "") + mov.getImporte() %>
                                    </td>
                                    <td><%= mov.getTipoMovimiento().getDescripcion() %></td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="4" class="text-center">No hay movimientos para mostrar</td>
                            </tr>
                    <%
                        }
                    %>
        </tbody>
     	</table>
     	
     	
	
     	
      </div>
    </main>


</body>
</html>