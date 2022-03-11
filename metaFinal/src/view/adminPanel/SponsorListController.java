/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.regex.*;
import java.util.*;
//import com.jfoenix.validation.RegexValidator;
//import com.pusiknas.web.util.EmailValidator;
import javafx.collections.transformation.FilteredList;
import entities.evenement;
import java.net.URL;
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
import entities.sponsor;
import entities.user;
import java.io.File;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.evenement.Serviceevenement;
import services.sponsor.Servicesponsor;
import services.voiture.VoitureCRUD;

/**
 * FXML Controller class
 *
 * @author Z4RGA
 */
public class SponsorListController implements Initializable {

    /* Buttons */
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;

    //********* Z4RGA ******************//
    sponsor sponsor;

    @FXML
    private TableView<sponsor> table;

    @FXML
    private TableColumn<sponsor, Integer> sids;
    @FXML
    private TableColumn<sponsor, String> snomsponsor;
    @FXML
    private TableColumn<sponsor, String> stel;

    // Controle saisie sur l'email 
    @FXML
    private TableColumn<sponsor, String> semail;

    @FXML
    private TableColumn<sponsor, Date> sdate_sp;
    @FXML
    private TableColumn<sponsor, Float> sprix_sp;
    @FXML
    private TableColumn<sponsor, String> simage;

    //********* Connexion au BD  ******************//
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;

    //********* Z4RGA ******************//
    Servicesponsor sp;
    @FXML
    private TextField ids;
    @FXML
    private TextField nomsponsor;
    @FXML
    private TextField tel;

    /*  EMAIL AVEC CONTROLE SAISIE */
    @FXML
    private TextField email;

    ///////
    @FXML
    private TextField prix_sp;
    @FXML
    private Label file_path;
    @FXML
    private ImageView image_view;
    @FXML
    private AnchorPane left_main;
    @FXML
    private DatePicker date_sp;
    @FXML
    private ComboBox<?> ide;
    @FXML
    private Label maillabel;
    @FXML
    private Label phonelabel;
    private Label info;
    @FXML
    private TextField filterField;
    @FXML
    private Button insert_image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        affiche();
        combobox2();
          sids.setVisible(false);

        //observalble list to store data
        ObservableList<sponsor> FiltreddataList = FXCollections.observableArrayList();
        ObservableList<sponsor> dataList = getSponsor();

        sids.setCellValueFactory(new PropertyValueFactory<>("ids"));
        snomsponsor.setCellValueFactory(new PropertyValueFactory<>("nomsponsor"));
        stel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        semail.setCellValueFactory(new PropertyValueFactory<>("email"));
        sdate_sp.setCellValueFactory(new PropertyValueFactory<>("date_sp"));
        sprix_sp.setCellValueFactory(new PropertyValueFactory<>("prix_sp"));
        simage.setCellValueFactory(new PropertyValueFactory<>("image"));

        table.setItems(dataList);
        // Wrap the ObservableList in a FilteredList (initially display all data).

