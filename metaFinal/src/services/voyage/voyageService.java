
package services.voyage;

import services.voyage.IVoyage;
import entities.voyage;
import entities.voyage_organise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Config.Datasource;
import entities.user;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import services.user.UserService;

/**
 *
 * @author FLAM
 */
public class voyageService implements IVoyage{
    
    private Connection conn;
    private Statement ste,ste2;
    private PreparedStatement pste,pste2;
    public static int IDVOY,T;
  

    public voyageService() {
                  conn = Datasource.getInstance().getCnx();

    }
    
      public voyage afficherbyID(int id) {

   voyage v= new voyage();
   
        String req = "SELECT * FROM `voyage`WHERE Idv="+id+";";
       
        
        try {

              ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
             
                v.setIdv(rs.getInt(1));
                  v.setPays( rs.getString(2));
                
             v.setImage_pays(rs.getString(3));
             
          ;                                        
                                   
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return v; 
    }
     
    @Override
    public voyage ajout(voyage v) {
     
            String req = "INSERT INTO `voyage` (`Idv`,`Pays`,`Image_pays`) VALUE (?,?,?)";

        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1,v.getIdv());
             pste.setString(2,v.getPays());
                pste.setString(3, v.getImage_pays());
            pste.executeUpdate();
            System.out.println("voyage créée");
            
            System.out.println(v.toString());
        return v;
            
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
        
    }


  @Override
    public void modifier(int idv, voyage v) {
           String req = "UPDATE `voyage` SET "
                +"`Pays`=?,`Image_Pays`=?"
               + " WHERE Idv = '" + idv+ "'";
    
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1,v.getPays());
            pste.setString(2, v.getImage_pays());
 
         
            pste.executeUpdate();
            System.out.println("Voyage de id "+ idv+ " Updated sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public void supprimer(int idv) {
        String delete = "DELETE FROM voyage WHERE Idv = ? "; 
        /* IL FAUT UTILISER ON DELETE CASCADE POUR SUPPRIMER LES VOYAGES ET LES VOYAGES ORGANISES DE LA DESTINATION 
                EXEMPLE  : SI ON A 100 VOYAGES VERS LA FRANCE
                            ET ON ANNULE/ SUPPRIME LE VOYAGE DE LA FRANCE
                            TOUS LES VOYAGES ORGANISES VERS LA FRANCE DOIVENT ETRE SUPPRIMES */
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,idv);
            pste.executeUpdate();
            System.out.println("voyage de id "+idv+ " est supprime");
       
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    }
    
     public  List<String> getAllByID()  {

     List<String> CH = new ArrayList<>();
     
   
          String req = "SELECT Idv,pays  from `voyage`";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
            
               
                 CH.add(rs.getInt(1)+"  "+rs.getString(2));  
                 IDVOY=rs.getInt(1);
            }
            
            }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return CH;    
    }
    

    @Override
    public List<voyage> afficher() {

   List<voyage> listRV = new ArrayList<>();
   
        String req = "SELECT * FROM `voyage` ;";
       
        
        try {

              ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                voyage v= new voyage();
                v.setIdv(rs.getInt(1));
                  v.setPays( rs.getString(2));
                
             v.setImage_pays(rs.getString(3));
             
                 listRV.add(v) ;                                        
                                   
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(listVORG.toString());
        return listRV; 
    }

    @Override
    public void ajouter(voyage entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    public List<voyage> afficherByPays(String Pays) {
          String req = "SELECT * FROM `voyage` WHERE Pays='"+Pays+"';";

       ArrayList<voyage> v1 = new ArrayList<voyage>();
        voyage v = new voyage ();
        try {

              ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
                          

            while(rs.next()){
            
                v.setIdv(rs.getInt(1));
                  v.setPays( rs.getString(2));
                
             v.setImage_pays(rs.getString(3));
             
                                                       
              v1.add(v);
               
                 
                                                   
            }
       
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    
     return v1;
    
    
}
    
    @Override
    public voyage afficherById(voyage v) {
          String req = "SELECT * from `voyage` where Idv='"+v.getIdv()+"';";
       
        
        try {

              ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
                          

            while(rs.next()){
            
                v.setIdv(rs.getInt(1));
                  v.setPays( rs.getString(2));
                
             v.setImage_pays(rs.getString(3));
             
                                                       
                                   
               
                 
                                                   
            }
       
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    
     return v;
    
    
}
    
}
