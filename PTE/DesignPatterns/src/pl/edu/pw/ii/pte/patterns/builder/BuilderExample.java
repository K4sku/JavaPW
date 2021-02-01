package pl.edu.pw.ii.pte.patterns.builder;

public class BuilderExample {

	public static void main(String[] args)  {
		StringBuilder builder = new StringBuilder();
		builder.append("Text");
		builder.append(1);
		builder.append('c');
		
		String str = builder.toString();
		assert str == "Text1c";
	}
	
}
