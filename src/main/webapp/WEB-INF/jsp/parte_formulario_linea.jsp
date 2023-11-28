<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<%@ page isELIgnored="false"%>

<div class="container fila">
	<div class="row">
		<div class="col col-md-2">
   			<label>
   				<input type="radio" name="borrar"/>
	   			Orden *
   			</label>
		</div>
		<div class="col col-md-10">
			<input type="number" name="orden" class="form-control orden-input" required value="${linea.orden}" min="1"/>
		</div>
	</div>
	<c:if test="${oidpartetipo eq 1}">
		<div class="row">
			<div class="col col-md-2">
				Ubicación
			</div>
			<div class="col col-md-10">
				<input name="ubicacion" class="form-control" value="${linea.ubicacion}">
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Nº Placa
			</div>
			<div class="col col-md-10">
				<input name="numPlaca" class="form-control" value="${linea.numPlaca}">
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Tipo
			</div>
			<div class="col col-md-10">
				<select name="tipo" class="form-control" data-live-search="true">
					<c:forEach items="${tiposextintor}" var="te">
						<c:choose>
							<c:when test="${linea.tipo.oid eq te.oid}">
								<option value="${te.oid}" selected="selected">${te.tipo}</option>
							</c:when>
							<c:otherwise>
								<option value="${te.oid}">${te.tipo}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Kg
			</div>
			<div class="col col-md-10">
				<input type="number" name="kg" class="form-control" value="${linea.kg}"/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Fabricante
			</div>
			<div class="col col-md-10">
				<input name="fabricante" class="form-control" value="${linea.fabricante}"/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Fecha fabricación
			</div>
			<div class="col col-md-10">
				<input name="fechaFabricacion" class="form-control" type="date" value='<fmt:formatDate value="${linea.fechaFabricacion}" pattern="yyyy-MM-dd"/>'/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Último retimbrado
			</div>
			<div class="col col-md-10">
				<input name="ultimoRetimbrado" class="form-control" type="date" value='<fmt:formatDate value="${linea.ultimoRetimbrado}" pattern="yyyy-MM-dd"/>'/>
			</div>
		</div>
		<div class="row"> 
			<div class="col col-md-2">
				Pruebas
			</div>
			<div class="col col-md-10">
				<select name="prueba" class="form-control" data-live-search="true">
					<c:forEach items="${pruebas}" var="e">
						<c:choose>
							<c:when test="${linea.rt eq true and e.oid eq 1}">
								<option value="${e.oid}" selected="selected">${e.descr}</option>
							</c:when>
							<c:when test="${linea.rc eq true and e.oid eq 2}">
								<option value="${e.oid}" selected="selected">${e.descr}</option>
							</c:when>
							<c:when test="${linea.rv eq true and e.oid eq 3}">
								<option value="${e.oid}" selected="selected">${e.descr}</option>
							</c:when>
							<c:when test="${linea.nuevo eq true and e.oid eq 4}">
								<option value="${e.oid}" selected="selected">${e.descr}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.oid}">${e.descr}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Cartel
			</div>
			<div class="col col-md-10">
				<input name="cartel" class="form-control" type="date" value='<fmt:formatDate value="${linea.cartel}" pattern="yyyy-MM-dd"/>'/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Altura
			</div>
			<div class="col col-md-10">
				<select name="altura" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.altura eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Precio
			</div>
			<div class="col col-md-10">
				<input type="number" step="0.01" name="precio" class="form-control" value="${linea.precio}"/>
			</div>
		</div>
	</c:if>
	<c:if test="${oidpartetipo eq 2}">
		<div class="row">
			<div class="col col-md-2">
				Ubicación
			</div>
			<div class="col col-md-10">
				<input name="ubicacion" class="form-control" value="${linea.ubicacion}">
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Número de serie
			</div>
			<div class="col col-md-10">
				<input name="numSerie" class="form-control" value="${linea.numSerie}">
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Tipo
			</div>
			<div class="col col-md-10">
				<select name="tipo" class="form-control" data-live-search="true">
					<c:forEach items="${tiposextintor}" var="te">
						<c:choose>
							<c:when test="${linea.tipoBie.oid eq te.oid}">
								<option value="${te.oid}" selected="selected">${te.tipo}</option>
							</c:when>
							<c:otherwise>
								<option value="${te.oid}">${te.tipo}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Equipo
			</div>
			<div class="col col-md-10">
				<select name="longMang" class="form-control" data-live-search="true">
					<c:forEach items="${combolongbie}" var="te">
						<c:choose>
							<c:when test="${linea.longMang eq te.oid}">
								<option value="${te.oid}" selected="selected">${te.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${te.oid}">${te.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Fecha fabricación
			</div>
			<div class="col col-md-10">
				<input name="fechaRetimA" class="form-control" type="date" value='<fmt:formatDate value="${linea.fechaRetimA}" pattern="yyyy-MM-dd"/>'/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Fecha retimbrado
			</div>
			<div class="col col-md-10">
				<input name="fechaRetimB" class="form-control" type="date" value='<fmt:formatDate value="${linea.fechaRetimB}" pattern="yyyy-MM-dd"/>'/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Presión estática
			</div>
			<div class="col col-md-10">
				<div class="form-gropup">
					<div class="input-group">
						<input type="number" step="0.01" name="presionEstatica" class="form-control" value="${linea.presionEstatica}"/>
						<span class="input-group-addon">bar</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Presión dinámica
			</div>
			<div class="col col-md-10">
				<div class="form-gropup">
					<div class="input-group">
						<input type="number" step="0.01" name="presionDinamica" class="form-control" value="${linea.presionDinamica}"/>
						<span class="input-group-addon">bar</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Manguera
			</div>
			<div class="col col-md-10">
				<select name="manguera" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.manguera eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Lanza
			</div>
			<div class="col col-md-10">
				<select name="lanza" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.lanza eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Válvula
			</div>
			<div class="col col-md-10">
				<select name="valvula" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.valvula eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Manómetro
			</div>
			<div class="col col-md-10">
				<select name="manometro" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.manometro eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Cristal
			</div>
			<div class="col col-md-10">
				<select name="cristal" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.cristal eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Señales
			</div>
			<div class="col col-md-10">
				<select name="sennales" class="form-control" data-live-search="true">
					<c:forEach items="${combosino}" var="e">
						<c:choose>
							<c:when test="${linea.sennales eq e.valor}">
								<option value="${e.valor}" selected="selected">${e.valor}</option>
							</c:when>
							<c:otherwise>
								<option value="${e.valor}">${e.valor}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Estado general
			</div>
			<div class="col col-md-10">
				<input name="estadoGeneral" class="form-control" value="${linea.estadoGeneral}">
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Precio
			</div>
			<div class="col col-md-10">
				<input type="number" step="0.01" name="precio" class="form-control" value="${linea.precio}"/>
			</div>
		</div>
	</c:if>
	<c:if test="${oidpartetipo eq 4}">
		<div class="row">
			<div class="col col-md-2">
				Cantidad
			</div>
			<div class="col col-md-10">
				<input type="number" name="cantidad" class="form-control" value="${linea.cantidad}"/>
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Precio
			</div>
			<div class="col col-md-10">
				<input type="number" step="0.01" name="precio" class="form-control" value="${linea.precio}"/> 
			</div>
		</div>
		<div class="row">
			<div class="col col-md-2">
				Descripción
			</div>
			<div class="col col-md-10">
				<textarea id="descripcion" name="descripcion" class="form-control" rows="5" cols="30">${linea.descripcion}</textarea>
			</div>
		</div>
	</c:if>
	<hr/>
</div>

