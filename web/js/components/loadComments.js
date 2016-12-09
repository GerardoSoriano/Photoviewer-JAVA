$(document).on("click",".fa-comments",function(){
  var imageCard = $(this).parent().parent().parent().parent().parent();

  var idParent = $(imageCard).attr("idpost");

  $.ajax({
    method:     "POST",
    url:        "../LoadData",
    dataType:   "json",
    success:    function(msg){
      $.each(msg,function(key,val){
        if(val.idpost == idParent){
          $("body").append(''+
          '<div class="windowPopup" idPost='+ val.idpost +'>' +
            '<div class="mediaContainer">' +
              '<div class="head">' +
                '<div class="buttonClose">' +
                  '<button class="close">x</button>' +
                '</div>' +
                '<div class="icons">' +
                  '<div class="likes">' +
                    '<span class="fa fa-heart"></span>' +
                    '<h1 class="amountOfLikes">10</h1>' +
                  '</div>' +
                  '<div class="comments">' +
                    '<span class="fa fa-comments"></span>' +
                    '<h1 class="amountOfComments">10</h1>' +
                    '<h1>Comentarios</h1>' +
                  '</div>' +
                  '<div class="shares">' +
                    '<span class="fa fa-share-alt"></span>' +
                    '<h1 class="amountOfShares">10</h1>' +
                    '<h1>veces compartido</h1>' +
                  '</div>' +
                '</div>' +
              '</div>' +
              '<div class="user">' +
                '<div class="addComment">' +
                  '<div class="imageProfile">' +
                    '<img src="../images/profile.jpg" alt>' +
                  '</div>' +
                  '<div class="username">' +
                    '<h1>gerardosoriano97</h1>' +
                    '<br>' +
                    '<textarea name="textComment" class="textareaComment" rows="5" maxlength="255" placeholder="Escribe un comentario"></textarea>' +
                  '</div>' +
                '</div>' +
              '</div>' +
              '<div class="comment">' +
                '<div class="imageProfileComment">' +
                  '<img src="../images/profile.jpg" alt>' +
                '</div>' +
                '<div class="userComment">' +
                  '<div class="commentPoster">' +
                    '<a href="" class="comment_user">Roberto Villegas Rodriguez</a>' +
                  '</div>' +
                  '<div class="commentCaption">' +
                    '<h1>' +
                      val.description +
                    '</h1>' +
                  '</div>' +
                '</div>' +
              '</div>' +
            '</div>' +
          '</div>'
          );
        }
      });
    },
    error:    function(msg){
      console.log("error" + msg);
    }
  });
});

$(document).on("keypress","textarea.textareaComment",function(e){
  if(e.which == 13){

    var oThis = this;
    var text = $(this).val();
    var idParent = $(this).parent().parent().parent().parent().parent().attr("idPost");

    $.ajax({
      method:     "POST",
      url:        "../UploadCommentServlet",
      data:       {
          comment :  text,
          idPost  :  idParent
      },
      success:    function(msg){
        if (msg == "success") {
          $(oThis).val("");
        }
      },
      error:      function(msg){
        alert("no jalo");
      }
    });
  }
});

$(document).on("click","button.close",function(){
  $("div.windowPopup").remove();
});
