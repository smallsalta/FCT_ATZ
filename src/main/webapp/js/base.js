$(document).ready
(
	function() 
	{
		console.log("ready!");
		
		$(".menu").click
		(
			function()
			{
				window.location.href = $(this).attr("id") + ".do";
			}
		);
	}
);