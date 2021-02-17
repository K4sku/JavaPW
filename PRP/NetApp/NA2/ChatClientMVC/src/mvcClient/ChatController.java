package mvcClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChatController implements AutoCloseable {

    @FXML
    private Label welcomeLabel;
    
    @FXML
    TextField messageTextField;
    @FXML
    WebView webViewMessages;
    @FXML
    Circle circleImage;
    @FXML
    ImageView sendImageView;
    @FXML
    ImageView attachImageImageView;

    private String userName = "";
    private String senderName ="";
    private String host;
    private int port;
    
    private Socket socket;
    private BufferedReader inputBufferedReader;
    private PrintWriter outputPrintWriter;
    private final int PROTOCOL_PREFIX_LENGTH = 3;
    private Document messagesLayout;
    final FileChooser fileChooser = new FileChooser();

    
    
    @FXML
    private void initialize() {
    	String welcome = "Nice to see you there! This is a welcome message. " +
    					"Say hello to other users.";
    	messagesLayout = Jsoup.parse(
    			"<html><head><meta charset='UTF-8'>" +
    					"</head><body><ul><li class=\"welcome\"><div class=\"message\"><div class=\"content\">" +
    					welcome +
    					"</div></div></li></ul></body></html>",
    					"UTF-16",
    					Parser.xmlParser()
    			);
    	webViewMessages.getEngine().loadContent(messagesLayout.html());
    	webViewMessages.getEngine().setUserStyleSheetLocation(
    			getClass().getResource("application.css").toString()
    			);
    	
    }
    
    private String recieveMessage() throws IOException {
    	return inputBufferedReader.readLine();
    }
    
    public void run() {
    	try {
			socket = new Socket(host, port);
			inputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputPrintWriter = new PrintWriter(socket.getOutputStream(), true);
			sendMessage(userName);
			new Thread(task).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    Task<Void> task = new Task<Void>() {
    	@Override
    	protected Void call() {
	    	try {
		    	while (true) {
		    		if (isCancelled()) return null;
			    	String msg = receiveMessage();
			    	showMessage(toHTML(decodeUID(msg), "response"));
			    	System.out.println(msg);
			    	Thread.sleep(100);
		    	}
		    	} catch (IOException | InterruptedException ex) {
		    		if (isCancelled()) return null;
	    	}
	    	return null;
    	}

    };
    
    private String receiveMessage() throws IOException { 
    	return inputBufferedReader.readLine(); 
	} 

    
    private void sendMessage(String message) { 
    	outputPrintWriter.println(message); 
	}
    
    @FXML
    private void sendImageView_MouseReleased() {
	    if (messageTextField.getLength() == 0) return;
	    sendMessage(messageTextField.getText());
	    showMessage(toHTML(messageTextField.getText(), "request"));
	    messageTextField.clear();
    }
    
    @FXML
    private void attachImageImageView_MouseReleased() {
    	configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog((Stage) attachImageImageView.getScene().getWindow());
        if (file != null) {
        	sendMessage(file.toString());
        }
    }
    
    private static void configureFileChooser(
            final FileChooser fileChooser) {      
                fileChooser.setTitle("Select Picture");              
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                );
        }

    @FXML
    private void messageTextField_KeyPressed(KeyEvent e) {
	    if (e.getCode() == KeyCode.ENTER) {
	    	sendImageView_MouseReleased() ;
	    }
    }
    
    private void showMessage(Element message) {
    	Element wrapper = messagesLayout.getElementsByTag("ul").first();
    	wrapper.appendChild(message);
    	Platform.runLater(new Runnable() {
    		public void run() {
    				webViewMessages.getEngine().loadContent(messagesLayout.html());
    		}
		});
	}
	
    private Element toHTML(String message, String msgClass) {
    	System.out.println("toHTML:" + message);
    	Element wrapper = new Element("li").attr("class", msgClass);
    	Element image = new Element("img").attr("class", "avatar").attr("src",
    	new File("res/mikeross.png").toURI().toString());
    	if (msgClass.equals("request")) {
    		image.attr("src", new File("res/harveyspecter.png").toURI().toString());
    		new Element("span").attr("class", "author").append(senderName).appendTo(wrapper);
    	}
    	
    	image.appendTo(wrapper);
    	Element message_div = new Element("div").attr("class", "message").appendTo(wrapper);
    	new Element("div").attr("class", "content").append(message).appendTo(message_div);
    	return wrapper;
	}
    
    private String decodeUID(String msg) {
    	msg = msg.substring(PROTOCOL_PREFIX_LENGTH);
    	char sep = (char) 31;
    	String[] param = msg.split(String.valueOf(sep));
    	senderName = param[0];
    	return msg.substring(param[0].length() + 1);
	}
    

	@Override
	public void close() throws Exception {
		socket.close();
		task.cancel();
		// TODO Auto-generated method stub
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		welcomeLabel.setText("Hello " + this.userName + "!");
		Image myImage = new Image(new File("res/harveyspecter.png").toURI().toString());
		ImagePattern pattern = new ImagePattern(myImage);
		circleImage.setFill(pattern); 
		}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int i) {
		this.port = i;
	}
	
	
	
	
}
