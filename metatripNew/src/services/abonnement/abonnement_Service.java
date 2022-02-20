/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.abonnement;

import entities.abonnement;
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

/**
 *
 * @author User
 */
public class abonnement_Service implements Iabonnement{ 
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    private PreparedStatement pste2;
    
       public abonnement_Service() {
                conn = Datasource.getInstance().getCnx();

    } 
       
    @Override
    public void ajouter(abonnement a) {
        String req = "INSERT INTO abonnement (Type,`Prix_a`,`Date_achat`,`Date_expiration`,`Etat`,`Ref_paiement`) VALUE (?,?,?,?,?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, a.getType());
            pste.setInt(2, a.getPrix_a());
            pste.setDate(3, a.getDate_achat());
            pste.setDate(4, a.getDate_expiration());
            pste.setString(5, a.getEtat());
            pste.setInt(6, a.getRef_paiment());
            pste.executeUpdate();
            System.out.println("Abonnement ajouteé");
        } catch (SQLException ex) {
            Logger.getLogger(abonnement_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, abonnement a)  {
    
        String req = "UPDATE  abonnement SET "
                + "`Type`=?,`Prix_a`=?,`Date_achat`=?,`Date_expiration`=?,`Etat`=?,`Ref_paiement`=? "
                + "WHERE Ida = ?" ;
        System.out.println(a);
        try {
            pste2 = conn.prepareStatement(req);
            pste2.setString(1, a.getType());
            pste2.setInt(2, a.getPrix_a());
            pste2.setDate(3, a.getDate_achat());
            pste2.setDate(4, a.getDate_expiration());
            pste2.setString(5, a.getEtat());
            pste2.setInt(6, a.getRef_paiment());
            pste2.setInt(7, id);

            pste2.executeUpdate();
            System.out.println("abonnement modifiée");
           
            }
        catch (SQLException ex){
            Logger.getLogger(abonnement.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
       
 
    }

    @Override
    public void supprimer(int id)  {
            String delete = "DELETE FROM abonnement  WHERE Ida = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, id);
                        System.out.println("abonnement supprimé avec succes!");

            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(abonnement_Service.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public List<abonnement> afficher() {
              List<abonnement> abonnements = new ArrayList<>();
        String req = "SELECT * FROM abonnement";

        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                abonnement a = new abonnement();
                a.setIda(rs.getInt("Ida"));
                a.setType(rs.getString("Type"));
                a.setPrix_a(rs.getInt(2));
                a.setDate_achat(rs.getDate(3));
                a.setDate_expiration(rs.getDate(3));
                a.setEtat(rs.getString("Etat"));
                a.setRef_paiment(rs.getInt(3));
                

                abonnements.add(a);

            }

        } catch (SQLException ex) {
            Logger.getLogger(abonnement_Service.class.getName()).log(Level.SEVERE, null, ex);
        }

        return abonnements;
    
    }
        
    }
    
