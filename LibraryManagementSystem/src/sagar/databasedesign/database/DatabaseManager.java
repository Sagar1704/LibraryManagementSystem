package sagar.databasedesign.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
			+ BOOK + " VALUES('?', '?');";
	private static final String SELECT_BOOK = "SELECT " + ISBN + "," + TITLE
			+ " FROM " + SCHEMA + "." + BOOK + ";";

	private static final String AUTHOR = "author";
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String ROLE = "role";
	private static final String INSERT_AUTHOR = "INSERT INTO " + SCHEMA + "."
			+ AUTHOR + " VALUES('?', '?', ?, '?');";

	private static final String BRANCH = "branch";
	private static final String BRANCH_ID = "id";
	private static final String BRANCH_NAME = "branch_name";
	private static final String ADDRESS = "address";
	private static final String INSERT_BRANCH = "INSERT INTO " + SCHEMA + "."
			+ BRANCH + " VALUES(?, '?', '?');";

	private static final String BOOK_COPIES = "book_copies";
	private static final String COPIES = "num_of_copies";
	private static final String INSERT_COPIES = "INSERT INTO " + SCHEMA + "."
			+ BOOK_COPIES + " VALUES('?', ?, ?);";

	private static final String BORROWER = "borrower";
	private static final String CARD_NO = "card_no";
	private static final String FNAME = "fname";
	private static final String LNAME = "lname";
	private static final String PHONE = "phone";
	private static final String INSERT_BORROWER = "INSERT INTO " + SCHEMA + "."
			+ BORROWER + " VALUES('?', '?', '?', '?', '?');";

	private static final String BOOK_LOANS = "book_loans";
	private static final String DATE_OUT = "date_out";
	private static final String DUE_DATE = "due_date";
	private static final String DATE_IN = "date_in";
	private static final String INSERT_LOANS = "INSERT INTO " + SCHEMA + "."
			+ BOOK_LOANS + " VALUES('?', ?, '?', '?', '?', '?');";

	private static final String FINES = "FINES";
	private static final String LOAN_ID = "loan_id";
	private static final String FINE_AMT = "fine_amt";
	private static final String PAID = "paid";
	private static final String INSERT_FINES = "INSERT INTO " + SCHEMA + "."
			+ FINES + " VALUES('?', ?, ?);";

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
			statement.setString(0, book.getId());
			statement.setString(1, book.getTitle());
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
			statement.setString(0, book.getId());
			statement.setString(1, author.getName());
			statement.setInt(2, author.getType());
			statement.setString(3, author.getRole().getRole());
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

	public void insertBranch(Branch branch) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_BRANCH);
			statement.setInt(0, branch.getId());
			statement.setString(1, branch.getName());
			statement.setString(2, branch.getAddress());
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
			statement.setString(0, book.getId());
			statement.setInt(1, branch.getId());
			statement.setInt(2, copies);
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

	public void insertBorrower(Borrower borrower) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_BORROWER);
			statement.setString(0, borrower.getCardNumber());
			statement.setString(1, borrower.getFirstName());
			statement.setString(2, borrower.getLastName());
			statement.setString(3, borrower.getAddress());
			statement.setString(4, borrower.getPhone());
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

	public void insertLoans(Loan loan) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_LOANS);
			statement.setString(1, loan.getBook().getId());
			statement.setInt(2, loan.getBranch().getId());
			statement.setString(3, loan.getBorrower().getCardNumber());
			statement.setDate(4, new Date(loan.getDateOut().getTimeInMillis()));
			statement.setDate(5, new Date(loan.getDueDate().getTimeInMillis()));
			statement.setDate(6, new Date(loan.getDateIn().getTimeInMillis()));
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
					if(!line.isEmpty())
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
					if(!line.isEmpty())
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
