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
import photoviewer.data.BDUtil;
import photoviewer.data.Conexion;

/**
 *
 * @author Gerardo Soriano
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        
        String search = request.getParameter("search");
        
        Conexion c=new Conexion();
        Connection con=c.getConexion();
        ResultSet rs = null;
        PreparedStatement cs = null;
        JsonArray out = null;
        JsonObject json = null;
            try{
                String query = "call sp_pv_search(?)";
                cs = con.prepareCall(query);
                cs.setString(1, search);
                rs = cs.executeQuery();
                try {
                    out = convertToJSON(rs);
                    json.add("results",out);
                } catch (Exception ex) {
                    Logger.getLogger(LoadImagesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.setContentType("application/json");  
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(out.toString()); 
        
                
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                BDUtil.cerrarStatement(cs);
                c.cerrarConexion();
            }
        
    }

     public static JsonArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JsonArray jsonArray = new JsonArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JsonObject obj = new JsonObject();
            for (int i = 0; i < total_rows; i++) {
                obj.addProperty(
                        resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), 
                        resultSet.getString(i + 1));
            }
            jsonArray.add(obj);
        }
        return jsonArray;
    }

}
