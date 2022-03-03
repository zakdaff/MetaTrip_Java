/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import com.google.zxing.WriterException;
import entities.reservation_voyage;
import entities.user;
import entities.voyage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.reservation_voyage.Reservation_Voyage_Service;
import services.user.MailSender;
import services.user.UserService;
import services.voyage.voyageService;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class Reservation_VoyageClientController implements Initializable {

    @FXML
    private DatePicker Date_depart;
    @FXML
    private DatePicker Date_arrivee;
    @FXML
    private Button Reserver;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
user u1 = new user();
 public user setUser(String username) {
  UserService u= new UserService();
        
 u1= u.getUserByEmail(username);
         return u1;
    }

   

    @FXML
    private void ReserverVoyage(ActionEvent event) throws WriterException, IOException {

          String xx=Date_depart.getValue().toString();  
            Date date=Date.valueOf(xx);
          Alert alert = new Alert(Alert.AlertType.ERROR);
                 UserService us =new UserService();
                 Reservation_Voyage_Service r1=new Reservation_Voyage_Service();
                     System.out.println("ssssssssss"+r1.lastDatearrivebyuser(u1));  
        boolean ok = true;
        
               String str1=Date_depart.getValue().toString();  
      Date date1=Date.valueOf(str1);
        String str2=Date_arrivee.getValue().toString();  
     Date date2=Date.valueOf(str2);
            if(Date_depart.getValue().toString().isEmpty()||Date_arrivee.getValue().toString().isEmpty()){
               alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Form error");
                alert.showAndWait();
                 ok = false;
            }
            if(!date1.before(date2)){
                  
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date Depart > Date Arrive");
                alert.showAndWait();
                 ok = false;
            }
       
            if(date.compareTo(r1.lastDatearrivebyuser(u1))<0){
                  alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date Depart > Date Arrive");
                alert.showAndWait();
                 ok = false;
            }
            if(ok==true){
          /*    
            Reservation_Voyage_Service  rvs = new  Reservation_Voyage_Service ();
         voyageService v=new voyageService();
       
    
   voyage v1=v.afficherbyID(97);

            reservation_voyage r=new reservation_voyage(date1,date2,"NonPaye",u1,v1);
             
 System.out.println("585958855"+v1);
 
  System.out.println("test"+u1);
           rvs.ajouter(r);
           us.factureuser(r,u1);
        MailSender.sendFacture(u1.getEmail());*/
     

            }
    }
    
}
