package sagar.databasedesign.library;

public class Book {
	private String id;
	private String title;
	
	public Book(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	
	public Book() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
