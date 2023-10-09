var oidclientecarga		= null;
var oidclientecarga2	= null;

$(document).ready
(
	function() 
	{
		$("#oidcliente").val(oidclientecarga);
		$("#oidcliente2").val(oidclientecarga2);
		$(".selectpicker").selectpicker('refresh');
	}
);


$(
	function() 
	{
		$("#btn_confirm_si").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "factura_borrar.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-modif").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "factura_buscar_guardar.do");
			}
		);
		
		$("#btn-email").on
		(
			"click", 
			function()
			{
				$.post
				( 
					"factura_email.do", 
					$("#frm").serialize(),
					function(data) 
					{
						$("#mensaje").text(data);
						$('#myModal2').modal('show') 
					}
				);
			}
		);
		
		$("#btn-pdf").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "factura_pdf.do");
				$("#frm").submit();
			}
		);
		
		/**
		 * 13/10/2020
		 * Para las facturas, cuando se clona la fila, se desea borrar 
		 * el valor que tuvieran la fila anterior.
		 * Clonamos y borramos los valores.
		 */
		$("#mas").click
		(
			function()
			{
				radioActual().find(".form-control").each
				(
					function()
					{
						$(this).val("");
					}
				)
			}
		);
		
		$("#btn-copy").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "factura_copia.do");
				$("#frm").submit();
			}
		);
	}
);

