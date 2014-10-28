package sagar.databasedesign.librarymanagementsystem;

import java.net.PasswordAuthentication;

import sagar.databasedesign.database.DatabaseManager;

public class User {
	private PasswordAuthentication authentication;

	public PasswordAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(PasswordAuthentication authentication) {
		this.authentication = authentication;
	}

	public User(String userName, char[] password) {
		this.authentication = new PasswordAuthentication(userName, password);
	}
	
	public boolean authenticate() {
		DatabaseManager db = DatabaseManager.getInstane();
		try {
		if(db.getUserInfo(this) != null)
			return true;
		} finally {
			if(db != null)
				db.close();
		}
		return false;
	}
}
