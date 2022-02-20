/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.evenement;

import services.evenement.Serviceevenement;
import entities.reservation_event;

import entities.evenement;
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
import services.IService;
import Config.Datasource;
import entities.Voiture;
import entities.reservation_voiture;
import services.user.UserService;
/**
 *
 * @author Z4RGA
 */
public class Servicereservation_event  {
    
    private PreparedStatement ps,pste2,pste3;
    Connection cnx;

    public Servicereservation_event() {
         cnx = Datasource.getInstance().getCnx();
    }

    
 	

    public void ajout(reservation_event re) {
        
        String req2 = "INSERT INTO `reservation_event` (`Idrev`,`Nb_pers`,`Ide`,`Idu`) VALUES (?,?,?,?)";
           try {
        
              
  
              ps = cnx.prepareStatement(req2);
                   ps.setInt(1,re.getIdrev());
              ps.setInt(2,re.getNb_pers());
              
              
                    ps.setInt(3, re.getEvenement().getIde());
                     ps.setInt(4, re.getUser().getIdu());
                    System.out.println(re.getUser().getIdu());
           
                   
            ps.executeUpdate();
           
            System.out.println("reservation de event crée avec succes");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Servicereservation_event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void modifier(int idr,reservation_event re) {
     String req = "UPDATE `reservation_event` SET "
                +"`Nb_pers`=?"
               + " WHERE Idrev = '" + idr+ "'";
    
        try {
            ps = cnx.prepareStatement(req);
           ps.setInt(1,re.getNb_pers());
           
 
         
            ps.executeUpdate();
            System.out.println("reservation event de id "+ idr+ " Updated sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }

  
    public void supprimer(int Idrev) {
        
          try {
            String req = "delete from reservation_event where Idrev = ?";
            PreparedStatement psr = cnx.prepareStatement(req);
            psr.setInt(1, Idrev);
            psr.executeUpdate();
              System.out.println("Reservation Event supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public List<reservation_event> afficher() {
       
         List<reservation_event> listr = new ArrayList<>();
         String req = "SELECT * FROM `reservation_event` ;";
         String req1 = "SELECT * FROM `evenement` where `Ide` = ?";
         String req2 = "SELECT * FROM `user` where `Idu` = ?";
        
        try {
    ps = cnx.prepareStatement(req);
        PreparedStatement pste2 = cnx.prepareStatement(req1);
      PreparedStatement  pste3 = cnx.prepareStatement(req2);
           
          
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                reservation_event u = new reservation_event();
                
              u.setIdrev(rs.getInt(1));
               u.setNb_pers(rs.getInt(2));
                  
                       u.setEvenement(new evenement(rs.getInt(3)));
                        u.setUser(new user(rs.getInt(4)));
                
                  pste2.setInt(1,u.getEvenement().getIde()); //parametre de requete 2
             pste3.setInt(1,u.getUser().getIdu());
             
                ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                ResultSet rs3 = pste3.executeQuery();
                 
            
                
              // u.setEvenement(new evenement(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getDate(5),rs2.getFloat(6)));
                
             //  u.setUser(new user(rs3.getInt(1),rs3.getDouble(2),rs3.getString(3),rs3.getString(4),rs3.getDouble(5),rs3.getString(6),rs3.getString(7),rs3.getString(8),rs3.getDate(9)));
                

               
                  
           //System.out.println(rvo.toString());
             //  System.out.println(rvo.getUser().getIdu()); 
                 listr.add(u) ; 
                 
                                                   
            }}
        catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
           return listr;
    }
}

