<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<script src="js/contrato_crear.js"></script>
		<script src="js/factura_crear.js"></script>
		<script src="js/factura_buscar_cargar.js"></script>
		<link rel="stylesheet" href="css/login.css"/>
		<script src="https://maxailloud.github.io/confirm-bootstrap/assets/js/confirm-bootstrap.js"></script>
		
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
		
		<script> oidclientecarga = "${factura.TCliente.oid}"; </script>
		<script> oidclientecarga2 = "${factura.TCliente2.oid}"; </script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Factura > Buscar ... ${factura.TCliente.nombre} &nbsp; ${factura.TCliente.apellidos}
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<form id="frm" data-toggle="validator" role="form" action="factura_buscar_guardar.do" method="post">
			<c:import url="/factura_buscar_cargar_filas.do"/>
			
			<br/>
			
			<div class="container">
				<div class="row">
					<div class="col col-md-4 mt-5">
						<button id="btn-modif" class="btn btn-success btn-block login" type="submit">Modificar</button>
					</div>
					<div class="col col-md-4 mt-5">
						<button id="btn-copy" class="btn btn-warning btn-block login" type="button">Copiar</button>
					</div>
					<div class="col col-md-4 mt-5">
						<button id="btn-confirm" class="btn btn-danger btn-block login" type="button" data-toggle="modal" data-target="#myModal1">Borrar</button>
					</div>
				</div>
			</div>
			
			<br/>
			
			<div class="container">
				<div class="row">
					<div class="col col-md-6 mt-5">
						<button id="btn-email" class="btn btn-light btn-block login" type="button">eMail</button>
					</div>
					<div class="col col-md-6 mt-5">
						<button id="btn-pdf" class="btn btn-secondary btn-block login" type="button">PDF</button>
					</div>
				</div>
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
	    	<div class="modal fade" id="myModal2">
	  			<div class="modal-dialog">
	    			<div class="modal-content">
	      				<div class="modal-header">
	      					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        				<span class="modal-title">Confirmación del envío</span>
	      				</div>
	      				<div class="modal-footer">
	        				<span id="mensaje"></span>
	      				</div>
	    			</div>
	    		</div>
	    	</div>
		</form>
	</tiles:putAttribute>
		
</tiles:insertDefinition>