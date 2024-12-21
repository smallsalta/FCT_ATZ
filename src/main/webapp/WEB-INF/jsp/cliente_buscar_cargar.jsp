<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<script src="js/cliente_buscar_cargar.js"></script>
		<link rel="stylesheet" href="css/login.css">
		<script src="https://maxailloud.github.io/confirm-bootstrap/assets/js/confirm-bootstrap.js"></script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Clientes > Buscar ... ${cadena}
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<form id="frm" data-toggle="validator" role="form" action="cliente_buscar_guardar.do" method="post">
			<jsp:include page="cliente_formulario.jsp"/>		
		</form>
		<br/>
		<div class="container">
			<div class="row">
				<div class="col col-md-3">
					<button id="btn-modif" class="btn btn-success btn-block login" type="button">Modificar</button>
				</div>
				<c:if test='${usuario.TRol.descr eq "factura"}'>
					<div class="col col-md-3">
						<button id="btn-confirm" class="btn btn-danger btn-block login" type="button" data-toggle="modal" data-target="#myModal1">Borrar</button>
					</div>
					<div class="col col-md-3">
						<button id="btn-contrato" class="btn btn-light btn-block login" type="button">Contrato</button>
					</div>
					<div class="col col-md-3">
						<button id="btn-factura" class="btn btn-secondary btn-block login" type="button">Factura</button>
					</div>
				</c:if>
			</div>
			
			<c:if test='${usuario.TRol.descr eq "factura"}'>
				<br/>
				
				<div class="row">
					<div class="col col-md-2">
						<button id="btn-parte-bi" class="btn btn-primary btn-block login" type="button">Parte BI</button>
					</div>
					<div class="col col-md-2">
						<button id="btn-parte-ce" class="btn btn-primary btn-block login" type="button">Parte CE</button>
					</div>
					<div class="col col-md-2">
						<button id="btn-parte-au" class="btn btn-primary btn-block login" type="button">Parte AU</button>
					</div>
					<div class="col col-md-2">
						<button id="btn-parte-ex" class="btn btn-primary btn-block login" type="button">Parte EX</button>
					</div>
					<div class="col col-md-2">
						<button id="btn-parte-ob" class="btn btn-primary btn-block login" type="button">Parte OB</button>
					</div>
				</div>
			</c:if>
		</div>
		<div class="modal fade" id="myModal1">
  			<div class="modal-dialog">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<span class="modal-title">Confirmar borrado</span>
      				</div>
      				<div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        				<button id="btn_confirm_si" type="button" class="btn btn-primary" data-dismiss="modal">Sí</button>
      				</div>
    			</div>
    		</div>
    	</div>
	</tiles:putAttribute>
		
</tiles:insertDefinition>