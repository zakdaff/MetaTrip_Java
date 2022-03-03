/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class InterfaceReservationFXMLController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private ScrollPane hotelgrid;
    @FXML
    private GridPane grid;
    @FXML
    private HBox chosenhotelCard1;
    @FXML
    private TextField Nb_personnes;
    @FXML
    private DatePicker Date_depart;
    @FXML
    private DatePicker Date_arrivee;
    @FXML
    private Button reserver_btn;
    @FXML
    private Button affich_list;
    @FXML
    private Button retour_btn;
    @FXML
    private TextField Nb_personnes2;
    @FXML
    private ComboBox<?> Idh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void AfficherListe(ActionEvent event) throws IOException {
    
       
 try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/ReservationHotel.fxml"));
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

    @FXML
    private void Retour(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/AcceuilFXML.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    }
    

