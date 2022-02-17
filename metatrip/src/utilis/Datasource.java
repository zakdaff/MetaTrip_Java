/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metatrip;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author remo
 */
public class Datasource {

    private static String url = "jdbc:mysql://localhost:3306/metatrip";
    private static String user = "root";
    private static String pwd = "";
    
    private Connection cnx;
    private static Datasource instance;
    
    private Datasource() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("database connected");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    public static Datasource getInstance() {
        if(instance == null){
            instance = new Datasource();
        }
        return instance;
    }
    
    
    
    
    
}
