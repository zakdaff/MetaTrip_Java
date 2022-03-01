/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
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
import javafx.fxml.Initializable;
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
import services.user.UserService;
import services.voiture.VoitureCRUD;

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
    private TextField Date_depart;
    @FXML
    private TextField Date_arrivee;
        @FXML
    private ComboBox<?> etat15;
    @FXML
    private ComboBox<?> idu11;
    @FXML
    private ComboBox<?> Idv122;
    @FXML
    private Label Idv1;
    @FXML
    private Label Refpaiement;
   
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
    
    

    
    private String[] comboGender = {"Dispo", "indispo"};
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

//        I HAVE 5 COLUMNS

                    String sql = "INSERT INTO `reservation_voyage` (`Date_depart`,`Date_arrivee`,`etat`,`Idu`,`Idv`) VALUES (?,?,?,?,?)";
        try{
            
            if( etat15.getValue().toString().isEmpty() ){
                
                Alert alert = new Alert(AlertType.ERROR);
                
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
                
            }else{
                
                   String str1=Date_depart.toString();  
     Date date1=Date.valueOf(str1);
        String str2=Date_arrivee.toString();  
     Date date2=Date.valueOf(str2);
                
                 prepare.setDate(1,date1);
              prepare.setDate(2, date2);
              prepare.setString(3, etat15.getValue().toString());
               prepare.setInt(4, Integer.parseInt(idu11.getValue().toString()));
              prepare.setInt(5,Integer.parseInt(Idv122.getValue().toString()));
                            prepare.setInt(5,Integer.parseInt(Refpaiement1.getText()));
                prepare.executeUpdate();
            
                showData();
                clear();
            }
        }catch(Exception e){}
        
    }
        @FXML
    public void update(){
        
         Connection connect = Datasource.getInstance().getCnx();
        
       
    }
        @FXML
    public void delete(){
        
       
        
    }


    
    public void selectData(){
        
      
        
    }
    
    public void clear(){
        
   
        
    }
    
    public void textfieldDesign(){
        
     
        
    }
    
    public void defaultId(){
        
     
        
    }
      
         @FXML
    public void comboBox2(){
      VoitureCRUD u= new VoitureCRUD();
            List<Integer> list1 = new ArrayList<>();
        for(Integer data:u.gelallID()){
            System.out.print("77777"+data);
            list1.add(data);
            
        }
        
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
    
}