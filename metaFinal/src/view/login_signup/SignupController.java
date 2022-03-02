/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login_signup;

import entities.user;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class SignupController implements Initializable {

     @FXML
    private Button submitButton;

    @FXML
    private TextField Cin;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Tel;

    @FXML
    private TextField Email;

    @FXML
    private DatePicker DateNaissance;

    @FXML
    private PasswordField Password;

    @FXML
    private Label file_path;

      @FXML
    private ImageView image_view;

    @FXML
    private Button insert_image;
 
    @FXML
    private AnchorPane left_main;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    @FXML
    public void signup(ActionEvent event) throws SQLException, Exception  {

   
 Window owner = submitButton.getScene().getWindow();


        if ((Email.getText().isEmpty())||((!Email.getText().matches("\\w{3,}@\\S+")))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
           
        }
        if ((Password.getText().isEmpty())||(Password.getText().length()<4)) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          }
               if (DateNaissance.getValue().toString().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
            return;
            
          }  
               
                        int monEntier1 = 0;
        boolean ok1 = true;
                                                                                        try
                                                                                                 {
                                                                                                    monEntier1 = Integer.parseInt(Cin.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok1= false;
                                                                                                    }
     
        if (Cin.getText().isEmpty()||((Cin.getText().length()!=8)||(ok1==false))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Cin id");
            return;
           
        }
        if (Nom.getText().isEmpty()) {
    
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
            
          }
         if (image_view.getImage() == null ) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Image id");
            return;
           
        }
         int monEntier = 0;
        boolean ok = true;
                                                                                        try
                                                                                                 {
                                                                                                    monEntier = Integer.parseInt(Tel.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok = false;
                                                                                                    }
                                                                                       
      if (Prenom.getText().isEmpty()) {

           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Prenom id");
                                 return;

                     }
  if (Tel.getText().isEmpty()||(Tel.getText().length()!=8||(ok==false))) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Tel id");
            return;
           
        }
        String str2=DateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);

  user u1=new user(Cin.getText(),Nom.getText(),Prenom.getText(),Tel.getText(),Email.getText(),Password.getText(),file_path.getText(),date1);

   

                  LoginAndSignupService loginSignup = new LoginAndSignupService();
        boolean flag = loginSignup.Signup(u1);

        if (!flag) {
            showAlert(Alert.AlertType.ERROR, owner,"Form Error!","Form invalide" );
        } else {
          
            showAlert(Alert.AlertType.CONFIRMATION,owner,"Form Valide","Signup Successful");
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
