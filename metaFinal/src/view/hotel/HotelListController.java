/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import Config.Datasource;
import entities.hotel;
import entities.user;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class HotelListController implements Initializable {

    @FXML
    private TableView<hotel> tab_hotel;
    @FXML
    private TableColumn<hotel, Integer> id_hotel_tab;
    @FXML
    private TableColumn<hotel, String> nom_tab;
    @FXML
    private TableColumn<hotel, Integer> nb_etoile_tab;
    @FXML
    private TableColumn<hotel, String> adre_tab;
    @FXML
    private TextField Nom_hotel;
    @FXML
    private TextField Nb_etoiles;
    @FXML
    private TextField Adresse;
    @FXML
    private TextField Idh;
    private Button bsave;
    @FXML
    private TableColumn<hotel, String> image_tab;
    @FXML
    private Button delete;
    @FXML
    private Button bsave11;
    private TextField image;
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement st = null;
    @FXML
    private AnchorPane left_main;
    @FXML
    private Button bsave1;
    @FXML
    private Button insert_image;
    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        AffichageHotel();

    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {

        hotel h = tab_hotel.getSelectionModel().getSelectedItem();
        Idh.setText(String.valueOf(h.getIdh()));
        Nom_hotel.setText(h.getNom_hotel());
        Nb_etoiles.setText(String.valueOf(h.getNb_etoiles()));
        Adresse.setText(h.getAdresse());
       String picture ="file:" + h.getImage_hotel();
         Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = h.getImage_hotel();

        file_path.setText(path);
        file_path.setOpacity(0);

        bsave.setDisable(true);
    }

    private void delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE  FROM hotel  where idh = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(Idh.getText()));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Deleting user");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" hotel " + Nom_hotel.getText() + " avec ID" + Idh.getText() + " est supprimé avec succés");

            alert.showAndWait();

            st.executeUpdate();
            AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void DeleteHotel(ActionEvent event) {
        System.out.println(event.toString());
        delete();
       
    }

    @FXML
    private void EditHotel(ActionEvent event) {
        update();
        clear();
    }

    @FXML
    private void SaveHotel(ActionEvent event) {
        insert();
        clear();
      AffichageHotel();
    }

    private void insert() {
        con = Datasource.getInstance().getCnx();
        String insert = "INSERT INTO hotel (`Idh`,`Nom_hotel`,`Nb_etoiles`,`Adresse`,`image`) VALUES (?,?,?,?,?) ;";
        try {

            st = con.prepareStatement(insert);
            st.setString(1, Idh.getText());
            st.setString(2, Nom_hotel.getText());
            st.setString(3, Nb_etoiles.getText());
            st.setString(4, Adresse.getText());
            st.setString(5, file_path.getText());
            file_path.setOpacity(0);

//           Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Deleting user");
//		 Header Text: null
//		alert.setHeaderText(null);
//		alert.setContentText("'utilisateur " +nom.getText()+"   "+prenom.getText()+" est ajouté avec succés");
//
//		alert.showAndWait();
//           
//            st.executeUpdate();
//            Affiffiche();
         st.executeUpdate();
           AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void clear() {
        Idh.setText(null);
        Nom_hotel.setText(null);
        Nb_etoiles.setText(null);
        Adresse.setText(null);
       
        image_view.setImage(null);

        //sexe.getSelectionModel().selectFirst();
       
    }

    public ObservableList<hotel> getHotel() {
        ObservableList<hotel> list = FXCollections.observableArrayList();

        con = Datasource.getInstance().getCnx();
        String select = "SELECT * FROM hotel ;";

        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                hotel h = new hotel();
                h.setIdh(rs.getInt("Idh"));
                h.setNom_hotel(rs.getString("Nom_hotel"));
                h.setNb_etoiles(rs.getInt("Nb_etoiles"));
                h.setAdresse(rs.getString("Adresse"));
                h.setImage_hotel(rs.getString("image"));
//                

                list.add(h);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    private void AffichageHotel() {

        ObservableList<hotel> list = getHotel();
        id_hotel_tab.setCellValueFactory(new PropertyValueFactory<hotel, Integer>("Idh"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("Nom_hotel"));
        nb_etoile_tab.setCellValueFactory(new PropertyValueFactory<hotel, Integer>("Nb_etoiles"));
        adre_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("Adresse"));
        image_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("image"));

        tab_hotel.setItems(list);
    }

    public void update() {

        con = Datasource.getInstance().getCnx();

        String path = file_path.getText();

        path = path.replace("\\", "\\\\");

        String sql = "UPDATE hotel SET `Nom_hotel` = '"
                + Nom_hotel.getText() + "', `Nb_etoiles` = '"
                + Nb_etoiles.getText() + "', `Adresse` = '"
                + Adresse.getText()
                + "', `image` = '" + image_view
                + "' WHERE Idh = '" + Idh.getText() + "'";

        try {

            if (Idh.getText().isEmpty() | Nom_hotel.getText().isEmpty()
                    | Nb_etoiles.getText().isEmpty()
                    | Adresse.getText().isEmpty()
                    | image_view.getImage() == null) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            } else {

                st.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();

                AffichageHotel();
                clear();

            }
        } catch (Exception e) {
        }

    }

// private void update1() {
//	con =Datasource.getInstance().getCnx();
//            String update = "UPDATE `hotel` SET "
//                +"`Nom_hotel`=?,`Nb_etoiles`=? ,`Adresse`=?,`image`=?"
//                + "WHERE `Idh` =? ;";
//        try {
//                st = con.prepareStatement(update);
//                st.setString(1, Idh.getText());
//                st.setString(2, Nb_etoiles.getText());
//                st.setString(3, Adresse.getText());
//        
//                st.setString(4, file_path.getText());
//             
//              
//            
//            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
//    Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Updating hotel");
//
//		// Header Text: null
//		alert.setHeaderText(null);
//	alert.setContentText(" Hotel " +Nom_hotel.getText()+" est modifié avec succés");
//		alert.showAndWait();
//                
//                st.executeUpdate();
//                AffichageHotel();
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
    @FXML
    private void insert_image(ActionEvent event) {
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

}
