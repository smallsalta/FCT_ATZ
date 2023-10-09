var oidclienteload = "";

jQuery.fn.swapWith = function(to) {
	return this.each(function() {
		var copy_to = $(to).clone(true);
		var copy_from = $(this).clone(true);
		$(to).replaceWith(copy_from);
		$(this).replaceWith(copy_to);
	});
};

$(
	function() 
	{
		$("#mas").click
		(
			function()
			{
				if( $("input:checked").length == 1 )
				{
					var obj = radioActual();
					$(obj).clone().insertAfter(".fila:last");
				}
			}
		);
		
		$("#menos").click
		(
			function()
			{
				if( comprobar() )
				{
					var obj = radioActual();
					$(obj).remove();
				}
			}
		);
		
		$("#sube").click
		(
			function()
			{
				if( comprobar() )
				{
					var obj1	= radioActual();
					var obj2 	= obj1.prev(".fila");
					
					enroque(obj1, obj2);
				}
			}
		);
		
		$("#baja").click
		(
			function()
			{
				if( comprobar() )
				{
					var obj1	= radioActual();
					var obj2	= obj1.next(".fila");
					
					enroque(obj1, obj2);
				}
			}
		);
		
		actualizaClienteLoad();
	}
);

function comprobar()
{
	return ( $(":radio").length > 1 ) && ( $("input:checked").length == 1 )
}

function radioActual()
{
	return $( $("input:checked").parents(".fila").get(0) );
}

function enroque(obj1, obj2)
{
	if( ( obj1 != null ) && ( obj2 != null ) )
	{
		obj2.swapWith(obj1);
	}
}

function actualizaClienteLoad()
{
	if(oidclienteload != "")
	{
		$("#oidcliente").val(oidclienteload);
		$("#oidcliente").selectpicker('refresh');
	}
}