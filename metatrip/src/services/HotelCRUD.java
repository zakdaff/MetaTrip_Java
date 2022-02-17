/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utilis.Datasource;

/**
 *
 * @author Nayrouz
 */
public class HotelCRUD {
      private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public HotelCRUD() {
           conn = Datasource.getInstance().getCnx();

    }
    
    public void ajouterHotel(hotel h){
        try {
            String requete ="INSERT INTO hotel VALUES(?,?,?,?)";
               pste = conn.prepareStatement(requete);
            pste.setInt(1,h.getIdh());
            pste.setString(2,h.getNom_hotel());
            pste.setInt(3,h.getNb_etoiles());
            pste.setString(4,h.getAdresse());
            
            pste.executeUpdate();
            System.out.println("Hotel ajoutée aves=c succès");
                
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    public List<hotel> afficherHotels(){
        List<hotel> myList=new ArrayList();
        try {
          
            String requete ="SELECT * FROM hotel";
                       ste = conn.createStatement();
         ResultSet rs = ste.executeQuery(requete);
          
            
            while (rs.next()){
                hotel h =new hotel();
                  h.setIdh(rs.getInt(1));
                  h.setNom_hotel(rs.getString(2));
                  h.setNb_etoiles(rs.getInt(3));
                  h.setAdresse(rs.getString(5));
                  myList.add(h);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return myList ;
      
       }
    
    public void supprimerHotel(hotel h){
        try {
            String req ="DELETE FROM hotel where Idh=" + h.getIdh();
               ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
   public void modifierHotel(hotel h){
        try {
            String req= "UPDATE hotel SET Nom_hotel='" + h.getNom_hotel() + "',Nb_etoiles='" + h.getNb_etoiles()+"',Adresse'" + h.getAdresse()+ "'WHERE Idh="+h.getIdh();
             ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println();
        } catch (SQLException ex) {
         System.out.println(ex.getMessage());
        }
    
}
}
