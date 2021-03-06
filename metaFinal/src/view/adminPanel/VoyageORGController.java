/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
import Config.Metatrip;
import entities.EtatDispo;
import entities.voyage;
import entities.voyage_organise;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.user.UserService;
import services.voyage.voyageService;
import static services.voyage.voyageService.IDVOY;
import services.voyage.voyage_organise.VoyageORG_Service;

/**
 * FXML Controller class
 *
 * @author FLAM
 */
public class VoyageORGController implements Initializable {
    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TextField idvo;
    @FXML
    private TextField prix_billet;
    
    @FXML
    private TextField nb_nuitees;
    
    @FXML
    private TextField airline;
    @FXML
    private ComboBox<EtatDispo> etatVoyage;
    @FXML
    private ComboBox<String> Idv;
    @FXML
    private TableView<voyage_organise> table_view;
    @FXML
    private TableColumn<voyage_organise, Integer> uidvo;
    @FXML
    private TableColumn<voyage_organise, Float> uPrix_billet;
    @FXML
    private TableColumn<voyage_organise, String> uAirline;
    @FXML
    private TableColumn<voyage_organise, Integer> uNb_nuitees;
    @FXML
    private TableColumn<voyage_organise, EtatDispo> uetatVoyage;
    @FXML
    private TableColumn<voyage_organise, Integer> uIdv;
       private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

       private Connection conn;
    private Statement ste,ste2;
    private PreparedStatement pste,pste2;
     private EtatDispo[] comboGender = {EtatDispo.DISPO, EtatDispo.INDISPO};
    
     VoyageORG_Service vos=new VoyageORG_Service();
          voyageService vs=new voyageService();
    @FXML
    private TextField idvo1;
    
    public static int test=0;
    
         @Override
    public void initialize(URL url, ResourceBundle resource){
    
   comboBox();
   uidvo.setVisible(false);
   uIdv.setVisible(false);
        comboBox1();
       
      
        showData();
        
    }
     
        @FXML
    public void comboBox(){
        
        List<EtatDispo> list = new ArrayList<>();
          
        for(EtatDispo data: comboGender){
            
            list.add(data);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list);
        
        etatVoyage.setItems(dataList);
        
    }
     
