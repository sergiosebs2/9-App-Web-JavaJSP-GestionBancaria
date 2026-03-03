<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Ingrese a la cuenta</title>
</head>
<body>

	<div class="container-fluid vh-100" style="height: 100vh;">
        <div class="row h-100">
            <div class="col-md-8 p-0 h-100">
                <img class="w-100 h-100" src="https://images.unsplash.com/photo-1501167786227-4cba60f6d58f?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmFua3xlbnwwfHwwfHx8MA%3D%3D" alt="Imagen" class="img-fluid">
            </div>
            <div class="col align-self-center col-6 col-md-4">
                <div class="justify-content-end">
                    <form action="LoginServlet" method="post">
                        <div class="form-group">
                            <label for="usuario">Usuario</label>
                            <input required type="text" class="form-control" id="usuario" placeholder="Ingrese su nombre de usuario" name="txtUsuario">
                            
                        </div>
                        <div class="form-group">
                            <label for="contrasenia">Contraseña</label>
                            <input required type="password" class="form-control" id="contrasenia" placeholder="Ingrese su contraseña" name="txtContra">
                        </div>
                        <div class="form-check form-check-inline">
                            <input required class="form-check-input" type="radio" name="TipoUsuario" value="Administrador">
                            <label class="form-check-label" for="TipoUsuario">Administrador</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input required class="form-check-input" type="radio" name="TipoUsuario" value="Cliente">
                            <label class="form-check-label" for="TipoUsuario">Cliente</label>
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block" name="btnIngresar">Ingresar</button>
                        <% if (request.getSession().getAttribute("inicioDenegado") != null) { %>
					    	<p style="color: red;"><%= request.getSession().getAttribute("inicioDenegado") %></p>
						<% } %>
                    </form>
                </div>
            </div>
        </div>
    </div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>