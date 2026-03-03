<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="Entidades.*"%>
      <%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AdminUsuarios</title>
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
            "pageLength": 5, 
            "lengthMenu": [5, 10, 25, 50],
           });
	});
</script>
</head>
<body>
<jsp:include page="AdminNavBar.jsp"></jsp:include>
<div class="container mt-2">
	<h2 class="mb-4 mt-4 text-center"> Gestión de Usuarios</h2>
	
<%
		if(request.getAttribute("confirmacion") != null) {
		int confirmacion = (int)request.getAttribute("confirmacion");
		%> <% if (confirmacion == 1) { %>
			<div class="alert alert-success mt-3" role="alert">
			<i class="bi bi-check-circle-fill"></i> ¡Contraseña actualizada correctamente!
			</div>
		<% } else { %>
			<div class="alert alert-danger mt-3" role="alert">
			<i class="bi bi-check-circle-fill"></i> ERROR: No se pudo actualizar la contraseña.
			</div>
		<% }
		} %>

<div class="row mt-4">
		
	<div class="col-md-8">
	<div class="row">       
    
        <div class="col-md-6">
		    	<form action="servletAdminClientes" method="get">
		        <div class="mb-3">
		            <label for="idUsuario">Buscar por ID Usuario:</label>
		            <input type="text" id="idUsuario" name="idUsuario" class="form-control py-1" placeholder="Ingresar ID de Usuario" required>
		            <input type="submit" class="btn btn-secondary w-100 py-1 d-flex mt-3 text-center" name="btnFiltrarporUsuario" value="Filtrar por Usuario">
		        </div>
		    </form>           
		</div>        
		
		<div class="col-md-6">
		    	<form action="servletAdminClientes" method="get">
		        <div class="mb-3">
		            <label for="cliente">Buscar por cliente asociado:</label>
		            <input type="text" id="cliente" name="cliente" class="form-control py-1" placeholder="Ingresar código del cliente asociado" required>
		            <input type="submit" class="btn btn-secondary w-100 py-1 d-flex mt-3 text-center" name="btnFiltrarporCliente" value="Filtrar por Cliente">
		        </div>
		    </form>           
		</div> 
        </div>  
        <form action="servletAdminClientes" method="get">
        <div class="row mt-4 mb-5">
		
       		 <input type="submit" class="btn btn-warning w-75 py-1 d-flex mx-auto text-center " name="btnBuscarTodo" value="Mostrar Todos los Usuarios">
		
		</div>
		</form>
		<form action="servletAdminClientes" method="post">

	 <%
	 ArrayList<Usuario> listaUsuarios = null;
	if(request.getAttribute("listaU") != null) {
		 
		listaUsuarios = (ArrayList<Usuario>) request.getAttribute("listaU");    
	}
	%>
    <table class="table table-striped table-hover mt-4 text-center display" id="table_id">    
        		<thead class="table-light ">
            <tr>
                <th>ID Usuario</th>
                <th>Tipo de usuario</th>
                <th>Cliente asociado</th>
                <th>Nombre</th>
                <th>Contraseña</th>
                <th>Estado</th>
                 <th>Editar</th>
            </tr>
        </thead>
        <tbody>
         <% if(listaUsuarios != null && !listaUsuarios.isEmpty()) {
        for(Usuario us : listaUsuarios) { %>
             <tr>
	        <td><%= us.getIdUsuario() %></td>   
	        <td>Cliente</td>  
	        <td><%= us.getClienteAsociado() %></td> 	        
	        <td><%= us.getNombreUsuario() %></td>   
	        <td><%= "******" %></td> 
			<td><input type="checkbox" class="form-check-input " name="estado" id="chkEstado" <%= us.isEstado() ? "checked" : "" %> disabled  /></td>
			<td>
    			<input type="radio" name="usuarioSeleccionado" value="<%= us.getIdUsuario() %>" class="form-check-input"
    			        <%= (request.getParameter("usuarioSeleccionado") != null && 
                	request.getParameter("usuarioSeleccionado").equals(String.valueOf(us.getIdUsuario()))) ? "checked" : "" %>>
			</td>					
            </tr>
            <%}} %>
        </tbody>        
       </table>
  </form>
