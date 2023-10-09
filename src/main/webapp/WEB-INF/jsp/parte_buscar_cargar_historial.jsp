<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="contrato">

	<tiles:putAttribute name="body">
	
		<div class="container">
	 		
	 		<c:forEach items="${historial}" var="p" varStatus="cont">
				<div class="row">
	    			<div class="col col-md-4 mt-5">
	    				${cont.count}
	    			</div>
	    			<div class="col col-md-4 mt-5">
						${p.TUsuario.nombre} ${p.TUsuario.appelidos}
					</div>
					<div class="col col-md-4 mt-5">
						<fmt:formatDate value="${p.fecha}" pattern="dd/MM/yyyy HH:mm:ss"/>
	    			</div>
	    		</div>
			</c:forEach>
	 		
		</div>	
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>