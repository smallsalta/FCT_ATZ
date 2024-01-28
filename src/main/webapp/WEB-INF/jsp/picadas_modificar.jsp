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
		Picadas > Modificar 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form action="picadas_modificar_guardar.do" method="post">
			<div class="co  ntainer">
				<div class="row">
					<div class="col col-md-2">
						Fecha  
					</div>
					<div class="col col-md-10">
						<input type="date" id="" name="fecha" class="form-control" required="required" value="${picada.fecha}"/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Hora inicio  
					</div>
					<div class="col col-md-10">
						<input type="time" id="" name="hini" class="form-control" required="required" value="${picada.hini}"/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Hora fin  
					</div>
					<div class="col col-md-10">
						<input type="time" id="" name="hfin" class="form-control" required="required" value="${picada.hfin}"/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Notas  
					</div>
					<div class="col col-md-10">
						<textarea rows="" cols="" id="" name="info" class="form-control">${picada.info}</textarea>
					</div>
				</div>
			</div>
			
			<br/>
			<input type="hidden" name="oid" value="${picada.oid}"/>
			<button class="btn btn-info btn-block login" type="submit">Modificar</button>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>
