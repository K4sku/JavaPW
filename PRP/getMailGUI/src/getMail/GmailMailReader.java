package getMail;

import jakarta.mail.*;

import java.util.Properties;

public class GmailMailReader {

    private static Store store;
    private static Properties properties;

    public GmailMailReader() {
        Properties properties = getGmailProperties();
        Session session = Session.getInstance(properties,
                new getMail.GmailAuthenticator(properties.getProperty("userEmail"), properties.getProperty("userPassword")));
        connect(session);
        readEmails();
    }

    private static Properties getGmailProperties() {
        properties = new Properties();
        properties.setProperty("mail.store.protocol", "imap");
        properties.setProperty("mail.imap.ssl.enable", "true");
        properties.setProperty("incomingHost", "imap.gmail.com");

        return properties;
    }

    private static void connect(Session session) {
        try {
            store = session.getStore();
            store.connect(
                    properties.getProperty("incomingHost"),
                    properties.getProperty("userEmail"),
                    properties.getProperty("userPassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readEmails() {
        try {
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            for (int i = folder.getMessageCount(); i > 0; i--) {
                Message message = folder.getMessage(i);
                System.out.printf("%1$td/%1$tm/%1$tY %1$tH:%1$tM:%1$tS %2$s %3$s %n",
                        message.getReceivedDate(), message.getFrom()[0].toString(),
                        message.getSubject());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static class GmailAuthenticator extends Authenticator {
        private final String userName;
        private final String password;

        GmailAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }
}
