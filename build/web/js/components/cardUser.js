$(document).ready(function(){
    $.ajax({
        method:     "POST",
        url:        "../CardUserServlet",
        success:    function(msg){
          if (msg.avatar !== ""){
            $(".imageProfile").append('<img src="'+ msg.avatar +'" alt="">');
          }else{
            $(".imageProfile").append('<img src="../images/user.png" alt="">');
          }
          if (msg.cover !== "") {
            $(".imageCover").append('<img src="'+ msg.cover +'" alt="">');
          }else{
            $(".imageCover").append('<img src="../images/cover.jpg" alt="">');
          }
        },
        error:      function(msg){
          $(".imageProfile").append('<img src="../images/user.png" alt="">');
          $(".imageCover").append('<img src="../images/cover.jpg" alt="">');
        }
    });
});
