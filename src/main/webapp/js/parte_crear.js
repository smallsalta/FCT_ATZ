var oidclienteload = "";

function toggle(e) {
	if(e.value == 'true') {
		$(e).prop('checked', false);
		e.value = false;
	} else {
		$(e).prop('checked', true);
		e.value = true;
	}
}

$(document).ready(() => {
	$(':checkbox').each((i, e) => {
		if(e.value == 'true') {
			$(e).prop('checked', true);
		} else {
			$(e).prop('checked', false);
		}
	});
});



jQuery.fn.swapWith = function(to) {
	return this.each(function() {
		var copy_to = $(to).clone(true);
		var copy_from = $(this).clone(true);
		$(to).replaceWith(copy_from);
		$(this).replaceWith(copy_to);
	});
};

/* 
 *	Sobreescribe la función clone de JQuery
 *  para solucionar el bug con los textareas y selects
 *  
 *  REF: https://stackoverflow.com/a/11804162
 */
(function (original) {
  jQuery.fn.clone = function () {
    var result           = original.apply(this, arguments),
        my_textareas     = this.find('textarea').add(this.filter('textarea')),
        result_textareas = result.find('textarea').add(result.filter('textarea')),
        my_selects       = this.find('select').add(this.filter('select')),
        result_selects   = result.find('select').add(result.filter('select'));

    for (var i = 0, l = my_textareas.length; i < l; ++i) $(result_textareas[i]).val($(my_textareas[i]).val());
    for (var i = 0, l = my_selects.length;   i < l; ++i) result_selects[i].selectedIndex = my_selects[i].selectedIndex;

    return result;
  };
}) (jQuery.fn.clone);

$(
	function() 
	{
		$("#mas").click
		(
			function()
			{
				if( $(":radio:checked").length == 1 )
				{
					var obj = radioActual();
					// (obj).clone().insertAfter(".fila:last");
					$(obj).clone().insertAfter($(obj).parent().children().last());
				}
			}
		);
		
		$("#menos").click
		(
			function()
			{
				var obj = radioActual();
				if( comprobar(obj) )
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
				var obj = radioActual();
				if( comprobar(obj) )
				{
					/*var obj1	= radioActual();
					var obj2 	= obj1.prev(".fila");
					
					enroque(obj1, obj2);*/
					
					var obj2 = obj.prev(".fila");
					enroque(obj, obj2);
				}
			}
		);
		
		$("#baja").click
		(
			function()
			{
				var obj = radioActual();
				if( comprobar(obj) )
				{
					/*var obj1	= radioActual();
					var obj2	= obj1.next(".fila");
					
					enroque(obj1, obj2);*/
					
					var obj2 = obj.next(".fila");
					enroque(obj, obj2);
				}
			}
		);
		
		actualizaClienteLoad();
	}
);

function comprobar(obj) {
	return ($(obj).parent().children(".fila").length > 1) && ( $(":radio:checked").length == 1 );
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