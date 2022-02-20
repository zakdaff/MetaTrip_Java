/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.Datasource;

/**
 *
 * @author FLAM
 */
public class LoginAndSignupService {
      
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public LoginAndSignupService() {
        conn = Datasource.getInstance().getCnx();
    }

      private String Email;
      private String Password;
public String login (String email,String password) throws Exception {
    String test = null;
        List<user> users = new ArrayList<>();
        String req = "SELECT *  FROM `user` where Email="+email+" and Password ="+password;
        System.out.println();
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            user u = new user();
                u.setIdu(rs.getInt("idu"));
                  u.setCin( rs.getDouble("Cin"));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));      
                    u.setTel(rs.getDouble("Tel"));
            
              u.setEmail( rs.getString(3));
               u.setPassword(rs.getString(5));
                u.setImage(rs.getString(5));
              
                 users.add(u) ;   
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
  
           try{
               
         if(users.size()==0){
                             test="login failed";
                         
                         }
                         else{
                             test="login successful";
                         }
           }catch(IndexOutOfBoundsException e){
            System.out.println("login failed");
        }
       return test;
    }

}

