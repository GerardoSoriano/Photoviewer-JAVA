/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static controller.LoadImagesServlet.convertToJSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import photoviewer.data.BDUtil;
import photoviewer.data.Conexion;
import photoviewer.data.UserMethods;
import photoviewer.model.User;

/**
 *
 * @author Gerardo Soriano
 */
@WebServlet(name = "UploadCommentServlet", urlPatterns = {"/UploadCommentServlet"})
public class UploadCommentServlet extends HttpServlet {

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
        String comment = request.getParameter("comment");
        int idPost = Integer.parseInt(request.getParameter("idPost"));
        
        HttpSession session = request.getSession();
        User user = UserMethods.getUser(session.getAttribute("email").toString());
        
        Conexion c=new Conexion();
        Connection con=c.getConexion();
        ResultSet rs = null;
        PreparedStatement cs = null;
        JsonArray out = null;
        JsonObject json = null;
            try{
                String query = "call sp_pv_insertComment(?,?,?)";
                cs = con.prepareCall(query);
                cs.setInt(1, user.getId());
                cs.setInt(2, idPost);
                cs.setString(3, comment);
                cs.executeQuery();
                
                response.setContentType("text/plain");  
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("success"); 
        
                
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                BDUtil.cerrarStatement(cs);
                c.cerrarConexion();
            }
        
        
    }
}
