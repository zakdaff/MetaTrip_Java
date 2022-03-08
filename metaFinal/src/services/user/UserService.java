/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import Config.Datasource;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

//import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

import entities.reservation_voyage;
import entities.voyage;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import services.IService;
import services.IService;
import services.reservation_voyage.Reservation_Voyage_Service;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

  
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import entities.reservation_voiture;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import services.sponsor.Servicesponsor;


/**
 *
 * @author FLAM
 */
public class UserService implements IuserService {
    



    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public UserService() {
        conn = Datasource.getInstance().getCnx();
    }
 
   
 
    @Override
    public void ajouter(user u) {

              List<user> users = new ArrayList<>();
      String Email1=u.getEmail();
      String pass1=u.getPassword();
    //  System.out.println(Email1);
          // System.out.println(pass1);
      String password=u.getPassword();
      String req = "SELECT *  FROM user where Email='"+Email1+"'";
   
        try {

       ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
              user u1 = new user();
                u1.setIdu(rs.getInt(1));
                  u1.setCin( rs.getString(2));
                u1.setNom(rs.getString(3));
                u1.setPrenom(rs.getString(4));      
                    u1.setTel(rs.getString(5));
            
              u1.setEmail( rs.getString(6));
               u1.setPassword(rs.getString(7));
                u1.setImage(rs.getString(8));
                u1.setRole(rs.getInt(9));
                u1.setDateNaissance(rs.getDate(10));
                 users.add(u) ; 
                     
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                    System.out.println("+++++++size++++++++"+users.size());
                                    if(users.size()==0){
                                        
                                        

                                                         String req10 = "INSERT INTO user (Cin,`Nom`,`Prenom`,`Tel`,`Email`,`Password`,`Image`,`dateNaissance`) VALUES (?,?,?,?,?,?,?,?) ;";
                                                       try {
                                                           pste = conn.prepareStatement(req10);


                                                           pste.setString(1,u.getCin());
                                                           pste.setString(2, u.getNom());
                                                           pste.setString(3, u.getPrenom());
                                                            pste.setString(4,u.getTel());
                                                             pste.setString(5, u.getEmail());
                                                              pste.setString(6, doHashing(u.getPassword()));
                                                               pste.setString(7, u.getImage());
                                                                 pste.setDate(8, u.getDateNaissance());
                                                           pste.executeUpdate();
                                                           System.out.println("user créée");
                                                       } catch (SQLException ex) {
                                                           Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                                                       }
                                                       
                                    }
                                                       else{
                                             System.out.print("user deja exist ");

                                    }
    }
    

    public int  afficherUserByRole(String Email) {
     
        int Role = 0 ;
        String req = "SELECT`Role` FROM `user` WHERE `Email`='"+Email+"';";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            
                Role =rs.getInt(1);
                                                       
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Role;
    }
    
       @Override
    public void modifier( int id ,user u)    {
     
 
        String req = "UPDATE `user` SET "
                +"`Cin`=?,`Nom`=?,`Prenom`=?,`Tel`=?,`Email`=?,`Password`=?,`Image`=?,`dateNaissance`=?"
                + "WHERE idu = '" + id+ "'";
    
        try {
            pste = conn.prepareStatement(req);
           pste.setString(1,u.getCin());
            pste.setString(2, u.getNom());
            pste.setString(3, u.getPrenom());
             pste.setString(4,u.getTel());
              pste.setString(5, u.getEmail());

               pste.setString(6, doHashing(u.getPassword()));
                pste.setString(7, u.getImage());
                pste.setDate(8, u.getDateNaissance());
            pste.executeUpdate();
            System.out.println("user modifie");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void supprimer(int id )  {
      String delete = "DELETE FROM user  where idu = ?";
        try {
            pste = conn.prepareStatement(delete);
            pste.setInt(1,id);
            pste.executeUpdate();
            System.out.println("user supprimé");
        } catch (SQLException ex) {
                  
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);        
       
        }
    
    }

    @Override
    public List<user> afficher() {
        List<user> users = new ArrayList<>();
        String req = "SELECT * FROM `user`";
        
        try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                user u = new user();
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                   u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;                                        
                                                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public int nbUsers() throws SQLException {
    int nb=0;
          String req = "SELECT count(*) from `user` ;";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            nb=rs.getInt(1);
                                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;
    }

    @Override
    public int nbVoyagesDispo() throws SQLException {
   int nb=0;
   
          String req = "SELECT count(*) from `voyage_organise` where etatVoyage='DISPO';";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
            nb=rs.getInt(1);
                                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nb;    }
    
     public user getUserByID(int id)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Idu="+id+";";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                   u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;    
    }
     
     
      public    List<user> getuserbycin(int cin)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Cin="+cin+";";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                   u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;    
    }
      public  List<Integer> gelallID()  {
     List<Integer> ID = new ArrayList<>();
     
   
          String req = "SELECT Idu  from `user`";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
            
               
                 ID.add(rs.getInt(1));                                   
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ID;    
    }
    public user getUserByEmail (String Email)  {
     List<user> users = new ArrayList<>();
      user u = new user();
   
          String req = "SELECT * from `user` where Email='"+Email+"';";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
          
                  
                u.setIdu(rs.getInt(1));
                  u.setCin( rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));      
                    u.setTel(rs.getString(5));
            
              u.setEmail( rs.getString(6));
               u.setPassword(rs.getString(7));
                u.setImage(rs.getString(8));
                   u.setRole(rs.getInt(9));
                u.setDateNaissance(rs.getDate(10));
                 users.add(u) ;    
              
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
           System.out.println("1111111110"+u);
        return u;    
    }
    

