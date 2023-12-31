$(
	function() 
	{
		$(".lupaParte").click
		(
			function()
			{
				$("#tipoLupa").attr("value", "parte");
				$("#nparte").attr("value", $.trim( $(this).text() ) );
				$("#frm2").submit();
			}
		);
		
		$(".lupaFactura").click
		(
			function()
			{
				var fl = "#fl_" + $(this).attr("id").split("_")[1];
				
				$("#tipoLupa").attr("value", "factura");
				$("#n2factura").attr("value", $.trim( $(fl).text() ) );
				$("#frm2").submit();
			}
		);
		
		$(".lupaContrato").click
		(
			function()
			{
				$("#tipoLupa").attr("value", "contrato");
				$("#ncontrato").attr("value", $.trim( $(this).text() ) );
				$("#frm2").submit();
			}
		);
	}
);
