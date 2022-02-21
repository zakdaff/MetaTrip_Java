/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// imports
package services.sponsor;

import entities.sponsor;
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
public class Servicesponsor {

    Connection conn;
    private PreparedStatement pste;

    public Servicesponsor() {
        conn = Datasource.getInstance().getCnx();
    }

    public void ajouter(sponsor s) {
        try {
            String req = "insert into sponsor (nomsponsor,tel,email,date_sp,prix_sp,ide) values"
                    + "('" + s.getNomsponsor() + "'   ,   '" + s.getTel() + "'   ,   '" + s.getEmail() + "' ,  '" + s.getDate_sp() + "' ,  '" + s.getPrix_sp() + "' , '" + s.getEvenement().getIde() + "' )";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("sponsor ajoutée");
        } catch (SQLException ex) {
            Logger.getLogger(Servicesponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifier(sponsor s) {
        try {
            String req = "UPDATE sponsor SET nomsponsor='" + s.getNomsponsor() + "',tel='" + s.getTel() + "', prix_sp='" + s.getEmail() + "' WHERE ids=" + s.getIdS() + ";";
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("sponsor modif avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(Servicesponsor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id) {
        String delete = "DELETE FROM sponsor  where ids  = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1, id);
            pste.executeUpdate();

        } catch (SQLException ex) {

            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public List<sponsor> afficher() {
        List<sponsor> list = new ArrayList<>();
        try {
            String req = "select * from evenement";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                sponsor s = new sponsor();
                s.setIdS (rs.getInt(1));
                s.setNomsponsor(rs.getString("nomsponsor"));
                s.setTel(rs.getDouble("tel"));
                s.setEmail(rs.getString("email"));
                s.setDate_sp(rs.getDate("date_sp"));
                s.setPrix_sp(rs.getFloat("prix_sp"));
                list.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Servicesponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
