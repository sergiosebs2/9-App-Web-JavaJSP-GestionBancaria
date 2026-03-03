
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Entidades.Cliente" %>
    
   
<!DOCTYPE html>
<html>

<head>
	<style>
		h1 {
    	text-align: center;
		}
	</style>
	<meta charset="UTF-8">
	
	<title>Eliminar cliente</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<header>
    	<jsp:include page="AdminNavBar.jsp"></jsp:include>
	</header>

	<main class="container mt-4">
    	<h1 class="mb-4">Eliminar cliente</h1>
    	<div class="card p-4">
    		<form action="servletAdminClientes" class="mb-3" method="get">
        		<label for="usuario" class="form-label">Buscar cliente por código:</label>
        		<input type="number" id="usuario" name="codigoCliente"  class="form-control" required>
        		<button type="submit" name="btnBuscarClienteAEliminar" class="btn btn-primary mt-2">Buscar</button>
    		</form>

    		<table class="table table-bordered table-hover">
        		<thead class="table-light">
            	<tr>
                	<th>Usuario</th>
                	<th>DNI</th>
                	<th>CUIL</th>
                	<th>Nombre</th>
                	<th>Apellido</th>
                	<th>Correo electrónico</th>
            	</tr>
        		</thead>
        		<tbody>
        		
            		<%
                        Cliente cliente = (Cliente) request.getAttribute("cliente");
                        String codigoBuscado = request.getParameter("codigoCliente");

                        if (cliente != null && cliente.isEstado() != false) {
                    %>
                    <tr>
                        <td><%= cliente.getUsuario().getIdUsuario() %></td>
                        <td><%= cliente.getDni() %></td>
                        <td><%= cliente.getCuil() %></td>
                        <td><%= cliente.getNombre() %></td>
                        <td><%= cliente.getApellido() %></td>
                        <td><%= cliente.getCorreoElectronico() %></td>
                    </tr>
                    <%
                        } else {
                    %>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <%
                        }
                    %>
        		</tbody>
    		</table>
    		
    		<%
    		String mensaje = (String) request.getAttribute("mensajeNoEncontrado");
    		if (mensaje != null) {
      
			%> 
    		<div class="mt-3 text-danger text-center">
        		<%= mensaje %>
    		</div>
			<%
    		}
		%>
		 <%
                String mensajeEliminacion = (String) request.getAttribute("mensajeEliminacion");  
                if (mensajeEliminacion != null) {                                                  
                    boolean exito = mensajeEliminacion.toLowerCase().contains("exitosamente");     
            %>
            <div class="mt-3 text-center <%= exito ? "text-success" : "text-danger" %>">        
                <%= mensajeEliminacion %>                                                        
            </div>                                                                               
            <%
                }
            %>
            <%
				
				if (cliente != null && cliente.isEstado()) {
			%>
			<form id="formEliminar" action="servletAdminClientes" method="post">
				<input type="hidden" name="accion" value="eliminar">
				<input type="hidden" name="codigoCliente" value="<%= cliente.getCodCliente() %>">
				<button type="button" class="btn btn-danger" onclick="confirmarEliminacion()">Eliminar</button>
			</form>
			<%
				}
			%>
            
        </div>
    </main>


	
	
	<script>
        function confirmarEliminacion() {
            if (confirm("¿Estás seguro de que deseas eliminar este cliente?")) {
                document.getElementById("formEliminar").submit();
            }
        }
    </script>
</body>
</html>