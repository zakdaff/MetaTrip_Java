/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metatrip;

import entities.user;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import services.LoginAndSignupService;
import services.UserService;

/**
 *
 * @author medal
 */
public class Metatrip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
  // TODO code application logic here
        Datasource data =Datasource.getInstance();
          Datasource data2 =Datasource.getInstance();
          System.out.println(data.hashCode()+"-"+data2.hashCode());
          
        
        UserService us = new UserService();
         LoginAndSignupService loginSignup = new LoginAndSignupService();
     user u=new user(125,"flam", "fares", 256845, "ssflam@live.fr","0000", "image");
     
        user u2=new user(1255,"ssss", "cxx", 256845, "salma@live.fr",doHashing("12345678"), "image");
       user u1=new user(5,5866,"dafdouf", "zakzouk", 5895, "zak@live.fr","0000", "image");
         user u3=new user(5866,"ges", "nay", 5895, "nay@live.fr","0000", "image");
                 user u4=new user(58656,"khaldi", "imen", 5895, "zak@live.fr","0000", "image");
                 
      us.ajouter(u3);
      //us.modifier(1,u3);
      // System.out.print(p.getId());
    //us.supprimer(4);
       //ps.modifier(p);

  System.out.print("ss"+us.afficher());

 //System.out.print( loginSignup.login("zak@live.fr", "0000"));
   
 //loginSignup.Signup(u);

}
    
   public static String doHashing (String password) {
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