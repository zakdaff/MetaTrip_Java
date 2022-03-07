package view.adminPanel;
import Config.Datasource;
import Config.Metatrip;
import entities.Voiture;
import entities.reservation_voyage;
import entities.user;
import entities.voyage;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.reservation_voyage.Reservation_Voyage_Service;
import services.user.UserService;
import services.voiture.VoitureCRUD;
import services.voyage.voyageService;
import view.login_signup.LoginController;

/**
 * FXML Controller class
 *
 * @author medal
 */
public class Reservation_voyageController implements Initializable {

    @FXML
    private AnchorPane left_main;
   
    @FXML
    private TextField Idrv258;
    @FXML
    private Label Idrv0;
    @FXML
    private Label etat;

    @FXML
    private Label Idu1;
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
    private TextField Refpaiement1;
    @FXML
    private DatePicker Date_depart;
    @FXML
    private DatePicker Date_arrivee;
            @FXML
        private ComboBox<?> etat15;
    @FXML
    private ComboBox<Integer> idu11;
    @FXML
    private ComboBox<Integer> Idv122;
    @FXML
    private Label Idv1;

   
    @FXML
    
    private TableView<reservation_voyage> table_view;
    @FXML
    private TableColumn<reservation_voyage, Integer> col_id;
    @FXML
    private TableColumn<reservation_voyage, Date> col_date_depart;
    @FXML
    private TableColumn<reservation_voyage, Date> col_date_arrive;
    @FXML
    private TableColumn<reservation_voyage, String> col_etat;
    @FXML
    private TableColumn<reservation_voyage, Integer> col_Idu;
    @FXML
    private TableColumn<reservation_voyage, Integer> col_Idv;
    @FXML
    private TableColumn<reservation_voyage,Integer> col_Ref_paiement;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    
    private String[] comboGender = {"NonPaye", "Paye"};
    
public user setUser(String username) {
  UserService u= new UserService();
        user u1 = new user();
 u1= u.getUserByEmail(username);
         return u1;
    }
    
        @FXML
    public void comboBox(){
        
        List<String> list = new ArrayList<>();
            List<Integer> list1 = new ArrayList<>();
        for(String data: comboGender){
            
            list.add(data);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list);
        
        etat15.setItems(dataList);
        
    }
        

   @FXML
    public void comboBox1(){
        
       UserService u= new UserService();
            List<Integer> list1 = new ArrayList<>();
        for(Integer data:u.gelallID() ){
            
            list1.add(data);
            
        }
        
        ObservableList dataList = FXCollections.observableArrayList(list1);
        
        idu11.setItems(dataList);
        
    }
    
