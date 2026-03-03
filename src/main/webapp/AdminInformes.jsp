<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entidades.Cliente" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminInformes</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
<jsp:include page="AdminNavBar.jsp"></jsp:include>
<h2 class="mb-3 mt-3 text-center">Generador de Informes</h2>


<form action="servletAdminClientes" method="post">
	<div class="row justify-content-center mt-2">
		<div class="col-md-12 mt-3 text-center">
			<label for="dtpFechaInicial" class="form-label ms-4">Fecha Inicial:</label>
		    <input required type="date" class="ms-2"id="dtpFechaInicial" name="dtpFechaInicial">
		    
		    <label for="dtpFechaFinal" class="form-label ms-4">Fecha Final:</label>
		    <input required type="date" class="form-label ms-2"id="dtpFechaFinal" name="dtpFechaFinal">
	    </div>
		    <input type="submit" class="btn btn-primary mt-2 w-25" name="btnGenerarInforme" value="Generar informe">
    </div>

	<% if (request.getAttribute("fechaInicio") != null)
	{ %>
		<div class="container mt-4 text-center">
			<div class="row">
				<div class="col">
					<b>Fecha Inicial Seleccionada: <label>${fechaInicio}</label></b>
				</div>
				<div class="col">
					<b>Fecha Final Seleccionada: <label>${fechaFinal}</label></b>
				</div>
			</div>
		</div>

		<div class="container mt-4 text-center">
			<div class="row justify-content-center">
				<div class="col">
					<p class="fw-medium text-primary-emphasis">Cantidad de Cuentas Dadas de Alta: ${cuentasDadasAlta}</p>
					<table class="table table-bordered table-hover">
						<thead class="table-light">
						<tr>
							<th>Caja de ahorro</th>
							<th>Cuenta corriente</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>${cuentasAltaCajaAhorro}</td>
							<td>${cuentasAltaCorriente}</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="col">
					<p class="fw-medium text-primary-emphasis">Cantidad de cuentas dadas de baja: ${cuentasDadasBaja}</p>
					<table class="table table-bordered table-hover">
						<thead class="table-light">
						<tr>
							<th>Caja de ahorro</th>
							<th>Cuenta corriente</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>${cuentasBajaCajaAhorro}</td>
							<td>${cuentasBajaCorriente}</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row mt-4 justify-content-center">
					<%
						ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("clientesNuevosLista");

						if (clientes != null && !clientes.isEmpty()) {	%>
					<div class="col">
						<p class="fw-semibold text-primary-emphasis">Clientes nuevos asociados: <%= clientes.size() %></p>
						<table class="table table-bordered table-hover" id="table_id">
							<thead class="table-light">
							<tr>
								<th>Código cliente</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Fecha de alta</th>
							</tr>
							</thead>
							<tbody>
							<% for (Cliente c : clientes) { %>
							<tr>
								<td><%= c.getCodCliente() %></td>
								<td><%= c.getNombre() %></td>
								<td><%= c.getApellido() %></td>
								<td><%= c.getFechaDadoAlta() != null ? c.getFechaDadoAlta().toLocalDate() : "-" %></td>
							</tr>
							<% } %>
							<% } else { %>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<% } %>
							</tbody>
						</table>
					</div>


					<div class="row justify-content-center mt-3 ms-4">
						<div class="col">
							<div class="card text-bg-light mb-3" style="max-width: 18rem;">
								<div class="card-header"> <h5>Préstamos</h5></div>
								<div class="card-body">
									<p class="card-text">Activos: ${prestamosActivos}</p>
									<p class="card-text">Atrasados: ${prestamosAtrasados}</p>
									<p class="card-text">Saldados: ${prestamosSaldados}</p>
									<p class="card-text">Cumplibilidad de préstamos: ${cumplibilidad}</p>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card text-bg-light mb-3" style="max-width: 18rem;">
								<div class="card-header"> <h5>Saldos</h5> </div>
								<div class="card-body">
									<p class="card-text">Promedio por cuenta: ${saldoPromedio}</p>
									<p class="card-text">Total en el banco: ${saldoBanco}</p>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card text-bg-light mb-3" style="max-width: 18rem;">
								<div class="card-header"> <h5>Movimientos</h5> </div>
								<div class="card-body">
									<p class="card-text">Transferencias realizadas: ${transferencias}</p>
									<p class="card-text">Totales realizados: ${movimientos}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	<%}%>


</form>

</body>
</html>