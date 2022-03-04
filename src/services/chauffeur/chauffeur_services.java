/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.chauffeur;
import entities.Chauffeur;
import Config.Datasource;
import crud.Voiture;
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
 * @author ASUS I7
 */
public class chauffeur_services implements Ichaufffeur {
     private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public  chauffeur_services() {
           conn = Datasource.getInstance().getCnx();

    }


    @Override
    public void ajouter(Chauffeur ch) {
//              System.out.println("595xssssssssssss"+ch); 
//              System.out.println("sssssssssssssssssssssssssssssssssssss"+ch.getetatDispo());
  String req = "INSERT INTO `chauffeur` (`nom`,`prenom`,`photo`,`tel`,`description`,`etatDispo`) VALUES (?,?,?,?,?,?)";
     
  try {	       
            pste = conn.prepareStatement(req);
           // pste.setInt(1,ch.getidch());
            pste.setString(1,ch.getnom());
            pste.setString(2,ch.getprenom());
            pste.setString(3,ch.getphoto());
            pste.setString(4,ch.gettel());
            pste.setString(5,ch.getdescription());
            pste.setString(6,ch.getetatDispo());

            
            pste.executeUpdate();
            System.out.println("Ajouter ajoutée aves=c succès");
                
        } catch (SQLException ex) {
            Logger.getLogger(chauffeur_services.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void modifier(int idch, Chauffeur ch) {
            String req = "UPDATE  `chauffeur` SET "
                + "`nom`=?,`prenom`=?,`photo`=?,`tel`=?,`description`=?,`etatDispo`=? "
                + "WHERE idch = '" + idch + "'";
        System.out.println(ch);
        try {
            pste = conn.prepareStatement(req);
       
            pste.setString(1,ch.getnom());
            pste.setString(2,ch.getprenom());
            pste.setString(3,ch.getphoto());
            pste.setString(4,ch.gettel());
            pste.setString(5,ch.getdescription());
            pste.setString(6,ch.getetatDispo());
            pste.executeUpdate();
            System.out.println("Chauffeur modifiée");
           
            }
        catch (SQLException ex){
            Logger.getLogger(Chauffeur.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
       

    }

    @Override
    public void supprimer(int idch) {
              String delete = "DELETE FROM chauffeur  WHERE idch = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, idch);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(chauffeur_services.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  public int nbChauffeurDispo() throws SQLException {
   int nb=0;
   
          String req = "SELECT count(*) from `chauffeur` where etatDispo='DISPO';";
            try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
            nb=rs.getInt(1);            
            }}
            
            catch (SQLException ex) {
            Logger.getLogger(chauffeur_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;   
  }

    @Override
    public List<Chauffeur> afficher() {
List<Chauffeur> maList=new ArrayList();
        try {
          
            String requete ="SELECT * FROM chauffeur";
                      ste = conn.createStatement();
            ResultSet res =ste.executeQuery(requete);
            
            while (res.next()){
                Chauffeur cha =new Chauffeur();
                  cha.setidch(res.getInt(1));
                  cha.setnom(res.getString(2));
                  cha.setprenom(res.getString(3));
                  cha.setphoto(res.getString(4));
                  cha.settel(res.getString(5));
                  cha.setdescription(res.getString(6));
                  cha.setetatDispo(res.getString(7));
                  
               
               maList.add(cha);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return maList ;      }
}
