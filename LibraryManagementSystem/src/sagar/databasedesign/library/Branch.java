package sagar.databasedesign.library;

import java.util.ArrayList;
import java.util.HashMap;

import sagar.databasedesign.database.DatabaseManager;

public class Branch {
	private int id;
	private String name;
	private String address;
	private ArrayList<HashMap<Book, Integer>> books;

	public Branch(int id) {
		super();
		this.id = id;
	}

	public Branch(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Branch() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<HashMap<Book, Integer>> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<HashMap<Book, Integer>> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Branch other = (Branch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getAvailableCopies(Book book) {
		return DatabaseManager.getInstane().getAvailableCopies(book, this);
	}
}
