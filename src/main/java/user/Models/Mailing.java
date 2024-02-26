package user.Models;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class Mailing {
    public void sendRecoveryCode(String userEmail, String verificationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cinephoria.cinema@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Verification Code for Your Account");
            message.setText("Your verification code: " + verificationCode);

            Transport.send(message);
            System.out.println("Email notification sent to " + userEmail);
        } catch (Exception e) {
            System.out.println("Error sending email notification: " + e.getMessage());
        }
    }
}

     /*   public static void sendEmail(String recipient, String subject, String body) {
            // Set properties for the SMTP server
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Replace these with your Gmail credentials
            String username = "zh.zribi.heni@gmail.com";
            String password = "ovzl mlmu dbaf gias";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a MimeMessage object
                MimeMessage message = new MimeMessage(session);

                // Set content type to "text/html"
                message.setContent(body, "text/html; charset=utf-8");

                // Set recipient
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

                // Set email subject
                message.setSubject(subject);

                // Send the email
                Transport.send(message);

                System.out.println("Email sent successfully to " + recipient);
            } catch (MessagingException e) {
                System.err.println("Error sending email: " + e.getMessage());
            }
        }
    }*/


