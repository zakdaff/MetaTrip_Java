/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import entities.AffichHotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ItemController implements Initializable {

    private Label namehotel;
    private Label adressehotel;
    private ImageView image_hotel;
    @FXML
    private ImageView image;
    @FXML
    private Label nomhotel;
    @FXML
    private Label nb_etoile;
    @FXML
    private Label adressehotel1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData( AffichHotel affich){
        AffichHotel ah= new AffichHotel();
        Image image =new Image (getClass().getResourceAsStream(ah.getImgSrc()));
        image_hotel.setImage(image);
        namehotel.setText(ah.getName());
        adressehotel.setText(ah.getAdresse());
       
        
        
      
        
        
        
    }
    
}
