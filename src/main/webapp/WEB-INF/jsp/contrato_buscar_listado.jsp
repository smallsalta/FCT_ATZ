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
		Contrato > Buscar ${cliente.nombre} ${cliente.apellidos}
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:choose>
			<c:when test="${empty contratos}">
				No hay contratos.
			</c:when>
			<c:otherwise>
				<form id="frm" data-toggle="validator" role="form" action="contrato_buscar_cargar.do" method="post">
					<div class="funkyradio">
						<c:forEach items="${contratos}" var="c" varStatus="cont">
							<div class="funkyradio-default">
					            <c:choose>
									<c:when test="${cont.index == 0}">
										<input type="radio" id="${cont.index}" name="oid" value="${c.oid}" checked="checked"/>
									</c:when>
									<c:otherwise>
										<input type="radio" id="${cont.index}" name="oid" value="${c.oid}"/>
									</c:otherwise>
								</c:choose>
					            <label for="${cont.index}">
					            	${c.numero} 
					            	<fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy"/>
					            </label>
					        </div>
					    </c:forEach>
					</div>
					<br/>
					<button class="btn btn-info btn-block login" type="submit">Cargar</button>
				</form>
			</c:otherwise>
		</c:choose>		
	</tiles:putAttribute>
		
</tiles:insertDefinition>