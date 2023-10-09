$(
	function() 
	{
		$("#btn_confirm_si").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_borrar.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-modif").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_buscar_guardar.do");
			}
		);
		
		$("#btn-copy").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_copia.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-email").on
		(
			"click", 
			function()
			{
				$.post
				( 
					"parte_email.do", 
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
				$("#frm").attr("action", "parte_pdf.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-pdf-1").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_pdf_1.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-pdf-2").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_pdf_2.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-contrato").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_contrato.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-cuadrante").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_cuadrante.do");
				$("#frm").submit();
			}
		);
		
		$("#btn_fct_sl").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_factura_sl.do");
				$("#frm").submit();
			}
		);
		
		$("#btn_fct_carpinteria").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_factura_carpinteria.do");
				$("#frm").submit();
			}
		);
		
		cargarCliente();
	}
);

function cargarCliente()
{
	if( oidclientecarga != "" )
	{
		$("#oidcliente").val(oidclientecarga);
	}
}

