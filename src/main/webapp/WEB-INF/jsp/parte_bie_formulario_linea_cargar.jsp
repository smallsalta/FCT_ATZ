<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isELIgnored="false"%>

<ul class="nav nav-tabs">
	<li class="active"><a href="#1b" data-toggle="tab">BIE</a></li>
	<li><a href="#2b" data-toggle="tab">Bomba</a></li>
</ul>

<div class="tab-content clearfix">
	<div class="tab-pane active" id="1b">
		<c:choose>
			<c:when test="${empty lineasbie}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden * <input
								type="checkbox" />
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="orden"
								class="form-control orden-input" required value="${linea.orden}"
								min="1" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Ubicación</div>
						<div class="col col-md-10">
							<input name="ubicacion" class="form-control"
								value="${linea.ubicacion}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Número de serie</div>
						<div class="col col-md-10">
							<input name="numSerie" class="form-control"
								value="${linea.numSerie}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo</div>
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
						<div class="col col-md-2">Equipo</div>
						<div class="col col-md-10">
							<select name="longMang" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Fecha fabricación</div>
						<div class="col col-md-10">
							<input name="fechaRetimA" class="form-control" type="date"
								value='<fmt:formatDate value="${linea.fechaRetimA}" pattern="yyyy-MM-dd"/>' />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Fecha retimbrado</div>
						<div class="col col-md-10">
							<input name="fechaRetimB" class="form-control" type="date"
								value='<fmt:formatDate value="${linea.fechaRetimB}" pattern="yyyy-MM-dd"/>' />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Presión estática</div>
						<div class="col col-md-10">
							<div class="form-gropup">
								<div class="input-group">
									<input type="number" step="0.01" name="presionEstatica"
										class="form-control" value="${linea.presionEstatica}" /> <span
										class="input-group-addon">bar</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Presión dinámica</div>
						<div class="col col-md-10">
							<div class="form-gropup">
								<div class="input-group">
									<input type="number" step="0.01" name="presionDinamica"
										class="form-control" value="${linea.presionDinamica}" /> <span
										class="input-group-addon">bar</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Manguera</div>
						<div class="col col-md-10">
							<select name="manguera" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Lanza</div>
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
						<div class="col col-md-2">Válvula</div>
						<div class="col col-md-10">
							<select name="valvula" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Manómetro</div>
						<div class="col col-md-10">
							<select name="manometro" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Cristal</div>
						<div class="col col-md-10">
							<select name="cristal" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Señales</div>
						<div class="col col-md-10">
							<select name="sennales" class="form-control"
								data-live-search="true">
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
						<div class="col col-md-2">Estado general</div>
						<div class="col col-md-10">
							<input name="estadoGeneral" class="form-control"
								value="${linea.estadoGeneral}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Precio</div>
						<div class="col col-md-10">
							<input type="number" step="0.01" name="precio"
								class="form-control" value="${linea.precio}" />
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasbie}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden * <input
									type="checkbox" />
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="orden"
									class="form-control orden-input" required
									value="${linea.orden}" min="1" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Ubicación</div>
							<div class="col col-md-10">
								<input name="ubicacion" class="form-control"
									value="${linea.ubicacion}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Número de serie</div>
							<div class="col col-md-10">
								<input name="numSerie" class="form-control"
									value="${linea.numSerie}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo</div>
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
							<div class="col col-md-2">Equipo</div>
							<div class="col col-md-10">
								<select name="longMang" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Fecha fabricación</div>
							<div class="col col-md-10">
								<input name="fechaRetimA" class="form-control" type="date"
									value='<fmt:formatDate value="${linea.fechaRetimA}" pattern="yyyy-MM-dd"/>' />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Fecha retimbrado</div>
							<div class="col col-md-10">
								<input name="fechaRetimB" class="form-control" type="date"
									value='<fmt:formatDate value="${linea.fechaRetimB}" pattern="yyyy-MM-dd"/>' />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Presión estática</div>
							<div class="col col-md-10">
								<div class="form-gropup">
									<div class="input-group">
										<input type="number" step="0.01" name="presionEstatica"
											class="form-control" value="${linea.presionEstatica}" /> <span
											class="input-group-addon">bar</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Presión dinámica</div>
							<div class="col col-md-10">
								<div class="form-gropup">
									<div class="input-group">
										<input type="number" step="0.01" name="presionDinamica"
											class="form-control" value="${linea.presionDinamica}" /> <span
											class="input-group-addon">bar</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Manguera</div>
							<div class="col col-md-10">
								<select name="manguera" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Lanza</div>
							<div class="col col-md-10">
								<select name="lanza" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Válvula</div>
							<div class="col col-md-10">
								<select name="valvula" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Manómetro</div>
							<div class="col col-md-10">
								<select name="manometro" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Cristal</div>
							<div class="col col-md-10">
								<select name="cristal" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Señales</div>
							<div class="col col-md-10">
								<select name="sennales" class="form-control"
									data-live-search="true">
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
							<div class="col col-md-2">Estado general</div>
							<div class="col col-md-10">
								<input name="estadoGeneral" class="form-control"
									value="${linea.estadoGeneral}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Precio</div>
							<div class="col col-md-10">
								<input type="number" step="0.01" name="precio"
									class="form-control" value="${linea.precio}" />
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
			<c:when test="${empty lineasbomba}">
				<br />
				<div class="container fila">
					<div class="row">
						<div class="col col-md-2">
							<label> <input type="radio" name="borrar" /> Orden * <input
								type="checkbox" />
							</label>
						</div>
						<div class="col col-md-10">
							<input type="number" name="ordenBomba"
								class="form-control orden-input" required
								value="${linea.ordenBomba}" min="1" />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Tipo de bomba</div>
						<div class="col col-md-10">
							<select name="tipoBomba" class="form-control" data-live-search="true">
								<c:forEach items="${tiposbomba}" var="te">
									<c:choose>
										<c:when test="${linea.tipoBomba.oid eq te.oid}">
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
						<div class="col col-md-2">Marca</div>
						<div class="col col-md-10">
							<input name="marcaBomba" class="form-control"
								value="${linea.marcaBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Modelo</div>
						<div class="col col-md-10">
							<input name="modeloBomba" class="form-control"
								value="${linea.modeloBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Fecha</div>
						<div class="col col-md-10">
							<input name="fechaBomba" class="form-control" type="date"
								value='<fmt:formatDate value="${linea.fechaBomba}" pattern="yyyy-MM-dd"/>' />
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Motor</div>
						<div class="col col-md-10">
							<input name="motorBomba" class="form-control"
								value="${linea.motorBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Voltaje</div>
						<div class="col col-md-10">
							<input type="number" name="voltajeBomba" class="form-control"
								value="${linea.voltajeBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">RPM</div>
						<div class="col col-md-10">
							<input type="number" name="rpmBomba" class="form-control"
								value="${linea.rpmBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Manómetro</div>
						<div class="col col-md-10">
							<input name="manometroBomba" class="form-control"
								value="${linea.manometroBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Esfera</div>
						<div class="col col-md-10">
							<input name="esferaBomba" class="form-control"
								value="${linea.esferaBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Válvulas</div>
						<div class="col col-md-10">
							<input name="valvulasBomba" class="form-control"
								value="${linea.valvulasBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Saltos</div>
						<div class="col col-md-10">
							<input name="saltosBomba" class="form-control"
								value="${linea.saltosBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Fusibles</div>
						<div class="col col-md-10">
							<input name="fusiblesBomba" class="form-control"
								value="${linea.fusiblesBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Alarma</div>
						<div class="col col-md-10">
							<input name="alarmaBomba" class="form-control"
								value="${linea.alarmaBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Caudalímetro</div>
						<div class="col col-md-10">
							<input type="number" name="caudalimetroBomba"
								class="form-control" value="${linea.caudalimetroBomba}">
						</div>
					</div>
					<div class="row">
						<div class="col col-md-2">Presión</div>
						<div class="col col-md-10">
							<div class="form-gropup">
								<div class="input-group">
									<input type="number" name="presionBomba" class="form-control" value="${linea.presionBomba}" step="0.01"/> 
									<span class="input-group-addon">bar</span>
								</div>
							</div>
						</div>
					</div>
					<hr />
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${lineasbomba}" var="l" varStatus="i">
					<c:set scope="request" var="idx" value="${i.index}" />
					<c:set scope="request" var="linea" value="${l}" />
					<br />
					<div class="container fila">
						<div class="row">
							<div class="col col-md-2">
								<label> <input type="radio" name="borrar" /> Orden * <input
									type="checkbox" />
								</label>
							</div>
							<div class="col col-md-10">
								<input type="number" name="ordenBomba"
									class="form-control orden-input" required
									value="${linea.ordenBomba}" min="1" />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Tipo de bomba</div>
							<div class="col col-md-10">
								<select name="tipoBomba" class="form-control" data-live-search="true">
									<c:forEach items="${tiposbomba}" var="te">
										<c:choose>
											<c:when test="${linea.tipoBomba.oid eq te.oid}">
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
							<div class="col col-md-2">Marca</div>
							<div class="col col-md-10">
								<input name="marcaBomba" class="form-control"
									value="${linea.marcaBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Modelo</div>
							<div class="col col-md-10">
								<input name="modeloBomba" class="form-control"
									value="${linea.modeloBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Fecha</div>
							<div class="col col-md-10">
								<input name="fechaBomba" class="form-control" type="date"
									value='<fmt:formatDate value="${linea.fechaBomba}" pattern="yyyy-MM-dd"/>' />
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Motor</div>
							<div class="col col-md-10">
								<input name="motorBomba" class="form-control"
									value="${linea.motorBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Voltaje</div>
							<div class="col col-md-10">
								<input name="voltajeBomba" class="form-control"
									value="${linea.voltajeBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">RPM</div>
							<div class="col col-md-10">
								<input type="number" name="rpmBomba" class="form-control"
									value="${linea.rpmBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Manómetro</div>
							<div class="col col-md-10">
								<input name="manometroBomba" class="form-control"
									value="${linea.manometroBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Esfera</div>
							<div class="col col-md-10">
								<input name="esferaBomba" class="form-control"
									value="${linea.esferaBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Válvulas</div>
							<div class="col col-md-10">
								<input name="valvulasBomba" class="form-control"
									value="${linea.valvulasBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Saltos</div>
							<div class="col col-md-10">
								<input name="saltosBomba" class="form-control"
									value="${linea.saltosBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Fusibles</div>
							<div class="col col-md-10">
								<input name="fusiblesBomba" class="form-control"
									value="${linea.fusiblesBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Alarma</div>
							<div class="col col-md-10">
								<input name="alarmaBomba" class="form-control"
									value="${linea.alarmaBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Caudalímetro</div>
							<div class="col col-md-10">
								<input name="caudalimetroBomba"
									class="form-control" value="${linea.caudalimetroBomba}">
							</div>
						</div>
						<div class="row">
							<div class="col col-md-2">Presión</div>
							<div class="col col-md-10">
								<div class="form-gropup">
									<div class="input-group">
										<input type="number" name="presionBomba" class="form-control" value="${linea.presionBomba}" step="0.01"/> 
										<span class="input-group-addon">bar</span>
									</div>
								</div>
							</div>
						</div>
						<hr />
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>