<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="css/contrato_buscar_listado.css" rel="stylesheet"/>
		<script>
			var oids = null;
			
			$(document).ready
			(
				function() 
				{
					$("#todos").click
					(
						function()
						{
							oids = new Array();
							
							$(":radio").each
							(
								function(e)
								{
									oids[ oids.length ] = $(this).val();
								}
							);
							
							$.each
							(
								oids, 
								function( index, value ) 
								{
									var win = window.open( '', '_blank' );
									win.location.href = "informe_factura_pdf.do?oid=" + value;
								}
							);
						}
					);
				}
			);
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Informe > Facturas desde 
		<fmt:formatDate value="${fini}" pattern="dd/MM/yyyy"/>  
		hasta 
		<fmt:formatDate value="${ffin}" pattern="dd/MM/yyyy"/> 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="informe_factura_pdf.do" method="post">
		
			<c:set var="xfacturas" scope="request" value="${facturas}"/>
			<c:set var="xbase" scope="request" value="${base}"/>
			<c:set var="xiva" scope="request" value="${iva}"/>
			<c:set var="xtotal" scope="request" value="${total}"/>
			<c:import url="informe_sub_factura_listado.jsp"/>

			<br/>
			
			<div class="container">
				<div class="row">
					<div class="col col-md-6">
						<button class="btn btn-info btn-block login" type="submit">Descargar</button>
					</div>
					<div class="col col-md-6">
						<button id="todos" class="btn btn-info btn-block login" type="button">Descargar Todos</button>
					</div>
				</div>
			</div>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>