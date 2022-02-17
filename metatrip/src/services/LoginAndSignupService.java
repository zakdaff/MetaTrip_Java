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
import metatrip.Datasource;

/**
 *
 * @author medal
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
        String req = "SELECT *  FROM `user` where Email='"+email+"' and Password ='"+password+"'";
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




  public void Signup(user u) {
              List<user> users = new ArrayList<>();
      String Email1=u.getEmail();
      String pass1=u.getPassword();
      System.out.println(Email1);
           System.out.println(pass1);
      String password=u.getPassword();
      String req = "SELECT *  FROM `user` where Email='"+Email1+"'";
   
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            user u1 = new user();
                u1.setIdu(rs.getInt("idu"));
                  u1.setCin( rs.getDouble("Cin"));
                u1.setNom(rs.getString(2));
                u1.setPrenom(rs.getString(3));      
                    u1.setTel(rs.getDouble("Tel"));
            
              u1.setEmail( rs.getString(3));
               u1.setPassword(rs.getString(5));
                u1.setImage(rs.getString(5));
              
                 users.add(u) ;   
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("+++++++size++++++++"+users.size());
                                    if(users.size()==0){
                                        
                                        
                                                System.out.println("signup successful");
                                            String req99 = "INSERT INTO `user` (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`) VALUE (?,?,?,?,?,?,?)";
                                      try {
                                          pste = conn.prepareStatement(req99);
                                          pste.setDouble(1,u.getCin());
                                          pste.setString(2, u.getNom());
                                          pste.setString(3, u.getPrenom());
                                           pste.setDouble(4,u.getTel());
                                            pste.setString(5, u.getEmail());
                                             pste.setString(6, u.getPassword());
                                              pste.setString(7, u.getImage());
                                          pste.executeUpdate();
                                          System.out.println("user créée");
                                      } catch (SQLException ex) {
                                          Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                                       }
                                                       else{
                                             System.out.print("signup failed ");

                                    }
                       
                                    


                         
        
      
    }
}

