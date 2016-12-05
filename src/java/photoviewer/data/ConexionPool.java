/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoviewer.data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 *
 * @author Gerardo Soriano
 */
public class ConexionPool {
    private static ConexionPool pool = null;
    private static DataSource dataSource = null;
    
    public synchronized static ConexionPool getInstancia(){
        if (pool == null) {
            pool = new ConexionPool();
        }
        return pool;
    }
    private ConexionPool(){
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/photoviewer");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public Connection getConexion(){
        try{
            return dataSource.getConnection();
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public void liberarConexion(Connection c){
        try {
            if(c != null)
                c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
