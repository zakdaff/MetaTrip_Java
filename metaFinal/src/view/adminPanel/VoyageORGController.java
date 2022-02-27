/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
import entities.voyage;
import entities.voyage_organise;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.voyage.voyageService;
import services.voyage.voyage_organise.VoyageORG_Service;

/**
 * FXML Controller class
 *
 * @author FLAM
 */
public class VoyageORGController implements Initializable {

         @FXML
              private Button bsave;
              @FXML
               private Button bupdate;
                @FXML
                private Button bdelete;

		
		@FXML
		private TableView<voyage_organise> table;
                @FXML
		private TableColumn<voyage_organise, Integer> uidv;
                
                
		@FXML
		private TableColumn<voyage_organise, String> unom;

                @FXML
		private TableColumn<voyage_organise, String> uimage;
                                
	
                
		
                
                //*********si flam******************//
                @FXML
		private TextField  idv;
                
                
		@FXML
		private TextField  pays;
                
                 	
                @FXML
		private TextField  image_pays;
                                
	
                
		voyage_organise voyage;
                	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    VoyageORG_Service vs;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         /* ObservableList<String> sexel
                = FXCollections.observableArrayList("Masculin", "Feminin");
        sexe.setItems(sexel);
        sexe.setValue("Masculin");*/
        // TODO
        affiche();
    }    
    
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        voyage_organise v = table.getSelectionModel().getSelectedItem();
              idv.setText(String.valueOf(v.getIdv()));
        pays.setText(v.getPays());

         image_pays.setText(v.getImage_pays());
        
       // bsave.setDisable(true);
    }
    
    
  
  
     
    public ObservableList<voyage_organise> getVoyage(){
         ObservableList<voyage_organise> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select * from voyage v ;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
          voyage v=new voyage();
                v.setIdv(rs.getInt("idv"));
                v.setPays(rs.getString("pays"));
                
                v.setImage_pays(rs.getString("image_pays"));
                list.add(v);
            }}
         catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;}
 
      public void affiche() {
        ObservableList<voyage_organise> list = getVoyage();
        uidv.setCellValueFactory(new PropertyValueFactory<voyage, Integer>("idv"));
        unom.setCellValueFactory(new PropertyValueFactory<voyage, String>("pays"));
       
         uimage.setCellValueFactory(new PropertyValueFactory<voyage, String>("image_pays"));
  
        
        table.setItems(list);
 
    }
      
         void clear() {
        idv.setText(null);

        pays.setText(null);

        image_pays.setText(null);
        //sexe.getSelectionModel().selectFirst();
        bsave.setDisable(false);
    }
         
         private void insert() {
	con =Datasource.getInstance().getCnx();
       String insert = "INSERT INTO voyage_organise (`pays`,`Image_pays`) VALUES (?,?) ;";
        try {
            
            st = con.prepareStatement(insert);
            st.setString(1, pays.getText());
            st.setString(2, image_pays.getText());
     
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Adding voyage");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'voyage_organise de pays  " +pays.getText()+" est ajouté avec succés");

		alert.showAndWait();
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
 
         
 
         
          private void update() {
	con =Datasource.getInstance().getCnx();
            String update = "UPDATE `voyage_organise` SET "
                +"`pays`=?,`image_pays`=? "
                + "WHERE `idv` =? ;";
        try {
                st = con.prepareStatement(update);
                st.setString(1, pays.getText());
                st.setString(2, image_pays.getText());
             
                st.setInt(3, Integer.parseInt(idv.getText()));
            
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Updating voyage");

		// Header Text: null
		alert.setHeaderText(null);
	alert.setContentText("'voyage de pays  " +pays.getText()+" est modifié avec succés");
		alert.showAndWait();
                
                st.executeUpdate();
                affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
         
         public void delete() {
         con =Datasource.getInstance().getCnx();
        String delete = "DELETE FROM voyage  where idv = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idv.getText()));
            
            
            	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
	alert.setContentText("'voyage de ID  "+ idv.getText()+"  est supprimé avec succés");
		alert.showAndWait();
            
            
            
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    } 
         
    @FXML
    private void saveEvent(ActionEvent event) {
       insert();
        clear();
    }
 
    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }
 
    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }
 
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }
     
    
}
