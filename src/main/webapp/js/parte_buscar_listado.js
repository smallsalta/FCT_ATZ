$(
	function() 
	{
		$(".lupaParte").click
		(
			function()
			{
				var np = $.trim( $(this).text().split("/")[1] );
				
				$("#tipoLupa").attr("value", "parte");
				$("#nparte").attr("value", np );
				
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
		
		$(".lupaMailFactura, .lupaMailContrato").click
		(
			function()
			{
				var partes	= $(this).attr("id").split("_");
				var num		= partes[1];
				var tipo	= partes[0];
				
				var fl 	= "#fl_" + num;
				var cl 	= "#lc_" + num;
				var rd	= "#" + num;
				
				$("#tipoLupa").attr("value", tipo);
				$("#nparte").attr("value", $(rd).val() );
				$("#ncontrato").attr("value", $.trim( $(cl).text() ) );
				$("#n2factura").attr("value", $.trim( $(fl).text() ) );
				
				$("#frm2").submit();
			}
		);
	}
);
