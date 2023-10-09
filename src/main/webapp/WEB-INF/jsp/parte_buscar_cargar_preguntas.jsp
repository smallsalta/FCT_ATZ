<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="contrato">

	<tiles:putAttribute name="body">
	
		<div class="container">
	 		<c:forEach items="${preguntas}" var="p" varStatus="cont">
				<div class="row">
	    			<div class="col col-md-1">
	    				${cont.count}
	    			</div>
	    			<div class="col col-md-9">
						${p.descr}
					</div>
					<div class="col col-md-2">
						<select class="form-control" name="preguntas" id="pregunta_${p.oid}">
							<c:forEach items="${respuestas}" var="r">
								<option value="${p.oid}_${r.oid}">${r.descr}</option>
							</c:forEach>
						</select>
	    			</div>
	    		</div>
			</c:forEach>
			
			<script>
				$(document).ready
				(
					function() 
					{
						// Inicialmente esta pregunta a N
						$("#pregunta_70").val("70_3");

						// Esto es lo que hay en BD
						<c:forEach items="${parte.TPreguntasParte}" var="p">
							$("#pregunta_${p.TPreguntas.oid}").val("${p.TPreguntas.oid}_${p.TPreguntasRespuestas.oid}");
						</c:forEach>
					}
				);
			</script>
		</div>	
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>