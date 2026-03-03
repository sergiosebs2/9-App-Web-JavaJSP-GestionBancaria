<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Menú de Administrador</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="InicioAdmin.jsp">Banco UTN</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Clientes
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="servletAdminClientes?param=1">Agregar</a></li>
            <li><a class="dropdown-item" href="AdminEliminarCliente.jsp">Eliminar</a></li>
            <li><a class="dropdown-item" href="AdminUsuarios.jsp">Usuarios</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Cuentas
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="AdminAsignarCuentas.jsp">Asignar</a></li>
            <li><a class="dropdown-item" href="AdminCuentas.jsp">Listado</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="AdminPrestamos.jsp">Prestamos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="AdminInformes.jsp">Informes</a>
        </li>
      </ul>
      <div class="d-flex ms-auto align-items-center">
        <span class="me-3 fw-bold">Admin</span>
       <a href="Login.jsp" class="btn btn-danger">Salir</a>
      </div>
    </div>
  </div>
</nav>

</body>
</html>