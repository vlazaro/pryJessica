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
				<h2>Listado de Clientes</h2>
			</div>
			<ul>
				<table>
					<tr>
						<td><a href="menu.jsp">Ir al menú</a></td>
					</tr>
				</table>

				<table border="1" width="100%">
					<tr>
						<td>DNI</td>
						<td>NOMBRE</td>
						<td>PRIMER APELLIDO</td>
						<td>SEGUNDO APELLIDO</td>
						<td>USUARIO</td>
						<td>PASSWORD</td>
					</tr>
					<c:forEach var="prop" items="${lstpropietario}">
						<tr>
							<td><c:out value="${prop.dni}" /></td>
							<td><c:out value="${prop.nombre}" /></td>
							<td><c:out value="${prop.priperapellido}" /></td>
							<td><c:out value="${prop.segundoapellido}" /></td>
							<td><c:out value="${prop.usuario}" /></td>
							<td><c:out value="${prop.numerosecreto}" /></td>
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