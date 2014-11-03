package sagar.databasedesign.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import sagar.databasedesign.enums.Filter;
import sagar.databasedesign.library.Author;
import sagar.databasedesign.library.Book;
import sagar.databasedesign.library.Borrower;
import sagar.databasedesign.library.Branch;
import sagar.databasedesign.library.Fine;
import sagar.databasedesign.library.Loan;
import sagar.databasedesign.library.User;

/**
 * Singleton Database Manager
 * 
 * @author Sagar
 * 
 */
public final class DatabaseManager {
	private static DatabaseManager databaseManager;
	private static Connection connection = null;

	private static final String URI = "jdbc:mysql://localhost:3306/";
	private static final String SQLUSER = "root";
	private static final String SCHEMA = "library";
	private static final String USE_STATEMENT = "use " + SCHEMA + ";";

	private static final String INSERT_SQL = "library.sql";
	private static final String INSERT_SOURCE = "source " + INSERT_SQL;

	private static final String DROP_SQL = "drop_library.sql";

	private static final String USERINFO = "userinfo";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String USER = "utd";
	private static final String PWD = "utd";
	private static final String INSERT_USER = "INSERT INTO " + SCHEMA + "."
			+ USERINFO + " VALUES('" + USER + "', SHA1('" + PWD + "'));";
	private static final String SELECT_USER = "SELECT " + USERNAME + ", "
			+ PASSWORD + " FROM " + SCHEMA + "." + USERINFO + " WHERE "
			+ USERNAME + "=? AND " + PASSWORD + "=SHA1(?);";

	private static final String BOOK = "book";
	private static final String ISBN = "isbn";
	private static final String TITLE = "title";
	private static final String INSERT_BOOK = "INSERT INTO " + SCHEMA + "."
			+ BOOK + " VALUES(?, ?);";

	private static final String AUTHOR = "author";
	private static final String AUTHOR_NAME = "author_name";
	private static final String TYPE = "type";
	private static final String ROLE = "role";
	private static final String INSERT_AUTHOR = "INSERT INTO " + SCHEMA + "."
			+ AUTHOR + " VALUES(?, ?, ?, ?);";
	private static final String SELECT_BOOK = "SELECT " + ISBN + " FROM "
			+ SCHEMA + "." + BOOK;
	private static final String SELECT_ALL_BOOK = "SELECT DISTINCT " + ISBN
			+ ", " + TITLE + " FROM " + SCHEMA + "." + BOOK + " NATURAL JOIN "
			+ SCHEMA + "." + AUTHOR + " WHERE " + ISBN + " LIKE ? OR " + TITLE
			+ " LIKE ? OR " + AUTHOR_NAME + " LIKE ?;";
	private static final String SELECT_ISBN_BOOK = "SELECT DISTINCT " + ISBN
			+ ", " + TITLE + " FROM " + SCHEMA + "." + BOOK + " NATURAL JOIN "
			+ SCHEMA + "." + AUTHOR + " WHERE " + ISBN + " LIKE ?;";
	private static final String SELECT_TITLE_BOOK = "SELECT DISTINCT " + ISBN
			+ ", " + TITLE + " FROM " + SCHEMA + "." + BOOK + " NATURAL JOIN "
			+ SCHEMA + "." + AUTHOR + " WHERE " + TITLE + " LIKE ?;";
	private static final String SELECT_AUTHOR_BOOK = "SELECT DISTINCT " + ISBN
			+ ", " + TITLE + " FROM " + SCHEMA + "." + BOOK + " NATURAL JOIN "
			+ SCHEMA + "." + AUTHOR + " WHERE " + AUTHOR_NAME + " LIKE ?;";

	private static final String SELECT_AUTHOR = "SELECT " + AUTHOR_NAME
			+ " FROM " + SCHEMA + "." + AUTHOR + " WHERE " + ISBN + "=?;";

	private static final String BRANCH = "branch";
	private static final String BRANCH_ID = "branch_id";
	private static final String BRANCH_NAME = "branch_name";
	private static final String ADDRESS = "address";
	private static final String INSERT_BRANCH = "INSERT INTO " + SCHEMA + "."
			+ BRANCH + " VALUES(?, ?, ?);";

