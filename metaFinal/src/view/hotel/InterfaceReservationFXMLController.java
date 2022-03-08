/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import Config.Datasource;
import entities.Chambre;
import entities.hotel;
import entities.reservation_hotel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.hotel.HotelCRUD;
import services.reservation_hotel.Reserrvation_Hotel_Service;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    private Button affich_list;
    @FXML
    private Button retour_btn;
    @FXML
    private TextField Nb_personnes2;
    @FXML
    private ComboBox<String> Idh;
    Reserrvation_Hotel_Service rs=new Reserrvation_Hotel_Service();
    HotelCRUD hc=new HotelCRUD();
    Chambre c= new Chambre();
    private Connection conn= Datasource.getInstance().getCnx();;
    private Statement ste;
    private PreparedStatement pste;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ResultSet rs;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery("Select Nom_hotel from hotel");
            while (rs.next()) {  // loop

                Idh.getItems().addAll(rs.getString("Nom_hotel")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }    

    @FXML
    private void AfficherListe(ActionEvent event) throws IOException {
    
       
 try {
     //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
     //********
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
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
public boolean isNumeric(String str){
        if(str==null){
            return false;
        }
        try
        {
            int x=Integer.parseInt(str);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    @FXML
    private void reserver(ActionEvent event) {
        String erreurs="";
        if(Nb_personnes.getText().trim().isEmpty()){
            erreurs+="- Please enter person number \n";
        }
        if(Nb_personnes2.getText().trim().isEmpty()){
            erreurs+="- Please enter night days\n";
        }
        if(Date_arrivee.getValue()==null){
            erreurs+="- Please enter arrive date\n";
        }
        if(Date_depart.getValue()==null){
            erreurs+="- Please enter a depart date\n";
        }
        if(Idh.getValue()==null){
            erreurs+="- Please enter a hostel\n";
        }
        if(!isNumeric(Nb_personnes.getText().trim())){
            erreurs+="- Please enter a valid number \n";
        }
        if(!isNumeric(Nb_personnes2.getText().trim())){
            erreurs+="- Please enter a valid number \n";
        }
        if(Date_arrivee.getValue()!=null && Date_depart.getValue()!=null && Date_depart.getValue().compareTo(Date_arrivee.getValue())>0){
            erreurs+="- Please enter a valid date \n";
        }
        if(erreurs.length()>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add fail");
            alert.setContentText(erreurs);
            alert.showAndWait();
        }
        else{
            hotel h=hc.findByName(Idh.getValue());
        
    
        reservation_hotel r=new reservation_hotel(Integer.parseInt(Nb_personnes2.getText()),
                Integer.parseInt(Nb_personnes.getText()),
                60*Integer.parseInt(Nb_personnes.getText())*Integer.parseInt(Nb_personnes2.getText()),41,h.getIdh(),Date.valueOf(Date_depart.getValue()),Date.valueOf(Date_arrivee.getValue()));
        rs.ajouter(r);
        TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Reservation");
            tray.setMessage("You successufuly added a reservation in ur application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }
        
    }
   
    }
    

