/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Date;
import entities.reservation_voyage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.Datasource;

/**
 *
 * @author FLAM
 */
public class Reservation_Voyage_Service implements IReservation_Voyage{

        private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public Reservation_Voyage_Service() {
           conn = Datasource.getInstance().getCnx();

    }
    
    
    
    @Override
    public void ajouter(reservation_voyage rv) {
        
            String req2 = "INSERT INTO `reservation_voyage` (`Date_depart`,`Date_arrivee`,`Idu`,`Idv`) VALUES (?,?,?,?)";

        try {
        
            
              pste = conn.prepareStatement(req2);
              pste.setDate(1,rv.getDate_arrivee());
              pste.setDate(2, rv.getDate_arrivee());
               pste.setInt(3, rv.getUser().getIdu());
              pste.setInt(4, rv.getVoyage().getIdv());
                   
            pste.executeUpdate();
            System.out.println("reservation de voyage crée avec succes");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void modifier(int id, reservation_voyage entity) {
    }

    @Override
    public void supprimer(int id) {
    }

    @Override
    public List<reservation_voyage> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
