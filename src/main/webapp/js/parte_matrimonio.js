$(
	function() 
	{
		if( exito == "true" )
		{
			$('#myModal1').modal('show'); 
		}
		
		$(".guardar").click
		(
			function()
			{
				var contador	= $(this).attr('id').split("_")[1];
				var factura 	= $.trim( $("#factura_" + contador).val() );
				var contrato 	= $.trim( $("#contrato_" + contador).val() );
				
				if( factura != "" || contrato != "" )
				{
					$("#frm_" + contador).submit(); 
				}
				else
				{
					$('#myModal2').modal('show');	
				}
			}
		);
		
		$(".borrar").click
		(
			function()
			{
				var contador = $(this).attr('id').split("_")[1];
				
				$("#frm_" + contador).attr("action", "parte_borrar_matrimonio.do");
				$("#frm_" + contador).submit();
			}
		);
		
		$(".lupa").click
		(
			function()
			{
				var contador 	= $(this).attr('id').split("_")[2];
				var tipo		= $(this).attr('id').split("_")[1];
				
				$("#tipoLupa_" + contador).attr("value", tipo);
				$("#frm_" + contador).attr("action", "parte_matrimonio_lupa.do");
				$("#frm_" + contador).submit();
			}
		);
	}
);
