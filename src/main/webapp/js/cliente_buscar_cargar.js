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
		
		$("#btn-parte-bi").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_crear_bie.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-parte-ce").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_crear_centralita_central.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-parte-au").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_crear_centralita_auxiliares.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-parte-ex").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_crear.do");
				$("#frm").submit();
			}
		);
		
		$("#btn-parte-ob").on
		(
			"click", 
			function()
			{
				$("#frm").attr("action", "parte_crear_observaciones.do");
				$("#frm").submit();
			}
		);
	}
);

