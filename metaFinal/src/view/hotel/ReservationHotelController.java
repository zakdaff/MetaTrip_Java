/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import Config.Datasource;
import Config.Smsapi;
import entities.reservation_hotel;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.reservation_hotel.Mailapi;
import services.reservation_hotel.Reserrvation_Hotel_Service;
import services.user.UserService;
import java.text.DecimalFormat;
import javafx.scene.Parent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ReservationHotelController implements Initializable {

    @FXML
    private TableView<reservation_hotel> tab_reservation;
    @FXML
    private Button btn_cofirmation;
    @FXML
    private TableColumn<reservation_hotel, Integer> Idrh;
    @FXML
    private TableColumn<reservation_hotel, Integer> Nb_nuitees;
    @FXML
    private TableColumn<reservation_hotel, Integer> Nb_personnes;
    @FXML
    private TableColumn<reservation_hotel, Double> Prix;
    @FXML
    private TableColumn<reservation_hotel, Integer> Idu;
    @FXML
    private TableColumn<reservation_hotel, Integer> idc;
    @FXML
    private TableColumn<reservation_hotel, Date> Date_depart;
    @FXML
    private TableColumn<reservation_hotel, Date> Date_arrivee;
Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    UserService us=new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReservation();
        // TODO
    }    

    @FXML
    private void ConfirmerReservation(ActionEvent event) {
        user u=us.findByid(tab_reservation.getSelectionModel().getSelectedItem().getIdu());
        //code il mail (badlou)
        Mailapi.send("testapimail63@gmail.com", "TESTapimail2022", u.getEmail(), "Confirme Reservation", "You successfuly confirm your reservation");
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(0);
        
        
        Smsapi.sendSMS("+216"+df.format(u.getTel()), "Test sms api");

    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
     //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
     //********
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/AcceuilFXML.fxml"));
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

    private void AfficherReservation() {
         

        ObservableList<reservation_hotel> list = getReservation();
        Idrh.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Integer>("Idrh"));
        Nb_nuitees.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Integer>("Nb_nuitees"));
       Nb_personnes.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Integer>("Nb_personnes"));
        Prix.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Double>("Prix"));
        Idu.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Integer>("Idu"));
         idc.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Integer>("idh"));
           Date_depart.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Date>("Date_depart"));
           Date_arrivee.setCellValueFactory(new PropertyValueFactory<reservation_hotel, Date>("Date_arrivee"));

        tab_reservation.setItems(list);
    }

    private ObservableList<reservation_hotel> getReservation() {
        
        
        
        
        ObservableList<reservation_hotel> list = FXCollections.observableArrayList();

        con = Datasource.getInstance().getCnx();
        String select = "SELECT * FROM reservation_hotel ;";

        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
               reservation_hotel rh = new reservation_hotel();
                rh.setIdrh(rs.getInt("Idrh"));
                rh.setNb_nuitees(rs.getInt("Nb_nuitees"));
                rh.setNb_personnes(rs.getInt("Nb_personnes"));
                rh.setPrix(rs.getInt("Prix"));
                rh.setIdu(rs.getInt("Idu"));
                rh.setIdh(rs.getInt("idh"));
                rh.setDate_depart(rs.getDate("Date_depart"));
               rh.setDate_arrivee(rs.getDate("Date_arrivee"));
             
                

                list.add(rh);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
       
    
    }

    @FXML
    private void SupprimerReservation(ActionEvent event)throws SQLException {
        
    
        Reserrvation_Hotel_Service rh  = new Reserrvation_Hotel_Service();
         rh.supprimer(tab_reservation.getSelectionModel().getSelectedItem().getIdrh());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ReservationHotel.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherReservation();
           
            AfficherReservation();
            //AfficherTableASC();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      AfficherReservation();
    }
 
    
 

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        
        reservation_hotel rh = tab_reservation.getSelectionModel().getSelectedItem();
        

     

        

       
    }
    
    
}
