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
		Informe > Contratos desde 
		<fmt:formatDate value="${fini}" pattern="dd/MM/yyyy"/>  
		hasta 
		<fmt:formatDate value="${ffin}" pattern="dd/MM/yyyy"/> 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="informe_pdf.do" method="post">
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
			            	${c.TCliente.nombre} ${c.TCliente.apellidos}
			            </label>
			        </div>
			    </c:forEach>
			</div>
			<br/>
			<button class="btn btn-info btn-block login" type="submit">Descargar</button>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>