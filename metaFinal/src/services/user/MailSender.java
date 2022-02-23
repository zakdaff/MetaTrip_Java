/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package services.user;
import entities.user;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author medal
 */
public class MailSender {
    public static void sendMail(user user) throws Exception{
        System.out.println("Preparing to send email");
        Properties p = new Properties();
        
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
             p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        
        String myAccountEmail = "solidev.3a18@gmail.com";
        String password = "flam123.";
        
        Session session = Session.getInstance(p , new Authenticator() {
        @Override 
        protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(myAccountEmail, password);
      }
        });
        try{
        
        Message message = prepareMessage(session , myAccountEmail ,user );
         Transport.send(message);
             System.out.println("Message sent successfully");
         } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
       
    
    
    }

    private static Message prepareMessage(Session session, String myAccountEmail, user user) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Metatrip");
            String htmlcode="  <center><img src=\"https://pbs.twimg.com/profile_images/1118720684950085632/Qc9LxLu0_400x400.png\" alt=\"Girl in a jacket\" height=50%;width=50%></center>"
                    + "<center><h2>bienvenue sur notre site  Metatrip</h2> <br><h4>une fois metatrip!toujour metatrip </h4></center></br>"
                    + "</center><center><h3>voici les coordonnéesde votre compte:</h3></center><br><center>Email:"+user.getEmail()+"</center><br><center>Password:"+user.getPassword()+"</center></br>";
            message.setContent(htmlcode,"text/html");
            return message;
        } catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
