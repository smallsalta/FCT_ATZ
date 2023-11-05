<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="css/contrato_buscar_listado.css" rel="stylesheet"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Parte > Buscar ${cliente.nombre} ${cliente.apellidos}
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:choose>
			<c:when test="${empty partes}">
				No hay partes.
			</c:when>
			<c:otherwise>
				<form id="frm" data-toggle="validator" role="form" action="parte_buscar_cargar.do" method="post">
					<div class="funkyradio">
						<c:forEach items="${partes}" var="c" varStatus="cont">
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
					            	<h4>
					            		<c:choose>
						            		<c:when test="${c.estado.oid == 4}">
						            			<span class="label label-danger"> 
						            				${c.numero} 
						            			</span>
						            		</c:when>
						            		<c:otherwise>
						            			<span class="label label-default"> 
						            				${c.numero} 
						            			</span>
						            		</c:otherwise>
					            		</c:choose>
						            	
						            	&nbsp;
						            	<span class="label label-default">
						            		${c.TCliente.nombre} ${c.TCliente.apellidos} 
						            	</span>
						            	
						            	&nbsp;
						            	<span class="label label-success">
						            		<fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy"/>
						            	</span>
						            	
						            	<c:if test="${ matrimonio[c.numero].factura != null }">
							            	&nbsp;
							            	
							            	<c:choose>
							            		<c:when test="${ fn:substring( matrimonio[c.numero].numero2, 0, 1) eq '7' }">
					            					<span class="label label-primary">  
							            		</c:when>
							            		<c:otherwise>
						            				<span class="label label-info">
							            		</c:otherwise>
							            	</c:choose>
								            	
							            	<c:choose>
							            		<c:when test="${ pagadas[ matrimonio[c.numero].numero2 ] }">
					            					<u> ${ matrimonio[c.numero].factura } ${ facturas } </u>  
							            		</c:when>
							            		<c:otherwise>
						            				${ matrimonio[c.numero].factura } ${ facturas }
							            		</c:otherwise>
							            	</c:choose>
							            	
							            	</span>
						            	</c:if>
						            	
						            	<c:if test="${ matrimonio[c.numero].contrato != null }">
							            	&nbsp;
							            	<span class="label label-warning">
							            		${ matrimonio[c.numero].contrato }
							            	</span>
							            </c:if>
					            	</h4>
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