       @FXML
    public void comboBox1(){
        
     voyageService vs= new voyageService();
            List<String> list1 = new ArrayList();
          HashMap<Integer,String> listmap=new  HashMap<Integer,String> (); 
         String req = "SELECT Idv,pays  from `voyage`";
            try {
                  conn = Datasource.getInstance().getCnx();
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
            
               list1.add(rs.getString(2));
               listmap.put(rs.getInt(1), rs.getString(2));
            }
              ObservableList dataList = FXCollections.observableArrayList(list1);
                 Idv.setItems(dataList);
                 boolean ok=false;
                 for(Entry<Integer, String> entry: listmap.entrySet()) {
                     if (entry.getValue().toString().equals(Idv.getSelectionModel().getSelectedItem()) && ok==false)
                     {
                         System.out.println("ID  "+ entry.getKey());
                     
                              test=entry.getKey();
                     ok=true;
                     
                   /*  Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Adding voyage");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("id choisi"+test);

		alert.showAndWait();*/
                     break;
                     }
        
              /* Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Adding voyage");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("id choisi"+test);

		alert.showAndWait();*/
                 }
               
        
            }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
   
    

   
          
       
    }
     
     
   
    
    
  
     @FXML
       public void selectData(){
           etatVoyage.getSelectionModel().clearSelection();
           
       
           
          List<String> list1 = new ArrayList<>();
     
        voyage_organise vo = table_view.getSelectionModel().getSelectedItem();
             
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
    if(vo.getEtatVoyage().toString().equals("DISPO")){
       etatVoyage.getSelectionModel().selectFirst();
    }else{
       etatVoyage.getSelectionModel().selectLast();
    }
    
   
           
 
     idvo.setText(String.valueOf(vo.getIdvo()));
     prix_billet.setText(String.valueOf(vo.getPrix_billet()));
      airline.setText(vo.getAirline());
      nb_nuitees.setText(String.valueOf(vo.getNb_nuitees()));
       String pays=vos.getByPays(vo.getIdv());
       Idv.setValue(pays);
         System.out.println("id choisi est "+vos.ID);
       

        
    }
    
    
     public ObservableList<voyage_organise> dataList(){
        
        Connection con = Datasource.getInstance().getCnx();
        
        ObservableList<voyage_organise> dataList = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM `voyage_organise` ;";
        
        try{
          
             prepare = con.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
     
            System.out.println(rs);
            while(rs.next()){
                
              voyage_organise rvo = new voyage_organise();
                     
                 rvo.setIdvo(rs.getInt(1));
                rvo.setPrix_billet(rs.getFloat(2));
                rvo.setAirline(rs.getString(3));
                rvo.setNb_nuitees(rs.getInt(4));
             rvo.setEtatVoyage(EtatDispo.valueOf((rs.getString(5))));
             rvo.setIdv(rs.getInt(6));
             
         
                 System.out.println("rvo:"+rvo);
                dataList.add(rvo);
                
            }
            
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        System.out.println("dataList:"+dataList);
        return dataList;
        
    }
    
    
    public void showData(){
       
        ObservableList<voyage_organise> showList = dataList();
  System.out.println(showList);
        uidvo.setCellValueFactory(new PropertyValueFactory<voyage_organise, Integer>("Idvo"));
        uPrix_billet.setCellValueFactory(new PropertyValueFactory<voyage_organise, Float>("Prix_billet"));
        uAirline.setCellValueFactory(new PropertyValueFactory<voyage_organise, String>("Airline"));
        uNb_nuitees.setCellValueFactory(new PropertyValueFactory<voyage_organise, Integer>("Nb_nuitees"));
        uetatVoyage.setCellValueFactory(new PropertyValueFactory<>("etatVoyage"));
        uIdv.setCellValueFactory(new PropertyValueFactory<voyage_organise, Integer>("Idv"));
                    
        table_view.setItems(showList);
        
    }
    
    
    
    @FXML
       public void textfieldDesign(){
        
     
        
    }
    
    public void defaultId(){
        
    }
    
    
    
    public void insert(){
                Connection connect = Datasource.getInstance().getCnx();
                Alert alert = new Alert(AlertType.ERROR);
   int monEntier = 0;
        boolean ok = true;
//        I HAVE 5 COLUMNS


                    
            if( etatVoyage.getSelectionModel().isEmpty() || prix_billet.getText().isEmpty()||nb_nuitees.getText().toString().isEmpty()|| Idv.getValue().toString().isEmpty()||airline.getText().toString().isEmpty())
            {
     
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Fields are required!");
                alert.showAndWait();
                
                 ok = false;
            }
            
                                                                                               

            
            if(ok==true){
                 String sql = "INSERT INTO `voyage_organise` (`Prix_billet`,`Airline`,`Nb_nuitees`,`etatVoyage`,`Idv`) VALUES (?,?,?,?,?)";
               try{
              
                        prepare = connect.prepareStatement(sql);

                 prepare.setFloat(1,Float.parseFloat(prix_billet.getText()));
              prepare.setString(2, airline.getText());
              prepare.setInt(3,Integer.parseInt(nb_nuitees.getText()));
                prepare.setString(4,String.valueOf(etatVoyage.getValue().name()));
           
                   // System.out.println(Idv.getSelectionModel().getSelectedItem());  
              prepare.setInt(5,test);
                
                         
                prepare.executeUpdate();
            
                showData();
                clear();
            }
        catch(Exception e){
        
        System.out.println(e.getMessage());}
        
    }}
    
    
    
    
    
    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
        clear();
    }

    public void update(){
        
               
       int id =Integer.parseInt(idvo.getText());
               Float pb=Float.parseFloat(prix_billet.getText());  
               String air=airline.getText();
      int nb=Integer.parseInt(nb_nuitees.getText());
      EtatDispo etat=etatVoyage.getValue();
      
  
         
           voyage_organise v=new voyage_organise(pb,air,nb,etat);

               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Updating  ");

		// Header Text: null
		alert.setHeaderText(null);
		         alert.setContentText("Are you sure that you want to update it?");
                                     Optional<ButtonType> buttonType = alert.showAndWait();
		alert.showAndWait();
             if (buttonType.get() == ButtonType.OK) {
            
        
                   
    vos.modifier(id, v);
        showData();
              clear(); 
                 }
 
          
    }
    
    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
    }

    
    
    
    public void delete(){
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting  ");

		// Header Text: null
		alert.setHeaderText(null);
		         alert.setContentText("Are you sure that you want to deletee it?");
                                     Optional<ButtonType> buttonType = alert.showAndWait();
		alert.showAndWait();
             if (buttonType.get() == ButtonType.OK) {
            
        
                   
  vos.supprimer(Integer.parseInt(idvo.getText()));  
                showData();
                clear();
                 }

    }
    
    
    
    
    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }


   public void clear(){
        
        idvo.setText(null);
        prix_billet.setText(null);
       airline.setText(null);
        nb_nuitees.setText(null);
       etatVoyage.getSelectionModel().clearSelection();
       Idv.getSelectionModel().clearSelection();
    }
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }


    
    
    

       @FXML
public void home(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

            Metatrip.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/home.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
             Metatrip.stg.close();  
            stage.show();
           
    } catch(Exception e) {
        e.printStackTrace();
    }
}

        @FXML
public void logout(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

         Metatrip.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login_signup/login.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root)); 
              Metatrip.stg.close();  
            stage.show();
           
    } catch(Exception e) {
        e.printStackTrace();
    }
}

       @FXML
public void settings(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

              Metatrip.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/updateUser.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root)); 
          Metatrip.stg.close();  
            stage.show();
          
    } catch(Exception e) {
        e.printStackTrace();
    }}
    
    
       @FXML
public void toUser(ActionEvent event) throws Exception {               
    try {
          final Node source = (Node) event.getSource();
  
    
          Metatrip.stg.close();
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/UserList.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root)); 
                Metatrip.stg.close();  
            stage.show();
          
    } catch(Exception e) {
        e.printStackTrace();
    }}
    
          @FXML
public void toVoy(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

               Metatrip.stg.close();  
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/Voyage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
              Metatrip.stg.close();  
            stage.show();
     
    } catch(Exception e) {
        e.printStackTrace();
    }}

          @FXML
public void toVoyORG(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

                   Metatrip.stg.close();   
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/VoyageORG.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
        Metatrip.stg.close();  
            stage.show();
      
    } catch(Exception e) {
        e.printStackTrace();
    }}



       @FXML
public void toReserVoy(ActionEvent event) throws Exception {               
    try {
                  final Node source = (Node) event.getSource();

            Metatrip.stg.close();   
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/reservation_voyage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
           final Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));  
                  Metatrip.stg.close();  
            stage.show();
   
    } catch(Exception e) {
        e.printStackTrace();
    }}

    
    
    
    
    

    
}
