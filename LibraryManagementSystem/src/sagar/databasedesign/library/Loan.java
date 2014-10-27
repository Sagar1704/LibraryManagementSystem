package sagar.databasedesign.library;

import java.util.Date;

public class Loan {
	private String id;
	private Book book;
	private Branch branch;
	private Borrower borrower;
	private Date dateOut;
	private Date dateIn;
	private Date dueDate;
	
	public Loan(String id, Book book, Branch branch, Borrower borrower,
			Date dateOut, Date dateIn, Date dueDate) {
		super();
		this.id = id;
		this.book = book;
		this.branch = branch;
		this.borrower = borrower;
		this.dateOut = dateOut;
		this.dateIn = dateIn;
		this.dueDate = dueDate;
	}
	
	public Loan() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
