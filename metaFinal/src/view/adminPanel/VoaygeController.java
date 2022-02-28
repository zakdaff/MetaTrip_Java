/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.voyage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import services.voyage.voyageService;

import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class VoaygeController implements Initializable {
                @FXML
		private TextField  Pays;
                
                 @FXML
		private TextField  Image_Pays;
                 @FXML
                 private Button ajouterButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       @FXML
        private void insert(ActionEvent event) {
             Window owner = ajouterButton.getScene().getWindow();
             
             
                    if((Pays.getText().isEmpty())&& (Image_Pays.getText().isEmpty() )){
                    
            
                          showAlert(Alert.AlertType.ERROR, owner, "Form Error!","enter Image &Pays ");
            return;
          
                    } else {
                    String P = Pays.getText();
                    String Image= Image_Pays.getText();
                    voyageService vs= new voyageService();
                    voyage v1= new voyage(P,Image);
                    
                    vs.ajout(v1);
                    
                    
                    }
                    
        
        }
      public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
