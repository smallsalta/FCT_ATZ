<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Clientes > Buscar
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form data-toggle="validator" role="form" action="cliente_buscar_cargar.do" method="post">
			<div class="container">
				<div class="row">
					<div class="col col-md-2">
						Buscar
					</div>
					<div class="col col-md-10">
						<select id="oid" name="oid" class="selectpicker form-control" data-live-search="true" data-size="auto">
							<c:forEach items="${clientes}" var="c">
								<option value="${c.oid}">${c.nombre} ${c.apellidos}</option>
							</c:forEach>
				      	</select>
					</div>
				</div>
			</div>
			<br/>
			<button class="btn btn-info btn-block login" type="submit">Buscar</button>
		</form>
	
	</tiles:putAttribute>
		
</tiles:insertDefinition>