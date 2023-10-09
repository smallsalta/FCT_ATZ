<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<tiles:insertDefinition name="base">

	<tiles:putAttribute name="head">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css" rel="stylesheet"/>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
		<script src="js/parte_matrimonio.js"></script>
		<script> var exito = "${exito}"; </script>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="miga">
		Parte > Matrimonio listado
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body">
		<c:choose>
			<c:when test="${empty matrimonio}">
				No hay partes.
			</c:when>
			<c:otherwise>
				<div class="container form-group">
					<div class="row">
				    	<div class="col-sm-6 text-center"> Parte </div>
				    	<div class="col-sm-2 text-center"> Factura </div>
				    	<div class="col-sm-2 text-center"> Certificado </div>
				    	<div class="col-sm-2 text-center"> &nbsp; </div>
				    </div>
				    
					<c:forEach items="${matrimonio}" var="m" varStatus="cont">
						<form data-toggle="validator" role="form" action="parte_guardar_matrimonio.do" method="post" id="frm_${cont.index}">
							<div class="row">
						    	<div class="col-sm-6">
									<div class="form-group">
									
						    			<div class="input-group">
   											<span class="input-group-addon"> ${ partes[m.parte].TCliente.nombre} ${partes[m.parte].TCliente.apellidos } </span>
	  										<input type="number" name="nparte" class="form-control" value="${ m.parte }" readonly="readonly" />
									  		<div class="input-group-addon">
									    		<span class="glyphicon glyphicon-zoom-in lupa" id="lupa_parte_${cont.index}"> &nbsp; </span>
									  		</div>
									  	</div>

									</div>
						    	</div>
						    	<div class="col-sm-2">
						    		<div class="form-group">
						    			<div class="input-group">
	  										<input id="factura_${cont.index}" type="number" name="nfactura" class="form-control" value="${ m.factura == 0 ? '' : m.factura }"/>
									  		<div class="input-group-addon">
									    		<span class="glyphicon glyphicon-zoom-in lupa" id="lupa_factura_${cont.index}"> &nbsp; </span>
									  		</div>
									  	</div>
									</div>
						    	</div>
						    	<div class="col-sm-2">
						      		<div class="form-group">
						    			<div class="input-group">
	  										<input id="contrato_${cont.index}" type="number" name="ncontrato" class="form-control" value="${ m.contrato == 0 ? '' : m.contrato }"/>
									  		<div class="input-group-addon">
									    		<span class="glyphicon glyphicon-zoom-in lupa" id="lupa_contrato_${cont.index}"> &nbsp; </span>
									  		</div>
									  	</div>
									</div>
						    	</div>
						    	<div class="col-sm-2">
						    		<input type="hidden" name="oidmatrimonio" value='${m.oid}'/>
						    		<input type="hidden" name="fini" value='<fmt:formatDate value="${fini}" pattern="yyyy-MM-dd"/>'/>
						    		<input type="hidden" name="ffin" value='<fmt:formatDate value="${ffin}" pattern="yyyy-MM-dd"/>'/>
						    		<input type="hidden" name="tipoLupa" id="tipoLupa_${cont.index}"/>
						      		<button class="btn btn-info guardar" type="button" id="guardar_${cont.index}">Guardar</button>
						      		<button class="btn btn-info borrar" type="button" id="borrar_${cont.index}">Borrar</button>
						    	</div>
						  	</div>
					  	</form>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>		
		
		<div class="modal fade" id="myModal1">
  			<div class="modal-dialog">
    			<div class="modal-content">
      				<div class="modal-header">
      					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<span class="modal-title">Éxito</span>
      				</div>
      				<div class="modal-footer">
        				<span id="mensaje">Los datos se han guardado correctamente.</span>
      				</div>
    			</div>
    		</div>
    	</div>
    	
		<div class="modal fade" id="myModal2">
  			<div class="modal-dialog">
    			<div class="modal-content">
      				<div class="modal-header">
      					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<span class="modal-title">Error al guardar</span>
      				</div>
      				<div class="modal-footer">
        				<span id="mensaje">El número de la factura y/o contrato no pueden ser vacíos</span>
      				</div>
    			</div>
    		</div>
    	</div>
    	
	</tiles:putAttribute>
		
</tiles:insertDefinition>
