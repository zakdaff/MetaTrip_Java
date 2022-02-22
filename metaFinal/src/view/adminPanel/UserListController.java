/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import entities.user;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FLAM
 */
public class UserListController implements Initializable {
                @FXML
		private TextField ename;
		@FXML
		private TextField dept;
		@FXML
		private TextField mobNo;
		@FXML
		private TextField salary;
		
		@FXML
		private Label lavel;
		
		@FXML
		private TableView<user> table;
		
		@FXML
		private TableColumn<user, Integer> id;
		@FXML
		private TableColumn<user, String> nom;
		@FXML
		private TableColumn<user, String> prenom;
		@FXML
		private TableColumn<user, String> email;
		@FXML
		private TableColumn<user, Date> dateNaissance;
                @FXML
		private TableColumn<user, Double> tel;
		
		
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
