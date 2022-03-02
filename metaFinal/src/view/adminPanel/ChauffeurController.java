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
import entities.Chauffeur;
import entities.user;
import java.io.File;
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
public class ChauffeurController implements Initializable {
  
    
                    
		@FXML
		private TableView<Chauffeur> table;
		
		@FXML
		private TableColumn<Chauffeur, Integer> uidch;
                
                
		@FXML
		private TableColumn<Chauffeur, String> unom;
		@FXML
		private TableColumn<Chauffeur, String> uprenom;
		@FXML
		private TableColumn<Chauffeur, String> uphoto;
                
                //@FXML
		//private TableColumn<user, String> password;
                                	
                @FXML
		private TableColumn<Chauffeur, String> utel;
                                
		@FXML
		private TableColumn<Chauffeur, String> udescription;
                @FXML
		private TableColumn<Chauffeur, String> uetatDispo;
       
                
		@FXML
		private TextField  idch;
                
             
		@FXML
		private TextField  prenom;
                @FXML
		private TextField  description;
                
		@FXML
		private TextField  nom;
                @FXML
		private TextField  tel;
                
                @FXML
		private TextField  photo;      
		@FXML
		private TextField  etatDispo;
             
                
                //*********si flam******************//
             
                
		Chauffeur chauffeur;
                	
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
        Chauffeur u = table.getSelectionModel().getSelectedItem();
        idch.setText(String.valueOf(u.getidch()));
        nom.setText(u.getnom());
        
        prenom.setText(u.getprenom());
        //sexe.getSelectionModel().select(et.getSexe());
        tel.setText(u.gettel());
        photo.setText(u.getphoto());
      description.setText(u.getdescription());
      etatDispo.setText(u.getetatDispo());

        
       // bsave.setDisable(true);
    }
     
    public ObservableList<Chauffeur> getChauffeur(){
         ObservableList<Chauffeur> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select * from chauffeur ;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Chauffeur u = new Chauffeur();
                u.setidch(rs.getInt("idch"));
                u.setnom(rs.getString("nom"));
                
                u.setprenom(rs.getString("prenom"));
                u.setphoto(rs.getString("photo"));
                u.settel(rs.getString("tel"));
                u.setdescription(rs.getString("description"));
                u.setetatDispo(rs.getString("etatDispo"));
             
                list.add(u);
            }}
         catch (SQLException ex) {
            Logger.getLogger(UserListController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;}
 
      public void affiche() {
        ObservableList<Chauffeur> list = getChauffeur();
        uidch.setCellValueFactory(new PropertyValueFactory<Chauffeur, Integer>("idch"));
           unom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     
        uprenom.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("prenom"));
         uphoto.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("photo"));
        utel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        udescription.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("description"));
         uetatDispo.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("etatDispo"));
    
        
        table.setItems(list);
 
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
