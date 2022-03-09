/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
import Config.Metatrip;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfPTable;
import entities.user;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfPTable;
import entities.user;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.UserService;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.user.LoginAndSignupService;
import services.user.UserService;

/**
 * FXML Controller class
 *
 * @author FLAM
 */
public class UserListController implements Initializable {
              @FXML
              private Button bsave;
              @FXML
               private Button bupdate;
                @FXML
                private Button bdelete;

		
		@FXML
		private TableView<user> table;
		
		@FXML
		private TableColumn<user, Integer> uidu;
                
                
		@FXML
		private TableColumn<user, String> unom;
		@FXML
		private TableColumn<user, String> uprenom;
		@FXML
		private TableColumn<user, String> uemail;
                
                //@FXML
		//private TableColumn<user, String> password;
                                	
                @FXML
		private TableColumn<user, String> uimage;
                                
		@FXML
		private TableColumn<user, Date> udateNaissance;
                @FXML
		private TableColumn<user, Double> utel;
                @FXML
		private TableColumn<user, Double> ucin;
                
		
                
                
                
		@FXML
		private TextField  nom;
                
                
		@FXML
		private TextField  email;
                
                                	
		private TextField  image;
                                
                @FXML
		private TextField  tel;
                @FXML
		private TextField  cin;
                
