$(document).ready(function (){

    $.ajax({
        method:     "POST",
        url:        "../LoadData",
        dataType:   "json",
        success:    function(msg){
            console.log(msg);
            $.each(msg,function(key,val){
              $(".contentPictures").append(''+
              '<div class="imageCard">' +
                '<div class="mediaContainer">' +
                  '<div class="user">' +
                    '<div class="imageProfile">' +
                      '<img src="../images/profile.jpg" alt="">' +
                    '</div>' +
                    '<div class="username">' +
                    '</div>' +
                  '</div>' +
                  '<div class="icons">' +
                    '<ul>' +
                      '<li><span class="fa fa-heart"></span></li>' +
                      '<li><span class="fa fa-comments"></span></li>' +
                      '<li><span class="fa fa-share-alt"></span></li>' +
                      '<li><span class="fa fa-ellipsis-h"></span></li>' +
                    '</ul>' +
                  '</div>' +
                  '<div class="image">' +
                    '<img src="../images'+ val.post +'" alt="">' +
                  '</div>' +
                '</div>' +
              '</div>'
              );
            })
        }
    });

});
