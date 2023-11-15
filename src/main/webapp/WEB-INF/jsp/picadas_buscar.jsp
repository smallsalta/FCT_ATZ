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
		Picadas > Buscar 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form" action="picadas_buscar_listado.do" method="post" id="frm">
			<div class="container">
				<div class="row">
					<div class="col col-md-2">
						Usuario  
					</div>
					<div class="col col-md-10">
						<select id="oidusuario" name="oidusuario" class="selectpicker form-control" data-live-search="true" required>
							<option value="-1">:: Seleccione uno ::</option>
							<c:forEach items="${usuarios}" var="c">
								<option value="${c.oid}">${c.username}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Desde 
					</div>
					<div class="col col-md-10">
						<input id="fini" name="fini" class="form-control" type="date" required value='<fmt:formatDate value="${fini}" pattern="yyyy-MM-dd"/>'/>
					</div>
				</div>
				<div class="row">
					<div class="col col-md-2">
						Hasta
					</div>
					<div class="col col-md-10">
						<input id="fini" name="ffin" class="form-control" type="date" required value='<fmt:formatDate value="${ffin}" pattern="yyyy-MM-dd"/>'/>
					</div>
				</div>
			</div>
			
			<br/>
			
			<button class="btn btn-info btn-block login" type="submit">Buscar</button>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>
