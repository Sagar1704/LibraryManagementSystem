package sagar.databasedesign.library;

import java.util.ArrayList;

import sagar.databasedesign.database.DatabaseManager;

/**
 * Library Card owner
 * 
 * @author Sagar
 * 
 */
public class Borrower {
	public static final int MAX_BORROW = 3;

	private String cardNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phone;

	public Borrower(String cardNumber) {
		super();
		this.cardNumber = cardNumber;
	}

	public Borrower(String cardNumber, String firstName, String lastName,
			String address, String city, String state, String phone) {
		super();
		this.cardNumber = cardNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.setCity(city);
		this.setState(state);
		this.phone = phone;
	}

	public Borrower() {
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Borrower> getBorrowers() {
		return DatabaseManager.getInstane().getBorrowers();
	}
}
