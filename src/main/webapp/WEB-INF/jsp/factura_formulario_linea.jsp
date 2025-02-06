<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="container fila">
	<div class="row">
		<div class="col col-md-2">
   			<label>
   				<input type="radio" name="borrar"/>
	   			Cantidad *
   			</label>
		</div>
		<div class="col col-md-10">
			<input type="number" name="cantidadExt" class="form-control" required value="${linea.cantidad}" step="0.01" min="1"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Descripción
		</div>
		<div class="col col-md-10">
			<textarea name="descrExt" class="form-control">${linea.descripcion}</textarea>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Descuento *
		</div>
		<div class="col col-md-10">
			<input type="number" step="0.01" name="descuentoExt" class="form-control" required value="${linea.descuento}"/>
		</div>
	</div>
	<div class="row">
		<div class="col col-md-2">
			Precio *
		</div>
		<div class="col col-md-10">
			<input type="number" step="0.01" name="precioExt" class="form-control" required value="${linea.precio}"/>
		</div>
	</div>
	<hr/>
</div>

