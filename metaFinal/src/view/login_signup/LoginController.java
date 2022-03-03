package view.login_signup;

import Config.Metatrip;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;
import services.user.UserService;
import view.PartieClient.Reservation_VoyageClientController;
import view.adminPanel.Reservation_voyageController;

/**
 *
 * @author medal
 */
public class LoginController implements Initializable {
        public static  Stage stg1;
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
          
           UserService us=new UserService();
           user u1=us.getUserByEmail(emailId);
           link1(u1);
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

     @FXML
public void link(ActionEvent event) throws Exception {               
 
       try {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_signup/signup.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
         Metatrip.stg.close();   
    } catch(Exception e) {
        e.printStackTrace();
    }
}


public void link1(user user) throws Exception {               
 if(user.getRole()==1){
       try {
   
          
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminPanel/reservation_voyage.fxml"));
            Parent root = loader.load();
            Reservation_voyageController controller = loader.getController();
            controller.setUser(emailIdField.getText());
            //Personne.user = ;
            //Personne.user.get
            emailIdField.getScene().setRoot(root);
        } catch (IOException ex) {
         Metatrip.stg.close();   
    } catch(Exception e) {
        e.printStackTrace();
    }
}else{
    try {
     
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/PartieClient/Reservation_VoyageClient.fxml"));
            Parent root1 = loader1.load();
            Reservation_VoyageClientController controller = loader1.getController();
            controller.setUser(emailIdField.getText());
            //Personne.user = ;
            //Personne.user.get
            emailIdField.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }
 }

}
}