<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/view.css" media="all" />
<script type="text/javascript" src="js/view.js"></script>

</head>
<body id="main_body">

	<img id="top" src="top.png" alt="" />
	<div id="form_container">

		<h1>
			<a>Login</a>
		</h1>
		<form id="form_10860" class="appnitro" method="post" action="">
			<div class="form_description">
				<h2>Listado de Cuentas</h2>
			</div>
			<ul>
				<table>
					<tr>
						<td><a href="menu.jsp">Ir al menú</a></td>
					</tr>
				</table>

				<table border="1" width="100%">
					<tr>
						<td>NUMERO CUENTA</td>
						<td>DNI PROPIETARIO</td>
						<td>NOMBRE PROPIETARIO</td>
						<td>SALDO</td>
					</tr>
					<c:forEach var="cta" items="${lstcuentas}">
						<tr>
							<td><c:out value="${cta.numcuenta}" /></td>
							<td><c:out value="${cta.propietario.dni}" /></td>
							<td><c:out value="${cta.propietario.nombre}" /></td>
							<td><c:out value="${cta.saldo}" /></td>
						</tr>
					</c:forEach>
				</table>
			</ul>
		</form>
		<div id="footer">Generado por Jessica Boggio Noriega</div>
	</div>
	<img id="bottom" src="bottom.png" alt="" />
</body>
</html>