    @Override
    public List<?> VoyageParDates() throws SQLException {

List<Object> voyages = new ArrayList<>();   
          String req ="SELECT v.idv,v.pays,v.image_pays,rv.date_depart FROM `voyage` v,`reservation_voyage`  rv,`voyage_organise` vo"+
                 " where v.Idv=rv.Idv and vo.Idv=v.Idv and vo.etatVoyage='DISPO' "+ 
                    "group by Date_depart;";
            try {

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
              while(rs.next()){
                  
                voyage v = new voyage();
                reservation_voyage rv=new reservation_voyage();
                v.setIdv(rs.getInt(1));
                v.setPays( rs.getString(2));
                v.setImage_pays(rs.getString(3));
                rv.setDate_depart(rs.getDate(4));

                 voyages.add(v) ; 
                  voyages.add(rv.getDate_depart()) ; 
                                                   
            }
            
            
            }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return voyages;  
    
    }
    

    public void factureuser (reservation_voyage rv,user ux) throws WriterException, IOException{
              String xx="2010-09-11";  
      String xx2="2011-10-01";  
     String x2="2011-10-01";  
     System.out.println("getIdv"+rv.getVoyage().getIdv());
          System.out.println("getIdu"+rv.getUser().getIdu());
     Date date_sp=Date.valueOf(xx2);
              ArrayList table= new ArrayList <>();
          
     // GESTION SPONSOR 
     
 
     Servicesponsor ss = new Servicesponsor(); 
     
    // ss.ajouter(s);
                   
          
        // The data that the QR code will contain
        String dataz = ux.toString();
            
                   String path = "C:\\Users\\medal\\OneDrive\\Bureau\\Metatrip_git\\MetaTrip_Java\\MetaTrip_Java\\demo.png";
 
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType,
                          ErrorCorrectionLevel>();
 
        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
 
        // Create the QR code and save
        // in the specified folder
        // as a jpg file
                // Encoding charset
        String charset = "UTF-8";
        
        createQR(dataz, path, charset, hashMap, 200, 200);
        System.out.println("QR Code Generated!!! ");   
        // Encoding charset
       
              try {
                 
          Document document=new Document ();
  
                 PdfWriter.getInstance(document, new FileOutputStream("C:/Users/medal/OneDrive/Bureau/Metatrip_git/MetaTrip_Java/MetaTrip_Java/MetatripVoitureFacture.pdf"));
            document.open();
              Image image = Image.getInstance(path);
         
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
                            pdfPTable.addCell (""+ux.getNom()+""+ux.getPrenom()+"");
                              pdfPTable.addCell (""+rv.getVoyage().getIdv()+"");
                           pdfPTable.addCell (""+rv.getRef_paiement()+"");
                          document.add(pdfPTable);
             document.add(image);
                        document.close();
                        document.close ();

        } catch (Exception Exception) {
         System.out.println(Exception);
 }
              
              
    }
  
    
  
     public static void createQR(String data, String path,
                                String charset, Map hashMap,
                                int height, int width)
        throws WriterException, IOException
    {
 
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, width, height);
 
        MatrixToImageWriter.writeToFile(
            matrix,
            path.substring(path.lastIndexOf('.') + 1),
            new File(path));
    }
    
  
   public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
            
         


}
