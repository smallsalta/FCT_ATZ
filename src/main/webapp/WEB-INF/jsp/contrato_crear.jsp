<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
		<script src="js/contrato_crear.js"></script>
		<script> oidclienteload = "${oidclienteload}"; </script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		<c:choose>
			<c:when test='${usuario.TRol.descr eq "factura"}'>Contrato</c:when>
			<c:otherwise>Parte de trabajo</c:otherwise>
		</c:choose>
		 > Crear
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="contrato_crear_guardar.do" method="post" id="frm">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#1a" data-toggle="tab">Filas</a>
				</li>
				<li>
					<a href="#2a" data-toggle="tab">Preguntas</a>
				</li>
			</ul>

			<div class="tab-content clearfix">
				<div class="tab-pane active" id="1a">
					<br/>
					<c:import url="/contrato_crear_filas.do"/>
				</div>
				<div class="tab-pane" id="2a">
					<br/>
					<c:import url="/contrato_crear_preguntas.do"/>
				</div>
			</div>
			
			<div class="container">
				<br/>
				<input type="hidden" name="oidusuario" value="${usuario.oid}" />
				<button id="guardar" class="btn btn-info btn-block" type="submit">Guardar</button>
			</div>
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>