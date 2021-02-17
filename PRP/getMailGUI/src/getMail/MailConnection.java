package getMail;

import jakarta.mail.*;
import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.Properties;

public class MailConnection {
    private static Store store;
    private static Session session;
    private static final Properties properties = new Properties();

    private String host;

    public MailConnection(String userEmail, String userPassword) {

        setProperties(userEmail, userPassword);

        Session session = Session.getInstance(properties,
                new GmailAuthenticator(properties.getProperty("userEmail"), properties.getProperty("userPassword")));
        connect(session);
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

    public void close() {
        try {
            store.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setProperties(String userEmail, String userPassword) {
        properties.setProperty("mail.store.protocol", "imap");
        properties.setProperty("mail.imap.ssl.enable", "true");
        properties.setProperty("incomingHost", "imap.gmail.com");
        properties.setProperty("userEmail", userEmail);
        properties.setProperty("userPassword", userPassword);
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

    public TreeItem readFolderStructure() {
        TreeItem inbox = null;
        try {
//            Folder defaultFolder = store.getFolder("[Gmail]");
            Folder defaultFolder = store.getDefaultFolder();
            inbox = fillFolderChildren(defaultFolder, inbox);
            return inbox;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return inbox;
    }

    public Session getSession() {
        return session;
    }

    private TreeItem fillFolderChildren(Folder nodeFolder, TreeItem tree) throws MessagingException {
        TreeItem treeItem = new TreeItem(nodeFolder.getName());
        if (null == tree) {
            tree = treeItem;
        } else {
            tree.getChildren().add(treeItem);
        }
        Folder[] childrenFolders = nodeFolder.list();
        for (Folder childFolder : childrenFolders) {
            fillFolderChildren(childFolder, treeItem);
        }
        return tree;
    }


    public int getMessagesInFolderCount(String folderPath) {
        int messagesCountInFolder = 0;
        try {
            var folder = store.getFolder(folderPath);
            if (folder.getType() != 2) {
                folder.open(Folder.READ_ONLY);
                messagesCountInFolder = folder.getMessageCount();
                folder.close();
            } else {
                System.out.println("Folder nie może zawierać wiadomości.");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return messagesCountInFolder;
    }

    public Message[] fetchMessages(String selectedFolderPath, int messagesCountInFolder) {
        Message[] fetchedMessages = new Message[messagesCountInFolder];
        try {
            var folder = store.getFolder(selectedFolderPath);
            if (folder.getType() != 2) {
                folder.open(Folder.READ_ONLY);
                for (int i = messagesCountInFolder; i > 0; i--) {
                    fetchedMessages[i - 1] = folder.getMessage(i);
                }
                folder.close();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return fetchedMessages;
    }

    public String[] loadMessageContent(Folder folder, int messagesNumber) {
        String[] emailTypeAndContent = {"", ""};
        Message email = null;
        try {
            folder.open(Folder.READ_ONLY);
            email = folder.getMessage(messagesNumber);
            emailTypeAndContent[0] = email.getContentType();
            emailTypeAndContent[1] = email.getContent().toString();
            folder.close();
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return emailTypeAndContent;
    }
}
