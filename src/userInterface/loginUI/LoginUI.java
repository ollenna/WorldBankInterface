package userInterface.loginUI;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame implements ActionListener {
	
	/*
	 * @file: LoginUI.java
	 * @author: Jessica Schwarze
	 * @date: 03/31/2021
	 * @description: LoginUI displays UI for login
	 */
	
	// define serialVersionUID
	private static final long serialVersionUID = 1L;
	
	// define class variable to represent an instance of the Login UI
	private static LoginUI instance;

	// define text fields for username and password as class variables
	private JTextField usernameField = new JTextField();
	private JTextField passwordField = new JTextField();
	
	static JLabel topLabel = new JLabel();
	private Proxy proxyServer = new Proxy();
	
	// constructor method for a new login window
	private LoginUI() {
		// set window title
		super("Login");

		// set top bar
		JPanel topPanel = new JPanel();
		topLabel.setText("Welcome to the Visual Interface for World Bank Data");
		topPanel.add(topLabel);
		
		// set mid panel
		JPanel midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(2, 2));
		
		// set username field
		JLabel userLabel = new JLabel("                  Username: ");
		midPanel.add(userLabel);
		midPanel.add(usernameField);
		
		// set password field
		JLabel passwordLabel = new JLabel("                  Password: ");
		midPanel.add(passwordLabel);
		midPanel.add(passwordField);
		
		// set login button
		JPanel lowPanel = new JPanel();
		JButton loginButton = new JButton("Login");
		lowPanel.add(loginButton);
		loginButton.addActionListener(this);

		// add components to container
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(midPanel, BorderLayout.WEST);
		getContentPane().add(lowPanel, BorderLayout.SOUTH);
	}
	
	/*
	 * Create a new LoginUI, singleton design pattern
	 * @return: instance - a new instance of LoginUI
	 */
	public static LoginUI loginInstance() {
		if (instance == null)
			instance = new LoginUI();

		return instance;
	}

	/*
	 * method gets String input from LoginUI text fields
	 * @return: input - an array of 2 strings, containing the username and password input
	 */
	public String[] getUserInput(){
		String[] input = new String[2];
		input[0] = usernameField.getText();
		input[1] = passwordField.getText();
		return input;
	}
	
	/*
	 * method executes every time the login button is pressed; calls the proxy server.
	 * @param: e - indicator that an event (button click) has been performed
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			proxyServer.requestCheck(getUserInput());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/*
	 * main method launches LoginUI
	 */
	public static void main(String[] args) {
		JFrame frame = LoginUI.loginInstance();
		frame.setSize(400, 400);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}