/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import entities.hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ItemController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label adresse;
    @FXML
    private Label nb_etoile;
    @FXML
    private Label nomhotel;
     private hotel hotels;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(hotel h ) {
        hotels = h;
        nomhotel.setText(h.getNom_hotel().toString());
        adresse.setText(h.getAdresse());
        nb_etoile.setText(String.valueOf(h.getNb_etoiles()));
       
        String picture ="file:" +  h.getImage_hotel();
        Image image1 = new Image(picture, 250, 180, false, true);
       image.setImage(image1);
        
       
       
    }


    @FXML
    private void Reserver(ActionEvent event) {
        try {
     
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/InterfaceReservationFXML.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           System.err.println(ex.getMessage());;
        }
    }
    
}
