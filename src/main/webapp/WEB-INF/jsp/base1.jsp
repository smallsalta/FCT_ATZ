<!doctype html>

<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>

<html lang="en">
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    
		<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/css/custom-theme/jquery-ui-1.10.0.custom.css" rel="stylesheet"/>
		<link href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/css/bootstrap.css" rel="stylesheet"/>
		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.2/jquery-ui.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.2/js/bootstrap.js"></script>
		
		<tiles:insertAttribute name="head" />
	</head>
	<body>
	
		<div class="container-fluid">
			<div class="row">
				<div class="col col-md-4">
					<img class="img-fluid" src="img/PROJECT_LOGO"/>
				</div>
				<div class="col col-md-8">
					<div class="col alert alert-info">
						Bienvenido ${usuario.username}, estás en <tiles:insertAttribute name="miga" />
					</div>
				</div>
			</div>
    		<div class="row">
    			<nav class="navbar navbar-default">
    				<div class="container-fluid">
	    				<div class="navbar-header">
	      					<a class="navbar-brand" href="#">Menú</a>
	    				</div>
	   					<ul class="nav navbar-nav">
					        <li class="dropdown">
						        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Clientes</a>
			                    <ul class="dropdown-menu">
			                    	<li><a href="cliente_crear.do">Crear</a></li>
			                    	<li><a href="cliente_buscar.do">Buscar</a></li>
			                    </ul>
			                </li>
			                <li class="dropdown">
						        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							        Partes de trabajo
						        </a>
			                    <ul class="dropdown-menu">
			                    	<li><a href="parte_crear_bie.do">Crear BIE</a></li>
			                    	<li><a href="parte_crear_centralita_central.do">Crear Centralita</a></li>
			                    	<li><a href="parte_crear_centralita_auxiliares.do">Crear Auxiliares</a></li>
			                    	<li><a href="parte_crear.do">Crear Extintor</a>
			                    	<li><a href="parte_crear_observaciones.do">Crear Observaciones</a></li>
			                    	<li><a href="parte_buscar.do">Buscar</a></li>
			                    	<c:if test='${usuario.TRol.descr eq "factura"}'>
			                    		<li><a href="parte_buscar_matrimonio.do">Matrimonio</a></li>
			                    	</c:if>
			                    </ul>
			                </li>
		                    <li class="dropdown">
						        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contratos</a>
			                    <ul class="dropdown-menu">
			                    	<c:if test='${usuario.TRol.descr eq "factura"}'>
			                    		<li><a href="contrato_crear.do">Crear</a></li>
			                    	</c:if>
			                    	<li><a href="contrato_buscar.do">Buscar</a></li>
			                    </ul>
			                </li>
			                <li class="dropdown">
						        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Facturas</a>
			                    <ul class="dropdown-menu">
			                    	<c:if test='${usuario.TRol.descr eq "factura"}'>
			                    		<li><a href="factura_crear.do">Crear</a></li>
			                    	</c:if>
			                    	<li><a href="factura_buscar.do">Buscar</a></li>
			                    </ul>
			                </li>
				            <c:if test='${usuario.TRol.descr eq "factura"}'>    
				                <li class="dropdown">
							        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Informes</a>
				                    <ul class="dropdown-menu">
	                    				<li><a href="informe_contrato.do">Contratos</a></li>
			                    		<li><a href="informe_factura.do">Facturas</a></li>
			                    		<li><a href="informe_estados.do">Estados</a></li>
			                    		<li><a href="informe_resumen.do">Resumen</a></li>
			                    		<li><a href="informe_partes.do">Partes</a></li>
			                    		<li><a href="informe_contrato2_buscar.do">Contratos II</a></li>
			                    		<li><a href="informe_factura2_buscar.do">Facturas II</a></li>
			                    		<li><a href="informe_partes2_buscar.do">Partes II</a></li>
				                    </ul>
				                </li>
				             </c:if>
				             <li class="dropdown">
					        	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Picadas</a>
	                    		<ul class="dropdown-menu">
                   					<li><a href="picadas_alta.do">Alta</a></li>
	                    			<li><a href="picadas_buscar.do">Buscar</a></li>
		                    	</ul>
		                	</li>  
			                <li><a href="j_spring_security_logout.do">Salir</a> </li>
		                </ul>
	                </div>
		        </nav>
		        <div class="container">
					<tiles:insertAttribute name="body"/>
				</div>
			</div>
		</div>
	</body>
</html>
