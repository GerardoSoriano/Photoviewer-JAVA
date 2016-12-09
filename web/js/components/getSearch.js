$(document).ready(function(){
  var text = document.cookie;

  $.ajax({
    method:       "POST",
    url:          "../SearchServlet",
    data:         {search: text},
    success:      function(msg){
      console.log(msg);
      alert('todo bien');
    },
    error:        function(msg){
      console.log(msg);
      alert('todo mal');
    }
  });
});
