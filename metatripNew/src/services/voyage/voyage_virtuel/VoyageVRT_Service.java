/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.voyage.voyage_virtuel;

/**
 *
 * @author Zak
 */
import entities.user;
import entities.Voyage_virtuel;
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
import services.user.UserService;

public class VoyageVRT_Service implements IVoyage_VRT_Service {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste2;

    public VoyageVRT_Service() {
        conn = Datasource.getInstance().getCnx();

    }

    @Override
    public void ajouter(Voyage_virtuel vv) throws SQLException {
        String req2 = "INSERT INTO `voyage_virtuel` (`Video`,`Image_v`,`Idv`,`Ida`) VALUE (?,?,?,?)";

        try {
            pste = conn.prepareStatement(req2);
            pste.setString(1, vv.getVideo());
            pste.setString(2, vv.getImage_v());
            pste.setInt(3, vv.getVoyage().getIdv());
            pste.setInt(4, vv.getAbonnement().getIda()); 

            System.out.println(vv.getVoyage().getIdv());
            pste.executeUpdate();
            System.out.println("voyage virtuel créée");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifier(int id, Voyage_virtuel vv)  {
    String req = "UPDATE `voyage_virtuel` SET "
                +"`Video`=?,`Image_v`=?,`Idv`=?,`Ida`=?"
               + " WHERE Idvv = " + id;
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, vv.getVideo());
            pste.setString(2, vv.getImage_v());
            pste.setInt(3, vv.getVoyage().getIdv());
            pste.setInt(4, vv.getAbonnement().getIda()); 

            pste.executeUpdate();
            System.out.println("voyage virtuel Modifier");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
    String req = "DELETE FROM `voyage_virtuel` where Idvv=?; ";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, id);


            pste.executeUpdate();
            System.out.println("voyage virtuel Supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Voyage_virtuel> afficher() throws SQLException {
           List<Voyage_virtuel> listVOV = new ArrayList<>();

           String req = "SELECT * FROM `voyage_virtuel` ;";
        String req2 = "SELECT * FROM `voyage` where `Idv` = ?";
                String req3 = "SELECT * FROM `abonnement` where `Ida` = ?";

        
        try {

            pste = conn.prepareStatement(req);
            pste2 = conn.prepareStatement(req2);
            
            ResultSet rs = pste.executeQuery();
            
            while(rs.next()){
                Voyage_virtuel vo = new Voyage_virtuel();
                
                vo.setIdv(rs.getInt(1));
                vo.setVideo(rs.getString(2));
                vo.setImage_v(rs.getString(3));
                vo.setIdv(rs.getInt(4));
                
                vo.setVoyage(new voyage(rs.getInt(5)));
                
                pste2.setInt(1,vo.getVoyage().getIdv()); //parametre de requete 2
                
                ResultSet rs2 = pste2.executeQuery(); //affichage des voyage de req 2
                
                if (rs2.next()){
                
               vo.setVoyage(new voyage(rs2.getInt(1),rs2.getString(2),rs2.getString(3)));
                }
                
             
               
                  
           //System.out.println(vo.toString());
              
                 listVOV.add(vo) ; 
               
                 
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println(listVOV.toString());
        return listVOV;
           }
    }


