/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.view.adminPanel;

import src.entities.*;
import Config.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author macbook
 */
public class Abonnementcontroller implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField id;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<?> type;
    @FXML
    private Label file_path;
    @FXML
    private TableView<abonnement> table_view;
    @FXML
    private TableColumn<abonnement, Integer> col_id;
    @FXML
    private TableColumn<abonnement, String> col_type;
    @FXML
    private TableColumn<abonnement, String> col_prix;
    @FXML
    private TableColumn<abonnement, Date> col_da1;
    @FXML
    private TableColumn<abonnement, Date> col_da2;
    @FXML
    private TableColumn<abonnement, String> col_etat;
    @FXML
    private TableColumn<abonnement, Integer> col_rp;
    @FXML
    private Button OnClickedStatistique;
    @FXML
    private Button clear;
    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private Button insert;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button print;
    @FXML
    private DatePicker da1;
    @FXML
    private DatePicker da2;

    private String[] comboGender = {"Gold", "Silver", "Bronze"};
    @FXML
    private Button OnClickedStatistique2;

    @FXML
    public void comboBox() {

        List<String> list = new ArrayList<>();

        for (String data : comboGender) {

            list.add(data);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        type.setItems(dataList);

    }

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
  
    public ObservableList<abonnement> dataList() {

        connect = Datasource.getInstance().getCnx();

        ObservableList<abonnement> dataList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM abonnement";

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            abonnement data;

            while (result.next()) {

                data = new abonnement(result.getInt("Ida"), result.getString("Type"),
                        result.getString("Prix_a"), result.getDate("Date_achat"),
                        result.getDate("Date_expiration"), result.getString("Etat"), result.getInt("Ref_paiment"));

                dataList.add(data);

            }

        } catch (Exception e) {
        }

        return dataList;
    }

    public void showData() {
        ObservableList<abonnement> showList = dataList();

        col_id.setCellValueFactory(new PropertyValueFactory<>("Ida"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("Prix_a"));
        col_da1.setCellValueFactory(new PropertyValueFactory<>("Date_achat"));
        col_da2.setCellValueFactory(new PropertyValueFactory<>("Date_expiration"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        col_rp.setCellValueFactory(new PropertyValueFactory<>("Ref_paiment"));

        table_view.setItems(showList);

    }

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign(KeyEvent event) {
    }


    @FXML
    private void selectData(MouseEvent event) {
    }

    @FXML
    private void clear() {
    }

    @FXML
    private void insert(ActionEvent event) {
        connect = Datasource.getInstance().getCnx();
//        I HAVE 5 COLUMNS
        String sql = "INSERT INTO abonnement VALUES (?,?,?,?,?,?,?)";

        try {

            if (id.getText().isEmpty()
                    | type.getSelectionModel().isEmpty()
                    | prix.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            } else {
                String str1 = da1.getValue().toString();
                java.sql.Date date1 = java.sql.Date.valueOf(str1);

                String str2 = da2.getValue().toString();
                java.sql.Date date2 = java.sql.Date.valueOf(str2);

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, id.getText());
                prepare.setString(2, (String) type.getSelectionModel().getSelectedItem());
                prepare.setString(3, prix.getText());
                prepare.setDate(4, date1);
                prepare.setDate(5, date2);

                prepare.setString(6, "Non payè");
                prepare.setInt(7, 1);

                prepare.executeUpdate();

                showData();
                clear();
            }
        } catch (Exception e) {
            System.out.println("exception ghali: "+e);
        }
    }

    @FXML
    private void update(ActionEvent event) {
//         connect = connectDb();
//        
//        String path = file_path.getText();
//        
//        path = path.replace("\\", "\\\\");
//        
//        String sql = "UPDATE account SET  `Type` = '" 
//                + prix.getText() 
//                + "', `Type` = '" 
//                + type.getSelectionModel().getSelectedItem() 
//                + "', `picture` = '" + path 
//                
//                + "' WHERE id = '" + id.getText() + "'";
//        
//        try{
//            
//            if(id.getText().isEmpty() | surname.getText().isEmpty()
//                    | given.getText().isEmpty() 
//                    | gender.getSelectionModel().isEmpty()
//                    | image_view.getImage() == null){
//                
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Enter all blank fields!");
//                alert.showAndWait();
//                
//            }else{
//            
//                statement = connect.createStatement();
//                statement.executeUpdate(sql);
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                alert.setTitle("MarcoMan Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Successfully Update the data!");
//                alert.showAndWait();
//
//                showData();
//                clear();
//                
//            }
//        }catch(Exception e){}
    }

    @FXML
    private void delete(ActionEvent event) {

        String sql = "DELETE from abonnement WHERE `Ida` = '" + id.getText() + "'";

        connect = Datasource.getInstance().getCnx();

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.OK) {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

            } else {

                return;

            }

            showData();
            clear();

        } catch (Exception e) {
        }
    }

    public void selectData() {

        abonnement data = table_view.getSelectionModel().getSelectedItem();

        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        id.setText(String.valueOf(data.getIda()));
        type.getSelectionModel().clearSelection();

        prix.setText(data.getPrix_a());

    }

    @FXML
    private void print(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox();
        showData();
//        stat();

    }

    @FXML
    private void OnClickedStatistique(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("/crud_1/Piechart.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(VoyageVirtuel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnClickedStatistique2(ActionEvent event) {
          try {  Parent root = FXMLLoader.load(getClass().getResource("lineChart.fxml")); //Main.fxml
        //Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); //Main.fxml
        // Parent   root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Voyage virtuel");

        stage.setScene(scene);
        stage.show();} catch (IOException ex) {
            Logger.getLogger(VoyageVirtuel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
