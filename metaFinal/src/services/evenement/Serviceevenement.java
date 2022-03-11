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
import entities.reservation_voyage;
import entities.user;
import entities.voyage;
import java.sql.Date;
import services.user.UserService;
/**
 *
 * @author Z4RGA
 */
public class Serviceevenement {

    Connection conn;    private PreparedStatement pste;    private Statement ste;


    public Serviceevenement() {
           conn = Datasource.getInstance().getCnx();
    }

    
 	

    public void ajouter(evenement e) {
        try {
            String req = "insert into evenement (Type_event,Chanteur,Adresse,Date_event,prix_e,image) values"
                    + "('" + e.getType_event() + "'   ,   '" + e.getChanteur() + "'   ,   '" + e.getAdresse() + "' ,  '" + e.getDate_event() + "' ,  '" + e.getPrix_e() + "' , '" + e.getImage() + "' )" ;
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
                e.setImage(rs.getString("image"));
                list.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }                

     // Affichage par chanteur 
    public List<evenement> afficher_par_chanteur(String Chanteur) 
    {
        List<evenement> listR = new ArrayList<>();
        try {
            
            String req = "select * from `evenement` where `Chanteur`='"+Chanteur+"';";
          ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                   evenement u = new evenement();
                u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                   u.setImage(rs.getString(7));
              
                 listR.add(u) ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listR;
    }
    
        // Affichage par date
    public List<evenement> afficher_par_date(Date date) 
    {
        List<evenement> listR = new ArrayList<>();
        try {
            
            String req = "select * from `evenement` where `Date_event`='"+date+"';";
          ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                   evenement u = new evenement();
                 u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                     u.setImage(rs.getString(7));
              
                 listR.add(u) ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listR;
    }

          // Affichage par TYPE EVENT 
    public List<evenement> afficher_par_Type_event(String type )
    {
        List<evenement> listR = new ArrayList<>();
        try {
            
            String req = "select * from `evenement` where `Type_event`='"+type+"';";
          ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                   evenement u = new evenement();
                 u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                     u.setImage(rs.getString(7));
              
                 listR.add(u) ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listR;
    }
    
           // Affichage par TYPE EVENT ET CHANTEUR 
    public List<evenement> afficher_par_Type_event_chanteur(String chanteur, String Type )
    {
        List<evenement> listRee = new ArrayList<>();
        try {
            
            String req = "select * from `evenement` where `Type_event`='"+Type+"' AND `Chanteur`='"+chanteur+"' ;";
          ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
               
                   evenement u = new evenement();
                   
                 u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                     u.setImage(rs.getString(7));
        
                 listRee.add(u) ;
                 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRee;
    }
    
      // Cheap Event
    public List<evenement> cheap_event()
    {

       List<evenement> listR = new ArrayList<>(); 
       String req = "select * from `evenement` ORDER BY `prix_e` ASC; "  ;
         
       
       
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
              while(rs.next()){
                   
              evenement u = new evenement();
             
                 u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                    u.setImage(rs.getString(7));
                 
             listR.add(u) ;
                                                   
            }
            
            
            }
           catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
      }
        return listR;
    }
    
    
    //  HIGH PRICE EVENT 
      public List<evenement> expensive_event()
    {

       List<evenement> listR = new ArrayList<>(); 
       String req = "select * from `evenement` ORDER BY `prix_e` DESC; "  ;
         
       
       
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
              while(rs.next()){
                   
              evenement u = new evenement();
             
                 u.setIde(rs.getInt(1));
                  u.setType_event( rs.getString(2));
                  u.setChanteur( rs.getString(3));
                  u.setAdresse( rs.getString(4));
                   u.setDate_event( rs.getDate(5));
                   u.setPrix_e( rs.getFloat(6));
                    u.setImage(rs.getString(7));
                 
             listR.add(u) ;
                                                   
            }
            
            
            }
           catch (SQLException ex) {
            Logger.getLogger(Serviceevenement.class.getName()).log(Level.SEVERE, null, ex);
      }
        return listR;
    }
      
      
    public  List<Integer> gelallID()  {
     List<Integer> ID = new ArrayList<>();
     
   
          String req = "SELECT Ide from `evenement`";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
     
               
                 ID.add(rs.getInt(1));                                   
            }}
            catch (SQLException ex) {
          
        }
        
        return ID;    
    }
    
    
        public  List<String> gelallChanteur()  {
            
         List<String> Chanteur = new ArrayList<>();
     
   
          String req = "SELECT Ide from `evenement`";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
     
               
                 Chanteur.add(rs.getString(3));                                   
            }}
            catch (SQLException ex) {
          
        }
        
        return Chanteur;  
        
    }
    
    
}
