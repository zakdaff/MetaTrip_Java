/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import javafx.scene.image.Image;
import Config.Datasource;
import entities.Chambre;
import entities.hotel;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.chambre.Chambre_service;
import services.hotel.HotelCRUD;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ChambreListController implements Initializable {

    @FXML
    private TextField numc;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    private TextField image;
    @FXML
    private TableView<Chambre> tab_chambre;
    @FXML
    private TableColumn<Chambre, Integer> idc_tab;
    @FXML
    private TableColumn<Chambre, Integer> numc_tab;
    @FXML
    private TableColumn<Chambre, String> type_tab;
    @FXML
    private TableColumn<Chambre, String> etat_tab;
    @FXML
    private TableColumn<Chambre, String> image_tab;
    @FXML
    private TableColumn<Chambre, Integer> idh_tab;
    @FXML
    private ComboBox<String> etat;

    ObservableList<String> etatt = FXCollections.observableArrayList("Disponible", "Non Disponible");
    ObservableList<String> typee = FXCollections.observableArrayList("Single", "Double");
    ObservableList<Chambre> list = FXCollections.observableArrayList();

    ResultSet rs = null;
    private Connection con = null;
    @FXML
    private AnchorPane left_main;
    @FXML
    private Label file_path;
    @FXML
    private ImageView image_view;
    PreparedStatement st = null;
    @FXML
    private ComboBox<String> nomhotel;
    private Connection conn = Datasource.getInstance().getCnx();
    ;
    private Statement ste;
    private PreparedStatement pste;
    Chambre_service cs = new Chambre_service();
    @FXML
    private TextField tfrecherche;
    @FXML
    private TableColumn<Chambre, Float> prix_tab;
    @FXML
    private TextField prixc;
    HotelCRUD hc=new HotelCRUD();

    public boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int x = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etat.setPromptText("Selectioner l'etat du chambre");
        etat.setItems(etatt);
        type.setPromptText("Selectioner le type");
        type.setItems(typee);
        AfficherChambre();
        ResultSet rs;
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery("Select Nom_hotel from hotel");
            while (rs.next()) {  // loop

                nomhotel.getItems().addAll(rs.getString("Nom_hotel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfaceReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        recherche_avance();
        // TODO
    }
     
    @FXML
    private void AjouterChambre(ActionEvent event) {
        InsertHotel();
        AfficherChambre();
    }

    @FXML
    private void DeleteChambre(ActionEvent event) {
        Delete();

        AfficherChambre();
    }

    @FXML
    private void EditChambre(ActionEvent event) {
        String erreurs = "";
        if (numc.getText().trim().isEmpty()) {
            erreurs += "- Please enter a number\n";
        }
        if (type.getValue() == null) {
            erreurs += "- Please enter a Type\n";
        }
        if (etat.getValue() == null) {
            erreurs += "- Please enter a state\n";
        }
        if (nomhotel.getValue() == null) {
            erreurs += "- Please enter a hostel\n";
        }
        if (!isNumeric(numc.getText().trim())) {
            erreurs += "- Please enter a valide number\n";
        }
        if (prixc.getText().trim().isEmpty()) {
            erreurs += "- Please enter a number\n";
        }
        if (erreurs.length() > 0) {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Reservation");
            tray.setMessage("Fail");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        } else {
              hotel h=hc.findByName(nomhotel.getValue());
            Chambre ch = new Chambre(Integer.parseInt(numc.getText()), type.getValue(), etat.getValue(),h.getIdh(), file_path.getText());
            cs.modifier(tab_chambre.getSelectionModel().getSelectedItem().getIdc(), ch);

            AfficherChambre();
            clear();
        }

    }
     

    private void AfficherChambre() {
        list.clear();
        list = getChambre();
        idc_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("idc"));
        numc_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("numc"));
        image_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("image_chambre"));
        type_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("type"));
        etat_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("etat_dispo"));
        idh_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("idh"));
        prix_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Float>("prixc"));
        tab_chambre.setItems(list);
    }

    private ObservableList<Chambre> getChambre() {
        ObservableList<Chambre> list = FXCollections.observableArrayList();

        Connection con = Datasource.getInstance().getCnx();
        String select = "SELECT * FROM chambre";

        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chambre ch = new Chambre();
                ch.setIdc(rs.getInt("idc"));
                ch.setNumc(rs.getInt("numc"));
                ch.setImage_chambre(rs.getString("image"));
                ch.setType(rs.getString("type"));
                ch.setEtat_dispo(rs.getString("etat"));
                ch.setIdh(rs.getInt("idh"));
                ch.setPrixc(rs.getFloat("prixc"));

                list.add(ch);
            }
        } catch (SQLException ex) {
            System.err.println("ereeur offfff" + ex);

        }
        return list;
    }

    private void InsertHotel() {
        String erreurs = "";
        if (numc.getText().trim().isEmpty()) {
            erreurs += "- Please enter a number";
        }
        if (type.getValue() == null) {
            erreurs += "- Please enter a Type";
        }
        if (etat.getValue() == null) {
            erreurs += "- Please enter a state";
        }
        if (nomhotel.getValue() == null) {
            erreurs += "- Please enter a hostel";
        }
        if (erreurs.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add fail");
            alert.setContentText(erreurs);
            alert.showAndWait();
        } else {
            con = Datasource.getInstance().getCnx();
            String insert = "INSERT INTO chambre (`numc`,`image`,`type`,`etat`,`Idh`,`prixc`) VALUES (?,?,?,?,?,?) ;";
            try {
                 hotel h=hc.findByName(nomhotel.getValue());

                PreparedStatement st = con.prepareStatement(insert);

                st.setString(1, numc.getText());
                st.setString(2, file_path.getText());
                file_path.setOpacity(0);

                st.setString(3, type.getSelectionModel().getSelectedItem());
                st.setString(4, etat.getSelectionModel().getSelectedItem());
                st.setInt(5,h.getIdh());
                st.setString(6,prixc.getText());

                st.executeUpdate();
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Add Reservation");
                tray.setMessage("fail");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            //Notification
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("You successufuly added a chambre");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        }

    }

    private void Delete() {
        con = Datasource.getInstance().getCnx();
        String delete = "DELETE  FROM chambre  where idc = ?";
        try {
            PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1, tab_chambre.getSelectionModel().getSelectedItem().getIdc());

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Deleting chambre");
//
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText(" chambre " + numc.getText() + " avec ID" + tab_chambre.getSelectionModel().getSelectedItem().getIdc() + " est supprimé avec succés");

            alert.showAndWait();
//            

            st.executeUpdate();
            AfficherChambre();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete room");
            tray.setMessage("You successufuly deleted room in ur application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        if (tab_chambre.getSelectionModel().getSelectedItem() != null) {

            Chambre ch = tab_chambre.getSelectionModel().getSelectedItem();
            hotel h=hc.findByName(nomhotel.getValue());
            numc.setText(String.valueOf(ch.getNumc()));
            type.getSelectionModel().select(ch.getType());
            etat.getSelectionModel().select(ch.getEtat_dispo());
            nomhotel.setValue(h.getNom_hotel());
          prixc.setText(String.valueOf(ch.getPrixc()));
            //btn_save.setDisable(true);
            String picture = "file:" + ch.getImage_chambre();
            Image image = new Image(picture, 110, 110, false, true);

            image_view.setImage(image);

            String path = ch.getImage_chambre();

            file_path.setText(path);
            file_path.setOpacity(0);
        }

    }

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

    public void Update() {

        con = Datasource.getInstance().getCnx();

        String path = file_path.getText();

        path = path.replace("\\", "\\\\");

        String sql = "UPDATE chambre SET `numc` = '"
                + numc.getText() + "', `image` = '"
                + image_view + "', `type` = '"
                + type.getSelectionModel().getSelectedItem()
                + "', `etat` = '"
                + etat.getSelectionModel().getSelectedItem()
                +"', `prixc` = '"
                + prixc.getText()
                + "' WHERE idc = '" + tab_chambre.getSelectionModel().getSelectedItem().getIdc() + "'";

        try {

            if (numc.getText().isEmpty() | prixc.getText().isEmpty()
                    | type.getSelectionModel().isEmpty()
                    | etat.getSelectionModel().isEmpty()
                    | image_view.getImage() == null) {

                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Add Reservation");
                tray.setMessage("failed");
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(1000));

            } else {

                System.out.println("updaaaaaaaaaaaaaate");
                st.executeUpdate(sql);
                System.out.println("updaaaaaaaaaaaaaate");
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Add Reservation");
                tray.setMessage("You successufuly update chambre");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));

                AfficherChambre();

            }
        } catch (Exception e) {
        }

    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
            //********
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

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

    public void recherche_avance() {
        FilteredList<Chambre> filteredlist = new FilteredList<>(list, b -> true);
        tfrecherche.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filteredlist.setPredicate(chambre -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowercasenewvalue = newValue.toLowerCase();
                        if (chambre.getEtat_dispo().toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                        } else if (chambre.getType().toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                        } else if (String.valueOf(chambre.getIdc()).toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                        } else if (String.valueOf(chambre.getIdh()).toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                        } else if (String.valueOf(chambre.getNumc()).toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                         } else if (String.valueOf(chambre.getPrixc()).toLowerCase().indexOf(lowercasenewvalue) != -1) {
                            return true;
                        } else {
                            return false;
                        }

                    });
                }
        );
        tab_chambre.setItems(filteredlist);
    }

    private void clear() {
         
        
       numc.setText("");
        type.getSelectionModel().clearSelection();
        etat.getSelectionModel().clearSelection();
       nomhotel.getSelectionModel().clearSelection();
        image_view.setImage(null);


        //sexe.getSelectionModel().selectFirst();
       
    }
    
    }


