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
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.user.UserService;

/**
 * FXML Controller class
 *
 * @author FLAM
 */
public class VoyageController implements Initializable {
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
                
		
                
                //*********si flam******************//
                @FXML
		private TextField  idu;
                
                
		@FXML
		private TextField  nom;
                
		@FXML
		private TextField  prenom;
                
		@FXML
		private TextField  email;
                
                @FXML
		private TextField  password;
                                	
                @FXML
		private TextField  image;
                                
		@FXML
		private TextField dateNaissance;
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
         dateNaissance.setText(String.valueOf(u.getDateNaissance()));
         image.setText(u.getImage());
        
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
        dateNaissance.setText(null);
        image.setText(null);
        //sexe.getSelectionModel().selectFirst();
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
            st.setString(7, image.getText());
            st.setString(8, dateNaissance.getText());
          
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
                st.setString(6, image.getText());
                st.setString(7, dateNaissance.getText());
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
            
            
            	Alert alert = new Alert(AlertType.INFORMATION);
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
