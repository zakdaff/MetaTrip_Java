
package Config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import services.user.UserService;

/**
 *
 * @author FLAM
 */
public class excel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
           Connection conn = null;
           
     String url = "jdbc:mysql://localhost:3306/metatrip";
   String user = "root";
     String pwd = "";
    
                 conn = DriverManager.getConnection(url, user, pwd);

     Statement ste;
        PreparedStatement pst;
        String filepath="CC:\\Users\\medal\\OneDrive\\Bureau\\users.csv";
        
      String req = "INSERT INTO user (cin, nom ,"
                + "prenom , tel , email , "
                + "password , image, dateNaissance) VALUES ( ? , ? , ? , ? , ? , ? , ?, ? ) ;";
        int batchSize=20;

        try {
            pst = conn.prepareStatement(req);
            BufferedReader lineReader=new BufferedReader(new FileReader(filepath));

            String lineText=null;
            int count=0;
            
            // To JUMP COLUMNS NAMES
            //ineReader.readLine();
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(",");
                

                pst = conn.prepareStatement(req);
                pst.setString(1, data[0]);
                pst.setString(2, data[1]);
                pst.setString(3, data[2]);
                pst.setString(4, data[3]);
                pst.setString(5, data[4]);
                pst.setString(6, UserService.doHashing(data[5]));
                pst.setString(7, data[6]);
                pst.setDate(8, Date.valueOf(data[7]) );

                pst.addBatch();
                if(count%batchSize==0){
                    pst.executeBatch();
                }
            }
            lineReader.close();
            pst.executeBatch();

            System.out.println("Data has been inserted successfully.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        
    }



}

    
     
    
    
