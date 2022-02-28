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
import entities.user;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import services.evenement.Serviceevenement;
/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class EventListController implements Initializable {
    
    /* Buttons */ 

    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    

    //********* Z4RGA ******************//
    @FXML
		private TextField  Ide;
                
                
    @FXML
		private TextField  Type_event;
                
    @FXML
		private TextField  Chanteur;
                
    @FXML
		private TextField  Adresse;
                
    @FXML
		private TextField  Date_event;
                
      @FXML
                 private TextField prix_e;
    @FXML
                 private TextField image;            
                                

      //********* Z4RGA ******************//
       evenement evenement ; 

    @FXML
    private TableView<evenement> table;
    
    @FXML
    private TableColumn<evenement, Integer> eIde;
    @FXML
    private TableColumn<evenement, String> eType_event;
    @FXML
    private TableColumn<evenement, String> eChanteur;
    @FXML
    private TableColumn<evenement, String> eAdresse;
    @FXML
    private TableColumn<evenement, Date> eDate_event;
    @FXML
    private TableColumn<evenement, Float> eprix_e;
    @FXML
    private TableColumn<evenement, String> eimage;

    
    
     //********* Connexion au BD  ******************//
    
    Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    
    //********* Z4RGA ******************//
    Serviceevenement se ;
  
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//          Serviceevenement see = new Serviceevenement();
//        List personnes = see.afficher();
//        ObservableList list = FXCollections.observableArrayList(personnes);
//        table.setItems(list);
//        
//        eIde.setCellValueFactory(new PropertyValueFactory<>("Ide"));
//        eType_event.setCellValueFactory(new PropertyValueFactory<>("Type_event"));
//        eChanteur.setCellValueFactory(new PropertyValueFactory<>("Chanteur"));
//        eAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
//        eDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
//         eprix_e.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
//        eimage.setCellValueFactory(new PropertyValueFactory<>("image"));
 

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
    
       public ObservableList<evenement> getEvent(){
         ObservableList<evenement> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select * from evenement ;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
               evenement e = new evenement();
               
                e.setIde(rs.getInt("Ide"));
                e.setType_event(rs.getString("Type_event"));
                
                e.setChanteur(rs.getString("Chanteur"));
                e.setAdresse(rs.getString("Adresse"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setPrix_e(rs.getFloat("prix_e"));
                e.setImage(rs.getString("image"));
                
                list.add(e);
            }}
         catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
       }
       
       
       
       public void affiche() {
        ObservableList<evenement> list = getEvent();
    
        eIde.setCellValueFactory(new PropertyValueFactory<evenement, Integer>("Ide"));
        eType_event.setCellValueFactory(new PropertyValueFactory<evenement, String>("Type_event"));
        eChanteur.setCellValueFactory(new PropertyValueFactory<evenement, String>("Chanteur"));
        eAdresse.setCellValueFactory(new PropertyValueFactory<evenement, String>("Adresse"));
         
        eDate_event.setCellValueFactory(new PropertyValueFactory<evenement, Date>("Date_event"));
        eprix_e.setCellValueFactory(new PropertyValueFactory<evenement, Float>("prix_e"));
        eimage.setCellValueFactory(new PropertyValueFactory<evenement, String>("image"));
         
        
        table.setItems(list);
 
    }
    
}
