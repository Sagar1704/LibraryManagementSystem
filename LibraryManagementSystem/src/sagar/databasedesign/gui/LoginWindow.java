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
import sagar.databasedesign.library.Fine;
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
	private DefaultTableModel fineDTM;
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
	private JTextField textFieldCardNumber;
	private JLabel lblFirstName;
	private JTextField textFieldFirstName;
	private JLabel lblNewLibraryUser;
	private JLabel lblLastName;
	private JTextField textFieldLastName;
	private JLabel lblAddress;
	private JTextField textFieldAddress;
	private JLabel lblCity;
	private JTextField textFieldCity;
	private JLabel lblState;
	private JTextField textFieldState;
	private JLabel lblPhone;
	private JTextField textFieldPhone;
	private JPanel panelBorrower;
	private JMenuItem mntmAddBorrower;
	private JPanel panelFines;
	private JComboBox<String> comboBoxFinedUsers;
	private JLabel lblFineDue;
	private JTextField textFieldFine;
	private JLabel lblFines;
	private JMenuItem mntmPayFine;
	private JPanel panelPayFine;
	private JLabel lblPayFine;
	private JTable tableFine;
	private JButton btnPay;

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

		panelFines = new JPanel();
		panelFines.setBackground(new Color(255, 140, 0));
		panelFines.setBounds(0, 33, 1263, 628);
		panelFines.setVisible(false);

		panelPayFine = new JPanel();
		panelPayFine.setBackground(new Color(255, 140, 0));
		panelPayFine.setBounds(0, 33, 1263, 628);
		panelLMS.add(panelPayFine);
		panelPayFine.setLayout(null);
		panelPayFine.setVisible(false);

		lblPayFine = new JLabel("Pay Fine");
		lblPayFine.setForeground(new Color(0, 128, 0));
		lblPayFine.setBounds(564, 44, 127, 37);
		lblPayFine.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelPayFine.add(lblPayFine);

		JScrollPane scrollPaneFine = new JScrollPane();
		scrollPaneFine.setBounds(215, 100, 808, 475);
		panelPayFine.add(scrollPaneFine);

		tableFine = new JTable();
		tableFine
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableFine.setFillsViewportHeight(true);
		tableFine.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		tableFine.setForeground(new Color(0, 0, 139));
		tableFine.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tableFine.setRowHeight(50);
		tableFine.setColumnSelectionAllowed(false);
		scrollPaneFine.setColumnHeaderView(tableFine);
		scrollPaneFine.setViewportView(tableFine);

		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int selection : tableFine.getSelectedRows()) {
					new Fine(Integer.parseInt(""
							+ tableFine.getValueAt(selection, 0)),
							Float.parseFloat(""
									+ tableFine.getValueAt(selection, 3)), true)
							.updateFine();
					
				}
				JOptionPane.showMessageDialog(frmLogin, "Paid successfully");
				mntmPayFine.doClick();
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPay.setBounds(576, 583, 115, 29);
		btnPay.setEnabled(false);
		panelPayFine.add(btnPay);
		panelLMS.add(panelFines);
		panelFines.setLayout(null);

		comboBoxFinedUsers = new JComboBox<String>();
		comboBoxFinedUsers.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBoxFinedUsers.setBounds(443, 203, 356, 40);
		comboBoxFinedUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldFine.setText(""
						+ new Fine().getFinesByBorrower(new Borrower(Integer
								.parseInt(comboBoxFinedUsers.getItemAt(
										comboBoxFinedUsers.getSelectedIndex())
										.split("::")[0]))));
			}
		});
		panelFines.add(comboBoxFinedUsers);

		lblFineDue = new JLabel("Fine Due ::");
		lblFineDue.setForeground(new Color(0, 128, 0));
		lblFineDue.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblFineDue.setBounds(443, 317, 132, 30);
		panelFines.add(lblFineDue);

		textFieldFine = new JTextField();
		textFieldFine.setEditable(false);
		textFieldFine.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textFieldFine.setBounds(599, 317, 200, 30);
		panelFines.add(textFieldFine);
		textFieldFine.setColumns(10);

		lblFines = new JLabel("Fines");
		lblFines.setHorizontalAlignment(SwingConstants.CENTER);
		lblFines.setForeground(new Color(0, 128, 0));
		lblFines.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		lblFines.setBounds(443, 59, 356, 50);
		panelFines.add(lblFines);

		panelBorrower = new JPanel();
		panelBorrower.setBackground(new Color(255, 140, 0));
		panelBorrower.setBounds(0, 33, 1263, 628);
		panelLMS.add(panelBorrower);
		panelBorrower.setLayout(null);
		panelBorrower.setVisible(false);

		lblNewLibraryUser = new JLabel("New Library User");
		lblNewLibraryUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLibraryUser.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLibraryUser.setBounds(464, 16, 296, 40);
		panelBorrower.add(lblNewLibraryUser);

		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNumber.setForeground(new Color(0, 128, 0));
		lblCardNumber.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCardNumber.setBounds(356, 75, 191, 30);
		panelBorrower.add(lblCardNumber);

		textFieldCardNumber = new JTextField();
		textFieldCardNumber.setEnabled(false);
		textFieldCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldCardNumber.setBounds(592, 75, 323, 30);
		panelBorrower.add(textFieldCardNumber);
		textFieldCardNumber.setColumns(10);

		lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setForeground(new Color(0, 128, 0));
		lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblFirstName.setBounds(356, 140, 191, 30);
		panelBorrower.add(lblFirstName);

		textFieldFirstName = new JTextField();
		textFieldFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(592, 140, 323, 30);
		panelBorrower.add(textFieldFirstName);

		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setForeground(new Color(0, 128, 0));
		lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLastName.setBounds(356, 205, 191, 30);
		panelBorrower.add(lblLastName);

		textFieldLastName = new JTextField();
		textFieldLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(592, 205, 323, 30);
		panelBorrower.add(textFieldLastName);

		lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(new Color(0, 128, 0));
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblAddress.setBounds(356, 270, 191, 30);
		panelBorrower.add(lblAddress);

		textFieldAddress = new JTextField();
		textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(592, 270, 323, 30);
		panelBorrower.add(textFieldAddress);

		lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setForeground(new Color(0, 128, 0));
		lblCity.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCity.setBounds(356, 335, 191, 30);
		panelBorrower.add(lblCity);

		textFieldCity = new JTextField();
		textFieldCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(592, 335, 323, 30);
		panelBorrower.add(textFieldCity);

		lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setForeground(new Color(0, 128, 0));
		lblState.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblState.setBounds(356, 400, 191, 30);
		panelBorrower.add(lblState);

		textFieldState = new JTextField();
		textFieldState.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldState.setColumns(10);
		textFieldState.setBounds(592, 400, 323, 30);
		panelBorrower.add(textFieldState);

		lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setForeground(new Color(0, 128, 0));
		lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPhone.setBounds(356, 465, 191, 30);
		panelBorrower.add(lblPhone);

		textFieldPhone = new JTextField();
		textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(592, 465, 323, 30);
		panelBorrower.add(textFieldPhone);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldFirstName.getText().isEmpty()
						|| textFieldLastName.getText().isEmpty()
						|| textFieldAddress.getText().isEmpty()
						|| textFieldCity.getText().isEmpty()
						|| textFieldState.getText().isEmpty()
						|| textFieldPhone.getText().isEmpty())
					JOptionPane.showMessageDialog(frmLogin,
							"All fields are compulsary");
				else {
					Borrower borrower = new Borrower(Integer
							.parseInt(textFieldCardNumber.getText()),
							textFieldFirstName.getText(), textFieldLastName
									.getText(), textFieldAddress.getText(),
							textFieldCity.getText(), textFieldState.getText(),
							textFieldPhone.getText());
					ArrayList<Borrower> borrowers = borrower.getBorrowers();
					if (borrowers.contains(borrower))
						JOptionPane.showMessageDialog(frmLogin,
								"Only 1 library card per person");
					else {
						borrower.insertBorrower();
						JOptionPane.showMessageDialog(frmLogin,
								"New Borrower Added.");
						mntmAddBorrower.doClick();
					}
				}
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddUser.setBounds(529, 553, 136, 29);
		panelBorrower.add(btnAddUser);

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
												Integer.parseInt(comboBoxBorrower
														.getItemAt(
																comboBoxBorrower
																		.getSelectedIndex())
														.split("::")[0])));
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
				panelBorrower.setVisible(false);
				panelPayFine.setVisible(false);
				panelCheckin.setVisible(false);
				panelFines.setVisible(false);
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
				panelBorrower.setVisible(false);
				panelCheckin.setVisible(false);
				panelFines.setVisible(false);
				panelPayFine.setVisible(false);
			}
		});
		mntmCheckout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmCheckout);

		JMenuItem mntmCheckin = new JMenuItem("Checkin");
		mntmCheckin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearch.setVisible(false);
				panelBorrower.setVisible(false);
				panelFines.setVisible(false);
				panelPayFine.setVisible(false);
				panelCheckin.setVisible(true);
			}
		});
		mntmCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmCheckin);

		JMenuItem mntmGetFines = new JMenuItem("Get Fines");
		mntmGetFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFines.setVisible(true);
				panelSearch.setVisible(false);
				panelCheckin.setVisible(false);
				panelPayFine.setVisible(false);
				panelBorrower.setVisible(false);
				ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
				ArrayList<Fine> unPaidfines = new Fine().getUnpaidFines();
				ArrayList<Fine> paidFines = new Fine().getfines();
				for (Loan loan : new Loan().getLoans()) {
					if (loan.getDateIn().after(loan.getDueDate())) {
						Fine fine = new Fine(loan.getLoanId(), ((loan
								.getDateIn() == null ? Calendar.getInstance()
								.getTimeInMillis() : loan.getDateIn()
								.getTimeInMillis()
								- loan.getDueDate().getTimeInMillis())
								/ ((1000 * 60 * 60 * 24)) * Fine.RATE), false);
						if (unPaidfines.contains(fine)) {
							fine.updateFine();
						} else if(!paidFines.contains(fine)){
							fine.insertFine();
						}
						if (!borrowers.contains(loan.getBorrower()))
							borrowers.add(loan.getBorrower());
					}
				}
				for (Borrower borrower : borrowers) {
					comboBoxFinedUsers.addItem(borrower.getCardNumber() + "::"
							+ borrower.getFirstName() + " "
							+ borrower.getLastName());
				}

			}
		});
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

		mntmAddBorrower = new JMenuItem("Add Borrower");
		mntmAddBorrower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckin.setVisible(false);
				panelSearch.setVisible(false);
				panelBorrower.setVisible(true);
				textFieldCardNumber.setText(""
						+ (new Borrower().getMaxCardNo() + 1));
			}
		});
		mntmAddBorrower.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnAdministrator.add(mntmAddBorrower);

		mntmPayFine = new JMenuItem("Pay Fine");
		mntmPayFine.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mntmPayFine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelPayFine.setVisible(true);
				panelSearch.setVisible(false);
				panelCheckin.setVisible(false);
				panelFines.setVisible(false);
				panelBorrower.setVisible(false);
				ArrayList<Fine> fines = new Fine().getUnpaidFines();
				fineDTM = new DefaultTableModel(0, 0) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 3630094735024498745L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				fineDTM.addTableModelListener(new TableModelListener() {
					@Override
					public void tableChanged(TableModelEvent e) {
						if (fineDTM.getRowCount() == 0)
							btnPay.setEnabled(false);
						else
							btnPay.setEnabled(true);
					}
				});

				tableFine.setModel(fineDTM);

				String[] header = { "Loan ID", "Borrower", "Book Title", "Fine" };
				fineDTM.setColumnIdentifiers(header);
				for (Fine fine : fines) {
					Loan loan = new Loan();
					loan.setLoanId(fine.getLoanId());
					loan = loan.getLoanById();
					fineDTM.addRow(new String[] {
							"" + loan.getLoanId(),
							loan.getBorrower().getFirstName() + " "
									+ loan.getBorrower().getLastName(),
							loan.getBook().getTitle(), "" + fine.getFine_amt() });

				}

			}
		});
		mnAdministrator.add(mntmPayFine);

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
								new Borrower(Integer.parseInt(""
										+ ciTable.getValueAt(selection, 3))));
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

					if (isDateCorrect) {
						for (Loan loan : loans) {
							loan.checkin(loan.getBranch().getAvailableCopies(
									loan.getBook()));
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
					loans = new Loan().getLoans(new Borrower(Integer
							.parseInt(comboBoxCIBorrower.getItemAt(
									comboBoxCIBorrower.getSelectedIndex())
									.split("::")[0])));
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
							"" + loan.getBorrower().getCardNumber(),
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
