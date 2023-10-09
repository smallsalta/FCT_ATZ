<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="container">
	<div class="row">
		<div class="col col-md-2">
			DNI
		</div>
		<div class="col col-md-10">
			<input id="dni" name="dni" class="form-control" value="${cliente.dni}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Nombre *
		</div>
		<div class="col col-md-10">
			<input id="nombre" name="nombre" class="form-control" required value="${cliente.nombre}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Apellidos
		</div>
		<div class="col col-md-10">
			<input id="apellidos" name="apellidos" class="form-control" value="${cliente.apellidos}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Dirección
		</div>
		<div class="col col-md-10">
			<input id="direccion" name="direccion" class="form-control" value="${cliente.direccion}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Localidad
		</div>
		<div class="col col-md-10">
			<input id="localidad" name="localidad" class="form-control" value="${cliente.localidad}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Provincia
		</div>
		<div class="col col-md-10">
			<input id="provincia" name="provincia" class="form-control" value="${cliente.provincia}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Código postal
		</div>
		<div class="col col-md-10">
			<input id="cp" name="cp" class="form-control" pattern="^[0-9]{5}$" value="${cliente.cp}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Teléfono 1
		</div>
		<div class="col col-md-10">
			<input id="telefono1" name="telefono1" class="form-control" pattern="^[6|7|8|9]\d{8}$" value="${cliente.telefono1}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Teléfono 2
		</div>
		<div class="col col-md-10">
			<input id="telefono2" name="telefono2" class="form-control" pattern="^[6|7|8|9]\d{8}$" value="${cliente.telefono2}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Correo electrónico
		</div>
		<div class="col col-md-10">
			<input id="email" name="email" class="form-control" type="email" value="${cliente.email}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Observaciones
		</div>
		<div class="col col-md-10">
			<textarea rows="3" id="observaciones" name="observaciones" class="form-control">${cliente.observaciones}</textarea>
		</div>
	</div>
</div>

<input type="hidden" id="oidUsuario" name="oidUsuario" value="${usuario.oid}"/>
<input type="hidden" id="oid" name="oid" value="${cliente.oid}"/>