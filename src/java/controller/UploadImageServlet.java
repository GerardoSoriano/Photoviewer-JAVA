/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
import photoviewer.data.BDUtil;
import photoviewer.data.Conexion;

/**
 *
 * @author Gerardo Soriano
 */
@WebServlet(name = "UploadImageServlet", urlPatterns = {"/UploadImageServlet"})
@MultipartConfig
public class UploadImageServlet extends HttpServlet {
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
        
        String savePath = "/images";
        File fileSaveDir=new File(savePath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdir();
            }

        
        Part imagePart = request.getPart("photo");
        String fileName=extractFileName(imagePart);
        String filePath= savePath + File.separator + fileName ;
        String fileLocalPath = File.separator + fileName;
        imagePart.write(filePath);
        String description = request.getParameter("description");
        
        Conexion c=new Conexion();
        Connection con=c.getConexion();
        CallableStatement cs = null;
            
            try{
                String query = "call sp_pv_insertPost(?,?,1)";
                cs = con.prepareCall(query);
                cs.setString(1, fileLocalPath);
                cs.setString(2, description);
                cs.execute();   
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                BDUtil.cerrarStatement(cs);
                c.cerrarConexion();
            }
        
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("success"); 
        
    }

    // file name of the upload file is included in content-disposition header like this:
    //form-data; name="dataFile"; filename="PHOTO.JPG"
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
