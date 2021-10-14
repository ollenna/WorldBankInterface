package userInterface.loginUI;
import java.io.IOException;  
import javax.swing.JFrame;

import userInterface.mainUI.MainUI;

public class Proxy {
	
	/*
	 * @file: Proxy.java
	 * @author: Jessica Schwarze
	 * @date: 03/31/2021
	 * @description: Proxy class facilitates communication between LoginUI, LoginServer, and MainUI
	 */

	/*
	 * launchMainUI creates a new instance of MainUI
	 */
	public void launchMainUI() throws Exception{
		// open new MainUI window
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
		
		// close login UI window
		LoginUI.loginInstance().dispose();
	}
	
	/* requestCheck is called by the LoginUI class when the user presses the Login Button. This method will call 
	 * LoginServer to verify the credentials. If correct, this method will call launchMainUI(), if not an error message is
	 * displayed on LoginUI.
	 * @param: input - an array of 2 strings, containing text input by the user in the username and password fields
	*/
	public void requestCheck(String[] input) throws Exception {
		LoginServer loginServer = new LoginServer();
		try {
			// call LoginServer to verify credentials, launch MainUI if correct
			if(loginServer.verifyCredentials(input[0], input[1])) launchMainUI();
			
			// if credentials incorrect, display error message on LoginUI
			else LoginUI.topLabel.setText("Error: incorrect username or password");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
