<%@page import="Entidades.CuentaBancaria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ClienteTransferencias</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<header>
    	<jsp:include page="ClienteNavBar.jsp"></jsp:include>
	</header>
	
	<main class="container mt-5">
		<h1 class="mb-4">Transferencias</h1>
		<form id="formTransferencia" action="servletsClientes" method="post">
			<div class="mb-3">
				<h5>Cuenta de Origen:</h5>
				<div class="card">
					<div class="card-body">
					<% CuentaBancaria cuentaBancaria = (CuentaBancaria)request.getSession().getAttribute("cuentaElegida");
					String cbu = cuentaBancaria.getCBU();
					String tipoDeCuenta = cuentaBancaria.getTipoCuenta().getDescripcion();
					request.setAttribute("tipoCuenta", tipoDeCuenta);
					request.setAttribute("cbu", cbu);
					%>
						${tipoCuenta} | CBU: ${cbu}
					</div>
				</div>
       			
      		</div>
      		<div class="mb-3">
				<label id="cbuDestino" class="form-label">CBU destino</label>
				<input required type="number" min="0" step="1" oninput="this.value = this.value.replaceAll(/[.,]/g, '')" class="form-control" id="cbuDestino" name="txtCbuDestino" maxlength="22" placeholder="Ingrese el CBU de la cuenta destino">
			</div>
			<div class="mb-3">
				<label id="Monto" class="form-label">Monto a transferir</label>
				<input required type="number" step="0.01" min="0" oninput="this.value = this.value.replaceAll(/[.,]/g, '')" class="form-control" id="Monto" name="txtMonto" maxlength="22" placeholder="Ingrese el monto a transferir">
			</div>
			<div class="mb-3">
				<label class="form-label">Detalles</label>
				<input required type="text" class="form-control" name="txtDetalle" maxlength="80" placeholder="Ingrese detalle de transferencia">
			</div>
      		<button type="submit" onclick="confirmarTransferencia()" class="btn btn-primary mt-2" name="btnTransferir">Confirmar transferencia</button>
      		<%
      		if(request.getAttribute("transferenciaRealizada") != null)
      		{
      			boolean transferenciaRealizada = (boolean)request.getAttribute("transferenciaRealizada");
      			if(transferenciaRealizada)
          		{%>
          			<div class="card text-bg-success mt-3" style="max-width: 18rem;">
    				  <div class="card-body">
    				    <h5 class="card-title">Se realizó la transferencia. Saldo restante: $ ${saldoRestante}</h5>
    				  </div>
    				</div>
          			
          		<%} else {%>
          			<div class="card text-bg-danger mt-3" style="max-width: 18rem;">
    				  <div class="card-body">
    				    <h5 class="card-title">No se pudo realizar la transferencia.</h5>
    				  </div>
    				</div>
          			
          		<%} %>
      		<%} %>
      		
      		
		</form>
	</main>

<script>
        function confirmarTransferencia() {
            if (confirm("¿Seguro de que desea realizar esta transferencia?")) {
                document.getElementById("formTransferencia").submit();
            }
        }
    </script>
</body>
</html>