<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false"%>

<ul class="nav nav-tabs">
	<c:if test='${oidpartetipo eq 5}'>
		<li class="active"><a href="#1b" data-toggle="tab">Datos
				Central</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 5}'>
		<li><a href="#4b" data-toggle="tab">Pulsadores</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 5}'>
		<li><a href="#5b" data-toggle="tab">Sirenas</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 5}'>
		<li><a href="#3b" data-toggle="tab">Detectores</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 5}'>
		<li><a href="#2b" data-toggle="tab">Luminaria</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 6}'>
		<li class="active"><a href="#6b" data-toggle="tab">Equipo Auxiliar</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 6}'>
		<li><a href="#7b" data-toggle="tab">Retenedor</a></li>
	</c:if>
	<c:if test='${oidpartetipo eq 6}'>
		<li><a href="#8b" data-toggle="tab">Puertas</a></li>
	</c:if>
</ul>

<div class="tab-content clearfix">
	<c:if test='${oidpartetipo eq 5}'><div class="tab-pane active" id="1b">
		<c:choose>
			<c:when test="${empty lineascentral}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenCentral"
								class="form-control orden-input" required
								value="${linea.ordenCentral}" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marca" class="form-control" value="${linea.marca}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Pilotos</div>
						<div class="col col-md-10">
							<input name="pilotos" class="form-control"
								value="${linea.pilotos}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo de Batería</div>
						<div class="col col-md-10">
							<input name="tipoBateriaCentral" class="form-control"
								value="${linea.tipoBateriaCentral}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<select name="tipoCentral" class="form-control">
								<c:forEach items="${tipocentral}" var="te">
									<c:choose>
										<c:when test="${linea.tipoCentral eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Modelo</div>
						<div class="col col-md-10">
							<input name="modelo" class="form-control" value="${linea.modelo}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Relés</div>
						<div class="col col-md-10">
							<input name="reles" class="form-control" value="${linea.reles}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Unidades</div>
						<div class="col col-md-10">
							<input name="unidades" class="form-control"
								value="${linea.unidades}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Nº de zonas</div>
						<div class="col col-md-10">
							<input name="nZona" class="form-control" value="${linea.nZona}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zumbador</div>
						<div class="col col-md-10">
							<input name="zumbador" class="form-control"
								value="${linea.zumbador}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zonas</div>
						<div class="col col-md-10">
							<input name="zonas" class="form-control" value="${linea.zonas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Mandos control</div>
						<div class="col col-md-10">
							<input name="mandosControl" class="form-control"
								value="${linea.mandosControl}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Líneas cableado</div>
						<div class="col col-md-10">
							<input name="lineasCableado" class="form-control"
								value="${linea.lineasCableado}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zonas de reserva</div>
						<div class="col col-md-10">
							<input name="zonasReserva" class="form-control"
								value="${linea.zonasReserva}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Red. Indep</div>
						<div class="col col-md-10">
							<input name="redIndep" class="form-control"
								value="${linea.redIndep}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Temporizador</div>
						<div class="col col-md-10">
							<input name="temporizador" class="form-control"
								value="${linea.temporizador}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado de carga</div>
						<div class="col col-md-10">
							<input name="estadoCargaCentral" class="form-control"
								value="${linea.estadoCargaCentral}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioCentral"
								class="form-control" value="${linea.precioCentral}" />
						</div>
					</div>
					<hr />
				</div>

			</c:when>
			<c:otherwise>
				<c:forEach items="${lineascentral}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenCentral"
									class="form-control orden-input" required
									value="${linea.ordenCentral}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marca" class="form-control" value="${linea.marca}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Pilotos</div>
							<div class="col col-md-10">
								<input name="pilotos" class="form-control"
									value="${linea.pilotos}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo de Batería</div>
							<div class="col col-md-10">
								<input name="tipoBateriaCentral" class="form-control"
									value="${linea.tipoBateriaCentral}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<select name="tipoCentral" class="form-control">
									<c:forEach items="${tipocentral}" var="te">
										<c:choose>
											<c:when test="${linea.tipoCentral eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Modelo</div>
							<div class="col col-md-10">
								<input name="modelo" class="form-control"
									value="${linea.modelo}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Relés</div>
							<div class="col col-md-10">
								<input name="reles" class="form-control" value="${linea.reles}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Unidades</div>
							<div class="col col-md-10">
								<input name="unidades" class="form-control"
									value="${linea.unidades}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Nº de zonas</div>
							<div class="col col-md-10">
								<input name="nZona" class="form-control" value="${linea.nZona}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zumbador</div>
							<div class="col col-md-10">
								<input name="zumbador" class="form-control"
									value="${linea.zumbador}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zonas</div>
							<div class="col col-md-10">
								<input name="zonas" class="form-control" value="${linea.zonas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Mandos control</div>
							<div class="col col-md-10">
								<input name="mandosControl" class="form-control"
									value="${linea.mandosControl}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Líneas cableado</div>
							<div class="col col-md-10">
								<input name="lineasCableado" class="form-control"
									value="${linea.lineasCableado}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zonas de reserva</div>
							<div class="col col-md-10">
								<input name="zonasReserva" class="form-control"
									value="${linea.zonasReserva}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Red. Indep</div>
							<div class="col col-md-10">
								<input name="redIndep" class="form-control"
									value="${linea.redIndep}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Temporizador</div>
							<div class="col col-md-10">
								<input name="temporizador" class="form-control"
									value="${linea.temporizador}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado de carga</div>
							<div class="col col-md-10">
								<input name="estadoCargaCentral" class="form-control"
									value="${linea.estadoCargaCentral}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioCentral"
									class="form-control" value="${linea.precioCentral}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="tab-pane" id="2b">
		<c:choose>
			<c:when test="${empty lineasfuente}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenFuente"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input name="cantidadLuminaria" class="form-control"
								value="${linea.cantidadLuminaria}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubicacionLuminaria" class="form-control"
								value="${linea.ubicacionLuminaria}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<input name="tipoBateriaFuente" class="form-control"
								value="${linea.tipoBateriaFuente}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marchar" class="form-control"
								value="${linea.marchar}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoLuminaria" class="form-control"
								value="${linea.anoLuminaria}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<input name="estadoCargaFuente" class="form-control"
								value="${linea.estadoCargaFuente}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Lúmenes</div>
						<div class="col col-md-10">
							<input name="lumenes" class="form-control"
								value="${linea.lumenes}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioFuente"
								class="form-control" value="${linea.precioFuente}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasfuente}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenFuente"
									class="form-control orden-input" required
									value="${linea.ordenFuente}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input name="cantidadLuminaria" class="form-control"
									value="${linea.cantidadLuminaria}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubicacionLuminaria" class="form-control"
									value="${linea.ubicacionLuminaria}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<input name="tipoBateriaFuente" class="form-control"
									value="${linea.tipoBateriaFuente}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marchar" class="form-control"
									value="${linea.marchar}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoLuminaria" class="form-control"
									value="${linea.anoLuminaria}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<input name="estadoCargaFuente" class="form-control"
									value="${linea.estadoCargaFuente}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Lúmenes</div>
							<div class="col col-md-10">
								<input name="lumenes" class="form-control"
									value="${linea.lumenes}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioFuente"
									class="form-control" value="${linea.precioFuente}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="tab-pane" id="3b">
		<c:choose>
			<c:when test="${empty lineasdetectores}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenDetectores"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input type="number" name="cantidadDetectores"
								class="form-control orden-input"
								value="${linea.cantidadDetectores}" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubiDetectores" class="form-control"
								value="${linea.ubiDetectores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<select name="tipoDetectores" class="form-control">
								<c:forEach items="${tipodetectores}" var="te">
									<c:choose>
										<c:when test="${linea.tipoDetectores eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaDetectores" class="form-control"
								value="${linea.marcaDetectores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Función</div>
						<div class="col col-md-10">
							<select name="funcionDetectores" class="form-control">
								<c:forEach items="${funciondetectores}" var="te">
									<c:choose>
										<c:when test="${linea.funcionDetectores eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoDetectores" class="form-control"
								value="${linea.anoDetectores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zona</div>
						<div class="col col-md-10">
							<input name="zonaDetectores" class="form-control"
								value="${linea.zonaDetectores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<select name="estadoDetectores" class="form-control">
								<c:forEach items="${estadolineacentralita}" var="te">
									<c:choose>
										<c:when test="${linea.estadoDetectores eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioDetectores"
								class="form-control" value="${linea.precioDetectores}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasdetectores}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenDetectores"
									class="form-control orden-input" required
									value="${linea.ordenDetectores}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input type="number" name="cantidadDetectores"
									class="form-control orden-input"
									value="${linea.cantidadDetectores}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubiDetectores" class="form-control"
									value="${linea.ubiDetectores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<select name="tipoDetectores" class="form-control">
									<c:forEach items="${tipodetectores}" var="te">
										<c:choose>
											<c:when test="${linea.tipoDetectores eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaDetectores" class="form-control"
									value="${linea.marcaDetectores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Función</div>
							<div class="col col-md-10">
								<select name="funcionDetectores" class="form-control">
									<c:forEach items="${funciondetectores}" var="te">
										<c:choose>
											<c:when test="${linea.funcionDetectores eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoDetectores" class="form-control"
									value="${linea.anoDetectores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zona</div>
							<div class="col col-md-10">
								<input name="zonaDetectores" class="form-control"
									value="${linea.zonaDetectores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<select name="estadoDetectores" class="form-control">
									<c:forEach items="${estadolineacentralita}" var="te">
										<c:choose>
											<c:when test="${linea.estadoDetectores eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioDetectores"
									class="form-control" value="${linea.precioDetectores}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="tab-pane" id="4b">
		<c:choose>
			<c:when test="${empty lineaspulsadores}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenPulsadores"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input name="cantidadPulsadores" class="form-control"
								value="${linea.cantidadPulsadores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubiPulsadores" class="form-control"
								value="${linea.ubiPulsadores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<select name="tipoPulsadores" class="form-control">
								<c:forEach items="${tipopulsadores}" var="te">
									<c:choose>
										<c:when test="${linea.tipoPulsadores eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaPulsadores" class="form-control"
								value="${linea.marcaPulsadores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoPulsadores" class="form-control"
								value="${linea.anoPulsadores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zona</div>
						<div class="col col-md-10">
							<input name="zonaPulsadores" class="form-control"
								value="${linea.zonaPulsadores}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<select name="estadoPulsadores" class="form-control">
								<c:forEach items="${estadolineacentralita}" var="te">
									<c:choose>
										<c:when test="${linea.estadoPulsadores eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioPulsadores"
								class="form-control" value="${linea.precioPulsadores}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>

				<c:forEach items="${lineaspulsadores}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenPulsadores"
									class="form-control orden-input" required
									value="${linea.ordenPulsadores}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input name="cantidadPulsadores" class="form-control"
									value="${linea.cantidadPulsadores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubiPulsadores" class="form-control"
									value="${linea.ubiPulsadores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<select name="tipoPulsadores" class="form-control">
									<c:forEach items="${tipopulsadores}" var="te">
										<c:choose>
											<c:when test="${linea.tipoPulsadores eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaPulsadores" class="form-control"
									value="${linea.marcaPulsadores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoPulsadores" class="form-control"
									value="${linea.anoPulsadores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zona</div>
							<div class="col col-md-10">
								<input name="zonaPulsadores" class="form-control"
									value="${linea.zonaPulsadores}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<select name="estadoPulsadores" class="form-control">
									<c:forEach items="${estadolineacentralita}" var="te">
										<c:choose>
											<c:when test="${linea.estadoPulsadores eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioPulsadores"
									class="form-control" value="${linea.precioPulsadores}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="tab-pane" id="5b">
		<c:choose>
			<c:when test="${empty lineassirenas}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenSirenas"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input name="cantidadSirenas" class="form-control"
								value="${linea.cantidadSirenas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubiSirenas" class="form-control"
								value="${linea.ubiSirenas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<select name="tipoSirenas" class="form-control">
								<c:forEach items="${tiposirenas}" var="te">
									<c:choose>
										<c:when test="${linea.tipoSirenas eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaSirenas" class="form-control"
								value="${linea.marcaSirenas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoSirenas" class="form-control"
								value="${linea.anoSirenas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zona</div>
						<div class="col col-md-10">
							<input name="zonaSirenas" class="form-control"
								value="${linea.zonaSirenas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<select name="estadoSirenas" class="form-control">
								<c:forEach items="${estadolineacentralita}" var="te">
									<c:choose>
										<c:when test="${linea.estadoSirenas eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioSirenas"
								class="form-control" value="${linea.precioSirenas}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineassirenas}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenSirenas"
									class="form-control orden-input" required
									value="${linea.ordenSirenas}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input name="cantidadSirenas" class="form-control"
									value="${linea.cantidadSirenas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubiSirenas" class="form-control"
									value="${linea.ubiSirenas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<select name="tipoSirenas" class="form-control">
									<c:forEach items="${tiposirenas}" var="te">
										<c:choose>
											<c:when test="${linea.tipoSirenas eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaSirenas" class="form-control"
									value="${linea.marcaSirenas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoSirenas" class="form-control"
									value="${linea.anoSirenas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zona</div>
							<div class="col col-md-10">
								<input name="zonaSirenas" class="form-control"
									value="${linea.zonaSirenas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<select name="estadoSirenas" class="form-control">
									<c:forEach items="${estadolineacentralita}" var="te">
										<c:choose>
											<c:when test="${linea.estadoSirenas eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioSirenas"
									class="form-control" value="${linea.precioSirenas}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div></c:if>
	<c:if test='${oidpartetipo eq 6}'><div class="tab-pane active" id="6b">
		<c:choose>
			<c:when test="${empty lineasequipoauxiliar}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenEquipoAuxiliar"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input type="number" name="cantidadEquipoAuxiliar"
								class="form-control orden-input"
								value="${linea.cantidadEquipoAuxiliar}" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubicacionEquipoAuxiliar" class="form-control"
								value="${linea.ubicacionEquipoAuxiliar}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
						<div class="col col-md-10">
							<select name="tipoEquipoAuxiliar" class="form-control">
								<c:forEach items="${tiposequipoauxiliar}" var="te">
									<c:choose>
										<c:when test="${linea.tipoEquipoAuxiliar eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaEquipoAuxiliar" class="form-control"
								value="${linea.marcaEquipoAuxiliar}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoEquipoAuxiliar" class="form-control"
								value="${linea.anoEquipoAuxiliar}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Zona</div>
						<div class="col col-md-10">
							<input name="zonaEquipoAuxiliar" class="form-control"
								value="${linea.zonaEquipoAuxiliar}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<select name="estadoEquipoAuxiliar" class="form-control">
								<c:forEach items="${estadolineacentralita}" var="te">
									<c:choose>
										<c:when test="${linea.estadoEquipoAuxiliar eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioEquipoAuxiliar"
								class="form-control" value="${linea.precioEquipoAuxiliar}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasequipoauxiliar}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenEquipoAuxiliar"
									class="form-control orden-input" required
									value="${linea.ordenEquipoAuxiliar}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input type="number" name="cantidadEquipoAuxiliar"
									class="form-control orden-input"
									value="${linea.cantidadEquipoAuxiliar}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubicacionEquipoAuxiliar" class="form-control"
									value="${linea.ubicacionEquipoAuxiliar}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
							<div class="col col-md-10">
								<select name="tipoEquipoAuxiliar" class="form-control">
									<c:forEach items="${tiposequipoauxiliar}" var="te">
										<c:choose>
											<c:when test="${linea.tipoEquipoAuxiliar eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaEquipoAuxiliar" class="form-control"
									value="${linea.marcaEquipoAuxiliar}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoEquipoAuxiliar" class="form-control"
									value="${linea.anoEquipoAuxiliar}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Zona</div>
							<div class="col col-md-10">
								<input name="zonaEquipoAuxiliar" class="form-control"
									value="${linea.zonaEquipoAuxiliar}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<select name="estadoEquipoAuxiliar" class="form-control">
									<c:forEach items="${estadolineacentralita}" var="te">
										<c:choose>
											<c:when test="${linea.estadoEquipoAuxiliar eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioEquipoAuxiliar"
									class="form-control" value="${linea.precioEquipoAuxiliar}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="tab-pane" id="7b">
		<c:choose>
			<c:when test="${empty lineasretenedor}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenRetenedor"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input type="number" name="cantidadRetenedor"
								class="form-control orden-input"
								value="${linea.cantidadRetenedor}" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubiRetenedor" class="form-control"
								value="${linea.ubiRetenedor}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaRetenedor" class="form-control"
								value="${linea.marcaRetenedor}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoRetenedor" class="form-control"
								value="${linea.anoRetenedor}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<input name="estadoRetenedor" class="form-control"
								value="${linea.estadoRetenedor}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioRetenedor"
								class="form-control" value="${linea.precioRetenedor}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasretenedor}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenRetenedor"
									class="form-control orden-input" required
									value="${linea.ordenRetenedor}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input type="number" name="cantidadRetenedor"
									class="form-control orden-input"
									value="${linea.cantidadRetenedor}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubiRetenedor" class="form-control"
									value="${linea.ubiRetenedor}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaRetenedor" class="form-control"
									value="${linea.marcaRetenedor}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoRetenedor" class="form-control"
									value="${linea.anoRetenedor}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<input name="estadoRetenedor" class="form-control"
									value="${linea.estadoRetenedor}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioRetenedor"
									class="form-control" value="${linea.precioRetenedor}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div>
	<div class="tab-pane" id="8b">
		<c:choose>
			<c:when test="${empty lineaspuertas}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden *
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenPuertas"
								class="form-control orden-input" required value="0" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Cantidad</div>
						<div class="col col-md-10">
							<input type="number" name="cantidadPuertas"
								class="form-control orden-input"
								value="${linea.cantidadPuertas}" min="0" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubiPuertas" class="form-control"
								value="${linea.ubiPuertas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaPuertas" class="form-control"
								value="${linea.marcaPuertas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Año</div>
						<div class="col col-md-10">
							<input name="anoPuertas" class="form-control"
								value="${linea.anoPuertas}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Estado</div>
						<div class="col col-md-10">
							<select name="estadoPuertas" class="form-control">
								<c:forEach items="${estadolineacentralita}" var="te">
									<c:choose>
										<c:when test="${linea.estadoPuertas eq te.descr}">
											<option value="${te.descr}" selected="selected">${te.descr}</option>
										</c:when>
										<c:otherwise>
											<option value="${te.descr}">${te.descr}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precioPuertas"
								class="form-control" value="${linea.precioPuertas}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineaspuertas}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden *
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenPuertas"
									class="form-control orden-input" required
									value="${linea.ordenPuertas}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Cantidad</div>
							<div class="col col-md-10">
								<input type="number" name="cantidadPuertas"
									class="form-control orden-input"
									value="${linea.cantidadPuertas}" min="0" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubiPuertas" class="form-control"
									value="${linea.ubiPuertas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaPuertas" class="form-control"
									value="${linea.marcaPuertas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Año</div>
							<div class="col col-md-10">
								<input name="anoPuertas" class="form-control"
									value="${linea.anoPuertas}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Estado</div>
							<div class="col col-md-10">
								<select name="estadoPuertas" class="form-control">
									<c:forEach items="${estadolineacentralita}" var="te">
										<c:choose>
											<c:when test="${linea.estadoPuertas eq te.descr}">
												<option value="${te.descr}" selected="selected">${te.descr}</option>
											</c:when>
											<c:otherwise>
												<option value="${te.descr}">${te.descr}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precioPuertas"
									class="form-control" value="${linea.precioPuertas}" />
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</div></c:if>
</div>