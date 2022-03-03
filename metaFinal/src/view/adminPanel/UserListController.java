/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.adminPanel;

import Config.Datasource;
 
 



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
import javafx.fxml.Initializable;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
                private Button importCS;

		    @FXML
    private Label file_path;
                    
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
                
		
                
                //*********si flam******************//
                 @FXML
    private AnchorPane left_main;
                
                @FXML
		private TextField  idu;
                
             
		@FXML
		private TextField  prenom;
                
		@FXML
		private TextField  email;
                
                @FXML
		private PasswordField  password;      
		@FXML
		private TextField  nom;
             
                                	
               @FXML
    private ImageView image_view;
                                
		@FXML
		private DatePicker dateNaissance;
                @FXML
		private TextField  tel;
                @FXML
		private TextField  cin;
                
		user user;
                	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    UserService us;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         /* ObservableList<String> sexel
                = FXCollections.observableArrayList("Masculin", "Feminin");
        sexe.setItems(sexel);
        sexe.setValue("Masculin");*/
        // TODO
        affiche();
    }    
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

        String picture ="file:" +  u.getImage();
        
        Image image = new Image(picture, 110, 110, false, true);
        
        image_view.setImage(image);
        
        String path = u.getImage();
        
        file_path.setText(path);
        file_path.setOpacity(0);
        
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
                
        bsave.setDisable(false);
    }
         
         private void insert() {
	con =Datasource.getInstance().getCnx();
       String insert = "INSERT INTO user (`Cin`,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
        try {
            
            st = con.prepareStatement(insert);
            st.setString(1, cin.getText());
            st.setString(2, nom.getText());
            st.setString(3, prenom.getText());
            st.setString(4, tel.getText());
            st.setString(5, email.getText());
            st.setString(6, UserService.doHashing(password.getText()));
            st.setString(7, file_path.getText());
            file_path.setOpacity(0);
            
        String str2=dateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);
            st.setDate(8, date1);
          
            Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'utilisateur " +nom.getText()+"   "+prenom.getText()+" est ajouté avec succés");

		alert.showAndWait();
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
 
         
 
         
          private void update() {
	con =Datasource.getInstance().getCnx();
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
                st.setString(6, file_path.getText());
                
                      String str2=dateNaissance.getValue().toString();  
     Date date1=Date.valueOf(str2);
            st.setDate(7, date1);
            
           
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
         String delete = "DELETE FROM user  where idu = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idu.getText()));
            
            
            	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText(" utilisateur " +nom.getText()+"   "+prenom.getText()+" avec ID"+idu .getText()+" est supprimé avec succés");

		alert.showAndWait();
            
            
            
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    } 
         
    @FXML
    private void saveEvent(ActionEvent event) {
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
		
    
}
