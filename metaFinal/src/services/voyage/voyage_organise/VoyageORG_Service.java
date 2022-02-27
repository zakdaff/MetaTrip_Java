
package services.voyage.voyage_organise;

import services.voyage.voyage_organise.IVoyage_ORG_Service;
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
import entities.EtatDispo;
import services.user.UserService;

/**
 *
 * @author FLAM
 */
public class VoyageORG_Service implements IVoyage_ORG_Service{
    
    
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste2;

    public VoyageORG_Service() {
                conn = Datasource.getInstance().getCnx();

    }
    
    

       @Override
    public void ajouter(voyage_organise vo) {
            String req2 = "INSERT INTO `voyage_organise` (`Prix_billet`,`Airline`,`Nb_nuitees`,`etatVoyage`,`Idv`) VALUE (?,?,?,?,?)";

        try {
              pste = conn.prepareStatement(req2);
              pste.setFloat(1,vo.getPrix_billet());
              pste.setString(2, vo.getAirline());
              pste.setInt(3, vo.getNb_nuitees());
            pste.setString(4,vo.getEtatVoyage().name());
              
              pste.setInt(5, vo.getVoyage().getIdv());
                    //System.out.println(vo.getVoyage().getIdv());
            pste.executeUpdate();
            System.out.println("voyage organise créée");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @Override
    public void modifier(int idvo, voyage_organise vo) {
           String req = "UPDATE `voyage_organise` SET "
                +"`Prix_billet`=?,`Airline`=?,`Nb_nuitees`=?,`etatVoyage`=?"
               + " WHERE Idvo = '" + idvo+ "'";
        try {
            pste = conn.prepareStatement(req);
           pste.setFloat(1,vo.getPrix_billet());
            pste.setString(2, vo.getAirline());
            pste.setInt(3, vo.getNb_nuitees());
           pste.setString(4,vo.getEtatVoyage().name());
         
            pste.executeUpdate();
            System.out.println("Voyage organisé de id "+ idvo+ " Updated sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   @Override
    public void supprimer(int idvo) {
              String supp = "DELETE FROM voyage_organise  where Idvo = ?";
        try {
            pste = conn.prepareStatement(supp);
            pste.setInt(1,idvo);
            pste.executeUpdate();
                   System.out.println("Voyage organisé Deleted sucessfully");

        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    }

    @Override
    public List<voyage_organise> afficher() {
   List<voyage_organise> listVORG = new ArrayList<>();
   
        String req = "SELECT * FROM `voyage_organise` ;";
        String req2 = "SELECT * FROM `voyage` where `Idv` = ?";
        
        try {

            pste = conn.prepareStatement(req);
            pste2 = conn.prepareStatement(req2);
            
            ResultSet rs = pste.executeQuery();
            
            while(rs.next()){
                voyage_organise vo = new voyage_organise();
                
                vo.setIdvo(rs.getInt(1));
                vo.setPrix_billet(rs.getFloat(2));
                vo.setAirline(rs.getString(3));
                vo.setNb_nuitees(rs.getInt(4));
                
                vo.setVoyage(new voyage(rs.getInt(5)));
                
                pste2.setInt(1,vo.getVoyage().getIdv()); //parametre de requete 2
                
                ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                
                rs2.next();
                
               vo.setVoyage(new voyage(rs2.getInt(1),rs2.getString(2),rs2.getString(3)));
                
             
               
                  
           //System.out.println(vo.toString());
              
                 listVORG.add(vo) ; 
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return listVORG;    }

  
}
