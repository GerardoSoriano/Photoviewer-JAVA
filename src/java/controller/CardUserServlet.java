/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import photoviewer.data.UserMethods;
import photoviewer.model.User;

/**
 *
 * @author Gerardo Soriano
 */
@WebServlet(name = "CardUserServlet", urlPatterns = {"/CardUserServlet"})
public class CardUserServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(false);
         User user = UserMethods.getUser(session.getAttribute("email").toString());
         
         JsonObject obj = new JsonObject();
         
         Blob avatarBlob = user.getAvatar();
         Blob coverBlob = user.getCover();
         int avatarLength = 0;
         int coverLength = 0;
        try {
            if(avatarBlob != null){
                avatarLength = (int) avatarBlob.length();
            }else{
                avatarLength = 0;
            }
            if(coverBlob != null){
                coverLength = (int) coverBlob.length();
            }else{
                coverLength = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         byte[] avatar = null;
         byte[] cover = null;
        try {
            if(avatarBlob != null){
                avatar = avatarBlob.getBytes(1, avatarLength);
            }else{
                avatar = null;
            }
            if(coverBlob != null){
                cover = coverBlob.getBytes(1, coverLength);
            }else{
                cover = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CardUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String avatarBase64 = null;
        String coverBase64 = null;
        
                    if(avatarBlob != null){
                        avatarBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(avatar);   
                    }else{
                        avatarBase64 = "";
                    }
                    if(coverBlob != null){
                        coverBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(cover);   
                    }else{
                        coverBase64 = "";
                    }
                         
         obj.addProperty("email", user.getEmail());
         obj.addProperty("avatar", avatarBase64);
         obj.addProperty("cover", coverBase64);
         
         response.setContentType("application/json");  
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(obj.toString()); 
    }

}
