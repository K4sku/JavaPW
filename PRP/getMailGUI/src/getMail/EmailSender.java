package getMail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class EmailSender {
    private Store store;
    private final Properties properties = new Properties();


    public EmailSender(String userEmail, String userPassword, String emailRecipient, String subject, String htmlBody) {
        setProperties(userEmail, userPassword);
        Session session = Session.getInstance(properties,
                new GmailAuthenticator(properties.getProperty("userEmail"), properties.getProperty("userPassword")));

        sendHTMLMessage(session,
                properties.getProperty("userEmail"),
                emailRecipient,
                subject,
                htmlBody);
    }

    public boolean connect(Session session) {
        try {
            store = session.getStore();
            store.connect(
                    properties.getProperty("incomingHost"),
                    properties.getProperty("userEmail"),
                    properties.getProperty("userPassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return store.isConnected();
    }

    public boolean isConnected() {
        return store.isConnected();
    }

    public void close() throws MessagingException {
        store.close();
    }


    private void setProperties(String userEmail, String userPassword) {
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("userEmail", userEmail);
        properties.setProperty("userPassword", userPassword);
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


}
