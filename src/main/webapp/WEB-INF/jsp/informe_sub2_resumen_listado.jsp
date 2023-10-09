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
				<th>Fecha</th>
				<th>Cliente</th>
				<th>Dirección</th>
				<th>Localidad</th>
				<th>Teléfono</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${facturas}" var="c">
				<tr>
					<td> ${c.numero} </td>
					<td> <fmt:formatDate value="${c.fecha}" pattern="dd/MM/yyyy" /> </td>
					<td> ${c.TCliente.nombre} ${c.TCliente.apellidos} </td>
					<td> ${c.TCliente.direccion} </td>
					<td> ${c.TCliente.localidad} </td>
					<td> ${c.TCliente.telefono1} </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>