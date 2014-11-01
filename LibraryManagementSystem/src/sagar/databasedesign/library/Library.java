package sagar.databasedesign.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import sagar.databasedesign.database.DatabaseManager;
import sagar.databasedesign.enums.Role;
import sagar.databasedesign.exceptions.FileEmptyException;

/**
 * Complete Library information
 * 
 * @author Sagar
 * 
 */
public class Library {
	ArrayList<Book> books;
	private ArrayList<Branch> branches;
	private ArrayList<Borrower> borrowers;
	private ArrayList<Loan> loans;

	private static final String BRANCH_CSV = "library_branch.csv";
	private static final String BOOK_AUTHOR_CSV = "books_authors.csv";
	private static final String BOOK_COPIES_CSV = "book_copies.csv";
	private static final String BOOK_LOANS_CSV = "book_loans_data_F14.csv";
	private static final String BORROWER_CSV = "borrowers.csv";

	private static final List<String> ORGANIZATIONS = Collections
			.unmodifiableList(new ArrayList<String>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = -1299833223601612684L;

				{
					add("Various");
					add("The Beatles");
				}
			});

	public Library() {
		this.books = new ArrayList<Book>();
		this.branches = new ArrayList<Branch>();
		this.borrowers = new ArrayList<Borrower>();
		this.loans = new ArrayList<Loan>();

	}

	/**
	 * Re-Initialize the Library Database from the given input csv files
	 */
	public void initialize() {
		DatabaseManager db = null;
		try {
			readBranches();
			readBorrowers();
			readLoans();

			db = DatabaseManager.getInstane();
			db.dropLibrary();
			db.createLibrary();

			loadBooks();
			loadAuthors();
			loadBranches();
			loadCopies();
			loadBorrowers();
			loadLoans();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (FileEmptyException e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void loadBooks() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Book book : books) {
			db.insertBook(book);
		}
	}

	private void loadAuthors() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Book book : books) {
			for (Author author : book.getAuthors()) {
				db.insertAuthor(book, author);
			}
		}
	}

	private void loadBranches() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Branch branch : branches) {
			db.insertBranch(branch);
		}
	}

	private void loadCopies() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Branch branch : branches) {
			for (HashMap<Book, Integer> bookCopies : branch.getBooks()) {
				for (Book book : bookCopies.keySet()) {
					db.insertBookCopies(branch, book, bookCopies.get(book));
				}
			}
		}
	}

	private void loadBorrowers() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Borrower borrower : borrowers) {
			db.insertBorrower(borrower);
		}
	}

	private void loadLoans() {
		DatabaseManager db = DatabaseManager.getInstane();
		for (Loan loan : loans) {
			db.insertLoans(loan);
		}
	}

	/**
	 * Get the library branch information
	 * 
	 * @throws FileNotFoundException
	 * @throws FileEmptyException
	 */
	private void readBranches() throws FileNotFoundException,
			FileEmptyException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(getClass().getClassLoader()
					.getResourceAsStream(BRANCH_CSV));
			if (scanner.hasNextLine()) {
				HashMap<Branch, ArrayList<HashMap<Book, Integer>>> branches = readBranchCopies(readBooks());// Get
																											// books
																											// info
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					Branch branch = new Branch(Integer.parseInt(line
							.split("\t")[0]), line.split("\t")[1],
							line.split("\t")[2]);

					branch.setBooks(branches.get(branch));
					this.branches.add(branch);

				}
			} else
				throw new FileEmptyException(
						"Cannot Initialize. Contact Administrator");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	/**
	 * Get the book and author information
	 * 
	 * @return list of books
	 * @throws FileNotFoundException
	 * @throws FileEmptyException
	 */
	private ArrayList<Book> readBooks() throws FileNotFoundException,
			FileEmptyException {
		Scanner scanner = new Scanner(getClass().getClassLoader()
				.getResourceAsStream(BOOK_AUTHOR_CSV));
		try {
			if (scanner.hasNextLine()) {
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					String bookAuthorTitle = scanner.nextLine();
					String[] bookElements = bookAuthorTitle.split("\t");

					String authorsString = bookElements[1];
					ArrayList<Author> authors = new ArrayList<Author>();
					for (String authorString : authorsString.split(",")) {
						Author author;
						int type = 0;
						if (ORGANIZATIONS.contains(authorString)) {
							type = 1;
						}

						if (authorString.contains("(")) {
							String authorName = authorString.substring(0,
									authorString.indexOf('('));
							if (authorString.substring(
									authorString.indexOf('('),
									authorString.length()).contains(
									Role.EDITOR.getRole())) {
								author = new Author(authorName, type,
										Role.EDITOR);
							} else {
								author = new Author(authorName, type,
										Role.ILLUSTRATOR);
							}
						} else {
							author = new Author(authorString, type, Role.AUTHOR);
						}
						authors.add(author);
					}

					books.add(new Book(bookElements[0], bookElements[2],
							authors));
				}
			} else
				throw new FileEmptyException(
						"Cannot Initialize. Contact Administrator");
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return books;
	}

	/**
	 * Get the information about books present in different library branches
	 * 
	 * @param books
	 * @return Branch - <Book, Number of Copies>
	 * @throws FileNotFoundException
	 * @throws FileEmptyException
	 */
	private HashMap<Branch, ArrayList<HashMap<Book, Integer>>> readBranchCopies(
			ArrayList<Book> books) throws FileNotFoundException,
			FileEmptyException {
		HashMap<Branch, ArrayList<HashMap<Book, Integer>>> branches;
		Scanner scanner = new Scanner(getClass().getClassLoader()
				.getResourceAsStream(BOOK_COPIES_CSV));
		try {
			if (scanner.hasNextLine()) {
				scanner.nextLine();

				branches = new HashMap<Branch, ArrayList<HashMap<Book, Integer>>>();
				while (scanner.hasNextLine()) {
					String bookBranchCopy = scanner.nextLine();
					HashMap<Book, Integer> bookCopies = new HashMap<Book, Integer>();
					bookCopies.put(books.get(books.indexOf(new Book(
							bookBranchCopy.split("\t")[0]))), Integer
							.parseInt(bookBranchCopy.split("\t")[2]));

					Branch branch = new Branch(Integer.parseInt(bookBranchCopy
							.split("\t")[1]));
					if (branches.containsKey(branch)) {
						branches.get(branch).add(bookCopies);
					} else {
						ArrayList<HashMap<Book, Integer>> tempBooks = new ArrayList<HashMap<Book, Integer>>();
						tempBooks.add(bookCopies);
						branches.put(branch, tempBooks);
					}
				}
			} else
				throw new FileEmptyException(
						"Cannot Initialize. Contact Administrator");
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return branches;
	}

	/**
	 * Get the Library user information
	 * 
	 * @throws FileNotFoundException
	 * @throws FileEmptyException
	 */
	private void readBorrowers() throws FileNotFoundException,
			FileEmptyException {
		Scanner scanner = new Scanner(getClass().getClassLoader()
				.getResourceAsStream(BORROWER_CSV));
		try {
			if (scanner.hasNextLine()) {
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					String borrower = scanner.nextLine();
					String[] borrowerElement = borrower.split("\t");
					borrowers.add(new Borrower(borrowerElement[0],
							borrowerElement[1], borrowerElement[2],
							borrowerElement[3], borrowerElement[4],
							borrowerElement[5], borrowerElement[6]));
				}
			} else
				throw new FileEmptyException(
						"Cannot Initialize. Contact Administrator");
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

	/**
	 * Load the initial book loans data
	 * 
	 * @throws FileNotFoundException
	 * @throws FileEmptyException
	 */
	private void readLoans() throws FileNotFoundException, FileEmptyException {
		Scanner scanner = new Scanner(getClass().getClassLoader()
				.getResourceAsStream(BOOK_LOANS_CSV));

		try {
			if (scanner.hasNextLine()) {
				scanner.nextLine();
				while (scanner.hasNextLine()) {
					String loan = scanner.nextLine();
					String[] loanElement = loan.split("\t");
					loans.add(new Loan(new Book(loanElement[1]), new Branch(
							Integer.parseInt(loanElement[2])), new Borrower(
							loanElement[3]), loanElement[4], loanElement[5],
							loanElement[6].equalsIgnoreCase("NULL") ? null
									: loanElement[6]));
				}
			} else
				throw new FileEmptyException(
						"Cannot Initialize. Contact Administrator");
		} finally {
			if (scanner != null)
				scanner.close();
		}
	}

}