	private static final String BOOK_COPIES = "book_copies";
	private static final String COPIES = "num_of_copies";
	private static final String AV_COPIES = "available_copies";
	private static final String INSERT_COPIES = "INSERT INTO " + SCHEMA + "."
			+ BOOK_COPIES + " VALUES(?, ?, ?, ?);";
	// private static final String SELECT_COPIES = "SELECT SUM(" + COPIES
	// + ") FROM " + SCHEMA + "." + BOOK_COPIES + " WHERE " + ISBN + "=?;";
	private static final String SELECT_BRANCH = "SELECT " + BRANCH_ID + ", "
			+ COPIES + ", " + AV_COPIES + " FROM " + SCHEMA + "." + BOOK_COPIES
			+ " WHERE " + ISBN + "=?;";
	private static final String SELECT_AV_COPIES = "SELECT " + AV_COPIES
			+ " FROM " + SCHEMA + "." + BOOK_COPIES + " WHERE " + ISBN
			+ "=? AND " + BRANCH_ID + "=?;";
	private static final String UPDATE_COPIES = "UPDATE " + SCHEMA + "."
			+ BOOK_COPIES + " SET " + AV_COPIES + "=? WHERE " + ISBN
			+ "=? AND " + BRANCH_ID + "=?;";

	private static final String BORROWER = "borrower";
	private static final String CARD_NO = "card_no";
	private static final String FNAME = "fname";
	private static final String LNAME = "lname";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String PHONE = "phone";
	private static final String INSERT_BORROWER = "INSERT INTO " + SCHEMA + "."
			+ BORROWER + " VALUES(?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ALL_BORROWER = "SELECT " + CARD_NO
			+ ", " + FNAME + ", " + LNAME + ", " + ADDRESS + " FROM " + SCHEMA + "." + BORROWER;
	private static final String SELECT_CARD_BORROWER = "SELECT " + CARD_NO
			+ ", " + FNAME + ", " + LNAME + " FROM " + SCHEMA + "." + BORROWER
			+ " WHERE " + CARD_NO + "=?";
	private static final String SELECT_MAX_BORROWER = "SELECT MAX(" + CARD_NO
			+ ") FROM " + SCHEMA + "." + BORROWER;

	private static final String BOOK_LOANS = "book_loans";
	private static final String DATE_OUT = "date_out";
	private static final String DUE_DATE = "due_date";
	private static final String DATE_IN = "date_in";
	private static final String INSERT_LOANS = "INSERT INTO " + SCHEMA + "."
			+ BOOK_LOANS + "(" + ISBN + ", " + BRANCH_ID + ", " + CARD_NO
			+ ", " + DATE_OUT + ", " + DUE_DATE + ", " + DATE_IN
			+ ") VALUES(?, ?, ?, ?, ?, ?);";
	private static final String SELECT_BORROW_COUNT = "SELECT COUNT(*) FROM "
			+ SCHEMA + "." + BORROWER + " NATURAL JOIN " + SCHEMA + "."
			+ BOOK_LOANS + " WHERE " + CARD_NO + "=?;";
	private static final String SELECT_ISBN_LOAN = "SELECT " + ISBN + ", "
			+ TITLE + ", " + BRANCH_ID + ", " + CARD_NO + " FROM " + SCHEMA
			+ "." + BOOK_LOANS + " NATURAL JOIN " + SCHEMA + "." + BOOK
			+ " WHERE " + ISBN + "=? AND " + DATE_IN + " IS NULL;";
	private static final String SELECT_CARD_LOAN = "SELECT " + ISBN + ", "
			+ TITLE + ", " + BRANCH_ID + ", " + CARD_NO + " FROM " + SCHEMA
			+ "." + BOOK_LOANS + " NATURAL JOIN " + SCHEMA + "." + BOOK
			+ " WHERE " + CARD_NO + "=? AND " + DATE_IN + " IS NULL;";
	private static final String UPDATE_LOANS = "UPDATE " + SCHEMA + "."
			+ BOOK_LOANS + " SET " + DATE_IN + "=? WHERE " + ISBN + "=? AND "
			+ BRANCH_ID + "=? AND " + CARD_NO + "=?;";
	private static final String SELECT_DATE_OUT = "SELECT " + DATE_OUT
			+ " FROM " + SCHEMA + "." + BOOK_LOANS + " WHERE " + ISBN
			+ "=? AND " + BRANCH_ID + "=? AND " + CARD_NO + "=?;";

