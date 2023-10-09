<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="container">
	<div class="row alert alert-info">
		<div class="col-md-2">Número</div>
		<div class="col-md-1">Fecha</div>
		<div class="col-md-3">Cliente</div>
		<div class="col-md-3">Otro cliente</div>
		<div class="col-md-1">Base</div>
		<div class="col-md-1">IVA</div>
		<div class="col-md-1">Total</div>
	</div>
	<c:forEach items="${xfacturas}" var="c" varStatus="cont">
		<div class="row">
			<div class="col-md-2">
				<div class="funkyradio">
					<div class="funkyradio-default">
						<c:choose>
							<c:when test="${cont.index == 0}">
								<input type="radio" id="${cont.index}" name="oid" value="${c.oid}" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input type="radio" id="${cont.index}" name="oid" value="${c.oid}"/>
							</c:otherwise>
						</c:choose>
			            <label for="${cont.index}"> ${c.numero} </label>
			        </div>
		        </div>
			</div>
			<div class="col-md-1"> <fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy"/> </div>
			<div class="col-md-3">${c.TCliente.nombre} ${c.TCliente.apellidos}</div>
			<div class="col-md-3">${c.TCliente2.nombre} ${c.TCliente2.apellidos}</div>
			<div class="col-md-1 text-right"> <fmt:formatNumber value="${c.base}" pattern="#,###,##0.00"/> </div>
			<div class="col-md-1 text-right"> <fmt:formatNumber value="${c.baseIva}" pattern="#,###,##0.00"/> </div>
			<div class="col-md-1 text-right"> <fmt:formatNumber value="${c.total}" pattern="#,###,##0.00"/> </div>
		</div>
	</c:forEach>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-2"></div>
		<div class="col-md-3"></div>
		<div class="col-md-3"></div>
		<div class="col-md-1 text-right"> <fmt:formatNumber value="${xbase}" pattern="#,###,##0.00"/> </div>
		<div class="col-md-1 text-right"> <fmt:formatNumber value="${xiva}" pattern="#,###,##0.00"/> </div>
		<div class="col-md-1 text-right"> <fmt:formatNumber value="${xtotal}" pattern="#,###,##0.00"/> </div>
	</div>
</div>
