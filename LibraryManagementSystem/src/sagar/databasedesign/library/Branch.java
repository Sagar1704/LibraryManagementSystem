package sagar.databasedesign.library;

import java.util.ArrayList;
import java.util.HashMap;

public class Branch {
	private String id;
	private String name;
	private String address;
	private ArrayList<HashMap<Book, Integer>> books;

	public Branch(String id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Branch() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	
}