	private static final String FINES = "FINES";
	private static final String LOAN_ID = "loan_id";
	private static final String FINE_AMT = "fine_amt";
	private static final String PAID = "paid";
	private static final String INSERT_FINES = "INSERT INTO " + SCHEMA + "."
			+ FINES + " VALUES(?, ?, ?);";

	private DatabaseManager() {
		try {
			connection = DriverManager.getConnection(URI, SQLUSER, "");

			Statement stmt = connection.createStatement();

			stmt.execute(USE_STATEMENT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseManager getInstane() {
		if (databaseManager == null)
			databaseManager = new DatabaseManager();
		return databaseManager;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Insert a user into database
	 */
	public void insertUser() {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_USER);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the user for authentication
	 * 
	 * @param user
	 * @return
	 */
	public User getUserInfo(User user) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_USER);
			statement.setString(1, user.getAuthentication().getUserName());
			statement.setString(2, new String(user.getAuthentication()
					.getPassword()));
			rs = statement.executeQuery();

			if (rs != null && rs.next())
				return new User(rs.getString(USERNAME), rs.getString(PASSWORD)
						.toCharArray());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void insertBook(Book book) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_BOOK);
			statement.setString(1, book.getId());
			statement.setString(2, book.getTitle());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAuthor(Book book, Author author) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_AUTHOR);
			statement.setString(1, book.getId());
			statement.setString(2, author.getName());
			statement.setInt(3, author.getType());
			statement.setString(4, author.getRole().getRole());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Book> getBooks() {
		ArrayList<Book> books = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_BOOK);

			rs = statement.executeQuery();
			books = new ArrayList<Book>();
			while (rs != null && rs.next()) {
				books.add(new Book(rs.getString(ISBN), null, null));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	public ArrayList<Book> getBooks(String query, String filter) {
		ArrayList<Book> books = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		ResultSet rsAuthor = null;
		try {
			if (filter.equalsIgnoreCase(Filter.ALL.getFilter())) {
				statement = connection.prepareStatement(SELECT_ALL_BOOK);
				statement.setString(1, "%" + query + "%");
				statement.setString(2, "%" + query + "%");
				statement.setString(3, "%" + query + "%");
			} else if (filter.equalsIgnoreCase(Filter.ISBN.getFilter())) {
				statement = connection.prepareStatement(SELECT_ISBN_BOOK);
				statement.setString(1, "%" + query + "%");
			} else if (filter.equalsIgnoreCase(Filter.TITLE.getFilter())) {
				statement = connection.prepareStatement(SELECT_TITLE_BOOK);
				statement.setString(1, "%" + query + "%");
			} else if (filter.equalsIgnoreCase(Filter.AUTHOR.getFilter())) {
				statement = connection.prepareStatement(SELECT_AUTHOR_BOOK);
				statement.setString(1, "%" + query + "%");
			}

			rs = statement.executeQuery();
			books = new ArrayList<Book>();
			statement = connection.prepareStatement(SELECT_AUTHOR);
			while (rs != null && rs.next()) {
				statement.setString(1, rs.getString(ISBN));
				rsAuthor = statement.executeQuery();
				ArrayList<Author> authors = new ArrayList<Author>();
				while (rsAuthor != null && rsAuthor.next())
					authors.add(new Author(rsAuthor.getString(AUTHOR_NAME), 0,
							null));

				books.add(new Book(rs.getString(ISBN), rs.getString(TITLE),
						authors));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}

	public void insertBranch(Branch branch) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_BRANCH);
			statement.setInt(1, branch.getId());
			statement.setString(2, branch.getName());
			statement.setString(3, branch.getAddress());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertBookCopies(Branch branch, Book book, int copies) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_COPIES);
			statement.setString(1, book.getId());
			statement.setInt(2, branch.getId());
			statement.setInt(3, copies);
			statement.setInt(4, copies);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateAvailableCopies(Book book, Branch branch, int copies) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_COPIES);
			statement.setInt(1, copies);
			statement.setString(2, book.getId());
			statement.setInt(3, branch.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * public int getAvailableBookCount(String isbn) { PreparedStatement
	 * statement = null; ResultSet rs = null; int bookCount = 0; try { statement
	 * = connection.prepareStatement(SELECT_COPIES); statement.setString(1,
	 * isbn); rs = statement.executeQuery(); if (rs.next()) bookCount =
	 * rs.getInt(1); } catch (SQLException e) { e.printStackTrace(); } finally {
	 * try { if (statement != null) statement.close(); if (rs != null)
	 * rs.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * bookCount;
	 * 
	 * }
	 */

	public int getAvailableCopies(Book book, Branch branch) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		int availableCopies = 0;
		try {
			statement = connection.prepareStatement(SELECT_AV_COPIES);
			statement.setString(1, book.getId());
			statement.setInt(2, branch.getId());
			rs = statement.executeQuery();
			if (rs != null && rs.next())
				availableCopies = rs.getInt(AV_COPIES);
		} catch (SQLException e) {

		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return availableCopies;
	}

	public ArrayList<HashMap<Branch, Integer>> getBranchData(Book book) {
		ArrayList<HashMap<Branch, Integer>> branchesData = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_BRANCH);
			statement.setString(1, book.getId());
			rs = statement.executeQuery();
			branchesData = new ArrayList<HashMap<Branch, Integer>>();
			while (rs != null && rs.next()) {
				Branch branch = new Branch(rs.getInt(BRANCH_ID));
				HashMap<Book, Integer> books = new HashMap<Book, Integer>();
				books.put(book, rs.getInt(COPIES));
				ArrayList<HashMap<Book, Integer>> booksData = new ArrayList<HashMap<Book, Integer>>();
				booksData.add(books);
				branch.setBooks(booksData);

				HashMap<Branch, Integer> branches = new HashMap<Branch, Integer>();
				branches.put(branch, rs.getInt(AV_COPIES));

				branchesData.add(branches);
			}
		} catch (SQLException e) {

		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return branchesData;
	}

	public void insertBorrower(Borrower borrower) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_BORROWER);
			statement.setInt(1, borrower.getCardNumber());
			statement.setString(2, borrower.getFirstName());
			statement.setString(3, borrower.getLastName());
			statement.setString(4, borrower.getAddress());
			statement.setString(5, borrower.getCity());
			statement.setString(6, borrower.getState());
			statement.setString(7, borrower.getPhone());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Borrower> getBorrowers() {
		ArrayList<Borrower> borrowers = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_BORROWER);
			rs = statement.executeQuery();
			borrowers = new ArrayList<Borrower>();
			while (rs != null && rs.next()) {
				borrowers.add(new Borrower(rs.getInt(CARD_NO), rs
						.getString(FNAME), rs.getString(LNAME), rs.getString(ADDRESS), null,
						null, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borrowers;

	}

	public Borrower getBorrowers(String cardNo) {
		Borrower borrower = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_CARD_BORROWER);
			statement.setString(1, cardNo);
			rs = statement.executeQuery();
			while (rs != null && rs.next()) {
				borrower = new Borrower(rs.getInt(CARD_NO),
						rs.getString(FNAME), rs.getString(LNAME), null, null,
						null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borrower;

	}

	public int getMaxCardNo() {
		int max = 0;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SELECT_MAX_BORROWER);
			rs = statement.executeQuery();
			if (rs != null && rs.next()) {
				max = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return max;
	}

	public boolean insertLoans(Loan loan) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_LOANS);
			statement.setString(1, loan.getBook().getId());
			statement.setInt(2, loan.getBranch().getId());
			statement.setInt(3, loan.getBorrower().getCardNumber());
			statement.setDate(4, new Date(loan.getDateOut().getTimeInMillis()));
			statement.setDate(5, new Date(loan.getDueDate().getTimeInMillis()));
			if (loan.getDateIn() != null)
				statement.setDate(6, new Date(loan.getDateIn()
						.getTimeInMillis()));
			else
				statement.setDate(6, null);
			statement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Loan> getLoans(Book book) {
		ArrayList<Loan> loans = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		ResultSet rsBook = null;
		try {
			statement = connection.prepareStatement(SELECT_ISBN_LOAN);
			statement.setString(1, book.getId());
			rs = statement.executeQuery();
			loans = new ArrayList<Loan>();
			statement = connection.prepareStatement(SELECT_ISBN_BOOK);
			statement.setString(1, book.getId());
			rsBook = statement.executeQuery();
			if (rsBook != null && rsBook.next())
				book.setTitle(rsBook.getString(TITLE));
			while (rs != null && rs.next()) {

				loans.add(new Loan(book, new Branch(rs.getInt(BRANCH_ID)),
						getBorrowers(rs.getString(CARD_NO))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loans;
	}

	public ArrayList<Loan> getLoans(Borrower borrower) {
		ArrayList<Loan> loans = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		ResultSet rsBook = null;
		try {
			statement = connection.prepareStatement(SELECT_CARD_LOAN);
			statement.setInt(1, borrower.getCardNumber());
			rs = statement.executeQuery();
			loans = new ArrayList<Loan>();

			statement = connection.prepareStatement(SELECT_ISBN_BOOK);

			while (rs != null && rs.next()) {
				statement.setString(1, rs.getString(ISBN));
				rsBook = statement.executeQuery();
				Book book = new Book(rs.getString(ISBN));
				if (rsBook != null && rsBook.next())
					book.setTitle(rsBook.getString(TITLE));

				loans.add(new Loan(book, new Branch(rs.getInt(BRANCH_ID)),
						getBorrowers(rs.getString(CARD_NO))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loans;
	}

	public void checkout(Loan loan, int copies) {
		insertLoans(loan);
		updateAvailableCopies(loan.getBook(), loan.getBranch(), copies);
	}

	public void updateLoans(Book book, Branch branch, Borrower borrower,
			Date dateIn) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(UPDATE_LOANS);
			statement.setDate(1, dateIn);
			statement.setString(2, book.getId());
			statement.setInt(3, branch.getId());
			statement.setInt(4, borrower.getCardNumber());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void checkin(Loan loan, int copies) {
		updateLoans(loan.getBook(), loan.getBranch(), loan.getBorrower(),
				new Date(loan.getDateIn().getTimeInMillis()));
		updateAvailableCopies(loan.getBook(), loan.getBranch(), copies);
	}

	public Date getDateOut(Loan loan) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Date dateOut = null;
		try {
			statement = connection.prepareStatement(SELECT_DATE_OUT);
			statement.setString(1, loan.getBook().getId());
			statement.setInt(2, loan.getBranch().getId());
			statement.setInt(3, loan.getBorrower().getCardNumber());
			rs = statement.executeQuery();
			if (rs.next())
				dateOut = rs.getDate(DATE_OUT);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dateOut;
	}

	public int getBorrowCount(String cardNo) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		int borrowCount = 0;
		try {
			statement = connection.prepareStatement(SELECT_BORROW_COUNT);
			statement.setString(1, cardNo);
			rs = statement.executeQuery();
			if (rs.next())
				borrowCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borrowCount;
	}

	public void insertFine(Fine fine) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_FINES);
			statement.setString(0, fine.getLoanId());
			statement.setFloat(1, fine.getFine_amt());
			statement.setBoolean(2, fine.isPaid());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void dropLibrary() {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			Scanner scanner = null;
			try {
				scanner = new Scanner(getClass().getClassLoader()
						.getResourceAsStream(DROP_SQL));
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (!line.isEmpty())
						statement.addBatch(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (scanner != null)
					scanner.close();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createLibrary() {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			Scanner scanner = null;
			try {
				scanner = new Scanner(getClass().getClassLoader()
						.getResourceAsStream(INSERT_SQL));
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (!line.isEmpty())
						statement.addBatch(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (scanner != null)
					scanner.close();
			}
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		if (databaseManager != null)
			if (connection != null)
				try {
					connection.close();
					databaseManager = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

}
