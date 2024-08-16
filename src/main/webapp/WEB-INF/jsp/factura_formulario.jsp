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
					<c:when test="${factura.TCliente.oid eq c.oid}">
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
	<div class="col col-md-2" style="display:none;">
		Otro cliente *
	</div>
	<div class="col col-md-10" style="display:none;">
		<select id="oidcliente2" name="oidcliente2" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${clientes}" var="c">
				<c:choose>
					<c:when test="${factura.TCliente.oid eq c.oid}">
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
		Empresa *
	</div>
	<div class="col col-md-10">
		<select id="oidempresa" name="oidempresa" class="selectpicker form-control" data-live-search="true">
			<c:choose>
				<c:when test='${usuario.TRol.descr eq "factura"}'>
					<c:forEach items="${empresas}" var="c">
						<c:choose>
							<c:when test="${factura.TEmpresa.oid eq c.oid}">
								<option value="${c.oid}" selected="selected">${c.descr}</option>
							</c:when>
							<c:otherwise>
								<option value="${c.oid}">${c.descr}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<option value="7" selected="selected">Carpintería</option>
				</c:otherwise>
			</c:choose>
		</select>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha *
	</div>
	<div class="col col-md-10">
		<input id="fecha" name="fecha" class="form-control" type="date" required value='<fmt:formatDate value="${factura.fecha}" pattern="yyyy-MM-dd"/>'/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Número *
	</div>
	<div class="col col-md-10">
		<input id="numero" name="numero" class="form-control" required pattern="^[0-9]+$" value="${factura.numero}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		IVA *
	</div>
	<div class="col col-md-10">
		<input id="iva" name="iva" class="form-control" required type="number" step="0.01" value="${factura.iva}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Estado 
	</div>
	<div class="col col-md-10">
		<select id="oidestado" name="oidestado" class="selectpicker form-control" data-live-search="true">
			<c:forEach items="${estados}" var="e">
				<c:choose>
					<c:when test="${factura.TEstado.oid eq e.oid}">
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
		CC
	</div>
	<div class="col col-md-10">
		<input id="ccemail" name="ccemail" class="form-control" value="${factura.ccEmail}"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Ajuste
	</div>
	<div class="col col-md-10">
		<input id="ajuste" name="ajuste" class="form-control" value="${factura.ajuste}" type="number"/>
	</div>
</div>
<div class="row">
	<div class="col col-md-2">
		Fecha de creación
	</div>
	<div class="col col-md-10">
		<c:choose>
			<c:when test="${empty factura.auditoria1}">
				<input readonly class="form-control" value='<fmt:formatDate value="${factura.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${factura.auditoria1}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
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
			<c:when test="${empty factura.auditoria2}">
				<input readonly class="form-control" value='<fmt:formatDate value="${factura.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${factura.auditoria2}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
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
			<c:when test="${empty factura.envio}">
				<input readonly class="form-control" value='<fmt:formatDate value="${factura.envio}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:when>
			<c:otherwise>
				<input readonly class="form-control" type="datetime-local" value='<fmt:formatDate value="${factura.envio}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<input type="hidden" id="oid" name="oid" value="${factura.oid}"/>

<hr/>
