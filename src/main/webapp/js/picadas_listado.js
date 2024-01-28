$(document).ready
(
	function() 
	{
		$("#btn_modif").click
		(
			function()
			{
				enviar("picadas_modificar.do");
			}
		);
		
		$("#btn_borr").click
		(
			function()
			{
				enviar("picadas_borrar.do");
			}
		);
	}
);

function enviar(dst)
{
	$("#frm_picada").attr("action", dst);
	$("#frm_picada").submit();
}