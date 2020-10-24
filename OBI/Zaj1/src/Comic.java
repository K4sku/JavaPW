public class Comic extends Book {
	private boolean color;
	public Comic(String t, String a, int y, int p, boolean c) {
		super(t, a, y, p);
		color = c;
		// TODO Auto-generated constructor stub
	}
	
	public void requiresMagenta() {
		if (this.color==true) {
			System.out.println("This comic requires magenta ink");
		}else{
			System.out.println("This comic does not use magenta ink");
		}
	}

}