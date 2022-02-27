/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login_signup;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import services.user.LoginAndSignupService;

/**
 *
 * @author medal
 */
public class LoginController implements Initializable {
   @FXML
    private TextField emailIdField;
   @FXML
    private PasswordField passwordField;
    @FXML   
    private Button submitButton;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
   @FXML
    public void login(ActionEvent event) throws SQLException, Exception  {

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
 Window owner = submitButton.getScene().getWindow();


        if (emailIdField.getText().isEmpty()||((!emailIdField.getText().matches("\\w{3,}@\\S+")))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
           
        }
        if (passwordField.getText().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          
        }

        String emailId = emailIdField.getText();
        String password = passwordField.getText();

            LoginAndSignupService loginSignup = new LoginAndSignupService();
        boolean flag = loginSignup.login(emailId, password);

        if (!flag) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error!","Please enter correct Email and Password" );
        } else {
          
            showAlert(Alert.AlertType.CONFIRMATION,owner,"Form Valide","Login Successful");
        }
    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

 
}
