<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@ page isELIgnored="false"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="css/contrato_buscar_listado.css" rel="stylesheet"/>
		<script src="js/picadas_listado.js"></script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Picadas > Resultado 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:choose>
			<c:when test="${empty picadas}">
				No hay picadas.
			</c:when>
			<c:otherwise>
				<form id="frm_picada" method="post" action="picadas_borrar.do">
					<div class="funkyradio">
						<c:forEach items="${picadas}" var="c" varStatus="cont">
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
						            	<span class="label label-default">
						            		<fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy"/> 
						            	</span>
						            	
						            	&nbsp;
						            	
						            	<span class="label label-primary">
						            		<fmt:formatDate value="${c.hini}" pattern="HH:mm"/>
						            	</span>
						            	
						            	&nbsp;
						            	
						            	<span class="label label-primary">
						            		<fmt:formatDate value="${c.hfin}" pattern="HH:mm"/>
						            	</span>
						            	
						            	&nbsp;
						            	
						            	<span class="label label-warning">
						            		<fmt:formatNumber pattern="#0.00" value="${ ( c.hfin.time - c.hini.time ) / 3600000 }"/> h
						            	</span>
						            	
						            	<c:if test="${ not empty c.info }">
							            	&nbsp;
							            	
							            	<span class="label label-success" title='${ c.info }'>
							            		>
							            	</span>
						            	</c:if>
					            	</h4>
					            </label>
					        </div>
					    </c:forEach>
					</div>
					
					<br/>
					
					<div class="container">
						<div class="row">
							<div class="col col-md-6 mt-5">
								<button id="btn_modif" class="btn btn-info btn-block login" type="button">Modificar</button>
							</div>
							<div class="col col-md-6 mt-5">
								<button id="btn_borr" class="btn btn-info btn-block login" type="button">Borrar</button>
							</div>
						</div>
					</div>
					
					
				</form>
					
			</c:otherwise>
				
		</c:choose>	
	</tiles:putAttribute>
		
</tiles:insertDefinition>
