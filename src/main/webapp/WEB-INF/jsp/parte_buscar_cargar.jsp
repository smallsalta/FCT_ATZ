<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<script src="js/parte_crear.js"></script>
		<script src="js/parte_buscar_cargar.js"></script>
		<link rel="stylesheet" href="css/login.css" />
		<script
			src="https://maxailloud.github.io/confirm-bootstrap/assets/js/confirm-bootstrap.js"></script>
		<link rel="stylesheet" href="css/login.css" />
		<link
			href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css"
			rel="stylesheet" />
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

		<script>
			var oidclientecarga = "${oidcliente}";
		</script>
		<script>
			oidpartetipo = "${oidpartetipo}";
		</script>
	</tiles:putAttribute>

	<tiles:putAttribute name="miga">
		Parte > Buscar ... ${parte.TCliente.nombre} &nbsp; ${parte.TCliente.apellidos}
	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<form data-toggle="validator" role="form"
			action="parte_buscar_guardar.do" method="post" id="frm">

			<ul class="nav nav-tabs">
				<li class="active"><a href="#1a" data-toggle="tab">Filas</a></li>
				<c:if
					test="${oidpartetipo eq 1 or oidpartetipo eq 2 or oidpartetipo eq 5 or oidpartetipo eq 6}">
					<li><a href="#2a" data-toggle="tab">Preguntas</a></li>
				</c:if>
				<li><a href="#3a" data-toggle="tab">Historial cambios</a></li>
			</ul>

			<div class="tab-content clearfix">
				<div class="tab-pane active" id="1a">
					<br />
					<c:import url="/parte_buscar_cargar_filas.do" />
				</div>
				<c:if
					test="${oidpartetipo eq 1 or oidpartetipo eq 2 or oidpartetipo eq 5 or oidpartetipo eq 6}">
					<div class="tab-pane" id="2a">
						<br />
						<c:import url="/parte_buscar_cargar_preguntas.do" />
					</div>
				</c:if>
				<div class="tab-pane" id="3a">
					<br />
					<c:import url="/parte_buscar_cargar_historial.do" />
				</div>
			</div>

			<br />

			<div class="container">
				<div class="row">
					<div class="col col-md-4 mt-5">
						<button id="btn-modif" class="btn btn-success btn-block login"
							type="submit">Modificar</button>
					</div>
					<div class="col col-md-4 mt-5">
						<button id="btn-copy" class="btn btn-warning btn-block login"
							type="button">Copiar</button>
					</div>
					<div class="col col-md-4 mt-5">
						<button id="btn-confirm" class="btn btn-danger btn-block login"
							type="button" data-toggle="modal" data-target="#myModal1">Borrar</button>
					</div>
				</div>
			</div>

			<br />

			<div class="container">
				<div class="row">
					<div class="col col-md-4 mt-5">
						<button id="btn-email" class="btn btn-light btn-block login"
							type="button">eMail</button>
					</div>
					<c:if
						test="${oidpartetipo eq 1 or oidpartetipo eq 2 or oidpartetipo eq 4}">
						<div class="col col-md-4 mt-5">
							<button id="btn-pdf" class="btn btn-secondary btn-block login"
								type="button">PDF</button>
						</div>
					</c:if>
					<c:if test="${oidpartetipo eq 5}">
						<div class="col col-md-4 mt-5">
							<button id="btn-pdf-1" class="btn btn-secondary btn-block login"
								type="button">PDF</button>
						</div>
					</c:if>
					<c:if test="${oidpartetipo eq 6}">
						<div class="col col-md-4 mt-5">
							<button id="btn-pdf-2" class="btn btn-secondary btn-block login"
								type="button">PDF</button>
						</div>
					</c:if>
				</div>
			</div>

			<c:if test='${usuario.TRol.descr eq "factura"}'>
				<br />

				<div class="container">
					<div class="row">
						<c:if
							test="${oidpartetipo eq 1 or oidpartetipo eq 2 or oidpartetipo eq 5 or oidpartetipo eq 6}">
							<div class="col col-md-4 mt-5">
								<button id="btn-contrato" class="btn btn-primary btn-block"
									type="button">Crear contrato</button>
							</div>
						</c:if>
						<div class="col col-md-4 mt-5">
							<button id="btn_fct_sl" class="btn btn-default btn-block"
								type="button">Crear factura SL</button>
						</div>
						<div class="col col-md-4 mt-5">
							<button id="btn_fct_carpinteria"
								class="btn btn-default btn-block" type="button">Crear
								factura Carpinteria</button>
						</div>
					</div>
				</div>
			</c:if>

			<div class="modal fade" id="myModal1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<span class="modal-title">Confirmar borrado</span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">No</button>
							<button id="btn_confirm_si" type="button" class="btn btn-primary"
								data-dismiss="modal">Sí</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="myModal2">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
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
