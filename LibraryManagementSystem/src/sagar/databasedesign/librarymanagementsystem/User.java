package sagar.databasedesign.librarymanagementsystem;

import java.net.PasswordAuthentication;

public class User {
	private PasswordAuthentication authentication;

	public PasswordAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(PasswordAuthentication authentication) {
		this.authentication = authentication;
	}

	
}
