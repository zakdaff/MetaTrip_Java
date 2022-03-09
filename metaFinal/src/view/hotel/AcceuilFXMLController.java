/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.hotel;

import entities.AffichHotel;
import entities.hotel;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.hotel.HotelCRUD;
import view.hotel.ItemController;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class AcceuilFXMLController implements Initializable {

    @FXML
    private HBox chosenhotelCard;
    @FXML
    private HBox hosenhotelCard;
    @FXML
    private GridPane grid;
    List<AffichHotel> listeh= new ArrayList();
    @FXML
    private ScrollPane hotelgrid;
    @FXML
    private HBox chosenhotelCard1;
    @FXML
    private ImageView image_view;
    HotelCRUD hc=new HotelCRUD();
    List<hotel> data=hc.afficherHotels();
    int max_data=data.size();
    int i=0;
    @FXML
    private Label label;
    @FXML
    private Label label1;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(hc.afficherHotels().size()>0){
            hotel h=hc.afficherHotels().get(0);
        
        
        String picture ="file:" + h.getImage_hotel();
        label.setText(h.getNom_hotel());
//        label1.setText(h.getAdresse());
         Image image = new Image(picture);

        image_view.setImage(image);

        String path = h.getImage_hotel();
        }
        
//       listeh=new ArrayList(listeh);
//       int colums=0;
//       int row=1;
//       for (int i=0 ;i<listeh.size();i++){
//           VBox box =null;
//           try {
//               FXMLLoader fxm =new FXMLLoader();
//               fxm.setLocation(getClass().getResource("item.fxml"));
//               box = fxm.load();
//               ItemController itc=new ItemController();
//               itc.setData(listeh.get(i));
//               if (colums==3){
//                   colums=0;
//                   ++row;
//               }
//               grid.add(box, colums++, row);
//               
//               
//           } catch (IOException ex) {
//               System.err.println(ex.getMessage());
//          
//           }
       
    }    
    
//   private List<AffichHotel>AfficherHotel(){
//       List<AffichHotel> listeh= new ArrayList();
//       for (int i=0 ;i<100;i++){
//       AffichHotel lh= new AffichHotel();
//       lh.setImgSrc("/image/hotel1");
//       lh.setAdresse("Hamammet");
//       lh.setName("mouradi");
//       
//       
//   }
//    return listeh;
//   }

//    private void Reserver(ActionEvent event) {
//        try {
//                   
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/InterfaceReservationFXML.fxml"));
//            Scene scene = new Scene(parent);
//            
//            Stage stage = new Stage();
//            //stage.getIcons().add(new Image("wood.jpg"));
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
//        }

    @FXML
    private void next(ActionEvent event) {
        
        
        if(i<max_data-1){
            i++;
            hotel h=hc.afficherHotels().get(i);
        label.setText(h.getNom_hotel());
        String picture ="file:" + h.getImage_hotel();
         Image image = new Image(picture);

        image_view.setImage(image);

        String path = h.getImage_hotel();
        
        }
        

        //file_path.setText(path);
        //file_path.setOpacity(0);
    }

    @FXML
    private void prev(ActionEvent event) {
        System.out.println(i);
        
        if(i>0){
            i--;
            hotel h=hc.afficherHotels().get(i);
        label.setText(h.getNom_hotel());
        String picture ="file:" + h.getImage_hotel();
         Image image = new Image(picture);

        image_view.setImage(image);

        String path = h.getImage_hotel();
        
        }
    }

    @FXML
    private void Reserver(ActionEvent event) {
        try {
     //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
     //********
                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            //-******
            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/InterfaceReservationFXML.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("wood.jpg"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
           System.err.println(ex.getMessage());;
        }
            
    }

//    private void Gerer(ActionEvent event) {
//         try {
//     //9bal mat7el ay interface zid il zouz ostra hedhom taw tetsaker wtet7al  interface o5ra
//     //********
//                   Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
//            
//            stageclose.close();
//            //-******
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/hotel/InterfaceGestion.fxml"));
//            Scene scene = new Scene(parent);
//            
//            Stage stage = new Stage();
//            //stage.getIcons().add(new Image("wood.jpg"));
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (IOException ex) {
//           System.err.println(ex.getMessage());;
//        }
        
    }
        
    
    


