<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		&nbsp;
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Clientes > Crear
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="cliente_crear_guardar.do" method="post">
			<jsp:include page="cliente_formulario.jsp"/>
			<br/>
			<button class="btn btn-info btn-block login" type="submit">Guardar</button>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>