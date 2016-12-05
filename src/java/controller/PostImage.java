/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import photoviewer.data.Conexion;

/**
 *
 * @author Gerardo Soriano
 */
@WebServlet(name = "PostImage", urlPatterns = {"/PostImage"})
@MultipartConfig
public class PostImage extends HttpServlet {

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
        
            Part imagePart = request.getPart("fileImage");
            
            Conexion c=new Conexion();
            Connection con=c.getConexion();
            CallableStatement cs = null;
            
            try{
                String query = "call sp_pv_insertImage(?)";
                cs = con.prepareCall(query);
                cs.setBlob(1, imagePart.getInputStream());
                cs.execute();   
            }catch(SQLException e){
                e.printStackTrace();
            }
        
    }

}
