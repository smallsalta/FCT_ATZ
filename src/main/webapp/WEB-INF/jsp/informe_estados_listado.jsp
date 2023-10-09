<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="css/contrato_buscar_listado.css" rel="stylesheet"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Informe > Estados desde 
		<fmt:formatDate value="${fini}" pattern="dd/MM/yyyy"/>  
		hasta 
		<fmt:formatDate value="${ffin}" pattern="dd/MM/yyyy"/> 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="informe_factura_pdf.do" method="post">
		
			SIN PAGAR
			<c:set var="xfacturas" scope="request" value="${sinpagar}"/>
			<c:set var="xbase" scope="request" value="${sbase}"/>
			<c:set var="xiva" scope="request" value="${siva}"/>
			<c:set var="xtotal" scope="request" value="${stotal}"/>
			<c:import url="informe_sub_factura_listado.jsp"/>
			
			<br/>
			
			PAGADAS
			<c:set var="xfacturas" scope="request" value="${pagadas}"/>
			<c:set var="xbase" scope="request" value="${pbase}"/>
			<c:set var="xiva" scope="request" value="${piva}"/>
			<c:set var="xtotal" scope="request" value="${ptotal}"/>
			<c:import url="informe_sub_factura_listado.jsp"/>
			
			<br/>
			<button class="btn btn-info btn-block login" type="submit">Descargar</button>
			
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>