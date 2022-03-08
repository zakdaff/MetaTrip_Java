/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.chambre;

import Config.Datasource;
import entities.Chambre;
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
 * @author Nayrouz
 */
public class Chambre_service {
     private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste2;
    
       public Chambre_service() {
                conn = Datasource.getInstance().getCnx();

    } 
    
       public void ajouter(Chambre ch) {
     
            String req2 = "INSERT INTO `chambre` (`idc`,`numc`,`image`,`type`,`etat`,`idh`,`prixc`) VALUES (?,?,?,?,?,?,?)";
            

        try {
           
              pste = conn.prepareStatement(req2);
              pste.setInt(1,ch.getIdc());
              pste.setInt(2, ch.getNumc());
              pste.setString(3, ch.getImage_chambre());
              pste.setString(4, ch.getType());
              pste.setString(5,ch.getEtat_dispo());
              pste.setInt(6, ch.getHotel().getIdh());
              pste.setFloat(7, ch.getPrixc());
             
           
              pste.executeUpdate();
            System.out.println("chambre cree ");
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
     
    public void modifier(int idc, Chambre ch) {
           String req = "UPDATE `chambre` SET "
                +"`numc`=?,`type`=?,`etat`=?,`image`=?,`idh`=?,`prixc`=?"
               + " WHERE idc = '" + idc+ "'";
    
        try {
            pste = conn.prepareStatement(req);
         
            pste.setInt(1, ch.getNumc());
            pste.setString(2, ch.getType());
            pste.setString(3, ch.getEtat_dispo());
            pste.setString(4, ch.getImage_chambre());
            pste.setInt(5, ch.getIdh());
            pste.setFloat(6, ch.getPrixc());
            
            pste.executeUpdate();
            System.out.println("Chambre de id "+ idc+ " Updated sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void supprimer(int idc) {
              String supp = "DELETE FROM chambre  where idc = ?";
        try {
            pste = conn.prepareStatement(supp);
            pste.setInt(1,idc);
            pste.executeUpdate();
                   System.out.println("Chambre  Deleted sucessfully");

        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    }
    public List<Chambre> afficher() {
   List<Chambre> listCham = new ArrayList<>();
   
        String req = "SELECT * FROM `chambre` ;";
        String req2 = "SELECT * FROM `chambre` where `idc` = ?";
        
        try {

            pste = conn.prepareStatement(req);
            pste2 = conn.prepareStatement(req2);
            
            ResultSet rs = pste.executeQuery();
            
            while(rs.next()){
                Chambre ch = new Chambre ();
                
                ch.setIdc(rs.getInt(1));
                ch.setNumc(rs.getInt(2));
                ch.setImage_chambre(rs.getString(3));
                ch.setType(rs.getString(4));
                ch.setEtat_dispo(rs.getString(5));
                ch.setPrixc(rs.getFloat(6));
              
               
                
                                             
                listCham.add(ch);
                 
                                                   
            }
            
        } catch (SQLException ex) {
           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
          
        }
       // System.out.println(listVORG.toString());
        return listCham;    }

}
