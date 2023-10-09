<!doctype html>

<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>

<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/login.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="login-container">
			<div id="output"></div>
			<div class="avatar">
				<img class="img-fluid img-thumbnail" src="img/PROJECT_LOGO"/>
			</div>
			<br/>
			<div class="form-box">
				<form id="frm" action="j_spring_security_check.do" method="post">
					<input name="username" type="text" placeholder="usuario"/> 
					<input name ="password" type="password" placeholder="contraseña"/>
					<button class="btn btn-info btn-block login" type="submit">Entrar</button>
					<span class="version">Versión PROJECT_VERSION</span>
				</form>
			</div>
			<c:if test="${not empty error}">
				<br/>
				<div class="alert alert-info">${error}</div>
			</c:if>
		</div>
	</body>
</html>