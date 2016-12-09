$(document).ready(function (){

    $.ajax({
        method:     "POST",
        url:        "../LoadData",
        dataType:   "json",
        success:    function(msg){
            console.log(msg);
            $.each(msg,function(key,val){
              $(".contentPictures").append(''+
              '<div class="imageCard" idPost='+ val.idpost +'>' +
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

/*package Controllers;

import Models.CConnection;
import Models.CPost;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomePostServlet", urlPatterns = {"/HomePostServlet"})
public class HomePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            response.setContentType("text/plain");
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            CConnection c = new CConnection();
            Connection conn = c.getConexion();

            PreparedStatement pst = conn.prepareStatement("call sp_CargarPublicacionesInicio(?);");
            pst.setInt(1, idUsuario);
            ResultSet rs = pst.executeQuery();

            List<CPost> publicaciones = new ArrayList<CPost>();

            while (rs.next()) {
                CPost publicacion = new CPost();

                publicacion.setIdPublicacion(rs.getInt("idPublicacion"));
                publicacion.setIdUsuario(rs.getInt ("idUsuario"));
                publicacion.setTextoPublicacion(rs.getString ("textoPublicacion"));
                publicacion.setNombreUsuario(rs.getString("nombreUsuario"));

                byte[] fotoPerfil = rs.getBytes("fotoPerfil");

                if(fotoPerfil != null)
                {
                    String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);
                    publicacion.setFotoPerfil(base64);
                }
                else
                    publicacion.setFotoPerfil("");

                byte[] imagen = rs.getBytes("imagen");

                if(imagen != null)
                {
                    String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(imagen);
                    publicacion.setImagen(base64);
                }
                else
                    publicacion.setFotoPerfil("");

                publicaciones.add(publicacion);
            }

            String json = new Gson().toJson(publicaciones);
            response.getWriter().write(json);

        } catch (SQLException | IOException ex) {
            response.getWriter().write(ex.toString());
        }

    }

}*/
