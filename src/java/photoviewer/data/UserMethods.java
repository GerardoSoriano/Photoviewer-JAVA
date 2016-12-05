/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoviewer.data;

import java.io.IOException;
import java.io.InputStream;
import photoviewer.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class UserMethods {
 
 public static int insertar(User user){
   Conexion c=new Conexion();
   Connection con=c.getConexion();
   CallableStatement cs = null;
  try {  
   if(con!=null){
     String query = "{ call sp_pv_addUser(?,?,?,?,null,null,null,null,null,null,null,null) }";
            cs = con.prepareCall(query);
            cs.setString(1, user.getFullname());
            cs.setString(2, user.getUsername());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getPass());
            cs.execute();
            return 1;
   }
   
  } catch (SQLException e) {
   e.printStackTrace();
   return -1;
  } finally {
            BDUtil.cerrarStatement(cs);
            c.cerrarConexion();
        }
  return 1;
 }
 public static int userExist(String email, String pass){
    Conexion c = new Conexion();
    Connection con=c.getConexion();
    CallableStatement cs = null;
    String query = "Select email, pass from pv_user where email='" + email + "' and pass='" + pass + "';";
  try { 
   Statement st = con.createStatement();
   ResultSet rs = st.executeQuery(query);
   if(rs.next()){
   return 1;
   }
       
  }catch (SQLException e) {
   e.printStackTrace();
   return 0;
  } finally {
            BDUtil.cerrarStatement(cs);
            c.cerrarConexion();
        }
  return 0;
 }
 public static void updateUser(int idUsuario, String nombreCompleto, String nombreUsuario, String correoUsuario, String contraNueva, 
            String descripcion, InputStream imgPortada, InputStream imgPerfil){
    Conexion c = new Conexion();
    Connection con=c.getConexion();
    CallableStatement cs = null;
    
    try {
            if (imgPortada.available() <= 0) imgPortada = null;
            if (imgPerfil.available() <= 0) imgPerfil = null;
            
            String query = " { call sp_pv_updateUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            cs = con.prepareCall(query);
            
            cs.setInt(1, idUsuario);
            cs.setString(2, nombreCompleto);
            cs.setString(3, nombreUsuario);
            cs.setString(4, correoUsuario);
            cs.setString(5, contraNueva);
            cs.setString(6, descripcion);
            cs.setString(7, null);
            cs.setInt(8, 1);
            cs.setString(9, null);
            cs.setBinaryStream(10, null);
            cs.setBlob(11, imgPortada);
            cs.setBlob(12, imgPerfil);
            cs.setInt(13, 5);
            
            cs.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        finally {
            BDUtil.cerrarStatement(cs);
        }
 }

}