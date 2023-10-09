$(
	function() 
	{
		$("#oidcliente").on
		(
			"change", 
			function()
			{
				$("#oidcliente2").val( $("#oidcliente").val() );
				$("#oidcliente2").selectpicker('refresh');
			}
		);
		
		$("#oidempresa").on
		(
			"change", 
			function()
			{
				$.getJSON
				(
					"factura_siguiente_numero.do?oid=" + $(this).val(),
					function(data) 
					{
						$("#numero").val( data.oid );
					}
				);
			}
		);
	}
);

//////////////////////////////////////////////////
// Redefinimos el comportamiento del contrato
//////////////////////////////////////////////////
function actualizaClienteLoad()
{
	if(oidclienteload != "")
	{
		$("#oidcliente").val(oidclienteload);
		$("#oidcliente").selectpicker('refresh');
		
		$("#oidcliente2").val(oidclienteload);
		$("#oidcliente2").selectpicker('refresh');
	}
}