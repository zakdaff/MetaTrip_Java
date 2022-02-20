package metatrip;

import entities.Voiture;
import entities.hotel;
import entities.reservation_hotel;
import entities.reservation_voiture;
import entities.user;
import entities.reservation_voyage;
import entities.voyage;
import entities.voyage_organise;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import services.HotelCRUD;
import services.LoginAndSignupService;
import services.Reserrvation_Hotel_Service;
import services.Reservation_Voyage_Service;
import services.Reservation_voiture_Service;
import services.UserService;
import services.VoitureCRUD;
import services.VoyageORG_Service;
import services.voyageService;
import utilis.Datasource;

/**
 *
 * @author FLAM
 */
public class Metatrip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Datasource data = Datasource.getInstance();
        Datasource data2 = Datasource.getInstance();
        System.out.println(data.hashCode() + "-" + data2.hashCode());

        UserService us = new UserService();
        Reservation_Voyage_Service rvs=  new Reservation_Voyage_Service();
        
        voyageService vs= new voyageService();
        
        VoyageORG_Service vos = new VoyageORG_Service();

        LoginAndSignupService loginSignup = new LoginAndSignupService();
        user u = new user(125, "flam", "fares", 256845, "flam@live.fr", "0000", "image");

        user u1 = new user(5, 5866, "dafdouf", "zakzouk", 5895, "zak@live.fr", "0000", "image");
        user u3 = new user(5866, "ges", "nay", 5895, "zak@live.fr", "0000", "image");
        user u4 = new user(58656, "khaldi", "imen", 5895, "zak@live.fr", "0000", "image");

        voyage v = new voyage(369,"tounis", "c://maroc.png");
             voyage chrf = new voyage(97,"istanbul", "c://antalya.png");
        //voyage v1 = new voyage( 18,"espagne", "c://espagne.png");
    // vs.ajoutv(v); 
      //vs.ajoutv(v1); 
               // System.out.println( v1.getPays());
       // System.out.println(v.getIdv());
                //voyage_organise vo=new voyage_organise(2,150.6f,"Turkish Airlines",5,5,"torkiya","c://assets"); 

                                //voyage_organise vo2=new voyage_organise(5,50.6f,"Lufthansa",15,5,"torkiya","c://assets"); 
     voyage_organise vo3 = new voyage_organise(170.6f, "nex", 3, v);
          voyage_organise vo4 = new voyage_organise(10.6f, "flam", 3, v);
                voyage_organise vom = new voyage_organise(990.6f, "sounay", 3);
                
             
       
     String str="2015-03-31";    
     Date date=Date.valueOf(str);//converting string into sql date      System.out.println(date);
                user u2 = new user(26,195, "ssss", "cxx", 256845, "fares@live.fr", "12345678", "image");
               // us.ajouter(u2);
          reservation_voyage rv=new reservation_voyage();
          rv.setIdrv(999);
          rv.setDate_arrivee(date);
          rv.setDate_depart(date);
          
         rv.setRef_paiement(1);
         rv.setUser(u2);
         rv.setVoyage(chrf);
         hotel h1=new hotel(255,"hotel sousse",5,"sousse");
    
        
     //   rvs.ajouter(rv);
        reservation_hotel rh=new reservation_hotel();
        rh.setHotel(h1);
       rh.setNb_nuitees(5);
       rh.setNb_personnes(1);
       rh.setPrix( 5.5f);
       rh.setType_room("Suite");
        rh.setIdrh(255);
       rh.setUser(u2);
      
    
    
       Reserrvation_Hotel_Service rhs=new Reserrvation_Hotel_Service();
       //rhs.ajouter(rh);

               reservation_hotel rh1=new reservation_hotel("suiteRoom",5,1, (float) 5.5,h1,u2);
         //    System.out.println( rhs.afficher());
                 HotelCRUD hb= new HotelCRUD();

        hotel h2= new hotel (2,"Movenpick",5,"tunis");
 //hb.supprimerHotel(h2);
       // hb.ajouterHotel(h2);
      // rhs.modifier(rh.getIdrh(),rh1);
        //System.out.println(vo3.getVoyage());
    // vos.ajouter(vo3);
      // vos.ajouter(vo4);
       //vos.modifier(79, vom);
       
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
        Voiture v99 =new Voiture(1,"120TU120",12,"image","Mercedes");
        VoitureCRUD VC=new VoitureCRUD();
       // VC.ajouterVoiture(v99);
               Voiture v98 =new Voiture(1,"220TU120",12,"image","bmw");
        //VC.modifierVoiture(v98);
               reservation_voiture rhv=new reservation_voiture();
               rhv.setChauffeur("mohamed salah");
               rhv.setPrix_rent((float) 5.5);
               rhv.setTrajet("jerba");
               rhv.setUser(u2);
               rhv.setVoiture(v99);
            
     reservation_voiture rhv1=new reservation_voiture(5.5f,"cccc","jandouba",u2,v99);
  
              
               Reservation_voiture_Service rvs55= new Reservation_voiture_Service();
              //rvs55.ajouter(rhv);
              // rvs55.modifier(2,rhv1);
              //rvs55.supprimer(2);
             rvs55.afficher().toString();
              
               
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