        FilteredList<sponsor> filteredData = new FilteredList<>(dataList, b -> true);
// 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sponsor -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (sponsor.getNomsponsor().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (sponsor.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(sponsor.getTel()).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

// 3. Wrap the FilteredList in a SortedList. 
        SortedList<sponsor> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

    }


    /* Configuration des methodes des buttons */
    @FXML
    public void saveEvent(ActionEvent event) {
        insert();

    }

    @FXML
    private void updateEvent(ActionEvent event) {
        update();

        bsave.setDisable(false);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();

    }

    @FXML
    private void clearEvent(ActionEvent event) {
        clear();

    }

    private void clear() {

        ids.setText(null);
        nomsponsor.setText(null);
        tel.setText(null);
        email.setText(null);
        prix_sp.setText(null);
        info.setText(null);
        date_sp.setValue(null);
        ide.getSelectionModel().clearSelection();

        //  bsave.setDisable(false);
    }

 
    
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        

        sponsor s = table.getSelectionModel().getSelectedItem();
        ids.setText(String.valueOf(s.getIdS()));
        nomsponsor.setText(s.getNomsponsor());
        tel.setText(s.getTel());
        email.setText(s.getEmail());
        prix_sp.setText(String.valueOf(s.getPrix_sp()));
        info.setText(s.getEvenement().getChanteur());
        date_sp.setValue(s.getDate_sp().toLocalDate());

        String picture = "file:" + s.getImage();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = s.getImage();

        file_path.setText(path);
        file_path.setOpacity(0);

        // bsave.setDisable(true);
    }

    // Affichage 
    public ObservableList<sponsor> getSponsor() {
        ObservableList<sponsor> list = FXCollections.observableArrayList();

        con = Datasource.getInstance().getCnx();
        String select = "select * from sponsor ;";

        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                sponsor s = new sponsor();

                s.setIdS(rs.getInt("ids"));
                s.setNomsponsor(rs.getString("nomsponsor"));

                s.setTel(rs.getString("tel"));
                s.setEmail(rs.getString("email"));
                s.setDate_sp(rs.getDate("date_sp"));
                s.setPrix_sp(rs.getFloat("prix_sp"));
                s.setImage(rs.getString("image"));

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void affiche() {
        ObservableList<sponsor> list = getSponsor();

        sids.setCellValueFactory(new PropertyValueFactory<sponsor, Integer>("ids"));
        snomsponsor.setCellValueFactory(new PropertyValueFactory<sponsor, String>("nomsponsor"));
        stel.setCellValueFactory(new PropertyValueFactory<sponsor, String>("tel"));
        semail.setCellValueFactory(new PropertyValueFactory<sponsor, String>("email"));

        sdate_sp.setCellValueFactory(new PropertyValueFactory<sponsor, Date>("date_sp"));
        sprix_sp.setCellValueFactory(new PropertyValueFactory<sponsor, Float>("prix_sp"));
        simage.setCellValueFactory(new PropertyValueFactory<sponsor, String>("image"));

        table.setItems(list);

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

    // INSERTION 
    private void insert() {

        con = Datasource.getInstance().getCnx();

        
        // Control de saisie sur les champs vides 
        if (nomsponsor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer le nom de sponsor");

        }
        if (tel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer le nnuméro de telephone");

        }
        if (email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller entrer l'email de sponsor");

        }

        if (date_sp.getValue().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller Enter la date");
        }

        if (file_path.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuiller specifier le path de votre image");

        }
        
        else 
        {
        
        String insert = "INSERT INTO sponsor (`nomsponsor`,`tel`,`email`,`date_sp`,`prix_sp`,`ide`,`image`) VALUES (?,?,?,?,?,?,?) ;";
        try {

            st = con.prepareStatement(insert);
            st.setString(1, nomsponsor.getText());
            st.setString(2, tel.getText());
            st.setString(3, email.getText());
            st.setString(5, prix_sp.getText());
            st.setInt(6, Integer.parseInt(ide.getValue().toString()));

            st.setString(7, file_path.getText());
            file_path.setOpacity(0);

            String str2 = date_sp.getValue().toString();
            Date date1 = Date.valueOf(str2);
            st.setDate(4, date1);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sponsor Added");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("'Sponsor ajouté avec succée");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        }
       

    }

    /* Delete*/
    public void delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE FROM sponsor where ids = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(ids.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleting Sponsor");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" sponsor est supprimé avec succés");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /* UPDATE */
    private void update() {
        con = Datasource.getInstance().getCnx();
        String update = "UPDATE `sponsor` SET "
                + "`nomsponsor`=?,`tel`=?,`email`=?,`date_sp`=?,`prix_sp`=?,`image`=?"
                + "WHERE ids =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, nomsponsor.getText());
            st.setString(2, tel.getText());
            st.setString(3, email.getText());

            st.setString(6, file_path.getText());

            String str2 = date_sp.getValue().toString();
            Date date1 = Date.valueOf(str2);
            st.setDate(4, date1);

            st.setInt(7, Integer.parseInt(ids.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updating sponsor");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("'Sponsor est modifié avec succés");

            alert.showAndWait();

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ValidateEmail(MouseEvent event) {
        String PATTERN = "^(.+)@(\\S+)$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(email.getText());
        if (!match.matches()) {
            maillabel.setText("Email format is incorrect");

        } else {
            maillabel.setText(null);

        }

    }

    @FXML
    public void ValidPhoneNumber(MouseEvent event) {
        String PATTERN = "^\\d{8}$";
        Pattern patt = Pattern.compile(PATTERN);
        Matcher match = patt.matcher(tel.getText());

        if (!match.matches()) {
            phonelabel.setText("Phone format is incorrect");

        } else {
            phonelabel.setText(null);
        }

    }

    @FXML
    private void combobox2() {
        Serviceevenement e = new Serviceevenement();
        List<Integer> list1 = new ArrayList<>();
        for (Integer data : e.gelallID()) {

            list1.add(data);

        }

        ObservableList dataList = FXCollections.observableArrayList(list1);
        ide.setItems(dataList);
    }

}
