package service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class EmailService {

    private static String USER_NAME = "flashscore.adm";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "adminIS123"; // GMail password
    private static String FROM = "flashscore.adm@gmail.com";



    public static void sendEmail(String mail, String text) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");


        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {


            message.setFrom(new InternetAddress(FROM));
            InternetAddress[] toAddress = new InternetAddress[1];


            toAddress[0] = new InternetAddress(mail);

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }



            message.setSubject("Details about a game you bookmarked");
            message.setText(text);


            Transport transport = session.getTransport("smtp");


            transport.connect(host, FROM, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
