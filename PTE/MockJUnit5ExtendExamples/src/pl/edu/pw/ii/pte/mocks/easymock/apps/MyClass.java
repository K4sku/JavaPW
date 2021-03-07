package pl.edu.pw.ii.pte.mocks.easymock.apps;

public class MyClass {
	private SimpleLogger logger;
	
	public void setLogger(SimpleLogger value) {
		this.logger = value;
	}

	private String msg;
	
	public String message() {
		logger.messageGenerated(generateMsg());
		return generateMsg();
	}
	
	private String generateMsg() {
		return "Hello " + msg;
	}
	
	public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
 