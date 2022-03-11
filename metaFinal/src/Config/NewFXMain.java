/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Z4RGA
 */
public class NewFXMain extends Application {
    
    public void start(Stage primaryStage) throws IOException {
       
   // Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/AcceuilEvennement.fxml"));
    // Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/Gestion_reservation_events.fxml"));
  //  Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/ReserveEvent.fxml"));
  Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/EventList.fxml"));
  //Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/AcceuilEvennement.fxml"));
        Scene scene = new Scene(root);
        
        
//         String css = this.getClass().getResource("/css/style.css").toExternalForm();
//      scene.getStylesheets().add(css); 
      
      
      
        //sscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    }

    /**
     * @param args the command line arguments
     */
   

