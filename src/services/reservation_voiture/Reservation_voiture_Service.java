/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.reservation_voiture;

import entities.Voiture;
import entities.Chauffeur;

import entities.reservation_voiture;

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
import Config.Datasource;

/**
 *
 * @author medal
 */
public class Reservation_voiture_Service implements IReservation_voiture {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public Reservation_voiture_Service() {
        conn = Datasource.getInstance().getCnx();
    }


    @Override
    public void ajouter(reservation_voiture rv) {

        String req2 = "INSERT INTO `reservation_voiture` (`prix_rent`,`Trajet`,`Idu`,`Idvoit`,`idch`) VALUES (?,?,?,?,?) ;";

        try {
            //System.out.println(rv.getVoiture().getIdvoit()+"  " +rv.getUser().getIdu());
            pste = conn.prepareStatement(req2);
            pste.setFloat(1, rv.getPrix_rent());
                         pste.setString(2, rv.getTrajet());
                           pste.setInt(3, rv.getUser().getIdu());
                           pste.setInt(4, rv.getVoiture().getIdvoit());
           pste.setInt(5, rv.getChauffeur().getidch());
            
            
   
        ;
            pste.executeUpdate();
            System.out.println("reservation de voiture  crée avec succes");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public int sommePrixlocationvoiture() throws SQLException {
        int sum =0;

        String req = "SELECT SUM(reservation_voiture.Prix_rent) FROM reservation_Voiture;";
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                sum = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Reservation_voiture_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sum;
    }
 


    @Override
    public void modifier(int idrvoit, reservation_voiture rv) {
        String req = "UPDATE `reservation_voiture` SET "
                + "`prix_rent`=?,`Trajet`=?,`Idu`=?,`Idvoit`=?,`idch`=? WHERE `Idrvoit` = " + idrvoit;

        try {
            pste = conn.prepareStatement(req);
             pste.setFloat(1, rv.getPrix_rent());
                         pste.setString(2, rv.getTrajet());
                           pste.setInt(3, rv.getUser().getIdu());
                           pste.setInt(4, rv.getVoiture().getIdvoit());
           pste.setInt(5, rv.getChauffeur().getidch());

            pste.executeUpdate();
            System.out.println("reservation voiture   modifier");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int Idrvoit) {
        String delete = "DELETE FROM reservation_voiture  where Idrvoit  = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, Idrvoit);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(Reservation_voiture_Service.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }

    @Override
    public List<reservation_voiture> afficher() {
        List<reservation_voiture> Listrvvoiture = new ArrayList<>();
        String req = "SELECT * FROM `reservation_voiture` ;";
        String req1 = "SELECT * FROM `voiture` where `Idvoit` = ?";
        String req2 = "SELECT * FROM `user` where `Idu` = ?";
        String req3 = "SELECT * FROM `chauffeur` where `Idch` = ?";

        try {
            pste = conn.prepareStatement(req);
            PreparedStatement pste2 = conn.prepareStatement(req1);
            PreparedStatement pste3 = conn.prepareStatement(req2);
            PreparedStatement pste4 = conn.prepareStatement(req3);

            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                reservation_voiture u = new reservation_voiture();

                u.setIdrvoit(rs.getInt(1));
                u.setPrix_rent(rs.getFloat(2));
              
                u.setTrajet(rs.getString(3));
                u.setUser(new user(rs.getInt(4)));
                u.setVoiture(new Voiture(rs.getInt(5)));
                u.setChauffeur(new Chauffeur(rs.getInt(6)));
                ResultSet rs2 = pste2.executeQuery(); //affichage des voitures de req 2
                ResultSet rs3 = pste3.executeQuery();
                ResultSet rs4 = pste4.executeQuery();

                rs2.next();
                rs3.next();
                rs4.next();

                u.setChauffeur(new Chauffeur(rs4.getInt(1), rs4.getString(2), rs4.getString(3), rs4.getString(4), rs4.getInt(5), rs4.getString(6)));

                u.setVoiture(new Voiture(rs2.getInt(1), rs2.getString(2), rs2.getInt(3), rs2.getString(4), rs2.getString(5)));

                u.setUser(new user(rs3.getInt(1), rs3.getDouble(2), rs3.getString(3), rs3.getString(4), rs3.getDouble(5), rs3.getString(6), rs3.getString(7), rs3.getString(8), rs3.getDate(9)));

                Listrvvoiture.add(u);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Listrvvoiture.toString());
        return Listrvvoiture;
    }

}
