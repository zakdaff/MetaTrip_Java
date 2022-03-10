
package services.user;
import entities.user;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import javax.mail.Authenticator;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author medal
=======
import javax.mail.internet.MimeMessage;
/**
 *
 * @author FLAM
>>>>>>> origin/main
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
        String password = "NEXUS123.";
        
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

    
   public static void sendFacture(String to){
   
   //change accordingly   
        final String user="solidev.3a18@gmail.com";//change accordingly   
       //change accordingly     

        //1) get the session object      
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

        //2) compose message      
        try{    
            MimeMessage message = new MimeMessage(session);    
            message.setFrom(new InternetAddress(user));     
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
            message.setSubject(" Facture Metatrip");         

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText("Merci pour votre confiance ,voici votre facture de Reservation ");          

            //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            String filename = "C:\\Users\\FLAM\\Desktop\\MetatripGit\\MetaTrip_Java\\MetatripVoyageFacture.pdf";//change accordingly     
            DataSource source = new FileDataSource(filename);    
            messageBodyPart2.setDataHandler(new DataHandler(source));    
            messageBodyPart2.setFileName(filename);             

            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
            multipart.addBodyPart(messageBodyPart1);     
            multipart.addBodyPart(messageBodyPart2);      

            //6) set the multiplart object to the message object    
            message.setContent(multipart );        

            //7) send message    
            Transport.send(message);      
            System.out.println("message sent....");   

        }catch (MessagingException ex) {ex.printStackTrace();}  
    }
   }