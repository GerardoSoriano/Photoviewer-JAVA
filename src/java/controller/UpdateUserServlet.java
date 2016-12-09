/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.io.InputStream;
import java.sql.Blob;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import photoviewer.data.UserMethods;
/**
 *
 * @author RVR_
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/UpdateUserServlet"})
@MultipartConfig
public class UpdateUserServlet extends HttpServlet {


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
        String fullname = request.getParameter("name");
        String user = request.getParameter("user");
        String Pass = request.getParameter("newPassword");
        Part imgCover = request.getPart("uploadCoverImage");
        Part imgProfile = request.getPart("uploadProfileImage");
        
        InputStream imgC = null;
        InputStream imgP = null;
        imgC = imgCover.getInputStream();
        imgP= imgProfile.getInputStream();
            
        UserMethods.updateUser(1, fullname, user, session.getAttribute("email").toString(), Pass, "", imgC, imgP);
      
    }

}
