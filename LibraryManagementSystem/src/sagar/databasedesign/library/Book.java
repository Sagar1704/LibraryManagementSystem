package sagar.databasedesign.library;

import java.util.ArrayList;

public class Book {
	private String isbn;
	private String title;
	private ArrayList<Author> authors;

	private static final int ISBNMAX = 10;

	public Book(String isbn) {
		this.isbn = changeLength(isbn);
	}

	public Book(String isbn, String title, ArrayList<Author> authors) {
		this.isbn = changeLength(isbn);
		this.title = title;
		this.authors = authors;
	}

	public Book() {
	}

	public String getId() {
		return isbn;
	}

	public void setId(String isbn) {
		this.isbn = changeLength(isbn);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	/**
	 * Make the ISBN strictly 10 digit
	 * 
	 * @param isbn
	 * @return
	 */
	private String changeLength(String isbn) {
		if (isbn.toLowerCase().indexOf("x") != -1)
			isbn = isbn.substring(0, isbn.toLowerCase().indexOf("x"));
		for (int count = 0; count < ISBNMAX - isbn.length(); count++) {
			isbn = "0" + isbn;
		}
		return isbn;
	}
}