    public ObservableList<reservation_voyage> dataList(){
        
        Connection con = Datasource.getInstance().getCnx();
        
        ObservableList<reservation_voyage> dataList = FXCollections.observableArrayList();
        
        String sql = "SELECT  Idrv,Date_depart,Date_arrivee,etat,Idu,Idv,Ref_paiement  FROM `reservation_voyage`";
        
        try{
          
             prepare = con.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
     
            
            while(rs.next()){
                
              reservation_voyage rvo = new reservation_voyage();
                     
                 rvo.setIdrv(rs.getInt(1));
                rvo.setDate_depart(rs.getDate(2));
                rvo.setDate_arrivee(rs.getDate(3));
                rvo.setEtat(rs.getString("etat"));
                //rvo.setIdu(rs.getInt(5));
                //rvo.setIdv(rs.getInt(6));
               //

                
                rvo.setIdu(rs.getInt("Idu"));
               rvo.setIdv(rs.getInt("Idv"));
               rvo.setRef_paiement(rs.getInt("Ref_paiement"));
               System.out.println("test"+rs.getInt(1));
                dataList.add(rvo);
                
            }
            
        }catch(Exception e){
        System.out.println(e.getMessage());
        }
        
        return dataList;
        
    }
      @FXML
      public void print(){
        
  
    }
        @FXML
    public void showData(){
       
        ObservableList<reservation_voyage> showList = dataList();
  System.out.println(showList);
        col_id.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Integer>("Idrv"));
        col_date_depart.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Date>("Date_depart"));
        col_date_arrive.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Date>("Date_arrivee"));
        col_etat.setCellValueFactory(new PropertyValueFactory<reservation_voyage, String>("etat"));
        col_Idu.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Integer>("Idu"));
                col_Idv.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Integer>("Idv"));
                        col_Ref_paiement.setCellValueFactory(new PropertyValueFactory<reservation_voyage, Integer>("Ref_paiement"));
        table_view.setItems(showList);
        
    }
    

        @FXML
    public void insert50(){
                Connection connect = Datasource.getInstance().getCnx();
                Alert alert = new Alert(AlertType.ERROR);
   int monEntier = 0;
        boolean ok = true;
//        I HAVE 5 COLUMNS


                    
            if( etat15.getValue().toString().isEmpty() ||Refpaiement1.getText().toString().isEmpty()||Date_depart.getValue().toString().isEmpty()||Date_arrivee.getValue().toString().isEmpty()||etat15.getValue().toString().isEmpty()||idu11.getValue().toString().isEmpty()||Idv122.getValue().toString().isEmpty()||Refpaiement1.getText().toString().isEmpty())
            {
     
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
                
                 ok = false;
            }
            
                                                                                                  try
                                                                                                 {
                                                                                                    monEntier = Integer.parseInt(Refpaiement1.getText().toString());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {   
                                                                                                        alert.setTitle("Error Message");
                                                                                             alert.setHeaderText(null);
                                                                               alert.setContentText("RefPeiment doit étre un nombre");
                                                                                                 alert.showAndWait();
                                                                                                         ok = false;
                                                                                                    }
             String str1=Date_depart.getValue().toString();  
      Date date1=Date.valueOf(str1);
        String str2=Date_arrivee.getValue().toString();  
     Date date2=Date.valueOf(str2);
            if(!date1.before(date2)){
                  
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date Depart > Date Arrive");
                alert.showAndWait();
                 ok = false;
            }
            
            if(ok==true){
                 String sql = "INSERT INTO `reservation_voyage` (`Date_depart`,`Date_arrivee`,`etat`,`Idu`,`Idv`,`Ref_paiement`) VALUES (?,?,?,?,?,?)";
               try{
              
                        prepare = connect.prepareStatement(sql);
       System.out.println(Refpaiement1.getText().toString());
                 prepare.setDate(1,date1);
              prepare.setDate(2, date2);
              prepare.setString(3, etat15.getValue().toString());
               prepare.setInt(4, Integer.parseInt(idu11.getValue().toString()));
              prepare.setInt(5,Integer.parseInt(Idv122.getValue().toString()));
                            prepare.setInt(6,Integer.parseInt(Refpaiement1.getText().toString()));
                prepare.executeUpdate();
            
                showData();
                clear();
            }
        catch(Exception e){
        
        System.out.println(e.getMessage());}
        
    }}
        @FXML
    public void update(){
        
               
       String id =Idrv258.getText().toString();
               String str1=Date_depart.getValue().toString();  
      Date date1=Date.valueOf(str1);
        String str2=Date_arrivee.getValue().toString();  
     Date date2=Date.valueOf(str2);
   
           //  UserService us=new UserService();
          //  user u1= us.getUserByID(Integer.parseInt(idu11.getValue().toString()));
           //  voyageService VC=new voyageService();
              //  voyage v=VC.afficherbyID(Integer.parseInt(Idv122.getValue().toString()));

         
           reservation_voyage rv=new reservation_voyage(date1,date2,etat15.getValue().toString());
    Reservation_Voyage_Service rvs=new Reservation_Voyage_Service();
    rvs.modifier(Integer.parseInt(id), rv);
        showData();
                clear(); 
    }
        @FXML
    public void delete(){
  Reservation_Voyage_Service rv = new   Reservation_Voyage_Service();
  rv.supprimer(Integer.parseInt(Idrv258.getText()));  
    showData();
                clear();
    }
    


     @FXML
       public void selectData(){
           etat15.getSelectionModel().clearSelection();
          List<String> list1 = new ArrayList<>();
     
        reservation_voyage data = table_view.getSelectionModel().getSelectedItem();
              System.out.println("tttttttttttttttttttttttS"+data.getIdu());
        int num = table_view.getSelectionModel().getSelectedIndex();
        
        if((num-1) < -1)
            return;
        
if(data.getEtat().toString().equals("NonPaye")){
   etat15.getSelectionModel().selectFirst();
}else{
   etat15.getSelectionModel().selectLast();
}

 
     Idrv258.setText(String.valueOf(data.getIdrv()));
     Date_depart.setValue(data.getDate_depart().toLocalDate());
      Date_arrivee.setValue(data.getDate_arrivee().toLocalDate());
idu11.setValue(data.getIdu());
        Idv122.setValue(data.getIdv());
       Refpaiement1.setText(String.valueOf(data.getRef_paiement()));
        
    }
      @FXML
   public void clear(){
            Idv122.getSelectionModel().clearSelection();
        Idrv258.setText("");
        Date_depart.getEditor().clear();
        Date_arrivee.getEditor().clear();
        etat15.getSelectionModel().clearSelection();
        idu11.getSelectionModel().clearSelection();
    
        Refpaiement1.setText("");
        
    }
    public void textfieldDesign(){
        
     
        
    }
    
    public void defaultId(){
        
     
        
    }
      
         @FXML
    public void comboBox2(){
      voyageService u= new voyageService();
            List<Integer> list1 = new ArrayList<>();
       /* for(Integer data:u.getAllByID()){
           
            list1.add(data);
            
        }*/
        
        ObservableList dataList = FXCollections.observableArrayList(list1);
        Idv122.setItems(dataList);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle resource){
    
        comboBox();
        comboBox1();
          comboBox2();
        defaultId();
       
      
        showData();
        
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