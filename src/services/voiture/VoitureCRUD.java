/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.voiture;


import entities.Voiture;
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
public class VoitureCRUD {
       private final Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public VoitureCRUD() {
           conn = Datasource.getInstance().getCnx();

    }
    
    public void ajouterVoiture(Voiture v){
        try {
            String requete ="INSERT INTO voiture VALUES(?,?,?,?,?)";
            pste = conn.prepareStatement(requete);
            pste.setInt(1,v.getIdvoit());
            pste.setString(2,v.getMatricule());
            pste.setInt(3,v.getPuissance_fiscalle());
            pste.setString(4,v.getImage_v());
            pste.setString(5,v.getModele());
            
            pste.executeUpdate();
            System.out.println("Ajouter ajoutée aves=c succès");
                
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
   
    public List<Voiture> afficherVoitures(){
        List<Voiture> myList=new ArrayList();
        try {
          
            String requete ="SELECT * FROM voiture";
                      ste = conn.createStatement();
            ResultSet res =ste.executeQuery(requete);
            
            while (res.next()){
                Voiture v =new Voiture();
                  v.setIdvoit(res.getInt(1));
                  v.setMatricule(res.getString(2));
                  v.setPuissance_fiscalle(res.getInt(3));
                  v.setImage_v(res.getString(4));
                  v.setModele(res.getString(5));
                  
               
               myList.add(v);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        return myList ;
      
       }
    
    public void supprimerVoiture(Voiture v){
        try {
            String req ="DELETE FROM voiture where id_cmd=" + v.getIdvoit();
            
             ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Voiture supprime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void recherche_parmodele(String Modele){
        String req ="SELECT * FROM voiture where Modele=" +Modele+"'";
            try {
             ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("voici tout les modeles de cette voiture "+Modele);
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void modifierVoiture(Voiture v){
        try {
            String req= "UPDATE voiture SET `Matricule`='" + v.getMatricule() + "',`Puissance_fiscalle`='" + v.getPuissance_fiscalle()+"',`Image_v`='" + v.getImage_v()+"',`Modele`='" + v.getModele() + "'"
                    + "WHERE `Idvoit`="+v.getIdvoit();
        ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println(v);
        } catch (SQLException ex) {
         System.out.println(ex.getMessage());
        }
    }}
        


