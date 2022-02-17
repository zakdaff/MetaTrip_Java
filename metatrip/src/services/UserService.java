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
import java.sql.SQLException;
import metatrip.Datasource;
/**
 *
 * @author medal
 */
public class UserService implements IService<user> {
    



    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }
 
   
    private long Cin;
    private String Nom;
        private String Prenom;
        
    private long Tel;
      private String Email;
      private String Password;
      private String Image;
   

    @Override
    public void ajouter(user u) {
         String req = "INSERT INTO `user` (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`) VALUE (?,?,?,?,?,?,?)";
        try {
            pste = conn.prepareStatement(req);
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
    

    @Override
    public void modifier( int id ,user u)    {
     
 
        String req = "UPDATE `user` SET "
                +"`Cin`=?,`Nom`=?,`Prenom`=?,`Tel`=?,`Email`=?,`Password`=?,`Image`=?"
                + "WHERE idu = '" + id+ "'";
    
        try {
            pste = conn.prepareStatement(req);
           pste.setDouble(1,u.getCin());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
             pste.setDouble(4,u.getTel());
              pste.setString(5, u.getEmail());
               pste.setString(6, u.getPassword());
                pste.setString(7, u.getImage());
            pste.executeUpdate();
            System.out.println("user modifier");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimer(int id )  {
      String delete = "DELETE FROM user  where idu = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,id);
            pste.executeUpdate();
       
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    
    }

    @Override
    public List<user> afficher() {
        List<user> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        
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
        
        return users;
    }

    
  

            
         

 

    
    
  

  

  
}
