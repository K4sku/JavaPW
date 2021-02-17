package getMail;

import jakarta.mail.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MessageHeaderWrapper {
    private final Message message;
    private final Folder folder;
    private Address[] appRecipients;
    private Address[] from;
    private final String fromDisplay;
    private final int messageNumber;
    private Date receivedDate;
    private final String receivedDisplayedDate;
    private String subject;
    private Session session;
    private String datePattern;
    private SimpleDateFormat simpleDateFormat;

    public MessageHeaderWrapper(Message msg) {
        this.message = msg;
        this.folder = msg.getFolder();
        this.messageNumber = msg.getMessageNumber();
        try {
            folder.open(Folder.READ_ONLY);
            this.appRecipients = msg.getAllRecipients();
            this.subject = msg.getSubject();
            this.receivedDate = msg.getReceivedDate();
            this.from = msg.getFrom();
            folder.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        fromDisplay = from[0].toString();

        var now = new Date();
        var receivedInMillis = now.getTime() - receivedDate.getTime();
        if (receivedInMillis < 86400000) { // today in milliseconds
            datePattern = "HH:mm";
            simpleDateFormat = new SimpleDateFormat(datePattern);
            receivedDisplayedDate = simpleDateFormat.format(receivedDate);
        } else if (receivedInMillis < 604800000) { // 7 days in milliseconds
            receivedDisplayedDate = TimeUnit.MILLISECONDS.toDays(now.getTime() - receivedDate.getTime()) + " days ago";
        } else {
            datePattern = "dd MMM";
            simpleDateFormat = new SimpleDateFormat(datePattern);
            receivedDisplayedDate = simpleDateFormat.format(receivedDate);
        }
        System.out.println(receivedDisplayedDate);
    }

    public Folder getFolder() {
        return folder;
    }

    public Address[] getAppRecipients() {
        return appRecipients;
    }

    public Address[] getFrom() {
        return from;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public Session getSession() {
        return session;
    }

    public Message getMessage() {
        return message;
    }

    public String getFromDisplay() {
        return fromDisplay;
    }

    public String getReceivedDisplayedDate() {
        return receivedDisplayedDate;
    }

    public String getSubject() {
        return subject;
    }

}
