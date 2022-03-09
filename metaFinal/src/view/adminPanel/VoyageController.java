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
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
                private Button buser;
UserListController ul;

                 @FXML
    private AnchorPane left_main;
		
		@FXML
		private TableView<voyage> table;
		
		@FXML
		private TableColumn<voyage, Integer> uidv;
                
                
		@FXML
		private TableColumn<voyage, String> unom;

                @FXML
	
                private TableColumn<voyage, String> uimage;
   
		    @FXML
    private Label file_path;
                
		
                
                //*********si flam******************//
                @FXML
		private TextField  idv;

                
		@FXML
		private TextField  pays;
                

                @FXML
		private TextField  image_pays;
                
                
                
	             @FXML
    private ImageView image_view;
                
		voyage voyage;
                	
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement st=null;
 
    voyageService vs;
               
    
    @FXML
    private Button insert_image;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         /* ObservableList<String> sexel
                = FXCollections.observableArrayList("Masculin", "Feminin");
        sexe.setItems(sexel);
        sexe.setValue("Masculin");*/
        // TODO
        uidv.setVisible(false);
        affiche();
    }  
    
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
    
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        voyage v = table.getSelectionModel().getSelectedItem();
              idv.setText(String.valueOf(v.getIdv()));
        pays.setText(v.getPays());
  String picture ="file:" + v.getImage_pays();
         Image image = new Image(picture, 110, 110, false, true);
        
        image_view.setImage(image);
        
        String path = v.getImage_pays();
        
        file_path.setText(path);
        file_path.setOpacity(0);
        
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

         image_view.setImage(null);
        //sexe.getSelectionModel().selectFirst();
        bsave.setDisable(false);
    }
         
         private void insert() {
	con =Datasource.getInstance().getCnx();
       String insert = "INSERT INTO voyage (`pays`,`Image_pays`) VALUES (?,?) ;";
       ArrayList<voyage> v = new ArrayList<voyage>();
           System.out.println("size v++++++++++++++++++++++++++"+pays.getText());
       if((!pays.getText().isEmpty())||(!file_path.getText().isEmpty())){
        try {
            
            st = con.prepareStatement(insert);
            
         
        
            //st.setString(3, sexe.getSelectionModel().getSelectedItem());
            voyageService vs = new voyageService();
         v= (ArrayList<voyage>) vs.afficherByPays(pays.getText());
     
         if(v.size()==0){
                st.setString(1, pays.getText());
         file_path.setOpacity(0);
     
            st.setString(2, file_path.getText().toString());
           st.executeUpdate();
           
            Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Adding voyage");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setContentText("'voyage de pays  " +pays.getText()+" est ajouté avec succés");

		alert.showAndWait();
         }else{
                Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("voyage est déjà existe");

		// Header Text: null
		alert.setHeaderText(null);
		

		alert.showAndWait();
         }
          
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }}else{
         Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Form error");

		// Header Text: null
		alert.setHeaderText(null);
		alert.setTitle("Form error");

		alert.showAndWait();
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
               st.setString(2, file_path.getText());
             
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
		
    
}
