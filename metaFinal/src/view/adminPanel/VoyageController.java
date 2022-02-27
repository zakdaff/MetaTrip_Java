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
import entities.voyage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.user.UserService;
import services.voyage.voyageService;

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
                private Button blink;
UserListController ul;
		
		@FXML
		private TableView<voyage> table;
		
		@FXML
		private TableColumn<voyage, Integer> uidv;
                
                
		@FXML
		private TableColumn<voyage, String> unom;

                @FXML
		private TableColumn<voyage, String> uimage;
                                
	
                
		
                
                //*********si flam******************//
                @FXML
		private TextField  idv;
                
                
		@FXML
		private TextField  pays;
                
                 	
                @FXML
		private TextField  image_pays;
                                
	
                
		voyage voyage;
                	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    voyageService vs;
    
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
        voyage v = table.getSelectionModel().getSelectedItem();
              idv.setText(String.valueOf(v.getIdv()));
        pays.setText(v.getPays());

         image_pays.setText(v.getImage_pays());
        
       // bsave.setDisable(true);
    }
    
    
  
  
     
    public ObservableList<voyage> getVoyage(){
         ObservableList<voyage> list = FXCollections.observableArrayList();
		
				con =Datasource.getInstance().getCnx();
				String select="select * from voyage v ;";
			
				 try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
          voyage v=new voyage();
                v.setIdv(rs.getInt("idv"));
                v.setPays(rs.getString("pays"));
                
                v.setImage_pays(rs.getString("image_pays"));
                list.add(v);
            }}
         catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;}
 
      public void affiche() {
        ObservableList<voyage> list = getVoyage();
        uidv.setCellValueFactory(new PropertyValueFactory<voyage, Integer>("idv"));
        unom.setCellValueFactory(new PropertyValueFactory<voyage, String>("pays"));
       
         uimage.setCellValueFactory(new PropertyValueFactory<voyage, String>("image_pays"));
  
        
        table.setItems(list);
 
    }
      
         void clear() {
        idv.setText(null);

        pays.setText(null);

        image_pays.setText(null);
        //sexe.getSelectionModel().selectFirst();
        bsave.setDisable(false);
    }
         
         private void insert() {
	con =Datasource.getInstance().getCnx();
       String insert = "INSERT INTO voyage (`pays`,`Image_pays`) VALUES (?,?) ;";
        try {
            
            st = con.prepareStatement(insert);
            st.setString(1, pays.getText());
            st.setString(2, image_pays.getText());
     
          
            Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Adding voyage");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'voyage de pays  " +pays.getText()+" est ajouté avec succés");

		alert.showAndWait();
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
 
         
 
         
          private void update() {
	con =Datasource.getInstance().getCnx();
            String update = "UPDATE `voyage` SET "
                +"`pays`=?,`image_pays`=? "
                + "WHERE `idv` =? ;";
        try {
                st = con.prepareStatement(update);
                st.setString(1, pays.getText());
                st.setString(2, image_pays.getText());
             
                st.setInt(3, Integer.parseInt(idv.getText()));
            
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
    Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Updating voyage");

		// Header Text: null
		alert.setHeaderText(null);
	alert.setContentText("'voyage de pays  " +pays.getText()+" est modifié avec succés");
		alert.showAndWait();
                
                st.executeUpdate();
                affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
         
         public void delete() {
         con =Datasource.getInstance().getCnx();
        String delete = "DELETE FROM voyage  where idv = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(idv.getText()));
            
            
            	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Deleting user");

		// Header Text: null
		alert.setHeaderText(null);
	alert.setContentText("'voyage de ID  "+ idv.getText()+"  est supprimé avec succés");
		alert.showAndWait();
            
            
            
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
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
public void link(ActionEvent event) throws Exception {               
    try {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/adminPanel/UserList.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
           Metatrip.stg.close();    
    } catch(Exception e) {
        e.printStackTrace();
    }
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
