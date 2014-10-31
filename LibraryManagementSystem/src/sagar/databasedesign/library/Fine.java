package sagar.databasedesign.library;

/**
 * Keep track of the fines due
 * 
 * @author Sagar
 *
 */
public class Fine {
	private String loanId;
	private float fine_amt;
	private boolean paid;

	public Fine(String loanId, float fine_amt, boolean paid) {
		super();
		this.loanId = loanId;
		this.fine_amt = fine_amt;
		this.paid = paid;
	}

	public Fine() {
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
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

}
