/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Nayrouz
 */
public class Smsapi {
    public static final String ACCOUNT_SID = "AC6ba698bd123d56b82266793e17d6f98a";
    public static final String AUTH_TOKEN = "cc786ee58711f39d5c8616361028508d";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(num),new PhoneNumber("+17164543421"), msg).create();

        System.out.println(message.getSid());

    }
}