</div>

<div class="col-md-4 mt-4">
<form action="servletAdminClientes" method="post">
	<%
	Usuario us = null;
	if(request.getAttribute("Ufiltrado") != null) {
		 
		us = (Usuario) request.getAttribute("Ufiltrado");    
	}
	
	%>
<div class="card border-secondary rounded-1">  
  <div class="card-header bg-opacity-10 d-flex justify-content-between align-items-center py-2 ">
  <span class="fs-5 text-success">→</span>
    <h5 class="card-title fw-semibold fst-italic mb-0">Modificar Usuario - Nueva Contraseña</h5>    
  </div>
 
  <div class="card-body p-0">   
    <div class="row g-0 "> 
      <div class="col-md-6 p-3 border-end">
        <label class="form-label small text-muted mb-1">Tipo de Usuario</label>
        <input type="text" class="form-control form-control-sm" id="di1" value="<%= us != null ? us.getTipoUsuario().getDescripcion() : "" %>" name="txtInputNombreUsuario" readonly>
      </div>
      
      <div class="col-md-6 p-3">
        <label class="form-label small text-muted mb-1">Nombre</label>
        <input type="text" class="form-control form-control-sm" id="di1" value="<%= us != null ? us.getNombreUsuario() : "" %>" name="txtInputNombreUsuario" readonly>
      </div>
    </div>
      
    <div class="row g-0 bg-light"> 
      <div class="col-md-6 p-3 border-end">
        <label class="form-label small text-muted mb-1">Cliente Asociado</label>
        <input type="text" class="form-control form-control-sm" id="di2" value ="<%=us !=null ? us.getClienteAsociado() : ""%>" name="txtInputClienteAsociado" readonly>
      </div>
      
      <div class="col-md-6 p-3">
        <label class="form-label small text-muted mb-1">Contraseña</label>
        <div class="input-group input-group-sm">
          <input type="password" class="form-control" id="di3" value="<%= us!=null ? us.getContrasena() : "" %>" name="txtInputContraseña" readonly>          
        </div>
      </div>
    </div>   
    
    <div class="p-3">             
            <button class="btn btn-outline-warning w-100" type="button" data-bs-toggle="collapse" data-bs-target="#seccionEdicion" name="btnEditarContraseña">
                <i class="bi bi-pencil-square me-2"></i>Habilitar edición
            </button>            
           
            <div class="collapse mt-3" id="seccionEdicion">
                <div class="card card-body bg-light">
                    <h5 class="fw-semibold fst-italic mb-3">Completar </h5>                    
                    
                        <div class="mb-3">
                            <label class="form-label">Ingrese la nueva contraseña</label>
                            <input type="password" class="form-control" name="nuevaContrasena" required>
                            <br>
                            <label class="form-label">Confirme la nueva contraseña</label>
                            <input type="password" class="form-control" name="confNuevaContrasena" required>
                        </div>
						            <input type="button" class="btn btn-secondary" value="Guardar cambio" 
                   data-bs-toggle="modal" data-bs-target="#confirmarCambioModal">
        </div>
    </div>
   
    <div class="modal fade" id="confirmarCambioModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">¿Estás seguro?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <i class="bi bi-exclamation-triangle-fill text-warning me-2"></i>
                    Esta acción no se puede deshacer.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                 
                    <input type="submit" class="btn btn-warning" name="btnCambioContra" value="Sí, actualizar">
						</div>
            </div>
						
        </div>                
        </div>        
        </div>
		</div>
		</div>
		</form>	
		</div>
		</div>
		</div>
		

<script>
document.querySelectorAll('input[type="radio"][name="usuarioSeleccionado"]').forEach(radio => {
  radio.addEventListener('change', function() {
    this.form.submit();
  });
});
</script>
</body>
</html>