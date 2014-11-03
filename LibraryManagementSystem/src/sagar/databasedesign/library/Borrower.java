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

	private int cardNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String phone;

	public Borrower(int cardNumber) {
		super();
		this.cardNumber = cardNumber;
	}

	public Borrower(int cardNumber, String firstName, String lastName,
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

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
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
	
	public int getMaxCardNo() {
		return DatabaseManager.getInstane().getMaxCardNo();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Borrower other = (Borrower) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	public void insertBorrower() {
		DatabaseManager.getInstane().insertBorrower(this);
	}
	
}
