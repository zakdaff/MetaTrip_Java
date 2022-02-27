/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import Config.Datasource;
import services.IService;
import services.IService;

/**
 *
 * @author FLAM
 */
public class UserService implements IService<user> {
    



    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }
 
   
 
    @Override
    public void ajouter(user u) {
         String req = "INSERT INTO `user` (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
        try {
            pste = conn.prepareStatement(req);
	

            pste.setDouble(1,u.getCin());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
             pste.setDouble(4,u.getTel());
              pste.setString(5, u.getEmail());
               pste.setString(6, u.getPassword());
                pste.setString(7, u.getImage());
                  pste.setDate(8, u.getDateNaissance());
            pste.executeUpdate();
            System.out.println("user créée");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void modifier( int id ,user u)    {
     
 
        String req = "UPDATE `user` SET "
                +"`Cin`=?,`Nom`=?,`Prenom`=?,`Tel`=?,`Email`=?,`Password`=?,`Image`=?,`dateNaissance`=?"
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
                pste.setDate(8, u.getDateNaissance());
            pste.executeUpdate();
            System.out.println("user modifie");
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
            System.out.println("user supprimé");
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
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getDouble(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getDouble(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                u.setDateNaissance(rs.getDate(9));
                 users.add(u) ;                                        
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    
  

            
         

 

    
    
  

  

  
}
