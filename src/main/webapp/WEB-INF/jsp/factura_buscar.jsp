<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Factura > Buscar
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="factura_buscar_listado.do" method="post" id="frm">
			<div class="container">
				<div class="row">
					<div class="col col-md-2">
						Cliente * 
					</div>
					<div class="col col-md-10">
						<select id="oidcliente" name="oidcliente" class="selectpicker form-control" data-live-search="true" required>
							<option value="-1">:: Seleccione uno ::</option>
							<c:forEach items="${clientes}" var="c">
								<option value="${c.oid}">${c.nombre} ${c.apellidos}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Empresa *
					</div>
					<div class="col col-md-10">
						<select id="oidempresa" name="oidempresa" class="selectpicker form-control" data-live-search="true" required>
							<option value="-1">:: Seleccione una ::</option>
							<c:forEach items="${empresas}" var="c">
								<option value="${c.oid}">${c.descr}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Desde *
					</div>
					<div class="col col-md-10">
						<input id="fini" name="fini" class="form-control" type="date" required value='<fmt:formatDate value="${fini}" pattern="yyyy-MM-dd"/>'/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Hasta *
					</div>
					<div class="col col-md-10">
						<input id="fini" name="ffin" class="form-control" type="date" required value='<fmt:formatDate value="${ffin}" pattern="yyyy-MM-dd"/>'/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Número
					</div>
					<div class="col col-md-10">
						<input id="numero" name="numero" class="form-control" type="number" value="${numero}"/>
					</div>
				</div>
			</div>
			<br/>
			<button class="btn btn-info btn-block login" type="submit">Buscar</button>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>