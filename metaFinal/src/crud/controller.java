/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class controller implements Initializable {

    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField id;
    @FXML
    private TextField surname;
    @FXML
    private TextField given;
    @FXML
    private ComboBox<?> gender;
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
    private ImageView image_view;
    @FXML
    private Button insert_image;
    @FXML
    private TableView<?> table_view;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_surname;
    @FXML
    private TableColumn<?, ?> col_given;
    @FXML
    private TableColumn<?, ?> col_gender;
    @FXML
    private TableColumn<?, ?> col_picture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void textfieldDesign(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign(KeyEvent event) {
    }

    @FXML
    private void comboBox(ActionEvent event) {
    }

    @FXML
    private void insert(ActionEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void insertImage(ActionEvent event) {
    }

    @FXML
    private void selectData(MouseEvent event) {
    }
    
}
