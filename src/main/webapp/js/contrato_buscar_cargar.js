$(
	function() 
	{
		$("#btn_confirm_si").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "contrato_borrar.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-modif").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "contrato_buscar_guardar.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-copy").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "contrato_copia.do");
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
					"contrato_email.do", 
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
				$("#frm").attr("action", "contrato_pdf.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-cparte").on
		(
			"click", 
			function()
			{
				btnCparteClick();
			}
		);
		
		$("#btn-cuadrante").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "contrato_cuadrante.do");
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

function btnCparteClick()
{
	$("#frm").attr("action", "contrato_parte.do");
	$("#frm").submit();
}

