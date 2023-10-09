$(
	function() 
	{
		$("#btn_confirm_si").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "cliente_borrar.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-modif").on
		(
			"click", 
			function()
			{
				$("#frm").submit();
			}
		);
		
		$("#btn-contrato").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "contrato_crear.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-factura").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "factura_crear.do");
				$("#frm").submit();
			}
		);
	}
);

