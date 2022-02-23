/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.reservation_hotel;

import Config.Datasource;
import entities.hotel;
import services.reservation_hotel.IReservation_hotel;

import entities.reservation_hotel;
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
import services.user.UserService;



/**
 *
 * @author medal
 */
public class Reserrvation_Hotel_Service implements IReservation_hotel{
    
        private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public Reserrvation_Hotel_Service() {
            conn = Datasource.getInstance().getCnx();
    }
     
    @Override
    public void ajouter(reservation_hotel rh) {
      			
			
            String req2 = "INSERT INTO `reservation_hotel` (`Idrh`,`Type_room`,`Nb_nuitees`,`Nb_personnes`,`Prix`,`Idh`,`Idu`,`Date_depart`,`Date_arrivee`) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
        
            
              pste = conn.prepareStatement(req2);
                   pste.setInt(1,rh.getIdrh());
              pste.setString(2,rh.getType_room());
              pste.setInt(3, rh.getNb_nuitees());
                    pste.setInt(4, rh.getNb_personnes());
                     pste.setFloat(5, rh.getPrix());
                      
              pste.setInt(6, rh.getHotel().getIdh());
               pste.setInt(7, rh.getUser().getIdu());
                 pste.setDate(8, rh.getDate_depart());
                      pste.setDate(9, rh.getDate_arrivee());
                 

                   
            pste.executeUpdate();
            System.out.println("reservation de hotel crée avec succes");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }




    @Override
    public void modifier(int id, reservation_hotel entity) {
        String req = "UPDATE `reservation_hotel` SET "
                +"`Type_room`=?,`Nb_nuitees`=?,`Nb_personnes`=?,`Prix`=?,`Date_depart`=?,`Date_arrivee`=? WHERE `Idrh` = '"+id+ "'";

    

        try {
               pste = conn.prepareStatement(req); 
              pste.setString(1,entity.getType_room());
              pste.setInt(2, entity.getNb_nuitees());
                    pste.setInt(3, entity.getNb_personnes());
                     pste.setFloat(4, entity.getPrix());
                      
              pste.setDate(5, entity.getDate_depart());
               pste.setDate(6, entity.getDate_arrivee());
            pste.executeUpdate();
            System.out.println("reservationHotel  modifier");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        String delete = "DELETE FROM reservation_hotel  where Idrh = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,id);
            pste.executeUpdate();
       
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    }

    @Override
    public List<reservation_hotel> afficher() {
    List<reservation_hotel> hotels = new ArrayList<>();
        String req = "SELECT * FROM `reservation_hotel`";
         String req1 = "SELECT * FROM `hotel` where `Idh` = ?";
         String req2 = "SELECT * FROM `user` where `Idu` = ?";
        
        try {
    pste = conn.prepareStatement(req);
        PreparedStatement pste2 = conn.prepareStatement(req1);
      PreparedStatement  pste3 = conn.prepareStatement(req2);
           
          
            ResultSet rs = pste.executeQuery();
            
            
            while(rs.next()){
                reservation_hotel u = new reservation_hotel();
                
              u.setIdrh(rs.getInt(1));
               u.setType_room( rs.getString(2));
                u.setNb_nuitees(rs.getInt(3));
                u.setNb_personnes(rs.getInt(4));      
                    u.setPrix(rs.getFloat(5));
                
               
                           u.setHotel(new hotel(rs.getInt(6)));
                           u.setUser(new user(rs.getInt(7)));
                
                pste2.setInt(1,u.getHotel().getIdh()); //parametre de requete 2
             pste3.setInt(1,u.getUser().getIdu());
             
                ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                ResultSet rs3 = pste3.executeQuery();
                 
                rs2.next();
                rs3.next();
                
               u.setHotel(new hotel(rs2.getInt(1),rs2.getString(2),rs2.getInt(3),rs2.getString(4)));
                
               u.setUser(new user(rs3.getInt(1),rs3.getString(2),rs3.getString(3),rs3.getString(4),rs3.getString(5),rs3.getString(6),rs3.getString(7),rs3.getString(8),rs3.getDate(9)));
                

                  
         // System.out.println(u.toString());
              
                 hotels.add(u) ; 
                 
                 
                 
                 
                }                                          
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hotels;
    }
}
