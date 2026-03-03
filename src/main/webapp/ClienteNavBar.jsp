<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>Navbar Cliente</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="ClienteDashboard.jsp">Banco UTN</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">

        <li class="nav-item">
          <a class="nav-link" href="ClienteInicio.jsp">Cambiar Cuenta</a>
        </li>
        <li class="nav-item">
           <a href="#" id="linkHistorial" class="nav-link">Historial</a>
          <form id="formHistorial" method="post" action="servletsClientes" style="display:none;">
            <input type="hidden" name="btnVerHistorial" value="1" />
          </form>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ClientePrestamos.jsp">Préstamos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="ClienteTransferencias.jsp">Transferencias</a>
        </li>

      </ul>

      
      <div class="d-flex ms-auto align-items-center">
        <span class="me-3 fw-bold">
          <%= session.getAttribute("nombre") != null ? session.getAttribute("nombre") : "" %>
          <%= session.getAttribute("apellido") != null ? session.getAttribute("apellido") : "" %>
        </span>
        <a href="Login.jsp" class="btn btn-danger">Salir</a>
      </div>
    </div>
  </div>
</nav>
<script>
  document.getElementById('linkHistorial').addEventListener('click', function(event) {
    event.preventDefault(); 
    document.getElementById('formHistorial').submit();
  });
</script>
</body>
</html>