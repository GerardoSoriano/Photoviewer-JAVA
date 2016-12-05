$(document).ready(function(){
    $("#formUploadPhoto").submit(function(){
        var description = $("textarea.contentUploaded").val();
        var photo = $("#photo")[0].files[0];
        var mime = /image.*/;
        
        var formData = new FormData();
        formData.append('photo',photo);
        formData.append('description',description);
        
        $.ajax({
                method:   "POST",
                url:      "../UploadImageServlet",
                data:     formData,
                processData: false,
                contentType: false,
                success:    function(msg){
                    if(msg == "success")
                        alert("publicación posteada correctamente");
                },
                error:  function(msg){
                    console.log(msg);
                    alert("hubo un error con el servidor");
                }
              });
    });
});