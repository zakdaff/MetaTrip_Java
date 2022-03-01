/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.reservation_hotel;

import Config.Datasource;
import entities.Chambre;
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
public class Reserrvation_Hotel_Service implements IReservation_hotel {

    private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public Reserrvation_Hotel_Service() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(reservation_hotel rh) {

        System.out.println(rh.getChambre().getIdc());
        String req2 = "INSERT INTO `reservation_hotel` (`Idrh`,`Nb_nuitees`,`Nb_personnes`,`Prix`,`Idu`,`idc`,`Date_depart`,`Date_arrivee`) VALUES (?,?,?,?,?,?,?,?)";

        try {

            pste = conn.prepareStatement(req2);
            pste.setInt(1, rh.getIdrh());
            pste.setInt(2, rh.getNb_nuitees());
            pste.setInt(3, rh.getNb_personnes());
            pste.setFloat(4, rh.getPrix());
            pste.setInt(5, rh.getUser().getIdu());
            pste.setInt(6, rh.getChambre().getIdc());
            pste.setDate(7, rh.getDate_depart());
            pste.setDate(8, rh.getDate_arrivee());

            pste.executeUpdate();
            System.out.println("reservation de hotel crée avec succes");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier(int id, reservation_hotel entity) {
        String req = "UPDATE `reservation_hotel` SET "
                + "`Nb_nuitees`=?,`Nb_personnes`=?,`Prix`=?,`Date_depart`=?,`Date_arrivee`=? WHERE `Idrh` = '" + id + "'";

        try {
            pste = conn.prepareStatement(req);

            pste.setInt(1, entity.getNb_nuitees());
            pste.setInt(2, entity.getNb_personnes());
            pste.setFloat(3, entity.getPrix());

            pste.setDate(4, entity.getDate_depart());
            pste.setDate(5, entity.getDate_arrivee());
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
            pste.setInt(1, id);
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
            PreparedStatement pste3 = conn.prepareStatement(req2);

            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                reservation_hotel u = new reservation_hotel();

                u.setIdrh(rs.getInt(1));

                u.setNb_nuitees(rs.getInt(2));
                u.setNb_personnes(rs.getInt(3));
                u.setPrix(rs.getFloat(4));

                u.setChambre(new Chambre(rs.getInt(5)));
                u.setUser(new user(rs.getInt(6)));

                pste2.setInt(1, u.getChambre().getIdh()); //parametre de requete 2
                pste3.setInt(1, u.getUser().getIdu());

                ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                ResultSet rs3 = pste3.executeQuery();

                rs2.next();
                rs3.next();

                u.setChambre(new Chambre(rs2.getInt(1), rs2.getInt(2), rs2.getString(3), rs2.getInt(4)));

                u.setUser(new user(rs3.getInt(1), rs3.getDouble(2), rs3.getString(3), rs3.getString(4), rs3.getDouble(5), rs3.getString(6), rs3.getString(7), rs3.getString(8), rs3.getDate(9)));

                // System.out.println(u.toString());
                hotels.add(u);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hotels;
    }
    
     public int nbHotel() throws SQLException {
    int nbh=0;
          String req = "SELECT count(*) from `hotel` ;";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            nbh=rs.getInt(1);
                                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nbh;
    }
    
    
     public int nbChambreDispo() throws SQLException {
           int nb=0;
   
          String req = "SELECT count(*) from `chambre` where etat='DISPO';";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            nb=rs.getInt(1);
                                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;   
     }
     
    public void rechercherHotelparNom (String nom){
        
       String query= "SELECT * FROM hotel WHERE `Nom_Hotel`='"+nom+"';";
        try {
           
              
            ste=conn.createStatement();
             ResultSet rs =ste.executeQuery(query);
            int nbRows =rs.getRow();
            if(nbRows!=1){
                System.out.println("Hotel trouvé");
            }else {
                System.out.println("Hotel n'existe pas");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Reserrvation_Hotel_Service.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public List<hotel> getHotelByNom (String Nom)  {
     List<hotel> hotels = new ArrayList<>();
   
          String req = "SELECT * from `hotel` where Nom_hotel='"+Nom+"';";
            try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                hotel h = new hotel();
                h.setIdh(rs.getInt(1));   
                h.setNom_hotel(rs.getString(2));
                h.setNb_etoiles(rs.getInt(3));      
                h.setAdresse(rs.getString(4));
               
                 hotels.add(h) ;                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hotels;    
    }
    
    
}
