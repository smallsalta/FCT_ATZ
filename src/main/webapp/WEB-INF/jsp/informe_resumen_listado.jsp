<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" rel="stylesheet"/>
		<link href="//cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" rel="stylesheet"/>
		
		<script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"></script>
		
		<script>
			var cfg1 = {
			    		language:	{ "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json" },
			    		dom: 		'Blfrtip',	
			    		buttons: 	[ 'excel', 'pdf', 'print' ],
				    	columnDefs: 
					    		[
						    		{ targets: [3, 4, 5, 6], className: 'dt-body-right' },
			                        { targets: [0, 1], className: 'dt-body-center' }
			                    ]
				    	};

			var cfg2 = {
			    		language:	{ "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json" },
			    		dom: 		'Blfrtip',	
			    		buttons: 	[ 'excel', 'pdf', 'print' ],
				    	columnDefs: 
					    		[
						    		{ targets: [2, 3, 4, 5], className: 'dt-body-left' },
			                        { targets: [0, 1], className: 'dt-body-center' }
			                    ]
				    	};

			var cfg3 = {
			    		language:	{ "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json" },
			    		dom: 		'Blfrtip',	
			    		buttons: 	[ 'excel', 'pdf', 'print' ],
				    	columnDefs: 
					    		[
						    		{ targets: [3, 4, 5, 6, 7], className: 'dt-body-right' },
			                        { targets: [0, 1, 2], className: 'dt-body-center' }
			                    ]
				    	};
	    	
			$(document).ready
			(
				function() 
				{
					$('#t0').DataTable(cfg3);
		    		$('#t1').DataTable(cfg1);
		    		$('#t2').DataTable(cfg1);
		    		$('#t3').DataTable(cfg2);
				} 
			);
		</script>
		  
		<style>
			.divexample 
			{ 
				padding: 30px; 
				overflow-x: scroll;
			  	overflow-y: hidden;
			}
			
			.example, .container 
			{ 
				width: 100%; 
			}
		</style>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Informe > Resumen desde 
		<fmt:formatDate value="${fini}" pattern="dd/MM/yyyy"/>  
		hasta 
		<fmt:formatDate value="${ffin}" pattern="dd/MM/yyyy"/> 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingZero">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion"
							href="#collapseZero" aria-expanded="true"
							aria-controls="collapseOne"> Facturas </a>
					</h4>
				</div>
				<div id="collapseZero" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingOne">
					
					<c:set var="pagadas" scope="request" value="${pagadas}"/>
					<c:set var="sinpagar" scope="request" value="${sinpagar}"/>
					<c:set var="id" scope="request" value="t0"/>
					<c:import url="informe_sub3_resumen_listado.jsp"/>
					
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion"
							href="#collapseOne" aria-expanded="false"
							aria-controls="collapseOne"> Facturas pagadas </a>
					</h4>
				</div>
				<div id="collapseOne" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingOne">
					
					<c:set var="facturas" scope="request" value="${pagadas}"/>
					<c:set var="id" scope="request" value="t1"/>
					<c:import url="informe_sub1_resumen_listado.jsp"/>
					
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingTwo">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseTwo"
							aria-expanded="false" aria-controls="collapseTwo">
							Facturas sin pagar </a>
					</h4>
				</div>
				<div id="collapseTwo" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingTwo">
					
					<c:set var="facturas" scope="request" value="${sinpagar}"/>
					<c:set var="id" scope="request" value="t2"/>
					<c:import url="informe_sub1_resumen_listado.jsp"/>

				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading" role="tab" id="headingThree">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion" href="#collapseThree"
							aria-expanded="false" aria-controls="collapseThree">
							Contratos </a>
					</h4>
				</div>
				<div id="collapseThree" class="panel-collapse collapse"
					role="tabpanel" aria-labelledby="headingThree">
					
					<c:set var="facturas" scope="request" value="${contratos}"/>
					<c:set var="id" scope="request" value="t3"/>
					<c:import url="informe_sub2_resumen_listado.jsp"/>
					
				</div>
			</div>
		</div>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>