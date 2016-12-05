$(document).ready(function(){
    $.ajax({
        method:     "POST",
        url:        "../CardUserServlet",
        success:    function(msg){
            console.log(msg);
            $(".username").append('<h1>'+ msg +'</h1>');
        }
    });
});