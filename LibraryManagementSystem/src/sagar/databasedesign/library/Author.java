package sagar.databasedesign.library;

public class Author {
	private Book book;
	private String firstName;
	private String middleInitial;
	private String lastName;
	
	public Author(Book book, String firstName, String middleInitial,
			String lastName) {
		super();
		this.book = book;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	}
	
	public Author() {
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
