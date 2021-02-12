package getMail;

import jakarta.mail.*;

import java.util.Properties;


public class Main {

    private static Store store;
    private static Properties properties;


    public static void main(String[] args) {
        Properties properties = getGmailProperties();
        Session session = Session.getInstance(properties,
                new GmailAuthenticator("cezary.klos.mail@gmail.com", "0Ru2JYec9DEng9Gb"));
        connect(session);
        readEmails();
    }
    private static Properties getGmailProperties() {
        properties = new Properties();
        properties.put("mail.store.protocol", "imap");
//        properties.put("mail.transport.protocol", "imap");
//        properties.put("mail.imap.host", "imap.gmail.com");
//        properties.put("mail.imap.port", "993");
        properties.setProperty("mail.imap.ssl.enable", "true");
        properties.put("incomingHost","imap.gmail.com");

        return properties;
    }

    private static void connect(Session session) {
        try {
            store = session.getStore();
            store.connect(properties.getProperty("incomingHost"),
                    "cezary.klos.mail@gmail.com", "0Ru2JYec9DEng9Gb");
        } catch (Exception e) { e.printStackTrace(); }
    }
    private static void readEmails() {
        try {
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            for (int i = folder.getMessageCount(); i > 0; i--) {
                Message message = folder.getMessage(i);
                System.out.printf("%1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS %2$s %3$s %n",
                        message.getReceivedDate(), message.getFrom()[0].toString(),
                        message.getSubject() );
            }
        } catch (MessagingException e) { e.printStackTrace(); }
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
}
