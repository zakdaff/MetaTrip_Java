/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;


import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Config.Datasource;
import entities.hotel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
import javafx.stage.StageStyle;



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
    @FXML
    private TextField tfrecherche;
    ObservableList<hotel> list = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        AffichageHotel();
        recherche_avance();

    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
if(tab_hotel.getSelectionModel().getSelectedItem()!=null){
    hotel h = tab_hotel.getSelectionModel().getSelectedItem();
       
        Nom_hotel.setText(h.getNom_hotel());
        Nb_etoiles.setText(String.valueOf(h.getNb_etoiles()));
        Adresse.setText(h.getAdresse());
       String picture ="file:" + h.getImage_hotel();
         Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = h.getImage_hotel();

        file_path.setText(path);
        file_path.setOpacity(0);
}
        

        //bsave.setDisable(true);
    }

    private void delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE  FROM hotel  where idh = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1,tab_hotel.getSelectionModel().getSelectedItem().getIdh());

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Deleting user");

            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" hotel " + Nom_hotel.getText() + " avec ID" + tab_hotel.getSelectionModel().getSelectedItem().getIdh() + " est supprimé avec succés");

            alert.showAndWait();

            st.executeUpdate();
            //AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void DeleteHotel(ActionEvent event) {
        System.out.println(event.toString());
        delete();
        clear();
       AffichageHotel();
        recherche_avance();
       
    }

    @FXML
    private void EditHotel(ActionEvent event) {
        update();
        clear();
        AffichageHotel();
        recherche_avance();
    }

    @FXML
    private void SaveHotel(ActionEvent event) {
        insert();
        clear();
      AffichageHotel();
        recherche_avance();
    }

    private void insert() {
        con = Datasource.getInstance().getCnx();
        String insert = "INSERT INTO hotel (`Nom_hotel`,`Nb_etoiles`,`Adresse`,`image`) VALUES (?,?,?,?) ;";
        try {

            st = con.prepareStatement(insert);
            
            st.setString(1, Nom_hotel.getText());
            st.setString(2, Nb_etoiles.getText());
            st.setString(3, Adresse.getText());
            st.setString(4, file_path.getText());
            file_path.setOpacity(0);
            
                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully !");
                alert.showAndWait();
//
////           Alert alert = new Alert(AlertType.INFORMATION);
////		alert.setTitle("Deleting user");
////		 Header Text: null
////		alert.setHeaderText(null);
////		alert.setContentText("'utilisateur " +nom.getText()+"   "+prenom.getText()+" est ajouté avec succés");
////
////		alert.showAndWait();
           
//            st.executeUpdate();
//            Affiffiche();
         st.executeUpdate();
           //AffichageHotel();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    void clear() {
        
        Nom_hotel.setText(null);
        Nb_etoiles.setText(null);
        Adresse.setText(null);
       
        image_view.setImage(null);

        //sexe.getSelectionModel().selectFirst();
       
    }

    public ObservableList<hotel> getHotel() {
       
ObservableList<hotel> resultat=FXCollections.observableArrayList();
        con = Datasource.getInstance().getCnx();
        String select = "SELECT * FROM hotel ";

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

                resultat.add(h);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }

    private void AffichageHotel() {
        list.clear();
        System.out.println(getHotel());
        list = getHotel();
        id_hotel_tab.setCellValueFactory(new PropertyValueFactory<hotel, Integer>("Idh"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("Nom_hotel"));
        nb_etoile_tab.setCellValueFactory(new PropertyValueFactory<hotel, Integer>("Nb_etoiles"));
        adre_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("Adresse"));
        image_tab.setCellValueFactory(new PropertyValueFactory<hotel, String>("image_hotel"));

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
                + "', `image` = '" + file_path.getText()
                + "' WHERE Idh = '" + tab_hotel.getSelectionModel().getSelectedItem().getIdh() + "'";

        try {

            if (Nom_hotel.getText().isEmpty()
                    | Nb_etoiles.getText().isEmpty()
                    | Adresse.getText().isEmpty()
                    | image_view.getImage() == null) {

//                Alert alert = new Alert(AlertType.ERROR);
//
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Enter all blank fields!");
//                alert.showAndWait();
 TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("You successufuly added room in ur application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

            } else {

                st.executeUpdate(sql);
                
//                Alert alert = new Alert(AlertType.INFORMATION);
//
//                alert.setTitle("MarcoMan Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Successfully Update the data!");
//                alert.showAndWait();

 TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("You successufuly added room in ur application");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(1000));

                //AffichageHotel();
                clear();

            }
        } catch (Exception e) {
        }

    }


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
    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }
    public void recherche_avance(){
        FilteredList<hotel> filteredlist=new FilteredList<>(list,b->true);
        tfrecherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filteredlist.setPredicate(hotel->{
                        if(newValue==null || newValue.isEmpty()){
                            return true;
                        }
                        String lowercasenewvalue=newValue.toLowerCase();
                        if(hotel.getAdresse().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                        else if(hotel.getNom_hotel().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }

                        else if(hotel.getImage_hotel().toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }

                      
                        
                        else if(String.valueOf(hotel.getNb_etoiles()).toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                        
                       else if(String.valueOf(hotel.getIdh()).toLowerCase().indexOf(lowercasenewvalue)!=-1){
                            return true;
                        }
                        
                        
                        else{
                            return false;
                        }
                        
                    });
                }
        );
        tab_hotel.setItems(filteredlist);
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
     //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
     //********
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/InterfaceGestion.fxml"));
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

}
