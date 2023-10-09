<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="container fila">
	<div class="row">
		<div class="col col-md-2">
   			<label>
   				<input type="radio" name="borrar"/>
	   			Cantidad *
   			</label>
		</div>
		<div class="col col-md-10">
			<input type="number" name="cantidadExt" class="form-control" required value="${linea.cantidad}" min="1"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Número de placa *
		</div>
		<div class="col col-md-10">
			<input name="numeroPlacaExt" class="form-control" required value="${linea.numeroPlaca}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Fabricante
		</div>
		<div class="col col-md-10">
			<input name="fabricanteExt" class="form-control" value="${linea.fabricante}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Ubicación
		</div>
		<div class="col col-md-10">
			<input name="descrExt" class="form-control" value="${linea.descr}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Capacidad *
		</div>
		<div class="col col-md-10">
			<input lang="en" type="number" step="0.01" name="capacidadExt" class="form-control" required value="${linea.capacidad}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Fecha fabricación *
		</div>
		<div class="col col-md-10">
			<input name="fechaFabExt" class="form-control" type="date" required value="<fmt:formatDate value="${linea.fechaFab}" pattern="yyyy-MM-dd"/>"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Fecha retimbrado 
		</div>
		<div class="col col-md-10">
			<input name="fechaRetExt" class="form-control" type="date" value="<fmt:formatDate value="${linea.fechaRet}" pattern="yyyy-MM-dd"/>"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Precio *
		</div>
		<div class="col col-md-10">
			<input lang="en" type="number" step="0.01" name="precioExt" class="form-control" required value="${linea.precio}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Agente *
		</div>
		<div class="col col-md-10">
			<select name="agentesExt" class="form-control">
				<c:forEach items="${agentes}" var="c">
					<c:choose>
						<c:when test="${linea.TAgente.oid eq c.oid}">
							<option value="${c.oid}" selected="selected">${c.descr}</option>
						</c:when>
						<c:otherwise>
							<option value="${c.oid}">${c.descr}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
	      	</select>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Prueba *
		</div>
		<div class="col col-md-10">
			<select name="pruebasExt" class="form-control">
				<c:forEach items="${pruebas}" var="c">
					<c:choose>
						<c:when test="${linea.TPrueba.oid eq c.oid}">
							<option value="${c.oid}" selected="selected">${c.descr}</option>
						</c:when>
						<c:otherwise>
							<option value="${c.oid}">${c.descr}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
	      	</select>
		</div>
	</div>
	<hr/>
</div>

