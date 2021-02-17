package mailSend;

import java.util.Date;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;


public class MailSend {

    public static void main(String[] args) {
        Properties properties = getGmailProperties();
        Session session = Session.getInstance(properties,
                new GmailAuthenticator("cezary.klos.mail@gmail.com", "0Ru2JYec9DEng9Gb"));

        String sender = "cezary.klos.mail@gmail.com";
        String recipient = "cezary.klos.mail@gmail.com";
        sendTextMessage(session, sender, recipient, "Nowa wiadomość", "Have a good day :)");
        sendHTMLMessage(session, sender, recipient, "HTML message", "<h1>Have a good day :)</h1>");
        sendAttachmentMessage(session, sender, recipient, "Attachment message", "Have a good day", "G:\\MiecioLiza.png");
    }

    private static Properties getGmailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.debug","true");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.port","587");
        return properties;
    }

    private static class GmailAuthenticator extends Authenticator {
        private String userName;
        private String password;

        GmailAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }


    private static Message getMessage(Session session, String fromAddress,
                                      String toAddress, String subject) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromAddress));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toAddress));
        message.setSubject(subject);
        message.setSentDate(new Date());
        return message;
    }

    private static void sendTextMessage(Session session, String fromAddress,
                                        String toAddress, String subject, String body) {
        try {
            Message message = getMessage(session, fromAddress, toAddress, subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private static void sendHTMLMessage(Session session, String fromAddress,
                                        String toAddress, String subject, String body) {
        try {
            Message message = getMessage(session, fromAddress, toAddress, subject);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private static BodyPart getBodyPart(String fileName) throws
            MessagingException {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(fileName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        return messageBodyPart;
    }

    private static void sendAttachmentMessage(Session session,
                                              String fromAddress, String toAddress, String subject, String body,
                                              String fileName) {
        try {
            Message message = getMessage(session, fromAddress, toAddress, subject);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = getBodyPart(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}


