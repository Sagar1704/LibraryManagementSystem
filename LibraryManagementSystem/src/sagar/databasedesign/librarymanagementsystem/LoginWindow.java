package sagar.databasedesign.librarymanagementsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Login window for the Library Management system
 * 
 * @author Sagar
 * 
 */
public class LoginWindow {

	private JFrame frmLibraryManagementSystem;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblError;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmLibraryManagementSystem.setVisible(true);
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
		frmLibraryManagementSystem = new JFrame();
		frmLibraryManagementSystem.getContentPane().setBackground(Color.ORANGE);
		frmLibraryManagementSystem.setResizable(false);
		frmLibraryManagementSystem.getContentPane().setFont(
				new Font("Segoe UI Black", Font.PLAIN, 24));
		frmLibraryManagementSystem.setTitle("Library Management System");
		frmLibraryManagementSystem.setBounds(100, 100, 583, 412);
		frmLibraryManagementSystem
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTitle = new JLabel("LOGIN");
		lblTitle.setForeground(Color.GREEN);
		lblTitle.setBounds(214, 32, 146, 55);
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setToolTipText("Enter Username in the text");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblUsername.setBounds(133, 113, 128, 33);
		frmLibraryManagementSystem.getContentPane().setLayout(null);
		frmLibraryManagementSystem.getContentPane().add(lblTitle);
		frmLibraryManagementSystem.getContentPane().add(lblUsername);

		textFieldUsername = new JTextField();
		textFieldUsername.setToolTipText("utd");
		textFieldUsername.setBounds(301, 120, 146, 26);
		frmLibraryManagementSystem.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setToolTipText("Enter Password in the text");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		lblPassword.setBounds(133, 182, 128, 33);
		frmLibraryManagementSystem.getContentPane().add(lblPassword);

		textFieldPassword = new JTextField();
		textFieldPassword.setToolTipText("utd");
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(301, 189, 146, 26);
		frmLibraryManagementSystem.getContentPane().add(textFieldPassword);

		JButton btnLogin =  new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldUsername.getText().toString().isEmpty() || textFieldPassword.getText().toString().isEmpty())
					JOptionPane.showMessageDialog(frmLibraryManagementSystem, "Please enter both username and password");
				else
					JOptionPane.showMessageDialog(frmLibraryManagementSystem, "Press OK to log you in");
			}
		});

		btnLogin.setToolTipText("Click to log in");
		btnLogin.setBounds(214, 269, 146, 29);
		frmLibraryManagementSystem.getContentPane().add(btnLogin);
		
		lblError = new JLabel();
		lblError.setBounds(133, 320, 314, 29);
		frmLibraryManagementSystem.getContentPane().add(lblError);
	}
}
