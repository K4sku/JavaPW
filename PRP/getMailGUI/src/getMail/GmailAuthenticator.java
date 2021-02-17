package getMail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class GmailAuthenticator extends Authenticator {
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
