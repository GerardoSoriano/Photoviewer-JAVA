$(document).on("keypress","input[type='search']",function(e){
  if(e.which == 13){
    var text = $(this).val();
    document.cookie = ""+ text; 
    window.location.href = "search.html";
  }
});
