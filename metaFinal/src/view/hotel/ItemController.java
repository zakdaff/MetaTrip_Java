/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import entities.evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Label adresse;
    @FXML
    private Label date;
    private evenement events;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(evenement h ) {
       events = h;
        date.setText(String.valueOf(h.getDate_event()));
        adresse.setText(h.getAdresse());
        prix.setText(String.valueOf(h.getPrix_e()));
       
        String picture ="file:" +  h.getImage();
        Image image1 = new Image(picture, 250, 180, false, true);
       image.setImage(image1);
     }

    @FXML
    private void Reserver(ActionEvent event) {
    }
    
    
}
