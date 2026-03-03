<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Inicio</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="InicioAdmin.jsp">Banco UTN</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
     
      <div class="d-flex ms-auto align-items-center">
        <span class="me-3 fw-bold">Admin</span>
       <a href="Login.jsp" class="btn btn-danger">Salir</a>
      </div>
    
  </div>
</nav>
  </header>

  <main class="container mt-4">
    <h1 class="text-center mb-5">¡Bienvenido/a Administrador/a!</h1>

    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="servletAdminClientes?param=1" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Agregar Cliente</h5>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminEliminarCliente.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4"> 
              <h5 class="card-title text-primary">Eliminar Cliente</h5>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminCuentas.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Administrar Cuentas</h5>
            </div>
          </a> 
        </div>
      </div>
    </div>

    <div class="row justify-content-center g-4 py-4">
      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminAsignarCuentas.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Asignar Cuentas</h5>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminPrestamos.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Administrar Préstamos</h5>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminUsuarios.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Administrar Usuarios</h5>
            </div>
          </a>
        </div>
      </div>

      <div class="col-md-4">
        <div class="card h-100 border-primary">
          <a href="AdminInformes.jsp" class="text-decoration-none">
            <div class="card-body text-center bg-primary bg-opacity-10 py-4">
              <h5 class="card-title text-primary">Informes</h5>
            </div>
          </a>
        </div>
      </div>
    </div>
  </main>
</body>
</html>