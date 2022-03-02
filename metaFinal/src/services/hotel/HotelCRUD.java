/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.hotel;


import entities.hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Config.Datasource;

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
            String requete ="INSERT INTO hotel VALUES(?,?,?,?,?)";
               pste = conn.prepareStatement(requete);
            pste.setInt(1,h.getIdh());
            pste.setString(2,h.getNom_hotel());
            pste.setInt(3,h.getNb_etoiles());
            pste.setString(4,h.getAdresse());
            pste.setString(5,h.getImage_hotel());
            
            pste.executeUpdate();
            System.out.println("Hotel ajoutée avec succès");
                
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
                  h.setImage_hotel(rs.getString(6));
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
   public void modifierHotel( int idh ,hotel h){
       
        String req = "UPDATE `hotel` SET "
                +" Nom_hotel`=?,`type`=?"
               + " WHERE Idh = '" + idh+ "'";
    
        try {
            pste = conn.prepareStatement(req);
         
            pste.setInt(1, h.getIdh());
            pste.setString(2, h.getNom_hotel());
            pste.executeUpdate();
            System.out.println("Hotel de id "+ idh+ " Updated sucessfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        }
        
     
        
    

}
