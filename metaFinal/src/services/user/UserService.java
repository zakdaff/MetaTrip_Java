/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.user;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import Config.Datasource;
import static Config.Metatrip.doHashing;
import entities.reservation_voyage;
import entities.voyage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import services.IService;
import services.IService;

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
                u.setDateNaissance(rs.getDate(9));
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
    
    
    public List<user> getUserByEmail (String Email)  {
     List<user> users = new ArrayList<>();
   
          String req = "SELECT * from `user` where Email='"+Email+"';";
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
            }}
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;    
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
    
  

  

  

