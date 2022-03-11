/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.Rating;
import services.evenement.Serviceevenement;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class AcceuilEvennementController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private AnchorPane AnchorPane_publications;
    @FXML
    private GridPane grid;
    Serviceevenement hc= new Serviceevenement();
     private final List<evenement> publication = new ArrayList<>();
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
             publication.addAll(hc.afficher());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < publication.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ItemEvennement.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

             ItemEvennementController AA = fxmlloader.getController();
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
                 // TODO
                 
           final Rating rating = new Rating();
                        rating.setUpdateOnHover(false);
                        rating.setPartialRating(false);
                        rating.setMax(5);
                        
                        AnchorPane anchorpane = null ;
                        
                        anchorpane.getChildren().setAll(pane);
                 
                 
                 
                 
    }    }}
    

