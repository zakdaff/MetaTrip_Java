/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import entities.hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import services.hotel.HotelCRUD;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class AccFXMLController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private AnchorPane AnchorPane_publications;
     HotelCRUD hc= new HotelCRUD();
     private final List<hotel> publication = new ArrayList<>();
    @FXML
    private GridPane grid;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        publication.addAll(hc.afficherHotels());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < publication.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

              ItemController AA = fxmlloader.getController();
               //  AA.setData(produit.get(0));
                AA.setData(publication.get(i));
                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
              //  GridPane.setMargin(anchorPane,Insets(35));           
               // GridPane.setMargin(anchorPane, new javafx.geometry.Insets(35));
               // GridPane.setPadding(new Insets(133, 10, 111, 10))
                }
        } catch (IOException ex) {
            ex.printStackTrace();
    }    }}

    

