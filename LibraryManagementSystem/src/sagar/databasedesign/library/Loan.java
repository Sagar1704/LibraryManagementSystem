package sagar.databasedesign.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sagar.databasedesign.database.DatabaseManager;

public class Loan {
	private static final int DAYS = 14;
	
	private Book book;
	private Branch branch;
	private Borrower borrower;
	private Calendar dateOut = Calendar.getInstance();;
	private Calendar dueDate = Calendar.getInstance();;
	private Calendar dateIn = Calendar.getInstance();;

	private static final String DATE_PATTERN = "YYYY-MM-DD";

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			DATE_PATTERN, Locale.US);

	public Loan(Book book, Branch branch, Borrower borrower) {
		super();
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		this.dueDate.add(Calendar.DAY_OF_MONTH, DAYS);
		this.dateIn = null;
	}

	public Loan(Book book, Branch branch, Borrower borrower, String dateOut,
			String dueDate, String dateIn) {
		super();
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		try {
			this.dateOut.setTime(SDF.parse(dateOut));

			this.dueDate.setTime(SDF.parse(dueDate));
			if (dateIn == null)
				this.dateIn = null;
			else
				this.dateIn.setTime(SDF.parse(dateIn));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Loan() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Calendar getDateOut() {
		return dateOut;
	}

	public void setDateOut(Calendar dateOut) {
		this.dateOut = dateOut;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public Calendar getDateIn() {
		return dateIn;
	}

	public void setDateIn(Calendar dateIn) {
		this.dateIn = dateIn;
	}

	public int getBorrowCount(String cardNo) {
		return DatabaseManager.getInstane().getBorrowCount(cardNo);
	}
	
	public void checkout(int copies) {
		DatabaseManager.getInstane().checkout(this, copies);
	}
}
