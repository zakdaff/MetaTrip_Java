/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.evenement;

import entities.evenement;
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
import java.sql.Date;
import services.user.UserService;
/**
 *
 * @author Z4RGA
 */
public class Serviceevenement {

    Connection conn;    private PreparedStatement pste;

    public Serviceevenement() {
           conn = Datasource.getInstance().getCnx();
    }

    
 	

    public void ajouter(evenement e) {
        try {
            String req = "insert into evenement (Type_event,Chanteur,Adresse,Date_event,prix_e) values"
                    + "('" + e.getType_event() + "'   ,   '" + e.getChanteur() + "'   ,   '" + e.getAdresse() + "' ,  '" + e.getDate_event() + "' ,  '" + e.getPrix_e() + "' )" ;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 
    public void modifier(evenement e) {
      try {
            String req= "UPDATE evenement SET Type_event='" + e.getType_event() + "',Chanteur='" + e.getChanteur()+"', Adresse='" +e.getAdresse()+ "' WHERE Ide="+e.getIde()+";";
           Statement  st = conn.createStatement();
            st.executeUpdate(req);
           System.out.println("event modif avec succes");
        }  catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  
     
    public void supprimer(int id )  {
      String delete = "DELETE FROM evenement  where Ide = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,id);
            pste.executeUpdate();
       
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    
    }

    
     public List<evenement> afficher() {
        List<evenement> list = new ArrayList<>();
        try {
            String req ="select * from evenement";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                evenement e = new evenement();
                e.setIde(rs.getInt(1));
                e.setType_event(rs.getString("Type_event"));
                e.setChanteur(rs.getString("Chanteur"));
                e.setAdresse(rs.getString("Adresse"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setPrix_e(rs.getFloat("Prix_e"));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }                

   

   
    
   
    
    
}
