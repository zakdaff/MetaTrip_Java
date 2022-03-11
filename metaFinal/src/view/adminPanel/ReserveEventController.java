/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.evenement.Serviceevenement;
import services.user.UserService;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class ReserveEventController implements Initializable {

    @FXML
    private Button reserverbutt;
    @FXML
    private ComboBox<?> ide;
    @FXML
    private ComboBox<?> idu;

        //********* Connexion au BD  ******************//
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    Statement stmt = null;

    //****** *** Z4RGA ******************//
    @FXML
    private TextField Nb_pers;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard1;
 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox2();
        
 
     combobox3();
     
     
    }    



    @FXML
    private void combobox2(){
//          Serviceevenement e = new Serviceevenement();
//        List<Integer> list1 = new ArrayList<>();
//        for (Integer data : e.gelallID()) {
//
//            list1.add(data);
//
//        }
//
//        ObservableList dataList = FXCollections.observableArrayList(list1);
//        ide.setItems(dataList);


    Serviceevenement e = new Serviceevenement();
        List<String> list1 = new ArrayList<>();
        for (String data : e.gelallChanteur()) {

            list1.add(data);

        }

        ObservableList dataList = FXCollections.observableArrayList(list1);
        ide.setItems(dataList);

    }

    @FXML
    public void combobox3 (){
            
       UserService u= new UserService();
            List<Integer> list1 = new ArrayList<>();
        for(Integer data:u.gelallID() ){
            
            list1.add(data);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list1);
        
        idu.setItems(dataList);
    }
    
   
    
    // INSERTION 
    private void insert() {

        con = Datasource.getInstance().getCnx();

  

        String insert = "INSERT INTO reservation_event (`Nb_pers`,`Ide`,`Idu`) VALUES (?,?,?) ;";
        try {

            st = con.prepareStatement(insert);
            st.setString(1, Nb_pers.getText());
            st.setInt(2, Integer.parseInt(ide.getValue().toString()));
            st.setInt(3, Integer.parseInt(idu.getValue().toString()));

       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MetaTrip Event Reservation");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("[Noted! Please Wait for verification by admin ]");

            alert.showAndWait();

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReserveEventController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Reserver(ActionEvent event) {
        insert() ;
        send_notify();
        
    }
    
       /* Sent TRAY Notification */
    void send_notify() {
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("NEW RESERVATION ADDED")
                .graphic(null)
                .hideAfter(javafx.util.Duration.seconds(5))
                .hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked on");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    }

}