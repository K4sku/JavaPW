public class Book {
	private String title;
	private String author;
	private int year;
	private int pages;
	
	public Book (String t, String a, int y, int p) {
		title = t;
		author = a;
		year = y;
		pages =p;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}

	public boolean decadeOld() {
		if (2020 - year >= 10)
			return true;
		return false;
	}	
	
	public void printPages() {
		int pageCount = this.getPages();
		System.out.println("This book has " + pageCount + " pages.");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book potter = new Book("Harry Potter", "J.K. Rowling", 15, 666);
		Comic tytus = new Comic("Tytus", "Papcio", 26, 100, false);
		
		if (potter.decadeOld()) 
			potter.printPages();
		
		tytus.requiresMagenta();
	}

}
