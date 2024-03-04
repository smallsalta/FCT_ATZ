<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<%@ page isELIgnored="false"%>

<div class="row">
	<div class="col col-md-2">
		Cliente *
	</div>
	<div class="col col-md-10">
		<select id="oidcliente" name="oidcliente" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${clientes}" var="c">
				<c:choose>
					<c:when test="${parte.TCliente.oid eq c.oid}">
						<option value="${c.oid}" selected="selected">${c.nombre} ${c.apellidos}</option>
					</c:when>
					<c:otherwise>
						<option value="${c.oid}">${c.nombre} ${c.apellidos}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha *
	</div>
	<div class="col col-md-10">
		<input id="fecha" name="fecha" class="form-control" type="date" required value='<fmt:formatDate value="${parte.fecha}" pattern="yyyy-MM-dd"/>'/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Número *
	</div>
	<div class="col col-md-10">
		<input id="numero" name="numero" class="form-control" required pattern="^[0-9]+$" value="${parte.numero}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Estado 1 *
	</div>
	<div class="col col-md-10">
		<select id="oidestadoparte" name="oidestadoparte" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${estadosparte}" var="e">
				<c:choose>
					<c:when test="${parte.estado.oid eq e.oid}">
						<option value="${e.oid}" selected="selected">${e.descripcion}</option>
					</c:when>
					<c:otherwise>
						<option value="${e.oid}">${e.descripcion}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Estado 2
	</div>
	<div class="col col-md-10">
		<select id="oidestado" name="oidestado" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${estados}" var="e">
				<c:choose>
					<c:when test="${parte.estado2.oid eq e.oid}">
						<option value="${e.oid}" selected="selected">${e.descr}</option>
					</c:when>
					<c:otherwise>
						<option value="${e.oid}">${e.descr}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		DNI
	</div>
	<div class="col col-md-10">
		<input id="dni" name="dni" class="form-control" value="${parte.dni}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		CC
	</div>
	<div class="col col-md-10">
		<input id="ccemail" name="ccemail" class="form-control" value="${parte.ccEmail}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha de creación
	</div>
	<div class="col col-md-10">
		<c:choose>
			<c:when test="${empty parte.auditoria1}">
				<input readonly class="form-control" value='<fmt:formatDate value="${parte.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${parte.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Última modificación
	</div>
	<div class="col col-md-10">
		<c:choose>
			<c:when test="${empty parte.auditoria2}">
				<input readonly class="form-control" value='<fmt:formatDate value="${parte.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${parte.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Envío de correo
	</div>
	<div class="col col-md-10">
		<c:choose>
			<c:when test="${empty parte.auditoriaEmail}">
				<input readonly class="form-control" value='<fmt:formatDate value="${parte.auditoriaEmail}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${parte.auditoriaEmail}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Observaciones
	</div>
	<div class="col col-md-10">
		<textarea id="observaciones" name="observaciones" class="form-control" rows="5" cols="30">${parte.observaciones}</textarea>
	</div>
</div>

<input type="hidden" id="oidpartetipo" name="oidpartetipo" value="${oidpartetipo}"/>
<input type="hidden" id="oid" name="oid" value="${parte.oid}"/>

<hr/>