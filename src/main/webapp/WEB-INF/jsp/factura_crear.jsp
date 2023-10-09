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
		<script src="js/factura_crear.js"></script>
		<script> oidclienteload = "${oidclienteload}"; </script>
		<link rel="stylesheet" href="css/margen_boton.css"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Factura > Crear
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="factura_crear_guardar.do" method="post" id="frm">
			<c:import url="/factura_crear_filas.do"/>
			<div class="container">
				<br/>
				<button id="guardar" class="btn btn-info btn-block" type="submit">Guardar</button>
			</div>
		</form>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>