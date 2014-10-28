package sagar.databasedesign.librarymanagementsystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;

public class LibraryManagementSystem {

	private JFrame frmLibraryManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryManagementSystem window = new LibraryManagementSystem();
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
	public LibraryManagementSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibraryManagementSystem = new JFrame();
		frmLibraryManagementSystem.getContentPane().setBackground(new Color(255, 140, 0));
		frmLibraryManagementSystem.setTitle("Library Management System");
		frmLibraryManagementSystem.setBounds(100, 100, 1260, 700);
		frmLibraryManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmLibraryManagementSystem.setJMenuBar(menuBar);
		
		JMenu mnSearch = new JMenu("Library");
		mnSearch.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnSearch);
		
		JMenuItem mntmSearch = new JMenuItem("Search");
		mnSearch.add(mntmSearch);
		
		JMenuItem mntmInitialize = new JMenuItem("Initialize");
		mnSearch.add(mntmInitialize);
		
		JMenu mnAbout = new JMenu("Help");
		mnAbout.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnAbout);
		
		JMenuItem mntmUserManual = new JMenuItem("User Manual");
		mnAbout.add(mntmUserManual);
		
		JMenuItem mntmHelp = new JMenuItem("About");
		mnAbout.add(mntmHelp);
	}

}
