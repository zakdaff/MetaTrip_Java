/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

import Config.Datasource;
import Config.Metatrip;
import entities.VoyageX;
import entities.reservation_voyage;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import services.user.UserService;
import view.adminPanel.Reservation_voyageController;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class VoyageORGClientPartieController implements Initializable {
  user u1 = new user();


    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
        private Label Prix1;
    @FXML
    private TableColumn<VoyageX, String> Pays;
    @FXML
    private ImageView Image;
    @FXML
    private TableColumn<VoyageX, Float> Prix_billet;
    @FXML
    private TableColumn<VoyageX, String> Airline;
    @FXML
    private TableColumn<VoyageX, Integer> Nb_nuitees;
        @FXML
    private TableColumn<VoyageX, Integer> idv;
    @FXML
    private TableView<VoyageX> tableVoyage;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
        @FXML
    private Label VoyageNom;
    @FXML
    private Button ReserverButton;
   
    /**
     * Initializes the controller class.
     */
   
    @FXML
    private TableColumn<VoyageX, String> Image1;
    @FXML
    private Label username;
      ArrayList<Integer> IDs=new ArrayList<Integer>();
static String email;
    @FXML
    private Label idv10;
    @FXML
    private Label idv50;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     
       showData();
                   System.out.println("125:::"+u1);    

    }
 public user setUser(String email) {
     
  UserService u= new UserService();
     
  u1= u.getUserByEmail(email);
               System.out.println("sssssssssssss"+u1);

         username.setText(u1.getEmail());
         return u1;
    }

public int id=0;
    
   
    @FXML
       public void selectData(){
          
          List<String> list1 = new ArrayList<>();
     
        VoyageX data = tableVoyage.getSelectionModel().getSelectedItem();

 idv50.setText(String.valueOf(data.getIdv()));
        Prix1.setText(String.valueOf(data.getPrix_billet()));
       
        VoyageNom.setText(data.getPays());
       

         
    String picture ="file:" +  data.getImage_pays().toString();
System.out.println(data.getImage_pays().toString());
        Image image = new Image(picture, 110, 110, false, true);

        Image.setImage(image);

    }
    
    public void showData(){
       
        ObservableList<VoyageX> showList = dataList();
              System.out.println("type 1"+u1.getEmail());
                        idv.setCellValueFactory(new PropertyValueFactory<VoyageX, Integer>("idv"));
   Pays.setCellValueFactory(new PropertyValueFactory<VoyageX, String>("Pays"));
        Pays.setCellValueFactory(new PropertyValueFactory<VoyageX, String>("Pays"));
        Image1.setCellValueFactory(new PropertyValueFactory<VoyageX, String>("Image_pays"));
        Prix_billet.setCellValueFactory(new PropertyValueFactory<VoyageX, Float>("Prix_billet"));
        Airline.setCellValueFactory(new PropertyValueFactory<VoyageX, String>("Airline"));
        Nb_nuitees.setCellValueFactory(new PropertyValueFactory<VoyageX, Integer>("Nb_nuitees"));
 
        tableVoyage.setItems(showList);
     

}
public void link1(user user) throws Exception {  
 
      UserService u= new UserService();
     
  //u1= u.getUserByEmail(email);
    
/* if(user.getRole()==1){
       try {
   
          
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminPanel/reservation_voyage.fxml"));
            Parent root = loader.load();
            Reservation_voyageController controller = loader.getController();
            controller.setUser(user.getEmail());
            //Personne.user = ;
            //Personne.user.get
            username.getScene().setRoot(root);
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
            controller.setUser(username.getText());
            //Personne.user = ;
            //Personne.user.get
            username.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }
 }*/

}
 public ObservableList<VoyageX> dataList(){            

        System.out.println("sssssssssssss"+email);
     
         Connection con = Datasource.getInstance().getCnx();
        
        ObservableList<VoyageX> dataList = FXCollections.observableArrayList();
        
        String sql = "SELECT DISTINCT voyage.Idv,Pays,Image_pays,Prix_billet,Airline,Nb_nuitees \n" +
"FROM voyage join voyage_organise ON voyage_organise.Idv=voyage.Idv\n" +
"WHERE etatVoyage;";
      
 
        ArrayList<VoyageX> v2=new ArrayList<VoyageX>();

        try{
          
             prepare = con.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
     
            
     
 
              
       
         
            while(rs.next()){
               
                     VoyageX vx = new VoyageX();
             vx.setIdv(rs.getInt(1));
                vx.setPays(rs.getString(2));
                  vx.setImage_pays(rs.getString(3));
                   vx.setPrix_billet(rs.getFloat(4));
                   vx.setAirline(rs.getString(5));
                   vx.setNb_nuitees(rs.getInt(6));
                //rvo.setIdu(rs.getInt(5));
                //rvo.setIdv(rs.getInt(6));
               //

                dataList.add(vx);

                
            }
            
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
       
   System.out.println("type 2"+dataList);

    return  dataList ;
    }
    @FXML
    private void link1(ActionEvent event) 
    {
         System.out.println("ssssss**********************ssssss"+idv50.getText());
           UserService u= new UserService();
   user u10=  u.getUserByEmail(username.getText());

    try {
        String zzz = String.valueOf(idv50.getText());
            //Personne.user = ;
            //Personne.user.get
                      FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/view/PartieClient/Reservation_VoyageClient.fxml"));
            Parent root1 = loader1.load();
            Reservation_VoyageClientController controller = loader1.getController();
            controller.setUser(username.getText());
            controller.SetVoyage(zzz);
            //Personne.user = ;
            //Personne.user.get
            username.getScene().setRoot(root1);
            idv50.getScene().setRoot(root1);
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    



}