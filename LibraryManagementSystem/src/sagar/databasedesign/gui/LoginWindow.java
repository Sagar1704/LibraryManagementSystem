package sagar.databasedesign.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import sagar.databasedesign.library.Library;
import sagar.databasedesign.library.User;

/**
 * GUI for the Library Management system
 * 
 * @author Sagar
 * 
 */
public class LoginWindow {

	private JFrame frmLogin;

	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPanel panelLogin;
	private JPanel panelLMS;

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

		JLabel labelTitle = new JLabel("LOGIN");
		labelTitle.setVerticalAlignment(SwingConstants.TOP);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setForeground(Color.GREEN);
		labelTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		labelTitle.setAlignmentY(0.0f);
		labelTitle.setAlignmentX(0.5f);
		labelTitle.setBounds(528, 76, 289, 49);
		panelLogin.add(labelTitle);

		JLabel labelUsername = new JLabel("Username");
		labelUsername.setToolTipText("Enter Username in the text");
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		labelUsername.setBounds(424, 189, 199, 57);
		panelLogin.add(labelUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		textFieldUsername.setToolTipText("utd");
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(749, 190, 274, 56);
		panelLogin.add(textFieldUsername);

		JLabel labelPassword = new JLabel("Password");
		labelPassword.setToolTipText("Enter Password in the text");
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		labelPassword.setBounds(424, 307, 199, 65);
		panelLogin.add(labelPassword);

		JButton buttonLogin = new JButton("Login");
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
		buttonLogin.setBounds(528, 451, 289, 49);
		panelLogin.add(buttonLogin);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		passwordField.setBounds(749, 315, 274, 57);
		panelLogin.add(passwordField);

		panelLMS = new JPanel();
		panelLMS.setBackground(new Color(255, 165, 0));
		frmLogin.getContentPane().add(panelLMS, "name_611365057672554");
		panelLMS.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.setBounds(0, 0, 1263, 31);
		panelLMS.add(menuBar);

		JMenu mnLibrary = new JMenu("Library");
		mnLibrary.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnLibrary);

		JMenuItem mntmCheckin = new JMenuItem("Checkin");
		mntmCheckin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmCheckin);

		JMenuItem mntmInitialize = new JMenuItem("Initialize");
		mntmInitialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoice = JOptionPane.showConfirmDialog(panelLMS,
						"Are you Sure?\n(This will delete existing data)");
				switch (userChoice) {
				case 0:
					Library library = new Library();
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
		mnLibrary.add(mntmInitialize);

		JMenuItem mntmGetFines = new JMenuItem("Get Fines");
		mntmGetFines.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmGetFines);

		JMenuItem mntmSearch = new JMenuItem("Search Book");
		mntmSearch.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnLibrary.add(mntmSearch);

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

		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnHelp);

		JMenuItem mntmUserManual = new JMenuItem("User Manual");
		mntmUserManual.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmUserManual);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		mnHelp.add(mntmAbout);
	}
}
