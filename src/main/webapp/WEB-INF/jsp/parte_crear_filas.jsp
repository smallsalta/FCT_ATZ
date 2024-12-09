<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="contrato">

	<tiles:putAttribute name="body">
		<div class="container">
		
			<!-- Lo que hay antes de las pesta�as -->
			<jsp:include page="parte_formulario.jsp"/>	
		
			<c:forEach items="${parte.TParteLineas}" var="l" varStatus="i">
				<c:set scope="request" var="idx" value="${i.index}"/>
				<c:set scope="request" var="linea" value="${l}"/>
				<c:if test="${oidpartetipo eq 1 or oidpartetipo eq 4}">
					<jsp:include page="parte_formulario_linea.jsp"/>
				</c:if>
				<c:if test="${oidpartetipo eq 2}">
					<jsp:include page="parte_bie_formulario_linea.jsp"/>
				</c:if>
				<c:if test="${oidpartetipo eq 5 or oidpartetipo eq 6}">
					<jsp:include page="parte_centralita_formulario_linea.jsp"/>
				</c:if>
			</c:forEach>
		</div>
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