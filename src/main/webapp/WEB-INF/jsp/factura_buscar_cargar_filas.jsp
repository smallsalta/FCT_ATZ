<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="contrato">

	<tiles:putAttribute name="body">
		<jsp:include page="factura_formulario.jsp"/>		
		<c:forEach items="${factura.TFacturaLineaOrd}" var="l">
			<c:set scope="request" var="linea" value="${l}"/>
			<jsp:include page="factura_formulario_linea.jsp"/>
		</c:forEach>
		<br/>
		<div class="container">
			<div class="row">
				<div class="col col-md-3 mt-5">
					<button id="sube" class="btn btn-info btn-block glyphicon glyphicon-arrow-up" type="button"/>
				</div>
				<div class="col col-md-3 mt-5">
					<button id="mas" class="btn btn-info btn-block glyphicon glyphicon-plus-sign" type="button"/>
				</div>
				<div class="col col-md-3 mt-5">
					<button id="menos" class="btn btn-info btn-block glyphicon glyphicon-minus-sign" type="button"/>
				</div>
				<div class="col col-md-3 mt-5">
					<button id="baja" class="btn btn-info btn-block glyphicon glyphicon-arrow-down" type="button"/>				
				</div>
			</div>
		</div>
	</tiles:putAttribute>
		
</tiles:insertDefinition>