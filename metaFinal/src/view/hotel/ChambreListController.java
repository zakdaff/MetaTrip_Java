/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;
import javafx.scene.image.Image;
import Config.Datasource;
import entities.Chambre;

import java.io.File;
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
    
   ObservableList<String> etatt=FXCollections.observableArrayList("Disponible", "Non Disponible");
   ObservableList<String> typee=FXCollections.observableArrayList("Single", "Double");
    
    ResultSet rs = null;
    private Connection con = null;
    @FXML
    private AnchorPane left_main;
    @FXML
    private Label file_path;
    @FXML
    private ImageView image_view;
    PreparedStatement st=null;
   
    /**
     * Initializes the controller class.
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
        // TODO
    }    

    @FXML
    private void AjouterChambre(ActionEvent event) {
        InsertHotel();
    }

    @FXML
    private void DeleteChambre(ActionEvent event) {
        Delete();
        clear();
    }

    @FXML
    private void EditChambre(ActionEvent event) {
        Update();
         clear();
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

    private void InsertHotel() {
        con = Datasource.getInstance().getCnx();
       String insert = "INSERT INTO chambre (`idc`,`numc`,`image`,`type`,`etat`,`Idh`) VALUES (?,?,?,?,?,?) ;";
        try {
            
            PreparedStatement st = con.prepareStatement(insert);
            
          
          st.setString(1,idc.getText());
            st.setString(2, numc.getText());
             st.setString(3, file_path.getText());
            file_path.setOpacity(0);
            
            st.setString(4, type.getSelectionModel().getSelectedItem());
            st.setString(5, etat.getSelectionModel().getSelectedItem());
           
          
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void Delete() {
         con =Datasource.getInstance().getCnx();
        String delete = "DELETE  FROM chambre  where idc = ?";
        try {
             PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idc.getText()));
            
            
            	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deleting chambre");
//
		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(" chambre " +numc.getText()+" avec ID"+idc .getText()+" est supprimé avec succés");

		alert.showAndWait();
//            
            
            
            st.executeUpdate();
            AfficherChambre();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void clear() {
       idc.setText(null);
        numc.setText(null);
   image.setText(null);
    image_view.setImage(null);
      
        
      
        //sexe.getSelectionModel().selectFirst();
        btn_save.setDisable(false);
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
         Chambre ch = tab_chambre.getSelectionModel().getSelectedItem();
        idc.setText(String.valueOf(ch.getIdc()));
        numc.setText(String.valueOf(ch.getNumc()));
        type.getSelectionModel().select(ch.getType());
        etat.getSelectionModel().select(ch.getEtat_dispo());    
       btn_save.setDisable(true);
       String picture ="file:" + ch.getImage_chambre();
         Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = ch.getImage_chambre();

        file_path.setText(path);
        file_path.setOpacity(0);

    }
    @FXML
     public void insertImage(){

        FileChooser open = new FileChooser();

        Stage stage = (Stage)left_main.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if(file != null){

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

           file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            image_view.setImage(image);

        }else{

            System.out.println("NO DATA EXIST!");

        }
    }

    
        public void Update(){
        
       con =Datasource.getInstance().getCnx();
        
        String path = file_path.getText();
        
        path = path.replace("\\", "\\\\");
        
        String sql = "UPDATE chambre SET `numc` = '" 
                +numc.getText() + "', `image` = '" 
                + image_view + "', `type` = '" 
                + type.getSelectionModel().getSelectedItem() 
                + "', `etat` = '" 
                + etat.getSelectionModel().getSelectedItem() 
               
               
                + "' WHERE idc = '" + idc.getText() + "'";
        
        try{
            
            if(idc.getText().isEmpty() | numc.getText().isEmpty()
                     
                 | type.getSelectionModel().isEmpty()
                | etat.getSelectionModel().isEmpty()
                     | image_view.getImage() == null)
                       
                 {
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
                
            }else{
            
             
                st.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();

               AfficherChambre();
                clear();
                
            }
        }catch(Exception e){}
        
    }
    

    
}
