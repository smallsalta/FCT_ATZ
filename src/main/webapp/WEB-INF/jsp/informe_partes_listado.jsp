<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
	
		<link href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" rel="stylesheet"/>
		<link href="//cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" rel="stylesheet"/>
		<link href="css/informe_datatable.css" rel="stylesheet"/>
		
		<script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/dataTables.buttons.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/buttons.html5.min.js"></script>
		<script src="//cdn.datatables.net/buttons/2.0.1/js/buttons.print.min.js"></script>
		<script src="js/datatable_es.js"></script>
		
		<script>
			var cfg1 = {
			    		language:	dt_json_es,
			    		dom: 		'Blfrtip',	
			    		buttons: 	[ 'excel', 'pdf', 'print' ],
				    	columnDefs: 
					    		[
					    			{ targets: [3, 4, 5, 10], className: 'dt-body-left' },
			                        { targets: [0, 1, 2, 6], className: 'dt-body-center' },
			                        { targets: [7, 8, 9], className: 'dt-body-right' }
			                    ]
				    	};
	    	
			$(document).ready
			(
				function() 
				{
					$('#t0').DataTable(cfg1);
				} 
			);
		</script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Informe > Partes desde 
		<fmt:formatDate value="${fini}" pattern="dd/MM/yyyy"/>  
		hasta 
		<fmt:formatDate value="${ffin}" pattern="dd/MM/yyyy"/> 
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
	
		<table id="t0" class="example display compact">
			<thead>
				<tr>
					<th>Número</th>
					<th>Factura</th>
					<th>Fecha</th>
					<th>Cliente</th>
					<th>Dirección</th>
					<th>Localidad</th>
					<th>Teléfono</th>
					<th>Base</th>
					<th>IVA</th>
					<th>IRPF</th>
					<th>Estado</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${partes}" var="c">
					<tr>					
						<td>
							<c:choose>
		            			<c:when test="${c.TParteTipo.oid == 1}">
			            			<c:set var="pre" value="EX" />
			            		</c:when>
			            		<c:when test="${c.TParteTipo.oid == 2}">
			            			<c:set var="pre" value="BI" />
			            		</c:when>
			            		<c:when test="${c.TParteTipo.oid == 3}">
			            			<c:set var="pre" value="CE" />
			            		</c:when>
			            		<c:when test="${c.TParteTipo.oid == 4}">
			            			<c:set var="pre" value="OB" />
			            		</c:when>
			            		<c:when test="${c.TParteTipo.oid == 5}">
			            			<c:set var="pre" value="CE" />
			            		</c:when>
			            		<c:when test="${c.TParteTipo.oid == 6}">
			            			<c:set var="pre" value="AU" />
			            		</c:when>
			            		<c:otherwise>
			            			<c:set var="pre" value="¿?" />
			            		</c:otherwise>
		            		</c:choose>
		            		
		            		${pre} / ${c.numero}
						</td>
						<td> ${n2[c.numero]} </td>
						<td> <fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy" /> </td>
						<td> ${c.TCliente.nombre} ${c.TCliente.apellidos} </td>
						<td> ${c.TCliente.direccion} </td>
						<td> ${c.TCliente.localidad} </td>
						<td> ${c.TCliente.telefono1} / ${c.TCliente.telefono2} </td>
						<td> <fmt:formatNumber value="${totales[c.oid]}" pattern="#,###,##0.00" /> &euro; </td>
						
						<c:choose>
							<c:when test="${partesl.contains(c.numero)}">
								<td> 
									<fmt:formatNumber value="${totales[c.oid]*0.21}" pattern="#,###,##0.00" /> &euro;
								</td>
								<td>
									<fmt:formatNumber value="${totales[c.oid]*0.2}" pattern="#,###,##0.00" /> &euro;
								</td>
							</c:when>
							<c:otherwise>
								<td> &nbsp; </td>
								<td> &nbsp; </td>
							</c:otherwise>
						</c:choose>
						
						<td> ${ estados[c.numero] } </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</tiles:putAttribute>
		
</tiles:insertDefinition>