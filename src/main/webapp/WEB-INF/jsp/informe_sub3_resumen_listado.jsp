<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib prefix="c" uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false"%>

<div class="divexample">
	<table id="${id}" class="example display nowrap compact">
		<thead>
			<tr>
				<th>Número</th>
				<th>Pagada</th>
				<th>Fecha</th>
				<th>Cliente</th>
				<th>Documento</th>
				<th>Base</th>
				<th>IVA</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pagadas}" var="c">
				<tr>
					<td> ${c.numero} </td>
					<td> PAGADAS </td>
					<td> <fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy" /> </td>
					<td> ${c.TCliente.nombre} ${c.TCliente.apellidos} </td>
					<td> ${c.TCliente.dni} </td>
					<td> <fmt:formatNumber value="${c.base}" pattern="#,###,##0.00" /> </td>
					<td> <fmt:formatNumber value="${c.baseIva}" pattern="#,###,##0.00" /> </td>
					<td> <fmt:formatNumber value="${c.total}" pattern="#,###,##0.00" /> </td>
				</tr>
			</c:forEach>
			<c:forEach items="${sinpagar}" var="c">
				<tr>
					<td> ${c.numero} </td>
					<td> SIN PAGAR </td>
					<td> <fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy" /> </td>
					<td> ${c.TCliente.nombre} ${c.TCliente.apellidos} </td>
					<td> ${c.TCliente.dni} </td>
					<td> <fmt:formatNumber value="${c.base}" pattern="#,###,##0.00" /> </td>
					<td> <fmt:formatNumber value="${c.baseIva}" pattern="#,###,##0.00" /> </td>
					<td> <fmt:formatNumber value="${c.total}" pattern="#,###,##0.00" /> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>