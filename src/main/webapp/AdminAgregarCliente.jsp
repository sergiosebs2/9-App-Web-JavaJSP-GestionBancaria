<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="AdminNavBar.jsp"></jsp:include>
<div class="container mt-5">
        <h2 class="mb-4 text-center">Agregar Cliente</h2>
        <form action="servletAdminClientes" method="post">
        
        <div class="row mb-3">
                <div class="col-md-6">
                    <label for="dni" class="form-label">DNI</label>
                    <input required type="text" pattern="[0-9]{8}" class="form-control" id="dni" name="txtDni" value="${dni}">
                </div>
                <div class="col-md-6">
                    <label for="cuil" class="form-label">CUIL</label>
                    <input required type="text" pattern="[0-9]{11}"  class="form-control" id="cuil" name="txtCuil" value="${cuil}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input required type="text" pattern="^[A-Za-zÁÉÍÓÚáéíóúñÑüÜ ]+$" class="form-control" id="nombre" name="txtNombre" value="${nombre}">
                </div>
                <div class="col-md-6">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input required type="text" pattern="^[A-Za-zÁÉÍÓÚáéíóúñÑüÜ ]+$" class="form-control" id="apellido" name="txtApellido" value="${apellido}">
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label d-block">Sexo</label>
                <div class="form-check form-check-inline">
                    <input required class="form-check-input" type="radio" name="rdoSexo" id="femenino" value="F" ${sexo == 'F' ? 'checked' : ''}>
                    <label class="form-check-label" for="femenino">Femenino</label>
                </div>
                <div class="form-check form-check-inline">
                    <input required class="form-check-input" type="radio" name="rdoSexo" id="masculino" value="M" ${sexo == 'M' ? 'checked' : ''}>
                    <label class="form-check-label" for="masculino">Masculino</label>
                </div>
                <div class="form-check form-check-inline">
                    <input required class="form-check-input" type="radio" name="rdoSexo" id="otro" value="O" ${sexo == 'O' ? 'checked' : ''}>
                    <label class="form-check-label" for="otro">Otro</label>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="nacionalidad" class="form-label">Nacionalidad</label>
                    <select required class="form-select" id="nacionalidad" name="ddlnacionalidad">
                    <option value="">Seleccione</option>
                    <option>${nacionalidades}</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
                    <input required type="date" max="2007-06-30" class="form-control" id="fechaNacimiento" name="txtfechaNacimiento" value="${fechaNacimiento}">
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="provincia" class="form-label">Provincia</label>
                    <select required class="form-select" id="provincia" name="ddlprovincia" onchange="cargarLocalidades()">
                    <option value="">Seleccione</option>
                     <option>${provincias}</option>
                        </select>
                </div>
                <div class="col-md-4">
                    <label for="localidad" class="form-label">Localidad</label>
                    <select required class="form-select" id="localidad" name="ddllocalidad">
                        <option value="">Seleccione</option>
                        <option>${localidades}</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input required type="text" pattern="[a-zA-Z0-9\ áéíóúÁÉÍÓÚñÑüÜ]*" title="Solo se permiten letras y números" class="form-control" id="direccion" name="txtDireccion" value="${direccion}">
                </div>
            </div>

                <div class="row mb-3">
                <div class="col-md-6">
                    <label for="correo" class="form-label">Correo Electrónico</label>
                    <input required type="email" class="form-control" id="correo" name="txtCorreo" value="${correo}">
                </div>
                <div class="col-md-6">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input required type="text" pattern="[0-9]{10}" class="form-control" id="telefono" name="txtTelefono" value="${telefono}">
                </div>


            <div class="row mb-3">
                <div class="col-md-4">
                    <label for="usuario" class="form-label">Usuario</label>
                    <input required type="text" pattern="[a-zA-Z0-9\ áéíóúÁÉÍÓÚñÑüÜ]*" class="form-control" id="usuario" name="txtUsuario" value="${usuario}">
                </div>
                <div class="col-md-4">
                    <label for="contraseña" class="form-label">Contraseña</label>
                    <input required type="password" pattern="[a-zA-Z0-9\ áéíóúÁÉÍÓÚñÑüÜ]*" min="7" class="form-control" id="contrasenia" name="txtContrasenia" value="${contrasenia}">
                </div>
                <div class="col-md-4">
                    <label for="confirmarContraseña" class="form-label">Confirmar Contraseña</label>
                    <input required type="password" pattern="[a-zA-Z0-9\ áéíóúÁÉÍÓÚñÑüÜ]*" min="7" class="form-control" id="confirmarContrasenia" name="txtconfirmarContrasenia" value="${confirmarContrasenia}">
                </div>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary" name="accion" value="Agregar">Agregar Cliente</button>
            </div>
            <%
    String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) {
        boolean esExito = mensaje.toLowerCase().contains("exito");
%>
    <div class="alert <%= esExito ? "alert-success" : "alert-danger" %> mt-2">
        <%= mensaje %>
    </div>
<% } %>
            </div>         
            </form>          
            </div>    
            
            <script>
    function cargarLocalidades() {
        const idProvincia = document.getElementById("provincia").value;

        fetch("servletAdminClientes?accion=cargarLocalidades&idProvincia=" + idProvincia)
            .then(response => response.text())
            .then(htmlOptions => {
                const ddlLocalidad = document.getElementById("localidad");
                ddlLocalidad.innerHTML = htmlOptions;
            })
            .catch(error => console.error("Error al cargar localidades:", error));
    }
</script>      

</body>
</html>