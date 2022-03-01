/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import Config.Datasource;
import entities.Chambre;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class ChambreListController implements Initializable {

    @FXML
    private TextField idc;
    @FXML
    private TextField numc;
    @FXML
    private TextField type;
    @FXML
    private Button bsave11;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_edit;
    @FXML
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
    private Connection conn = null;
    java.sql.Statement ste = null;
    PreparedStatement pste = null;
    PreparedStatement pste2 = null;
    ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherChambre();
        // TODO
    }    

    @FXML
    private void AjouterChambre(ActionEvent event) {
    }

    @FXML
    private void DeleteChambre(ActionEvent event) {
    }

    @FXML
    private void EditChambre(ActionEvent event) {
    }

    private void AfficherChambre() {
        ObservableList<Chambre> list = getChambre();
        idc_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("idc"));
        numc_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("numc"));
        image_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("image"));
        type_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("type"));
        etat_tab.setCellValueFactory(new PropertyValueFactory<Chambre, String>("etat"));
        idh_tab.setCellValueFactory(new PropertyValueFactory<Chambre, Integer>("idh"));
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

                list.add(ch);
            }
        } catch (SQLException ex) {
            System.err.println("ereeur offfff" +ex);

        }
        return list;
    }
    
}
