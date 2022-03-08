/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import java.io.*;

import javafx.event.EventHandler;
import entities.evenement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.Datasource;
import doryan.windowsnotificationapi.fr.Notification;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import services.evenement.Serviceevenement;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class EventListController implements Initializable {

    public static Stage stg1;

    /* Buttons */
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;

    //********* Z4RGA ******************//
    @FXML
    private TextField Ide;

    @FXML
    private TextField Type_event;

    @FXML
    private TextField Chanteur;

    @FXML
    private TextField Adresse;

    @FXML
    private TextField prix_e;

    //********* Z4RGA ******************//
    evenement evenement;

    @FXML
    private TableView<evenement> table;

    @FXML
    private TableColumn<evenement, Integer> eIde;
    @FXML
    private TableColumn<evenement, String> eType_event;
    @FXML
    private TableColumn<evenement, String> eChanteur;
    @FXML
    private TableColumn<evenement, String> eAdresse;
    @FXML
    private TableColumn<evenement, Date> eDate_event;
    @FXML
    private TableColumn<evenement, Float> eprix_e;
    @FXML
    private TableColumn<evenement, String> eimage;

    //********* Connexion au BD  ******************//
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    Statement stmt = null;

    //****** *** Z4RGA ******************//
    Serviceevenement se;
    @FXML
    private Button insert_image;
    @FXML
    private Label file_path;
    @FXML
    private ImageView image_view;
    @FXML
    private AnchorPane left_main;
    @FXML
    private DatePicker Date_event;
    @FXML
    private Label seee;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//          Serviceevenement see = new Serviceevenement();
//        List personnes = see.afficher();
//        ObservableList list = FXCollections.observableArrayList(personnes);
//        table.setItems(list);
//        
//        eIde.setCellValueFactory(new PropertyValueFactory<>("Ide"));
//        eType_event.setCellValueFactory(new PropertyValueFactory<>("Type_event"));
//        eChanteur.setCellValueFactory(new PropertyValueFactory<>("Chanteur"));
//        eAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
//        eDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
//         eprix_e.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
//        eimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        affiche();

        //observalble list to store data
        ObservableList<evenement> FiltreddataList = FXCollections.observableArrayList();
        ObservableList<evenement> dataList = getEvent();

        eIde.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        eType_event.setCellValueFactory(new PropertyValueFactory<>("Type_event"));
        eChanteur.setCellValueFactory(new PropertyValueFactory<>("Chanteur"));
        eAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        eDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
        eprix_e.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        eimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        table.setItems(dataList);

        /* Search Bar */
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<evenement> filteredData = new FilteredList<>(dataList, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(evenement -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (evenement.getChanteur().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (evenement.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(evenement.getIde()).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

// 3. Wrap the FilteredList in a SortedList. 
        SortedList<evenement> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

    }

    /* INSERTION IMAGE */
    @FXML
    public void insertImage() {

        FileChooser open = new FileChooser();

        Stage stage = (Stage) left_main.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    /* Les methodes des buttons */
    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
        clear();
    }

    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }

    @FXML
    private void clearEvent(ActionEvent event) {
        clear();

    }

    private void clear() {
        Ide.setText(null);
        Type_event.setText(null);
        Chanteur.setText(null);
        Adresse.setText(null);
        Date_event.setValue(null);
        prix_e.setText(null);
        table.refresh();
        //   bsave.setDisable(false);

    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        evenement e = table.getSelectionModel().getSelectedItem();
        Ide.setText(String.valueOf(e.getIde()));
        Type_event.setText(e.getType_event());
        Chanteur.setText(e.getChanteur());
        Adresse.setText(e.getAdresse());
        prix_e.setText(String.valueOf(e.getPrix_e()));

        Date_event.setValue(e.getDate_event().toLocalDate());

        String picture = "file:" + e.getImage();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = e.getImage();

        file_path.setText(path);
        file_path.setOpacity(0);

        // bsave.setDisable(true);
    }

    // Affichage 
    public ObservableList<evenement> getEvent() {
        ObservableList<evenement> list = FXCollections.observableArrayList();

        con = Datasource.getInstance().getCnx();
        String select = "select * from evenement ;";

        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                evenement e = new evenement();

                e.setIde(rs.getInt("Ide"));
                e.setType_event(rs.getString("Type_event"));

                e.setChanteur(rs.getString("Chanteur"));
                e.setAdresse(rs.getString("Adresse"));
                e.setDate_event(rs.getDate("Date_event"));
                e.setPrix_e(rs.getFloat("prix_e"));
                e.setImage(rs.getString("image"));

                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void affiche() {
        ObservableList<evenement> list = getEvent();

        eIde.setCellValueFactory(new PropertyValueFactory<evenement, Integer>("Ide"));
        eType_event.setCellValueFactory(new PropertyValueFactory<evenement, String>("Type_event"));
        eChanteur.setCellValueFactory(new PropertyValueFactory<evenement, String>("Chanteur"));
        eAdresse.setCellValueFactory(new PropertyValueFactory<evenement, String>("Adresse"));

        eDate_event.setCellValueFactory(new PropertyValueFactory<evenement, Date>("Date_event"));
        eprix_e.setCellValueFactory(new PropertyValueFactory<evenement, Float>("prix_e"));
        eimage.setCellValueFactory(new PropertyValueFactory<evenement, String>("image"));

        table.setItems(list);

    }

    // INSERTION 
    private void insert() {

        // Control de saisie sur les champs vides 
        if (Type_event.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer le Type de evenemenet");

        }
        if (Chanteur.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer le Chanteur");

        }
        if (Adresse.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer l'addresse de EVENEMENT");

        }

        if (prix_e.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer le PRIX");

        }

        if (Date_event.getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller Enter la date");
        }

        if (file_path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller specifier le path de votre image");

        }

        con = Datasource.getInstance().getCnx();
        String insert = "INSERT INTO evenement (`Type_event`,`Chanteur`,`Adresse`,`Date_event`,`prix_e`,`image`) VALUES (?,?,?,?,?,?) ;";
        try {

            st = con.prepareStatement(insert);
            st.setString(1, Type_event.getText());
            st.setString(2, Chanteur.getText());
            st.setString(3, Adresse.getText());
            st.setString(5, prix_e.getText());
            st.setString(6, file_path.getText());
            file_path.setOpacity(0);

            String str2 = Date_event.getValue().toString();
            Date date1 = Date.valueOf(str2);
            st.setDate(4, date1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Event Added");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("'Evenement ajouté avec succée");

            alert.showAndWait();
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
            st.executeUpdate();
          //  send_notify();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /* Delete*/
    public void delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE FROM evenement  where Ide = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(Ide.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting event");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" event est supprimé avec succés");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /* UPDATE */
    private void update() {
        con = Datasource.getInstance().getCnx();
        String update = "UPDATE `evenement` SET "
                + "`Type_event`=?,`Chanteur`=?,`Adresse`=?,`Date_event`=?,`prix_e`=?,`image`=?"
                + "WHERE Ide =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, Type_event.getText());
            st.setString(2, Chanteur.getText());
            st.setString(3, Adresse.getText());
            st.setString(5, prix_e.getText());
            st.setString(6, file_path.getText());

            String str2 = Date_event.getValue().toString();
            Date date1 = Date.valueOf(str2);
            st.setDate(4, date1);

            st.setInt(7, Integer.parseInt(Ide.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating event");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("'Event est modifié avec succés");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(EventListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evenement_switch(ActionEvent event) {

        try {
//                   
            Parent parent = FXMLLoader.load(getClass().getResource("SponsorList.fxml"));
            String css = this.getClass().getResource("/css/BACK.css").toExternalForm();
            parent.getStylesheets().add(css);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /*  CLICK TO SAVE TO EXCEL */
    
    @FXML
    private void savetopdf(ActionEvent event) {
        try {
            writeExcel();
        } catch (IOException ex) {
            Logger.getLogger(EventListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeExcel() throws IOException {
        Writer writer = null;

        ObservableList<evenement> data = getEvent();

        eIde.setCellValueFactory(new PropertyValueFactory<>("Ide"));
        eType_event.setCellValueFactory(new PropertyValueFactory<>("Type_event"));
        eChanteur.setCellValueFactory(new PropertyValueFactory<>("Chanteur"));
        eAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        eDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
        eprix_e.setCellValueFactory(new PropertyValueFactory<>("prix_e"));
        eimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        try {
            File file = new File("C:\\Users\\Z4RGA\\OneDrive\\Documents\\Metatrip\\JAWHABEHY\\ZARGA\\MetaTrip_Java\\EventList.csv.");
            writer = new BufferedWriter(new FileWriter(file));

            for (evenement evenement : data) {

                String text = evenement.getIde() + "," + evenement.getType_event() + "," + evenement.getChanteur() + "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Excel");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("'Excel File Created ..");

            alert.showAndWait();
    }



    @FXML
    private void chart_switch(ActionEvent event) {

        try {
//                   
            Parent parent = FXMLLoader.load(getClass().getResource("Chart.fxml"));
            Scene scene = new Scene(parent);
  String css = this.getClass().getResource("../../css/BACK.css").toExternalForm();
      scene.getStylesheets().add(css); 
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
}
