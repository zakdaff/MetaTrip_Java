/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.Datasource;
import entities.sponsor;
import entities.user;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import services.evenement.Serviceevenement;
import services.sponsor.Servicesponsor;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class SponsorListController implements Initializable {
    

    
    /* Buttons */ 
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    
    
    //********* Z4RGA ******************//
    
    sponsor sponsor ; 
    
    
    @FXML
    private TableView<sponsor> table;
    
    @FXML
    private TableColumn<sponsor, Integer> sids;
    @FXML
    private TableColumn<sponsor, String> snomsponsor;
    @FXML
    private TableColumn<sponsor, String> stel;
    @FXML
    private TableColumn<sponsor, String> semail;
    @FXML
    private TableColumn<sponsor, Date> sdate_sp;
    @FXML
    private TableColumn<sponsor, Float> sprix_sp;
    @FXML
    private TableColumn<sponsor, String> simage;
    
    
    
        //********* Connexion au BD  ******************//
    
    Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    
    //********* Z4RGA ******************//
    Servicesponsor sp ;
    @FXML
    private TextField ids;
    @FXML
    private TextField nomsponsor;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private TextField prix_sp;
    @FXML
    private Button insert_image;
   
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         affiche(); 
    }    

    @FXML
    private void saveEvent(ActionEvent event) {
    }

    @FXML
    private void updateEvent(ActionEvent event) {
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
    }

    @FXML
    private void clearEvent(ActionEvent event) {
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
    }
    
    
       // Affichage 
    
       public ObservableList<sponsor> getSponsor(){
         ObservableList<sponsor> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select * from sponsor ;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
               sponsor s = new sponsor();
               
                s.setIdS(rs.getInt("ids"));
                s.setNomsponsor(rs.getString("nomsponsor"));
                
                s.setTel(rs.getString("tel"));
                s.setEmail(rs.getString("email"));
                s.setDate_sp(rs.getDate("date_sp"));
                s.setPrix_sp(rs.getFloat("prix_sp"));
                s.setImage(rs.getString("image"));
                
                list.add(s);
            }}
         catch (SQLException ex) {
            Logger.getLogger(SponsorListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
       }
       
       
       
       public void affiche() {
        ObservableList<sponsor> list = getSponsor();
    
       sids.setCellValueFactory(new PropertyValueFactory<sponsor, Integer>("ids"));
        snomsponsor.setCellValueFactory(new PropertyValueFactory<sponsor, String>("nomsponsor"));
        stel.setCellValueFactory(new PropertyValueFactory<sponsor, String>("tel"));
        semail.setCellValueFactory(new PropertyValueFactory<sponsor, String>("email"));
         
        sdate_sp.setCellValueFactory(new PropertyValueFactory<sponsor, Date>("date_sp"));
        sprix_sp.setCellValueFactory(new PropertyValueFactory<sponsor, Float>("prix_sp"));
        simage.setCellValueFactory(new PropertyValueFactory<sponsor, String>("image"));
         
        
        table.setItems(list);
 
    }

    @FXML
    private void insertImage(ActionEvent event) {
    }
    
    
    
    
}
