package sagar.databasedesign.library;

import java.util.ArrayList;
import java.util.HashMap;

import sagar.databasedesign.database.DatabaseManager;

/**
 * Keep track of the fines due
 * 
 * @author Sagar
 *
 */
public class Fine {
	public static final float RATE = 0.25f;
	private int loanId;
	private float fine_amt;
	private boolean paid;

	public Fine(int loanId, float fine_amt, boolean paid) {
		super();
		this.loanId = loanId;
		this.fine_amt = fine_amt;
		this.paid = paid;
	}

	public Fine() {
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public float getFine_amt() {
		return fine_amt;
	}

	public void setFine_amt(float fine_amt) {
		this.fine_amt = fine_amt;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public void insertFine() {
		DatabaseManager.getInstane().insertFine(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + loanId;
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
		Fine other = (Fine) obj;
		if (loanId != other.loanId)
			return false;
		return true;
	}
	
	public ArrayList<Fine> getfines() {
		return DatabaseManager.getInstane().getFines();
	}
	
	public float getFinesByBorrower(Borrower borrower) {
		return DatabaseManager.getInstane().getFinesByBorrower(borrower);
	}
	
	public void updateFine() {
		DatabaseManager.getInstane().updateFine(fine_amt, paid, loanId);
	}
}
