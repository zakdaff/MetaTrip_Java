/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private TextField idvo;
    @FXML
    private TextField prix_billet;
    @FXML
    private TextField airline;
    @FXML
    private TextField Nb_nuitees;
    @FXML
    private TextField etatVoyage;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
