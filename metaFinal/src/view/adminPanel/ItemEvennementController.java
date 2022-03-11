/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.evenement;
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
 * @author Z4RGA
 */
public class ItemEvennementController implements Initializable {

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
            try {
     
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
            Parent parent = FXMLLoader.load(getClass().getResource("ReserveEvent.fxml"));
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
    