		user user;
                	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    UserService us;
    @FXML
    private ImageView image_view;
    @FXML
    private Button insert_image;
    @FXML
    private Label file_path;
    @FXML
    private AnchorPane left_main;
    @FXML
    private TextField idu;
    @FXML
    private TextField prenom;
    @FXML
    private TextField password;
    @FXML
    private DatePicker dateNaissance;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         /* ObservableList<String> sexel
                = FXCollections.observableArrayList("Masculin", "Feminin");
        sexe.setItems(sexel);
        sexe.setValue("Masculin");*/
        // TODO
        uidu.setVisible(false);
        affiche();
    }    
    
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        user u = table.getSelectionModel().getSelectedItem();
        idu.setText(String.valueOf(u.getIdu()));
        cin.setText(String.valueOf(u.getCin()));
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        //sexe.getSelectionModel().select(et.getSexe());
        tel.setText(String.valueOf(u.getTel()));
         email.setText(u.getEmail());
         dateNaissance.setValue(u.getDateNaissance().toLocalDate());
      
            String picture ="file:" +  u.getImage().toString();

        Image image1 = new Image(picture, 110, 110, false, true);

        image_view.setImage(image1);
        password.setText("");
       // bsave.setDisable(true);
    }
    
    
  
  
     
    public ObservableList<user> getUser(){
         ObservableList<user> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select u.idu,u.cin,u.nom,u.prenom,u.tel,u.email,u.image,u.dateNaissance from user u where u.Role=0;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                user u = new user();
                u.setIdu(rs.getInt("idu"));
                u.setCin(rs.getString("cin"));
                
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setTel(rs.getString("tel"));
                u.setEmail(rs.getString("email"));
                u.setDateNaissance(rs.getDate("dateNaissance"));
                u.setImage(rs.getString("image"));
                list.add(u);
            }}
         catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;}
 
      public void affiche() {
        ObservableList<user> list = getUser();
        uidu.setCellValueFactory(new PropertyValueFactory<user, Integer>("idu"));
           ucin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        unom.setCellValueFactory(new PropertyValueFactory<user, String>("nom"));
        uprenom.setCellValueFactory(new PropertyValueFactory<user, String>("prenom"));
        utel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        uemail.setCellValueFactory(new PropertyValueFactory<user, String>("email"));
         uimage.setCellValueFactory(new PropertyValueFactory<user, String>("image"));
         udateNaissance.setCellValueFactory(new PropertyValueFactory<user, Date>("dateNaissance"));
        
        table.setItems(list);
 
    }
      
         void clear() {
        idu.setText(null);
        cin.setText(null);
        nom.setText(null);
        prenom.setText(null);
        tel.setText(null);
        email.setText(null);
        password.setText(null);
           dateNaissance.getEditor().clear();

        image_view.setImage(null);
        file_path.setText(null);
        //sexe.getSelectionModel().selectFirst();
        bsave.setDisable(false);
    }

         private void insert() throws Exception {
              String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
      boolean ok = true;
         boolean flag=false;
     ArrayList<user> user5 = new ArrayList<>();
          ArrayList<user> user6= new ArrayList<>();
 Window owner = bsave.getScene().getWindow();
System.out.println("date"+dateNaissance.getValue());
   if (prenom.getText().isEmpty()==true) { 
   
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your Prenom id");
             ok = false;
                             

                     }
  
    if (dateNaissance.getValue()==null) {
              
      
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                 ok = false;
                      return;
 
            
          }  
       if (dateNaissance.getValue()==null) {
              
      
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                 ok = false;
                      return;
         
            
          }  
   
         String str20=dateNaissance.getValue().toString();  
     Date date11=Date.valueOf(str20);
       Date date20=Date.valueOf(timeStamp);
      if (date11.after(date20)) { 
   
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "date invalide par rapport a la date System");
           
                  ok = false;      
                       return;

                     }             
     
        if ((email.getText().isEmpty())||((!email.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")))) {
         
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
          
              ok = false;
                   return;
            
           
        }
           if (image_view.getImage() == null ) {
        
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Image id");
        
              ok = false;
                   return;
           
        }  
           if (nom.getText().isEmpty()==true) {
   
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a Nom");
              
            ok = false;
                 return;
            
          } 
         if (cin.getText().isEmpty()==true){
            
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your Cin id");
          
             ok = false;
                  return;
         }
        if (password.getText().isEmpty()==true){
     
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Password vide");
             
                  ok = false;
                 return;
          }
        if(password.getText().length()<4){
          showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "changer votre Password");
             
                  ok = false;
                 return;
        
        }
        
        

               if (dateNaissance.getValue().toString().isEmpty()==true) {
                 System.out.println("date invalide");
  
             showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a DateNaissance");
                   ok = false;
              return;
            
          }  
               
                        int monEntier1 = 0;
        boolean ok1 = true;                                                            
                                                                                                if(!cin.getText().toString().isEmpty()==true){
                                                                                                   try
                                                                                                 {
                                                                                                    monEntier1 = Integer.parseInt(cin.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok1= false;
                                                                                                    }
                                                                                                        }
                                                                                      
        
        if (((cin.getText().length()!=8)||(ok1==false))) {
            
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
               " Cin est invalide");
        
         ok = false;
           
        }

      
         int monEntier = 0;
                                                                           if(!tel.getText().toString().isEmpty()){
                                                                                        try
                                                                                                 {
                                                                                                    monEntier = Integer.parseInt(tel.getText());
                                                                                        // s'il ne contient que des chiffres (0 à 9) c'est ok sauf si les limites int sont dépassées
                                                                                        // sinon une exception est levée
                                                                                                 }
                                                                                                     catch(NumberFormatException nfe)
                                                                                                    {
                                                                                                       ok = false;
                                                                                                    }}
                                                                                 
        System.out.println("password+++++++"+password.getText());
  if ((tel.getText().length()!=8||(ok==false))) {
         
           showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Nb de Tel faible ou invalide");
            
              ok = false;
           
        }
   UserService us5 =new UserService();
   user5=(ArrayList<user>) us5.getuserbycin(Integer.parseInt(cin.getText()));
   user6=(ArrayList<user>) us5.getuserbyTel(Integer.parseInt(tel.getText()));
   if(user5.size()>0){
          
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "cin est deja utilise");
                 ok = false;
                      return;
   
   }
      if(user6.size()>0){
   
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Tel est deja utilise");
                 ok = false;
                      return;
   
   }   
  if( ok==true){
   String str2=dateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);


  user u1=new user(cin.getText(),nom.getText(),prenom.getText(),tel.getText(),email.getText(),password.getText(),file_path.getText(),date1);

   

                  LoginAndSignupService loginSignup = new LoginAndSignupService();
         flag = loginSignup.Signup(u1);
 
  }
  if( flag==false){
    showAlert(Alert.AlertType.ERROR, owner,"Form Error!","Email deja utilisé" );
       return;
  }
     else{
          

  UserService us=new UserService();
           user u23=us.getUserByEmail(email.getText().toString());
         
           
             showAlert(Alert.AlertType.CONFIRMATION,owner,"Form Valide","Signup Successful");
                return;
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
   
          private void update() {
                String str20=dateNaissance.getValue().toString();  
     Date date11=Date.valueOf(str20);
	con =Datasource.getInstance().getCnx();
        	String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            String update = "UPDATE `user` SET "
                +"`Cin`=?,`Nom`=?,`Prenom`=?,`Tel`=?,`Email`=?,`Image`=?,`dateNaissance`=?"
                + "WHERE idu =?";
        try {
                st = con.prepareStatement(update);
                st.setString(1, cin.getText());
                st.setString(2, nom.getText());
                st.setString(3, prenom.getText());
                st.setString(4, tel.getText());
                st.setString(5, email.getText());
                //st.setString(6, UserService.doHashing(password.getText()));
                st.setString(6, image.getText());
                st.setDate(7, date11);
                st.setInt(8, Integer.parseInt(idu.getText()));
                   
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
    Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'utilisateur " +nom.getText()+"   "+prenom.getText()+" avec ID"+idu .getText()+" est modifié avec succés");

		alert.showAndWait();
                
                st.executeUpdate();
                affiche();
        } catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
         
         public void delete() {
         con =Datasource.getInstance().getCnx();
   
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
		         alert.setContentText("Are you sure that you want to delete it?");
                                     Optional<ButtonType> buttonType = alert.showAndWait();
		alert.showAndWait();
             if (buttonType.get() == ButtonType.OK) {
            
      UserService us = new   UserService();
      System.out.println("idu"+idu.getText().toString());
  us.supprimer(Integer.parseInt(idu.getText()));  

           affiche();
                 }
    } 
         
    @FXML
    private void saveEvent(ActionEvent event) throws Exception {
       insert();
        clear();
    }
 
    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }
 
    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }
 
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }
    
   
    
    @FXML
    private void importCSV(ActionEvent event) throws IOException, SQLException {
        String xx="C:/Users/FLAM/Desktop/users.csv";
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Importer un fichier csv");
        Stage stage = (Stage)cin.getScene().getWindow();
        File fileCSV = filechooser.showOpenDialog(stage);
        
       
        
        
   
    
      String req = "INSERT INTO user (cin, nom ,"
                + "prenom , tel , email , "
                + "password , image, dateNaissance) VALUES ( ? , ? , ? , ? , ? , ? , ?, ? ) ;";
        int batchSize=20;

        try {
            st = con.prepareStatement(req);
            BufferedReader lineReader=new BufferedReader(new FileReader(xx));

            String lineText=null;
            int count=0;
            
            // To JUMP COLUMNS NAMES
            //ineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");
                

                st = con.prepareStatement(req);
                st.setString(1, data[0]);
                st.setString(2, data[1]);
                st.setString(3, data[2]);
                st.setString(4, data[3]);
                st.setString(5, data[4]);
                st.setString(6, UserService.doHashing(data[5]));
                st.setString(7, data[6]);
                st.setDate(8, Date.valueOf(data[7]) );

                st.addBatch();
                if(count%batchSize==0){
                    st.executeBatch();
                }
            }
            lineReader.close();
            st.executeBatch();

            System.out.println("Data has been inserted successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        
    
        
        
        
        
        
        
        
        
        
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'utilisateur(s)");
            alert.setContentText("Liste d'utilisateurs importée avec succées.");
            alert.show();
          
        
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


  /*
    public void factureuser (user u){
         
              ArrayList table= new ArrayList <>();
              String imag1e="C:\\Users\\medal\\OneDrive\\Bureau\\Metatrip_git\\MetaTrip_Java\\MetaTrip_Java\\metaFinal\\src\\services\\user\\websiteQRCode_noFrame.png";
 
            
              try {
                 
          Document document=new Document ();
         
          
         
     document.open () ;

        Paragraph para=new Paragraph ("Facture  Voyage :");
        document.add (para);

        //simple paragraph
 
                            //add table
                             PdfPTable pdfPTable =new PdfPTable(7);
                              

                              PdfPCell pdfCell1 = new PdfPCell(new Phrase("Id Voyage ")); 
                     
                     
                            PdfPCell pdfCell2 = new PdfPCell(new Phrase("Date_depart"));
                             PdfPCell pdfCell3 = new PdfPCell(new Phrase("Date_depart"));
                              PdfPCell pdfCell4 = new PdfPCell(new Phrase("Etat"));
                            PdfPCell pdfCell50 = new PdfPCell(new Phrase("Nom&Prenom:"));
                                    PdfPCell pdfCell5 = new PdfPCell(new Phrase("IDVoiture:"));
                                       PdfPCell pdfCell555 = new PdfPCell(new Phrase("Ref_paiement:")); 
                                       
                         //PDPage page=new PDPage();
                        
                                      

                                       pdfPTable.addCell(pdfCell1);
                                pdfPTable.addCell(pdfCell2);
                                 pdfPTable.addCell(pdfCell3);
                                  pdfPTable.addCell(pdfCell4);
                                        pdfPTable.addCell(pdfCell50);
                                         pdfPTable.addCell(pdfCell5);
                        pdfPTable.addCell(pdfCell555);
                            pdfPTable.addCell(""+rv.getIdrv()+"");
                            pdfPTable.addCell (""+rv.getDate_depart()+"");
                            pdfPTable.addCell(""+rv.getDate_arrivee()+"");
                            pdfPTable.addCell(""+rv.getEtat()+"");
                            pdfPTable.addCell (""+rv.getIdu()+"");
                              pdfPTable.addCell (""+rv.getIdv()+"");
                           pdfPTable.addCell (""+rv.getRef_paiement()+"");
                          document.add(pdfPTable);
   
                        document.close();
                        document.close ();

        } catch (Exception Exception) {
         System.out.println(Exception);
 }
    }*/

        
  
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

     
   
    
}