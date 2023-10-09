<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<script src="js/contrato_crear.js"></script>
		<script src="js/contrato_buscar_cargar.js"></script>
		<link rel="stylesheet" href="css/login.css"/>
		<script src="https://maxailloud.github.io/confirm-bootstrap/assets/js/confirm-bootstrap.js"></script>
		
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
		
		<script> var oidclientecarga = "${oidcliente}"; </script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Contrato > Buscar ... ${contrato.TCliente.nombre} &nbsp; ${contrato.TCliente.apellidos}
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:if test='${errorPase != null}'>
			<div class="container-fluid">
				<div class="row">
					<div class="col alert alert-danger alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<p>${errorPase}</p>
					</div>
				</div>
			</div>
		</c:if>
		<form id="frm" data-toggle="validator" role="form" action="contrato_buscar_guardar.do" method="post">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#1a" data-toggle="tab">Filas</a>
				</li>
				<li>
					<a href="#2a" data-toggle="tab">Preguntas</a>
				</li>
			</ul>

			<div class="tab-content clearfix">
				<div class="tab-pane active" id="1a">
					<br/>
					<c:import url="/contrato_buscar_cargar_filas.do"/>
				</div>
				<div class="tab-pane" id="2a">
					<br/>
					<c:import url="/contrato_buscar_cargar_preguntas.do"/>
				</div>
			</div>
			
			<br/>
			
			<div class="container">
				<div class="row">
					<div class="col col-md-4 mt-5">
						<input class="btn btn-block login btn-parte" type="button" value="Crear parte" onclick="btnCparteClick();" />
					</div>
					<div class="col col-md-4 mt-5"> &nbsp; </div>
					<div class="col col-md-4 mt-5"> &nbsp; </div>
				</div>
			</div>
			
			<br/>
			
			<c:if test='${usuario.TRol.descr eq "factura"}'>
			
				<div class="container">
					<div class="row">
						<div class="col col-md-4 mt-5">
							<button id="btn-modif" class="btn btn-success btn-block login" type="button">Modificar</button>
						</div>
						<div class="col col-md-4 mt-5">
							<button id="btn-copy" class="btn btn-warning btn-block login" type="button">Copiar</button>
						</div>
						<div class="col col-md-4 mt-5">
							<button id="btn-confirm" class="btn btn-danger btn-block login" data-toggle="modal" data-target="#myModal1" type="button">Borrar</button>
						</div>
					</div>
				</div>
				
				<br/>
				
				<div class="container">
					<div class="row">
						<div class="col col-md-4 mt-5">
							<button id="btn-email" class="btn btn-light btn-block login" type="button">eMail</button>
						</div>
						<div class="col col-md-4 mt-5">
							<button id="btn-pdf" class="btn btn-secondary btn-block login" type="button">PDF</button>
						</div>
						<div class="col col-md-4 mt-5">
							<button id="btn-cuadrante" class="btn btn-info btn-block login" type="button">Cuadrante</button>
						</div>
					</div>
				</div>
				
			</c:if>
			
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
