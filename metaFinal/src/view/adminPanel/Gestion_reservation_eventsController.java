/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;
import Config.Datasource;
import entities.evenement;
import entities.reservation_event;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class Gestion_reservation_eventsController implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TableView<reservation_event> table;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<reservation_event, Integer> rIdrev;
    @FXML
    private TableColumn<reservation_event, Integer> rNb_pers;
    @FXML
    private TableColumn<reservation_event, Integer> rIde;
    @FXML
    private TableColumn<reservation_event, Integer> rIdu;


    
     //********* Z4RGA ******************//
    
    
      reservation_event reservation_event; 
      

    //********* Connexion au BD  ******************//
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    Statement stmt = null;

    //****** *** Z4RGA ******************//
    @FXML
    private TextField IdrevF;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         affiche();
         
         
         /* SEARCH BAR */ 
         
         
         //observalble list to store data
        ObservableList<reservation_event> FiltreddataList = FXCollections.observableArrayList();
        ObservableList<reservation_event> dataList = getReserver_events();

        rIdrev.setCellValueFactory(new PropertyValueFactory<>("Idrev"));
        rNb_pers.setCellValueFactory(new PropertyValueFactory<>("Nb_pers"));
        rIde.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        rIdu.setCellValueFactory(new PropertyValueFactory<>("Idu"));

        table.setItems(dataList);

        /* Search Bar */
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<reservation_event> filteredData = new FilteredList<>(dataList, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reservation_event -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(reservation_event.getIdrev()).contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(reservation_event.getIdu()).contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(reservation_event.getIde()).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

// 3. Wrap the FilteredList in a SortedList. 
        SortedList<reservation_event> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
         
    }    

    /* confirm reservation sent notif to desktop */ 
    
    @FXML
    private void updateEvent(ActionEvent event) {
        send_notify();
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        
           delete();
        
        
    }

    @FXML
    private void ValidateEmail(MouseEvent event) {
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
    }

    
    /*  REFRESH */
    
    @FXML
    private void clearEvent(ActionEvent event) {
        table.refresh();
    }

    
    // Affichage 
    public ObservableList<reservation_event> getReserver_events() {
        ObservableList<reservation_event> list = FXCollections.observableArrayList();

        con = Datasource.getInstance().getCnx();
        String select = "select * from reservation_event ;";

        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                reservation_event re = new reservation_event();

                re.setIdrev(rs.getInt("Idrev"));
                re.setNb_pers(rs.getInt("Nb_pers"));

                re.setIde(rs.getInt("Ide"));
                re.setIdu(rs.getInt("Idu"));
       

                list.add(re);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_reservation_eventsController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void affiche() {
        ObservableList<reservation_event> list = getReserver_events();

        rIdrev.setCellValueFactory(new PropertyValueFactory<reservation_event, Integer>("Idrev"));
        rNb_pers.setCellValueFactory(new PropertyValueFactory<reservation_event, Integer>("Nb_pers"));
        rIde.setCellValueFactory(new PropertyValueFactory<reservation_event, Integer>("Ide"));
        rIdu.setCellValueFactory(new PropertyValueFactory<reservation_event, Integer>("Idu"));

        table.setItems(list);

    }
    
     /* Delete*/
    public void delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE FROM reservation_event where Idrev = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(IdrevF.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting Reservation");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" EVENT Reservation deleted ");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(Gestion_reservation_eventsController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    
     
        /* Sent TRAY Notification */
    void send_notify() {
        Notifications notificationBuilder = Notifications.create()
                .title("Alert")
                .text("[NEWS] Your Event Reservation Has been Accepted By ADMIN ")
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
