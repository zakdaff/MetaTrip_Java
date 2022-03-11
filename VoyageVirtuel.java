package src.view.adminPanel;

//import controllerZ.AjouterActiviteController;
import Config.Datasource;
import src.entities.voyage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import src.entities.Voyage_virtuel;
import src.entities.abonnement;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

public class VoyageVirtuel implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField idv;

    @FXML
    private TextField ida;
    @FXML
    private TextField nom;

    @FXML
    private ComboBox<?> gender;
    @FXML
    private ComboBox<Integer> ab;
    @FXML
    private ComboBox<Integer> vo;
    @FXML
    private Label file_path;

    @FXML
    private Button insert;

    @FXML
    private Button update;

    @FXML
    private Button clear;

    @FXML
    private Button delete;

    @FXML
    private Button print;
    @FXML
    private Button back;

    @FXML
    private ImageView image_view;

    @FXML
    private Button insert_image;
    @FXML
    private Button OnClickedStatistique;

    @FXML
    private TableView<Voyage_virtuel> table_view;

    @FXML
    private TableColumn<Voyage_virtuel, Integer> col_id;

    @FXML
    private TableColumn<Voyage_virtuel, Integer> col_idv;

    @FXML
    private TableColumn<Voyage_virtuel, Integer> col_ida;

    @FXML
    private TableColumn<Voyage_virtuel, String> col_gender;

    @FXML
    private TableColumn<Voyage_virtuel, String> col_picture;
    @FXML
    private TableColumn<Voyage_virtuel, String> col_nom;

    @FXML
    private AnchorPane left_main;

    private String[] comboGender = {"Streaming", "Video"};

    public void comboBox() {

        List<String> list = new ArrayList<>();

        for (String data : comboGender) {

            list.add(data);

        }

        ObservableList dataList = FXCollections.observableArrayList(list);

        gender.setItems(dataList);

    }
    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;
    private Statement statement;
    private ResultSet result;
    private ResultSet result2;

    @FXML
    private void back(ActionEvent event) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource("../PartieClient/market.fxml"));
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

    public void comboBoxab() {

        connect = Datasource.getInstance().getCnx();

        ObservableList<Integer> dataList = FXCollections.observableArrayList();

        String sql = "SELECT Ida FROM `abonnement`;";

        try {
            prepare = connect.prepareStatement(sql);

            result = prepare.executeQuery();

            while (result.next()) {
                abonnement data;

                result.getInt("Ida");

                dataList.add(result.getInt("Ida"));

            }

        } catch (Exception e) {
            System.out.println("execptionn fel clee" + e);
        }

        ab.setItems(dataList);
        //   System.out.println("" + dataList);

    }

    public void comboBoxvo() {

        connect = Datasource.getInstance().getCnx();

        ObservableList<Integer> dataList = FXCollections.observableArrayList();

        String sql = "SELECT  Idv FROM `voyage`;";

        try {
            prepare = connect.prepareStatement(sql);

            result = prepare.executeQuery();

            while (result.next()) {
                voyage data;

                result.getInt("Idv");

                dataList.add(result.getInt("Idv"));

            }

        } catch (Exception e) {
            System.out.println("execptionn fel clee" + e);
        }

        vo.setItems(dataList);
        System.out.println("" + dataList);

    }

    public ObservableList<Voyage_virtuel> dataList() {

        connect = Datasource.getInstance().getCnx();

        ObservableList<Voyage_virtuel> dataList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM voyage_virtuel";

        try {
            prepare = connect.prepareStatement(sql);

            result = prepare.executeQuery();

            Voyage_virtuel data;

            while (result.next()) {

                data = new Voyage_virtuel(result.getInt("Idvv"),
                        result.getString("Image_v"),
                        result.getInt("Idv"),
                        result.getInt("Ida"),
                        result.getString("Video"),
                        result.getString("Nom")
                );

                dataList.add(data);

            }

        } catch (Exception e) {
            System.out.println("execptionn fel clee" + e);
        }

        return dataList;

    }

    public void showData() {
        ObservableList<Voyage_virtuel> showList = dataList();

        col_id.setCellValueFactory(new PropertyValueFactory<>("Idvv"));
        col_idv.setCellValueFactory(new PropertyValueFactory<>("Idv"));
        col_ida.setCellValueFactory(new PropertyValueFactory<>("Ida"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("Video"));
        col_picture.setCellValueFactory(new PropertyValueFactory<>("Image_v"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));

        table_view.setItems(showList);

    }

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

    public void insert() {

        try {

            int i = 0;
            connect = Datasource.getInstance().getCnx();
            Boolean test = false;

            String sql = "INSERT INTO voyage_virtuel VALUES (?,?,?,?,?,?)";
            if (vo.getSelectionModel().isEmpty()
                    | ab.getSelectionModel().isEmpty()
                    | gender.getSelectionModel().isEmpty()
                    | nom.getText().isEmpty()) {

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, i);
                prepare.setString(2, file_path.getText());
                prepare.setInt(3, vo.getSelectionModel().getSelectedItem());
                prepare.setInt(4, ab.getSelectionModel().getSelectedItem());
                prepare.setString(5, (String) gender.getSelectionModel().getSelectedItem());
                prepare.setString(6, nom.getText());
                prepare.executeUpdate();

                test = true;

                showData();
                clear();

                if (test) {
                    i++;
                    System.out.println("normalment tajoutit maalem");
                    System.out.println("i wallet " + i);

                    // Faites quelque chose ici si la condition est vraie.
                }
                test = false;
                System.out.println("i wallet " + i);

            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }

    public void update() {

        connect = Datasource.getInstance().getCnx();

        String path = file_path.getText();

        path = path.replace("\\", "\\\\");

        String sql = "UPDATE voyage_virtuel SET `Idvv` = '"
                + id.getText()
                + "', `Idv` = '"
                + vo.getSelectionModel().getSelectedItem()
                + "', `Ida` = '"
                + ab.getSelectionModel().getSelectedItem()
                + "', `Video` = '"
                + gender.getSelectionModel().getSelectedItem()
                + "', `Image_v` = '" + path
                + "', `Nom` = '"
                + nom.getText()
                + "' WHERE Idvv = '" + id.getText() + "'";
        try {

            if (image_view.getImage() == null) {

                Alert alert = new Alert(AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();

            } else {

                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();

                showData();
                clear();

            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public void delete() {

        String sql = "DELETE from voyage_virtuel WHERE `Idvv` = '" + id.getText() + "'";

        connect = Datasource.getInstance().getCnx();

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);

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

    public void print() {
//        
//        connect = connectDb();
//        
//        try{
//            JasperDesign jDesign = JRXmlLoader.load("F:\\ajava\\6 NUMBER\\CRUD\\src\\crud\\report.jrxml");
//        
//            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
//            
//            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connect);
//            
//            JasperViewer viewer = new JasperViewer(jPrint, false);
//            
//            viewer.setTitle("MarcoMan Report");
//            viewer.show();
//            
//        }catch(Exception e){}
    }
//    

    public void selectData() {

        Voyage_virtuel data = table_view.getSelectionModel().getSelectedItem();

        int num = table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        id.setText(String.valueOf(data.getIdvv()));
        vo.getSelectionModel().clearSelection();
        ab.getSelectionModel().clearSelection();
        gender.getSelectionModel().getSelectedItem();
        nom.setText(String.valueOf(data.getNom()));

        String picture = "file:" + data.getImage_v();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = data.getImage_v();

        file_path.setText(path);

    }

    public void clear() {

        id.setText("");

        vo.getSelectionModel().clearSelection();
        ab.getSelectionModel().clearSelection();
        gender.getSelectionModel().clearSelection();
        image_view.setImage(null);

    }

    public void textfieldDesign() {

        if (id.isFocused()) {

            id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            idv.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            ida.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        } else if (idv.isFocused()) {

            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            idv.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            ida.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        } else if (ida.isFocused()) {

            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            idv.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            ida.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            gender.setStyle("-fx-border-width:1px; -fx-background-color:transparent");

        } else if (gender.isFocused()) {

            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            idv.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            ida.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            gender.setStyle("-fx-border-width:2px; -fx-background-color:#fff");

        }

    }

    public void defaultId() {

        id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");

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

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        comboBox();
        comboBoxab();
        comboBoxvo();
        col_id.setVisible(false);
        col_idv.setVisible(false);
        col_ida.setVisible(false);

        defaultId();

        showData();

    }

}
