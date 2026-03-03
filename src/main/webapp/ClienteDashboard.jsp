<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entidades.Cliente,Entidades.CuentaBancaria" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Cliente cliente = (Entidades.Cliente) session.getAttribute("clienteSesion");
    CuentaBancaria cuenta = (Entidades.CuentaBancaria) session.getAttribute("cuentaSesion");
%>

<jsp:include page="ClienteNavBar.jsp"></jsp:include>
<div class="container mt-4">
	<div class="row justify-content-center">
		<div class="col-md-4">
    
    		<label class="h5 form-label d-block fw-bold border-bottom pb-2">Mis datos personales</label>
    
			<div class="mb-2">
				<label class="form-label text-secondary mb-1">Nombre:</label>
				<label id="lblNombreCompleto" class="form-control bg-light">
				<%= cliente.getNombre() %> <%= cliente.getApellido() %>
				</label>
			</div>
			<div class="mb-2">
				<label class="form-label text-secondary mb-1">DNI:</label>
				<label id="lblDNI" class="form-control bg-light"><%= cliente.getDni() %></label>
			</div>
			<div class="mb-2">
				<label class="form-label text-secondary mb-1">Ubicación:</label>
				<label id="lblUbicacion" class="form-control bg-light">
				<%= cliente.getLocalidad().getDescripcion() %>, <%= cliente.getProvincia().getDescripcion() %>
				</label>
			</div>
			<div class="mb-2">
				<label class="form-label text-secondary mb-1">Teléfono:</label>
				<label id="lblTelefono" class="form-control bg-light"><%= cliente.getTelefono() %></label>
			</div>
			<div class="mb-2">
				<label class="form-label text-secondary mb-1">Correo electrónico:</label>
				<label id="lblCorreoElectronico" class="form-control bg-light"><%= cliente.getCorreoElectronico() %></label>
			</div>
		</div>
		<div class="col-md-7">
		
			<div class="row justify-content-center g-4 py-4">		    
			    <div class="col-md-4 position-relative pe-md-3">
			        <div class="card h-100 border-primary ">
			            <a href="ClienteTransferencias.jsp" class="text-decoration-none">
			                <div class="card-body text-center py-4 bg-primary bg-opacity-10">
								<h5 class="card-title text-primary">Transferir</h5>
			                    <p class="card-text text-muted">Transferi en el acto hasta $500000</p>
			                </div>
			            </a>
			        </div>
			    </div>
			    <div class="col-md-4 position-relative px-md-3">
			        <div class="card h-100 border-success ">
			            <a href="ClientePrestamos.jsp" class="text-decoration-none">
			                <div class="card-body text-center py-4 bg-success bg-opacity-10">
			                  <h5 class="card-title text-success">Prestamos</h5>
			                    <p class="card-text text-muted">Solicita tu prestamo personal!</p>
			                </div>
			            </a>
			        </div>
			    </div>
			</div>

			<!-- Nueva card de Saldo -->
			<div class="row justify-content-center g-4 py-4">
			    <div class="col-md-8">
			        <div class="card h-100 border-info">
			            <div class="card-body text-center py-4 bg-info bg-opacity-10">
			                <h5 class="card-title text-info">Saldo actual</h5>
			                <p class="card-text fw-bold fs-4 text-dark">
			                    <% if (cuenta != null) { %>
			                        $<%= cuenta.getSaldo() %>
			                    <% } else { %>
			                        No se encontró la cuenta seleccionada.
			                    <% } %>
			                </p>
			            </div>
			        </div>
			    </div>
			</div>

		</div>
	</div>
</div>
</body>
</html>