
package Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import entities.EtatDispo;
import entities.Voiture;
import entities.Voyage_virtuel;
import entities.abonnement;
import entities.evenement;
import entities.hotel;
import entities.reservation_event;
import entities.reservation_hotel;
import entities.reservation_voiture;
import entities.user;
import entities.voyage;
import entities.voyage_organise;
import entities.sponsor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javafx.application.Application;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.reservation_voyage;
import services.abonnement.abonnement_Service;
import services.evenement.Serviceevenement;
import services.evenement.Servicereservation_event;
import services.hotel.HotelCRUD;
import services.reservation_hotel.Reserrvation_Hotel_Service;
import services.reservation_voiture.Reservation_voiture_Service;
import services.reservation_voyage.Reservation_Voyage_Service;
import services.sponsor.Servicesponsor;
import services.user.LoginAndSignupService;
import services.user.MailSender;
import services.user.UserService;
import services.voiture.VoitureCRUD;
import services.voyage.voyageService;
import services.voyage.voyage_organise.VoyageORG_Service;
import services.voyage.voyage_virtuel.VoyageVRT_Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing. EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;





import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static services.user.UserService.doHashing;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import java.util.Map;




import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import services.user.MailSender;

public class Metatrip  extends Application {

    /**
     * @param args the command line arguments
     */
    
    
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
    
    
    public static Stage stg;
    	@Override
	public void start(Stage primaryStage) throws IOException {
            
             Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/reservation_voyage.fxml"));
        Scene scene = new Scene(root, 1200, 650);
     this.stg=primaryStage;
        primaryStage.setTitle("Welcome to MetaTrip!");
        primaryStage.setScene(scene);
        primaryStage.show();
		/*try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/adminPanel/UserList.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/view/adminPanel/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
    
    
    
    
    public static void main(String[] args) throws Exception, WriterException, IOException,
               NotFoundException{
        // TODO code application logic here
        Datasource data = Datasource.getInstance();
        Datasource data2 = Datasource.getInstance();

        System.out.println(data.hashCode() + "-" + data2.hashCode());

        UserService us = new UserService();
        Reservation_Voyage_Service rvs=  new Reservation_Voyage_Service();
        
        

        
        voyageService vs= new voyageService();
        System.out.println( vs.getAllByID());
        
        VoyageORG_Service vos = new VoyageORG_Service();
        //vos.afficher().toString();
        abonnement_Service a = new abonnement_Service();
           VoyageVRT_Service vov = new VoyageVRT_Service();
        LoginAndSignupService loginSignup = new LoginAndSignupService();
        
        HotelCRUD hc=new HotelCRUD();
        String xx="2010-09-11";  
      String xx2="2011-10-01";  
     Date date=Date.valueOf(xx);
  Date date2=Date.valueOf(xx2);
  
          Date datedebut = Date.valueOf(xx);
        Date datefinal = Date.valueOf(xx2 );//converting string into sql date   
        voyage v2024 = new voyage(2024, "Madrid", "c://madrid.png");

        abonnement abon1 = new abonnement(2, "ss", 90, datedebut, datefinal, "Etat", 1);
        abonnement abon2 = new abonnement("ss", 90, datedebut, datefinal, "Etat", 1);
       
       // a.ajouter(abon1);
         Voyage_virtuel vov2 = new Voyage_virtuel("Videsso", "Image", abon1, v2024);
          //vov.afficher();
             //vov.ajouter(vov2);   
       //  vov.modifier(3,vov2);
        // vov.supprimer(3);

        user u1 = new user(9999, "5866", "dafdouf", "zakzouk", "5895", "zak@live.fr", "0000", "image",date2);
         user u8 = new user(811,"199525", "ssss", "cxx", "2568435", "fares.lamloum@esprit.tn", doHashing("12345678"), "image",date2);
         //MailSender.sendMail(u8);
         //us.ajouter(u8);
     
         // MailSender.sendMail(u8);
         //us.ajouter(u8);
        //us.supprimer(810);
        user u3 = new user("5866", "ges", "nay", "5895", "zak@live.fr", "0000", "image",date2);
        user u4 = new user("58656", "khaldi", "imen", "5895", "zak@live.fr", "0000", "image",date2);

       voyage v = new voyage(501,"tounis", "c://berlin.png");
             voyage chrf = new voyage(967,"istanbul", "c://antalya.png");
        voyage v1 = new voyage( 605,"gafsa", "c://beja.png");
        //System.out.println(us.afficherById(u1));
   //vs.ajout(v1); 
   
     //vs.ajout(v1); 
        //System.out.println(vs.afficher());
               // System.out.println( v1.getPays());
       // System.out.println(v.getIdv());

                                //voyage_organise vo2=new voyage_organise(5,50.6f,"Lufthansa",15,5,"torkiya","c://assets"); 
     voyage_organise vom = new voyage_organise(170.6f, "aazaa", 3,EtatDispo.INDISPO, v1);
          //voyage_organise vo4 = new voyage_organise(10.6f, "flam", 3, v);
               // voyage_organise vom = new voyage_organise(990.6f, "sounay", 3);
                //vos.ajouter(vom);
        //vos.modifier(88, vom);
        System.out.println("List voyages par date croissante"+ us.VoyageParDates());
           System.out.println("Le nombre de users est = "+us.nbUsers());
                //vos.ajouter(vo3);
      //  System.out.println("List voyages par date croissante"+ us.VoyageParDates());
         //  System.out.println("Le nombre de users est = "+us.nbUsers());

                     // System.out.println("Le nombre de voyages disponibles est = "+us.nbVoyagesDispo());
//System.out.println("userByEmail:"+us.getUserByEmail("nex@live.fr"));
     String str="2020-09-01";  
      String str2="2050-09-01";  
     Date date1=Date.valueOf(str);
     Date date9=Date.valueOf(str2);//converting string into sql date      System.out.println(date);
               // user u2 = new user(99925, "ssss", "cxx", 2568435, "bensaid.mohamedali@esprit.tn", "ghassen123", "image",date9);
             
              LoginAndSignupService LASS =new LoginAndSignupService();
             //LASS.Signup(u2);
         //   MailSender.sendMail(u2);
       //  MailSender.sendFacture("fares.lamlou@esprit.tn");

             //   user u2 = new user("99925", "ssss", "cxx", "2568435", "fares@live.fr", doHashing("12345678"), "image",date9);
           //   LoginAndSignupService LASS =new LoginAndSignupService();
              //LASS.Signup(u2);

          //    System.out.println(LASS.login("nex@live.fr", "aaaa"));
           //    us.ajouter(u1);
         reservation_voyage rv=new reservation_voyage();
      //    rv.setIdrv(999);
   
       /*   rv.setDate_depart(date);
         rv.setDate_arrivee(date2);
         rv.setEtat("Paye");
          
         rv.setRef_paiement(1);
         rv.setUser(u2);
         rv.setVoyage(chrf);       */
        
       //rvs.ajouter(rv);  
        //rvs.modifier(10, rv);
        //rvs.supprimer(6);
       //System.out.println(rvs.afficher().toString());
         
         
        //System.out.println(vo3.getVoyage());
     
      // vos.ajouter(vo4);
    
       
       //vs.modifier(97,chrf);
    //vs.supprimer(360);
     
      //System.out.println(vos.afficher()); 
                    //  System.out.println(vo3.toString());
       
        //us.modifier(1,u3);
        // System.out.print(p.getId());
        //us.supprimer(4);
        //ps.modifier(p);

  // System.out.print("ss"+us.afficher());
        //System.out.print(loginSignup.login("'zak@live.fr'", "0000"));
      Voiture v99 =new Voiture(95,"220TU120",12,"image","Mercedes");
        VoitureCRUD VC=new VoitureCRUD();
      //VC.ajouterVoiture(v99);
             Voiture v98 =new Voiture(3,"220TU120",12,"image","bmw");
        //VC.modifierVoiture(v98);
               reservation_voiture rhv=new reservation_voiture();
                  Reservation_voiture_Service rvs55= new Reservation_voiture_Service();
              /* rhv.setChauffeur("mohamed salah");
              rhv.setPrix_rent((float) 5.5);
              rhv.setTrajet("jerba");
              rhv.setUser(u2);
               rhv.setVoiture(v99);*/
            
                 user u18 = new user(811,"199525", "ssss", "cxx", "2568435", "nex@live.fr", doHashing("12345678"), "image",date2);
              // us.ajouter(u18);
                            Voiture v78 =new Voiture(2001,"220TU120",12,"image","bmw");
                             //     VC.ajouterVoiture(v78);
                                  
//    reservation_voiture rhv1=new reservation_voiture(5.5f,"cccc","jandouba",u8,v78);
  
              
            
            //rvs55.ajouter(rhv1);
              // rvs55.modifier(2,rhv1);
              //rvs55.supprimer(2);
            // rvs55.afficher().toString();
            
            
            
            hotel h=new hotel("4 seasons",4,"gammarth");
            hotel h2=new hotel(12,"gulden tulip",4,"gammarth");
         //  hc.ajouterHotel(h2);
           // hc.modifierHotel(h2);
           // hc.supprimerHotel(h2);
           Reserrvation_Hotel_Service rhs=new Reserrvation_Hotel_Service ();

              //    reservation_hotel rh =new reservation_hotel("single",2,1,2.2f,u8,h2,date1,date9);
          // rhs.ajouter(rh);
                  // rhs.modifier(2, rh);
                  // rhs.supprimer(2);
                   
                    //  System.out.println("les voyages les plus Réservé:"+rvs.ListVoyagePlusRéservé());
        //System.out.println ("le maximum de voyage Réserve est "+rvs.LeMaximumDevoyageReserve());
          //   System.out.println ("le minimum de voyage Réserve est "+rvs.LeMinimumevoyageReserve());
            //         System.out.println ("la list  de voyage Trié selon Prix est "+rvs.listedevoyageTrierParPrix());
                   
  
 Serviceevenement se = new Serviceevenement() ; 
 evenement e = new evenement(2, "hhhuhonl", "c", "7 rue 2938", date1, 12.0f) ; 
  evenement e1 = new evenement(4, "aaaaaaa", "c", "7 rue 2938", date1, 120.0f) ; 
  //se.ajouter(e1);
    //se.modifier(e);
  // se.supprimer(1);
  // System.out.println(se.afficher());
   user ux = new user(817,"195", "youssef", "cheour", "256845", "youssef.cheour@esprit.tn", "aaaa", "image",date);
   //us.ajouter(ux);
  Servicereservation_event resE=new Servicereservation_event();
  reservation_event resev=new reservation_event(22,82,e,ux);

//        System.out.println(data.hashCode() + "-" + data2.hashCode());
//
//        UserService us = new UserService();
//        Reservation_Voyage_Service rvs=  new Reservation_Voyage_Service();
//        
//        voyageService vs= new voyageService();
//        
//        VoyageORG_Service vos = new VoyageORG_Service();
//        abonnement_Service a = new abonnement_Service();
//           VoyageVRT_Service vov = new VoyageVRT_Service();
//        LoginAndSignupService loginSignup = new LoginAndSignupService();
//        
//        HotelCRUD hc=new HotelCRUD();
//        String xx="2010-09-11";  
//      String xx2="2011-10-01";  
//     Date date=Date.valueOf(xx);
//  Date date2=Date.valueOf(xx2);
//  
//          Date datedebut = Date.valueOf(xx);
//        Date datefinal = Date.valueOf(xx2 );//converting string into sql date   
//        voyage v2024 = new voyage(2024, "Madrid", "c://madrid.png");
//
//        abonnement abon1 = new abonnement(2, "ss", 90, datedebut, datefinal, "Etat", 1);
//        abonnement abon2 = new abonnement("ss", 90, datedebut, datefinal, "Etat", 1);
//       
//       // a.ajouter(abon1);
//         Voyage_virtuel vov2 = new Voyage_virtuel("Videsso", "Image", abon1, v2024);
//          //vov.afficher();
//             //vov.ajouter(vov2);   
//       //  vov.modifier(3,vov2);
//        // vov.supprimer(3);
//
//        user u1 = new user(9999, 5866, "dafdouf", "zakzouk", 5895, "zak@live.fr", "0000", "image",date2);
//         user u8 = new user(811,199525, "ssss", "cxx", 2568435, "nex@live.fr", doHashing("12345678"), "image",date2);
//         us.ajouter(u8);
//        //us.supprimer(810);
//        user u3 = new user(5866, "ges", "nay", 5895, "zak@live.fr", "0000", "image",date2);
//        user u4 = new user(58656, "khaldi", "imen", 5895, "zak@live.fr", "0000", "image",date2);
//
//       voyage v = new voyage(501,"tounis", "c://berlin.png");
//             voyage chrf = new voyage(967,"istanbul", "c://antalya.png");
//        voyage v1 = new voyage( 605,"gafsa", "c://beja.png");
//   //vs.ajout(v1); 
//   
//     //vs.ajout(v1); 
//      //  System.out.println(vs.afficher());
//               // System.out.println( v1.getPays());
//       // System.out.println(v.getIdv());
//
//                                //voyage_organise vo2=new voyage_organise(5,50.6f,"Lufthansa",15,5,"torkiya","c://assets"); 
//     voyage_organise vo3 = new voyage_organise(170.6f, "nexdd", 3,EtatVoyage.INDISPO, v1);
//          //voyage_organise vo4 = new voyage_organise(10.6f, "flam", 3, v);
//               // voyage_organise vom = new voyage_organise(990.6f, "sounay", 3);
//                //vos.ajouter(vo3);
//        System.out.println("List voyages par date croissante"+ us.VoyageParDates());
//           System.out.println("Le nombre de users est = "+us.nbUsers());
//
//                      System.out.println("Le nombre de voyages disponibles est = "+us.nbVoyagesDispo());
//
String strd="2020-09-01";  
//      String str2="2050-09-01";  
 Date datex=Date.valueOf(str);
//     Date date9=Date.valueOf(str2);//converting string into sql date      System.out.println(date);
//                user u2 = new user(99925, "ssss", "cxx", 2568435, "fares@live.fr", doHashing("12345678"), "image",date9);
//           //   LoginAndSignupService LASS =new LoginAndSignupService();
//              //LASS.Signup(u2);
//          //    System.out.println(LASS.login("nex@live.fr", "aaaa"));
//           //    us.ajouter(u1);
//        // reservation_voyage rv=new reservation_voyage();
//      //    rv.setIdrv(999);
//   
//       /*   rv.setDate_depart(date);
//         rv.setDate_arrivee(date2);
//         rv.setEtat("Paye");
//          
//         rv.setRef_paiement(1);
//         rv.setUser(u2);
//         rv.setVoyage(chrf);       */
//        
//       //rvs.ajouter(rv);  
//        //rvs.modifier(10, rv);
//        //rvs.supprimer(6);
     System.out.println(rvs.afficher());
//         
//         
//        //System.out.println(vo3.getVoyage());
//     
//      // vos.ajouter(vo4);
//       //vos.modifier(79, vom);
//       
//       //vs.modifier(97,chrf);
//    //vs.supprimer(360);
//     
System.out.println(vos.afficher().toString()); 
//                    //  System.out.println(vo3.toString());
//       
//        //us.modifier(1,u3);
//        // System.out.print(p.getId());
//        //us.supprimer(4);
//        //ps.modifier(p);
//
//  // System.out.print("ss"+us.afficher());
//        //System.out.print(loginSignup.login("'zak@live.fr'", "0000"));
//      Voiture v99 =new Voiture(95,"220TU120",12,"image","Mercedes");
//        VoitureCRUD VC=new VoitureCRUD();
//      //VC.ajouterVoiture(v99);
//             Voiture v98 =new Voiture(3,"220TU120",12,"image","bmw");
//        //VC.modifierVoiture(v98);
//               reservation_voiture rhv=new reservation_voiture();
//                  Reservation_voiture_Service rvs55= new Reservation_voiture_Service();
//              /* rhv.setChauffeur("mohamed salah");
//              rhv.setPrix_rent((float) 5.5);
//              rhv.setTrajet("jerba");
//              rhv.setUser(u2);
//               rhv.setVoiture(v99);*/
//            
//                 user u18 = new user(811,199525, "ssss", "cxx", 2568435, "nex@live.fr", doHashing("12345678"), "image",date2);
//              // us.ajouter(u18);
//                            Voiture v78 =new Voiture(2001,"220TU120",12,"image","bmw");
//                             //     VC.ajouterVoiture(v78);
//                                  
//    reservation_voiture rhv1=new reservation_voiture(5.5f,"cccc","jandouba",u8,v78);
//  
//              
//            
//            //rvs55.ajouter(rhv1);
//              // rvs55.modifier(2,rhv1);
//              //rvs55.supprimer(2);
//            // rvs55.afficher().toString();
//            
//            
//            
//            hotel h=new hotel("4 seasons",4,"gammarth");
//            hotel h2=new hotel(12,"gulden tulip",4,"gammarth");
//         //  hc.ajouterHotel(h2);
//           // hc.modifierHotel(h2);
//           // hc.supprimerHotel(h2);
//           Reserrvation_Hotel_Service rhs=new Reserrvation_Hotel_Service ();
//
//                   reservation_hotel rh =new reservation_hotel("single",2,1,2.2f,u8,h2,date1,date9);
//          // rhs.ajouter(rh);
//                  // rhs.modifier(2, rh);
//                  // rhs.supprimer(2);
//                   
//                      System.out.println("les voyages les plus Réservé:"+rvs.ListVoyagePlusRéservé());
//        System.out.println ("le maximum de voyage Réserve est "+rvs.LeMaximumDevoyageReserve());
//             System.out.println ("le minimum de voyage Réserve est "+rvs.LeMinimumevoyageReserve());
//                     System.out.println ("la list  de voyage Trié selon Prix est "+rvs.listedevoyageTrierParPrix());
//                   
//  
// Serviceevenement se = new Serviceevenement() ; 
evenement ex = new evenement(2, "hhhuhonl", "c", "7 rue 2938", date1, 12.0f) ; 
//  evenement e1 = new evenement(4, "aaaaaaa", "c", "7 rue 2938", date1, 120.0f) ; 
//  //se.ajouter(e1);
//    //se.modifier(e);
//  // se.supprimer(1);
//  // System.out.println(se.afficher());
//  user ux = new user(817,195, "nex", "nex", 256845, "nex@live.fr", "aaaa", "image",date);
//   //us.ajouter(ux);
//  Servicereservation_event resE=new Servicereservation_event();
//  reservation_event resev=new reservation_event(22,82,e,ux);

  //resE.ajout(resev);
 // resE.modifier(22,resev);
  //resE.supprimer(22);
// us.factureuser(rvs.affichervoyageByid(10));
     //System.out.println(resE.afficher());



       String grCodeData ="https://pbs.twimg.com/profile_images/1118720684950085632/Qc9LxLu0_400x400.png";
         String filePath = "C:\\Users\\medal\\OneDrive\\Bureau\\Metatrip_git\\MetaTrip_Java\\MetaTrip_Java\\metaFinal\\src\\services\\user\\websiteQRCode_noFrame.png";
    String charset ="UTF-8"; // or "ISO-8859-1";
   
   
  /*
try{
    Map <EncodeHintType,ErrorCorrectionLevel> ss = new Hashtable < EncodeHintType, ErrorCorrectionLevel > ();
    ss.put (EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
    BitMatrix matrix =new MultiFormatWriter().encode(new String(grCodeData.getBytes(charset), charset),
        BarcodeFormat.QR_CODE, 200, 200, (Hashtable) ss);
    MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
        .lastIndexOf ('.') + 1), new File(filePath));
 
    System.out.println("QR Code image created successfully!");
  }catch(Exception ex){
            System.err.println(ex.getMessage());
        }*/


        



  
   launch(args);              

     
     
     // GESTION SPONSOR 
     String x2="2011-10-01";  
     Date date_sp=Date.valueOf(xx2);
     sponsor s = new sponsor(25, "Vitalait", "22252718", "amine@zarga.tn", date_sp, 12.0f,e) ; 
     Servicesponsor ss = new Servicesponsor(); 
     
    // ss.ajouter(s);
                   
          
        // The data that the QR code will contain
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