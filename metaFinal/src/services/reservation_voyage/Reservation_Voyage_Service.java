package services.reservation_voyage;
import java.sql.Date;
import entities.reservation_voyage;
import entities.user;
import entities.voyage;
import entities.voyage_organise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Config.Datasource;

import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Map;
import services.user.UserService;

/**
 *
 * @author FLAM
 */
public class Reservation_Voyage_Service implements IReservation_Voyage{

        private Connection conn;
    private Statement ste;
    private PreparedStatement pste,pste2,pste3;

    public Reservation_Voyage_Service() {
           conn = Datasource.getInstance().getCnx();

    }
    
    
    
    @Override
    public void ajouter(reservation_voyage rv) {
        
            String req2 = "INSERT INTO `reservation_voyage` (`Date_depart`,`Date_arrivee`,`etat`,`Idu`,`Idv`) VALUES (?,?,?,?,?)";

        try {
        
            
              pste = conn.prepareStatement(req2);
              pste.setDate(1,rv.getDate_arrivee());
              pste.setDate(2, rv.getDate_arrivee());
              pste.setString(3, rv.getEtat());
               pste.setInt(4, rv.getUser().getIdu());
              pste.setInt(5, rv.getVoyage().getIdv());
                   
            pste.executeUpdate();
            System.out.println("reservation de voyage crée avec succes");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void modifier(int id, reservation_voyage rv) {
         String req = "UPDATE `reservation_voyage` SET "
                +"`Date_depart`=?,`Date_arrivee`=?,`etat`=?"
               + " WHERE Idrv = '" + id+ "'";
    
        try {
            pste = conn.prepareStatement(req);
           pste.setDate(1,rv.getDate_depart());
            pste.setDate(2, rv.getDate_arrivee());
            pste.setString(3, rv.getEtat());
         
            pste.executeUpdate();
            System.out.println("Reservation de Voyage de id "+ id+ " Updated sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int idrv) {
                String supp = "DELETE FROM reservation_voyage  where Idrv = ?";
        try {
            pste = conn.prepareStatement(supp);
            pste.setInt(1,idrv);
            pste.executeUpdate();
                   System.out.println("reservation_voyage Deleted sucessfully");

        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    }

@Override
    public List<reservation_voyage> afficher() {
   List<reservation_voyage> listRV = new ArrayList<>();
   
        String req = "SELECT * FROM `reservation_voyage` ;";
        String req2 = "SELECT * FROM `voyage` where `Idv` = ?";
        String req3 = "SELECT * FROM `user` where `Idu` = ?";
        
        try {

            pste = conn.prepareStatement(req);
            pste2 = conn.prepareStatement(req2);
            pste3 = conn.prepareStatement(req3);
            
            ResultSet rs = pste.executeQuery();
            
            while(rs.next()){
                reservation_voyage rvo = new reservation_voyage();
                
                rvo.setIdrv(rs.getInt(1));
                rvo.setDate_depart(rs.getDate(2));
                rvo.setDate_arrivee(rs.getDate(3));
                rvo.setEtat(rs.getString(4));
                rvo.setIdu(rs.getInt(5));
                rvo.setIdv(rs.getInt(6));

                
                rvo.setUser(new user(rs.getInt(5)));
                rvo.setVoyage(new voyage(rs.getInt(6)));
                
                pste2.setInt(1,rvo.getVoyage().getIdv()); //parametre de requete 2
                 pste3.setInt(1,rvo.getUser().getIdu());
               
                 ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                ResultSet rs3 = pste3.executeQuery(); 
                
                rs2.next();
                
                  rs3.next();
               
               rvo.setVoyage(new voyage(rs2.getInt(1),rs2.getString(2),rs2.getString(3)));
                rvo.setUser(
                        new user(rs3.getInt(1),rs3.getDouble(2),rs3.getString(3),rs3.getString(4),rs3.getDouble(5), rs3.getString(6),rs3.getString(7),rs3.getString(8),rs3.getDate(9)));
                        
                
             
               
                  
           //System.out.println(vo.toString());
              
                 listRV.add(rvo) ; 
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return listRV;    }


    
    public HashMap<  String,Integer> ListVoyagePlusRéservé(){
            HashMap<String,Integer > List = new HashMap< >();
           String req = "SELECT voyage.Pays,COUNT(*) 'nb de Reservation'\n" +
                                    "FROM`reservation_voyage` \n" +
                                    "JOIN`voyage_organise`   \n" +
                                        "JOIN `voyage`\n" +
                                        "ON voyage_organise.Idv=reservation_voyage.Idv\n" +
                                    "WHERE reservation_voyage.Idv=voyage.Idv\n" +
                                "GROUP BY voyage.Pays; ;";
    
            try {

            pste = conn.prepareStatement(req);
          
            
            ResultSet rs = pste.executeQuery();
            
             while(rs.next()){
             
               List.put(rs.getString(1),rs.getInt(2));
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return List;  
    
    }
        public HashMap<  String,Integer> LeMaximumDevoyageReserve(){
            HashMap<String,Integer > List = new HashMap< >();
           String req = "SELECT voyage.Pays, count(*)'Voyage le plus Réserve'\n" +
                                        "FROM`reservation_voyage` \n" +
                                        "                         JOIN`voyage_organise`   \n" +
                                        "                            JOIN `voyage`\n" +
                                        "                        ON voyage_organise.Idv=reservation_voyage.Idv\n" +
                                        "                        WHERE voyage_organise.Idv=voyage.Idv \n" +
                                        "				GROUP BY voyage.Pays\n" +
                                        "HAVING count(*) >= ALL (SELECT count(*) FROM`reservation_voyage` \n" +
                                        "                    JOIN `voyage_organise`   \n" +
                                        "                            JOIN `voyage`\n" +
                                        "                        ON voyage_organise.Idv=reservation_voyage.Idv\n" +
                                        "                        WHERE (reservation_voyage.Idv=voyage.Idv )\n" +
                                        "                        GROUP BY voyage.Pays)";
    
            try {

            pste = conn.prepareStatement(req);
          
            
            ResultSet rs = pste.executeQuery();
            
             while(rs.next()){
             
               List.put(rs.getString(1),rs.getInt(2));
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return List;  
    
    }
   public HashMap<  String,Integer> LeMinimumevoyageReserve(){
            HashMap<String,Integer > List = new HashMap< >();
           String req = "SELECT voyage.Pays, count(*)'Voyage le Moins Réserve'\n" +
"                                        FROM`reservation_voyage` \n" +
"                                                             JOIN`voyage_organise`   \n" +
"                                                                   JOIN `voyage` \n" +
"                                                               ON voyage_organise.Idv=reservation_voyage.Idv \n" +
"                                                               WHERE voyage_organise.Idv=voyage.Idv \n" +
"                                     				GROUP BY voyage.Pays \n" +
"                                        HAVING count(*) <= ALL (SELECT count(*) FROM`reservation_voyage`  \n" +
"                                                          JOIN `voyage_organise`    \n" +
"                                                                   JOIN `voyage` \n" +
"                                                               ON voyage_organise.Idv=reservation_voyage.Idv \n" +
"                                                               WHERE reservation_voyage.Idv=voyage.Idv \n" +
"                                                       GROUP BY voyage.Pays)";
    
            try {

            pste = conn.prepareStatement(req);
          
            
            ResultSet rs = pste.executeQuery();
            
             while(rs.next()){
             
               List.put(rs.getString(1),rs.getInt(2));
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return List;  
    
    }
   
    public List<Object> listedevoyageTrierParPrix(){
            List<Object>  List = new ArrayList< >();
           String req = "SELECT voyage_organise.Prix_billet,voyage.Pays,voyage_organise.Airline\n" +
                                                        "FROM `reservation_voyage` \n" +
"                                                          JOIN `voyage_organise`  \n" +
"                                                                   JOIN `voyage` \n" +
"                                                               ON voyage_organise.Idv=reservation_voyage.Idv \n" +
"                                                             WHERE reservation_voyage.Idv=voyage.Idv \n" +
"                                                     GROUP BY voyage_organise.Prix_billet";
    
            try {

            pste = conn.prepareStatement(req);
          
            
            ResultSet rs = pste.executeQuery();
            
             while(rs.next()){
             
               List.add(rs.getFloat(1));
                 List.add(rs.getString(2));
                List.add(rs.getString(3));
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return List;  
    
    }
}
