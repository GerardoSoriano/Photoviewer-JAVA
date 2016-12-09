$(document).ready(function()
{
$(".displayForm").click(function(){
	$(".form").fadeIn(200);
});
$("[value=Cancelar]").click(function(){
	$(".form").fadeOut(200);
});

});
