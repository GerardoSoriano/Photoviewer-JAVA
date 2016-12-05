var password;

$(document).ready(function(){

  $(".element").change(function(){
    $(this).removeClass("error");
  });

  $("#formLogin").submit(function(e){
    e.preventDefault();
    var $form = $(this);
    var allFine;
    var jsonObj = [];
    $form.find(".element").each(function(key, value){
      if(validateDatos(value)){
        jsonObj[$(value).attr("name")] = $(value).val();
        allFine = true;
      }else {
        alert("Hay un error de sintaxis en el " + $(value).attr("name"));
        $(value).addClass("error");
        allFine = false;
      }
    });
    if(allFine === true){
      $.ajax({
        method:   "POST",
        url:      "../LoginServlet",
        data:     {
                    email     : jsonObj['email'],
                    password  : jsonObj['password']
                  }
      }).done(function(msg){
          if(msg == "success")
              window.location.href = "../html/main.html";
      }).fail(function(msg){
        alert("error:");
      }).always(function(msg){
        console.log(msg);
      });
    }
  });
  $("#formRegister").submit(function(e){
    e.preventDefault();
    var $form = $(this);
    var allFine;
    var jsonObj = [];
    $form.find(".element").each(function(key, value){
      if(validateDatos(value)){
        jsonObj[$(value).attr("name")] = $(value).val();
        allFine = true;
      }else {
        alert("Hay un error de sintaxis en el " + $(value).attr("name"));
        $(value).addClass("error");
        allFine = false;
      }
    });
    if(allFine === true){
      $.ajax({
        method:   "POST",
        url:      "../RegisterServlet",
        data:     {
                    email           : jsonObj['email'],
                    username        : jsonObj['username'],
                    fullname        : jsonObj['fullname'],
                    pass            : jsonObj['password']
                  }
      }).done(function(msg){
          if(msg == "success")
              window.location.href = "../html/login.html";
      }).fail(function(msg){
        alert("error:");
      }).always(function(msg){
        console.log(msg);
      });
    }
  });

});

function validateDatos(e){
  var element = $(e).attr("name");
  var regExp;
  switch (element) {
    case "email":
      regExp = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      break;
    case "fullname":
      regExp = /^[a-zA-Z\s]+$/;
      break;
    case "username":
      regExp = /^[a-zA-Z0-9_.-]+$/;
      break;
    case "password":
      regExp = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6}/;
      password = $(e).val();
      break;
    case "confirmPassword":
      if ($(e).val() === password) {
        return true;
      }else {
        console.log($(e).val() + "\n" + password);
        return alert("Las contraseñas no coinciden");
      }
      break;
    default:
  }
  return regExp.test($(e).val());
}
