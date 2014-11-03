package sagar.databasedesign.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import sagar.databasedesign.enums.Filter;
import sagar.databasedesign.enums.SearchBy;
import sagar.databasedesign.library.Book;
import sagar.databasedesign.library.Borrower;
import sagar.databasedesign.library.Branch;
import sagar.databasedesign.library.Library;
import sagar.databasedesign.library.Loan;
import sagar.databasedesign.library.User;

import com.toedter.calendar.JCalendar;

/**
 * GUI for the Library Management system
 * 
 * @author Sagar
 * 
 */
public class LoginWindow {

	private JFrame frmLogin;
	private JLabel labelTitle;
	private JLabel labelUsername;
	private JTextField textFieldUsername;
	private JLabel labelPassword;
	private JPasswordField passwordField;
	private JPanel panelLogin;
	private JPanel panelLMS;
	private JPanel panelSearch;
	private JTextField textFieldSearch;
	private JTable table;
	private JButton buttonLogin;
	private JComboBox<String> comboBoxFilter;
	private JButton btnSearch;
	private JScrollPane scrollPaneResults;
	private DefaultTableModel coDTM;
	private JButton btnCheckout;
	private JPanel panelMenu;
	private JComboBox<String> comboBoxBorrower;
	private JLabel lblBorrower;
	private JMenuItem mntmSearch;
	private JPanel panelCheckin;
	private JMenuBar menuBar;
	private JMenuItem mntmCheckout;
	private JTable ciTable;
	private JScrollPane scrollPaneCI;
	private JComboBox<String> comboBoxCIBorrower;
	private JButton btnGetData;
	private JLabel lblCheckinDate;
	private JComboBox<String> comboBoxISBN;
	private JLabel lblSearchBy;
	private JComboBox<String> comboBoxSearchBy;
	private DefaultTableModel ciDTM;
	private JButton btnCheckin;
	private JCalendar calendar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.getContentPane().setBackground(Color.ORANGE);
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setFont(
				new Font("Segoe UI Black", Font.PLAIN, 24));
		frmLogin.setTitle("Library Management System");
		frmLogin.setBounds(100, 100, 1269, 701);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(new CardLayout(0, 0));

		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 165, 0));
		frmLogin.getContentPane().add(panelLogin, "name_611262067352413");
		panelLogin.setLayout(null);

		labelTitle = new JLabel("LOGIN");
		labelTitle.setVerticalAlignment(SwingConstants.TOP);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setForeground(new Color(0, 128, 0));
		labelTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		labelTitle.setAlignmentY(0.0f);
		labelTitle.setAlignmentX(0.5f);
		labelTitle.setBounds(475, 76, 289, 49);
		panelLogin.add(labelTitle);

		labelUsername = new JLabel("Username");
		labelUsername.setToolTipText("Enter Username in the text");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		labelUsername.setBounds(355, 190, 199, 57);
		panelLogin.add(labelUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		textFieldUsername.setToolTipText("utd");
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(583, 190, 378, 56);
		panelLogin.add(textFieldUsername);

		labelPassword = new JLabel("Password");
		labelPassword.setToolTipText("Enter Password in the text");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		labelPassword.setBounds(355, 311, 199, 65);
		panelLogin.add(labelPassword);

		buttonLogin = new JButton("Login");
		buttonLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldUsername.getText().equals("")
						|| passwordField.getPassword().length <= 0)
					JOptionPane.showMessageDialog(frmLogin,
							"Please enter both username and password");
				else {
					if (new User(textFieldUsername.getText(), passwordField
							.getPassword()).authenticate()) {
						panelLMS.setVisible(true);
						panelLogin.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(frmLogin,
								"Username and Password do not match");
					}
				}
			}
		});
		buttonLogin.setToolTipText("Click to log in");
		buttonLogin.setBounds(487, 451, 289, 49);
		panelLogin.add(buttonLogin);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		passwordField.setBounds(583, 315, 378, 57);
		panelLogin.add(passwordField);

		panelLMS = new JPanel();
		panelLMS.setBackground(new Color(255, 165, 0));
		frmLogin.getContentPane().add(panelLMS, "name_611365057672554");
		panelLMS.setLayout(null);

		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 1263, 33);
		panelMenu.setBackground(new Color(255, 165, 0));
		panelLMS.add(panelMenu);
		panelMenu.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 140, 0));
		menuBar.setBounds(0, 0, 1263, 34);
		panelMenu.add(menuBar);
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JMenu mnLibrary = new JMenu("Library");
		menuBar.add(mnLibrary);
		mnLibrary.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		panelSearch = new JPanel();
		panelSearch.setBackground(new Color(255, 165, 0));
		panelSearch.setBounds(0, 33, 1263, 628);
		panelLMS.add(panelSearch);
		panelSearch.setLayout(null);
		panelSearch.setVisible(false);

		btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCheckout.setVisible(false);
		btnCheckout.setEnabled(false);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedCount = table.getSelectedRowCount();
				if (selectedCount == 0)
					JOptionPane.showMessageDialog(frmLogin,
							"Please select a book");
				else {
					int borrowCount = new Loan()
							.getBorrowCount(comboBoxBorrower.getItemAt(
									comboBoxBorrower.getSelectedIndex()).split(
									"::")[0]);
					if (borrowCount == Borrower.MAX_BORROW)
						JOptionPane
								.showMessageDialog(
										frmLogin,
										"Sorry, Cannot Checkout!\n"
												+ comboBoxBorrower
														.getItemAt(comboBoxBorrower
																.getSelectedIndex())
												+ " has already reached max checkout limit of: "
												+ Borrower.MAX_BORROW);
					else if (selectedCount + borrowCount > Borrower.MAX_BORROW)
						JOptionPane.showMessageDialog(
								frmLogin,
								"Sorry, Cannot Checkout!\n"
										+ comboBoxBorrower
												.getItemAt(comboBoxBorrower
														.getSelectedIndex())
										+ " can only borrow "
										+ (Borrower.MAX_BORROW - borrowCount)
										+ " more books.");
					else {
						boolean inStock = true;
						for (int selection : table.getSelectedRows()) {
							if (table.getValueAt(selection, 5).toString()
									.equalsIgnoreCase("0")) {
								JOptionPane
										.showMessageDialog(frmLogin,
												"Sorry, one or more books in your selection are out of stock.");
								inStock = false;
								break;
							}
						}

						if (inStock) {
							for (int selection : table.getSelectedRows()) {
								Loan loan = new Loan(
										new Book(
												""
														+ table.getValueAt(
																selection, 0)),
										new Branch(
												Integer.parseInt(""
														+ table.getValueAt(
																selection, 3))),
										new Borrower(
												comboBoxBorrower
														.getItemAt(
																comboBoxBorrower
																		.getSelectedIndex())
														.split("::")[0]));
								loan.checkout(Integer.parseInt(""
										+ table.getValueAt(selection, 5)) - 1);
							}
							JOptionPane.showMessageDialog(frmLogin,
									"Checked Out");
							btnSearch.doClick();
						}
					}
				}
			}
		});
		btnCheckout.setBounds(552, 591, 128, 29);
		panelSearch.add(btnCheckout);

		mntmSearch = new JMenuItem("Search Book");
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearch.setVisible(true);
				btnCheckout.setVisible(false);
				lblBorrower.setVisible(false);
				comboBoxBorrower.setVisible(false);
			}
		});
		mntmSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmSearch);

		lblBorrower = new JLabel("Borrower    ");
		lblBorrower.setForeground(new Color(0, 128, 0));
		lblBorrower.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBorrower.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblBorrower.setBounds(215, 16, 198, 30);
		lblBorrower.setVisible(false);
		panelSearch.add(lblBorrower);

		comboBoxBorrower = new JComboBox<String>();
		comboBoxBorrower.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBoxBorrower.setBounds(416, 16, 377, 30);
		comboBoxBorrower.setVisible(false);
		panelSearch.add(comboBoxBorrower);

		mntmCheckout = new JMenuItem("Checkout");
		mntmCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmSearch.doClick();
				btnCheckout.setVisible(true);
				lblBorrower.setVisible(true);
				for (Borrower borrower : new Borrower().getBorrowers()) {
					comboBoxBorrower.addItem(borrower.getCardNumber() + "::"
							+ borrower.getFirstName() + " "
							+ borrower.getLastName());
				}
				comboBoxBorrower.setVisible(true);
			}
		});
		mntmCheckout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmCheckout);

		JMenuItem mntmCheckin = new JMenuItem("Checkin");
		mntmCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearch.setVisible(false);
				panelCheckin.setVisible(true);
			}
		});
		mntmCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmCheckin);

		JMenuItem mntmGetFines = new JMenuItem("Get Fines");
		mntmGetFines.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmGetFines);

		comboBoxFilter = new JComboBox<String>();
		comboBoxFilter.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBoxFilter.setBounds(893, 52, 128, 30);
		comboBoxFilter.addItem(Filter.ALL.getFilter());
		comboBoxFilter.addItem(Filter.ISBN.getFilter());
		comboBoxFilter.addItem(Filter.TITLE.getFilter());
		comboBoxFilter.addItem(Filter.AUTHOR.getFilter());
		panelSearch.add(comboBoxFilter);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setForeground(new Color(0, 0, 139));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		table.setRowHeight(50);
		table.setColumnSelectionAllowed(false);

		scrollPaneResults = new JScrollPane();
		scrollPaneResults
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneResults
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneResults.setBounds(215, 100, 808, 475);
		scrollPaneResults.setViewportView(table);
		panelSearch.add(scrollPaneResults);

		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Book> books = new Book().searchBooks(textFieldSearch
						.getText(), comboBoxFilter.getItemAt(comboBoxFilter
						.getSelectedIndex()));
				coDTM = new DefaultTableModel(0, 0) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 3630094735024498745L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				coDTM.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (coDTM.getRowCount() == 0)
							btnCheckout.setEnabled(false);
						else
							btnCheckout.setEnabled(true);
					}
				});

				table.setModel(coDTM);

				String[] header = { "ISBN", "Book Title", "Author",
						"Branch ID", "Total Copies", "Available Copies" };
				coDTM.setColumnIdentifiers(header);
				for (Book book : books) {
					// boolean first = true;
					for (HashMap<Branch, Integer> branches : book
							.getBranchData()) {
						for (Branch branch : branches.keySet()) {
							// if (first) {
							coDTM.addRow(new String[] { book.getId(),
									book.getTitle(),
									book.getAuthors().toString(),
									"" + branch.getId(),
									"" + branch.getBooks().get(0).get(book),
									"" + branches.get(branch) });
							// first = false;
							// } else {
							// dtm.addRow(new String[] {
							// "",
							// "",
							// "",
							// "" + branch.getId(),
							// "" + branch.getBooks().get(0).get(book),
							// "" + branches.get(branch) });
							// }
						}
					}

				}

			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnSearch.setBounds(792, 52, 101, 30);
		panelSearch.add(btnSearch);

		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textFieldSearch.setBounds(215, 52, 578, 30);
		panelSearch.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		textFieldSearch.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						if (textFieldSearch.getText().isEmpty())
							btnSearch.setEnabled(false);
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						if (!textFieldSearch.getText().isEmpty())
							btnSearch.setEnabled(true);
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						// Do Nothing
					}
				});

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(frmLogin,
						"Are you sure you want to exit?", "Exit", 0);
				switch (userChoice) {
				case 0:
					System.exit(0);
					break;
				case 1:
					// Do Nothing
					break;
				default:
					break;
				}

			}
		});
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmExit);

		JMenu mnAdministrator = new JMenu("Administrator");
		mnAdministrator.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnAdministrator);

		JMenuItem mntmInitialize = new JMenuItem("Initialize");
		mnAdministrator.add(mntmInitialize);
		mntmInitialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(panelLMS,
						"Are you Sure?\n(This will delete existing data)");
				switch (userChoice) {
				case 0:
					// Thread dbThread = new Thread("db");
					Library library = new Library();
					library.initialize();
					frmLogin.setCursor(Cursor
							.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					frmLogin.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					break;
				case 1:
				case 2:
					// Do Nothing
					break;
				default:
					break;
				}
			}
		});
		mntmInitialize.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JMenuItem mntmAddBorrower = new JMenuItem("Add Borrower");
		mntmAddBorrower.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAdministrator.add(mntmAddBorrower);

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnHelp);

		JMenuItem mntmUserManual = new JMenuItem("User Manual");
		mntmUserManual.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmUserManual);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmAbout);

		panelCheckin = new JPanel();
		panelCheckin.setBounds(0, 33, 1263, 628);
		panelLMS.add(panelCheckin);
		panelCheckin.setBackground(new Color(255, 165, 0));
		panelCheckin.setVisible(false);
		panelCheckin.setLayout(null);

		ciTable = new JTable();
		ciTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		ciTable.setFillsViewportHeight(true);
		ciTable.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		ciTable.setForeground(new Color(0, 0, 139));
		ciTable.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		ciTable.setRowHeight(50);
		ciTable.setColumnSelectionAllowed(false);

		scrollPaneCI = new JScrollPane();
		scrollPaneCI.setBounds(215, 100, 808, 475);
		scrollPaneCI
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCI
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneCI.setViewportView(ciTable);
		panelCheckin.add(scrollPaneCI);

		btnCheckin = new JButton("Checkin");
		btnCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Loan> loans = new ArrayList<Loan>();
				if (ciTable.getSelectedRowCount() == 0)
					JOptionPane.showMessageDialog(frmLogin,
							"Please select a book");
				else {
					boolean isDateCorrect = true;
					for (int selection : ciTable.getSelectedRows()) {
						Loan loan = new Loan(new Book(""
								+ ciTable.getValueAt(selection, 0)),
								new Branch(Integer.parseInt(""
										+ ciTable.getValueAt(selection, 2))),
								new Borrower(""
										+ ciTable.getValueAt(selection, 3)));
						Calendar cal = Calendar.getInstance();
						cal.setTime(calendar.getDate());
						loan.setDateIn(cal);
						loans.add(loan);
						if (loan.getDateOutDB().after(calendar.getDate())) {
							JOptionPane
									.showMessageDialog(frmLogin,
											"Checkn date cannot be before checkout date");
							isDateCorrect = false;
							break;
						}
					}

					if(isDateCorrect) {
						for (Loan loan : loans) {
							loan.checkin(loan.getBranch().getAvailableCopies(loan.getBook()));
						}
						JOptionPane.showMessageDialog(frmLogin, "Checked In");
						btnGetData.doClick();
					}
				}
			}
		});
		btnCheckin.setBounds(552, 591, 128, 29);
		btnCheckin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCheckin.setEnabled(false);
		panelCheckin.add(btnCheckin);

		comboBoxCIBorrower = new JComboBox<String>();
		comboBoxCIBorrower.setBounds(416, 30, 377, 30);
		comboBoxCIBorrower.setFont(new Font("Tahoma", Font.BOLD, 20));
		for (Borrower borrower : new Borrower().getBorrowers()) {
			comboBoxCIBorrower.addItem(borrower.getCardNumber() + "::"
					+ borrower.getFirstName() + " " + borrower.getLastName());
		}
		comboBoxCIBorrower.setVisible(false);
		comboBoxCIBorrower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnGetData.doClick();
			}
		});
		panelCheckin.add(comboBoxCIBorrower);

		btnGetData = new JButton("Get Data");
		btnGetData.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Loan> loans = new ArrayList<Loan>();
				if (("" + comboBoxSearchBy.getItemAt(comboBoxSearchBy
						.getSelectedIndex())).equalsIgnoreCase(SearchBy.ISBN
						.getSearchBy())) {
					loans = new Loan().getLoans(new Book(comboBoxISBN
							.getItemAt(comboBoxISBN.getSelectedIndex())));
				} else if (("" + comboBoxSearchBy.getItemAt(comboBoxSearchBy
						.getSelectedIndex()))
						.equalsIgnoreCase(SearchBy.BORROWER.getSearchBy())) {
					loans = new Loan().getLoans(new Borrower(comboBoxCIBorrower
							.getItemAt(comboBoxCIBorrower.getSelectedIndex())
							.split("::")[0]));
				}

				ciDTM = new DefaultTableModel(0, 0) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 3630094735024498745L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				ciDTM.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (ciDTM.getRowCount() == 0)
							btnCheckin.setEnabled(false);
						else
							btnCheckin.setEnabled(true);
					}
				});

				ciTable.setModel(ciDTM);

				String[] header = { "ISBN", "Book Title", "Branch ID",
						"Card No.", "Borrower" };
				ciDTM.setColumnIdentifiers(header);
				for (Loan loan : loans) {
					ciDTM.addRow(new String[] {
							loan.getBook().getId(),
							loan.getBook().getTitle(),
							"" + loan.getBranch().getId(),
							loan.getBorrower().getCardNumber(),
							loan.getBorrower().getFirstName() + " "
									+ loan.getBorrower().getLastName() });

				}

			}
		});
		btnGetData.setBounds(793, 30, 227, 30);
		panelCheckin.add(btnGetData);

		comboBoxISBN = new JComboBox<String>();
		comboBoxISBN.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBoxISBN.setBounds(416, 30, 377, 30);
		for (Book book : new Book().getBooks()) {
			comboBoxISBN.addItem(book.getId());
		}
		comboBoxISBN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnGetData.doClick();
			}
		});
		panelCheckin.add(comboBoxISBN);

		lblCheckinDate = new JLabel("Checkin Date");
		lblCheckinDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckinDate.setForeground(new Color(0, 128, 0));
		lblCheckinDate.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCheckinDate.setBounds(5, 83, 198, 30);
		panelCheckin.add(lblCheckinDate);

		calendar = new JCalendar();
		calendar.setBounds(5, 113, 198, 159);
		panelCheckin.add(calendar);

		lblSearchBy = new JLabel("Search By    ");
		lblSearchBy.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearchBy.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSearchBy.setForeground(new Color(0, 128, 0));
		lblSearchBy.setBounds(5, 30, 214, 30);
		panelCheckin.add(lblSearchBy);

		comboBoxSearchBy = new JComboBox<String>();
		comboBoxSearchBy.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBoxSearchBy.setBounds(215, 30, 198, 30);
		comboBoxSearchBy.addItem("ISBN");
		comboBoxSearchBy.addItem("Borrower");
		comboBoxSearchBy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (("" + comboBoxSearchBy.getItemAt(comboBoxSearchBy
						.getSelectedIndex())).equalsIgnoreCase(SearchBy.ISBN
						.getSearchBy())) {
					comboBoxISBN.setVisible(true);
					comboBoxCIBorrower.setVisible(false);
					btnGetData.doClick();
				} else if (("" + comboBoxSearchBy.getItemAt(comboBoxSearchBy
						.getSelectedIndex()))
						.equalsIgnoreCase(SearchBy.BORROWER.getSearchBy())) {
					comboBoxCIBorrower.setVisible(true);
					comboBoxISBN.setVisible(false);
					btnGetData.doClick();
				}
			}
		});
		panelCheckin.add(comboBoxSearchBy);

	}
}
