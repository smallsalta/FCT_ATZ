<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="row">
	<div class="col col-md-2">
		Cliente *
	</div>
	<div class="col col-md-10">
		<select id="oidcliente" name="oidcliente" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${clientes}" var="c">
				<c:choose>
					<c:when test="${contrato.TCliente.oid eq c.oid}">
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
		Anexo
	</div>
	<div class="col col-md-10">
		<textarea rows="3" id="anexo" name="anexo" class="form-control">${contrato.anexo}</textarea>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Dirección
	</div>
	<div class="col col-md-10">
		<input id="direccion" name="direccion" class="form-control" value="${contrato.direccion}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha *
	</div>
	<div class="col col-md-10">
		<input id="fecha" name="fecha" class="form-control" type="date" required value='<fmt:formatDate value="${contrato.fecha}" pattern="yyyy-MM-dd"/>'/>
	</div>
</div> 
<div class="row">
	<div class="col col-md-2">
		Revisión trimestral *
	</div>
	<div class="col col-md-10">
		<input type="number" step="0.01" name="trimestral" class="form-control" required value="${contrato.trimestral}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Número *
	</div>
	<div class="col col-md-10">
		<input readonly="readonly" id="numero" name="numero" class="form-control" required pattern="^[0-9]+$" value="${contrato.numero}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		CC
	</div>
	<div class="col col-md-10">
		<input id="ccemail" name="ccemail" class="form-control" value="${contrato.ccEmail}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha de creación
	</div>
	<div class="col col-md-10">
		<c:choose>
			<c:when test="${empty contrato.auditoria1}">
				<input readonly class="form-control" value='<fmt:formatDate value="${contrato.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${contrato.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
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
			<c:when test="${empty contrato.auditoria2}">
				<input readonly class="form-control" value='<fmt:formatDate value="${contrato.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${contrato.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
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
			<c:when test="${empty contrato.auditoriaEmail}">
				<input readonly class="form-control" value='<fmt:formatDate value="${contrato.auditoriaEmail}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${contrato.auditoriaEmail}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<input type="hidden" id="oid" name="oid" value="${contrato.oid}"/>

<hr/>