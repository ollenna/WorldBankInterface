package userInterface.loginUI;
import java.io.*;

public class LoginServer {
	
	/*
	 * @file: LoginServer.java
	 * @author: Jessica Schwarze
	 * @date: 03/31/2021
	 * @description: LoginServer class verifies input against valid credentials stored in CredentialsDB.txt
	 */
	
	/*
	 * verifyCredentials checks user input against valid credentials stored in CredentialsDB.txt
	 * @param: user - string containing the username input by the user
	 * @param: pw - string containing the password input by the user
	 * @return: result - a boolean value, true indicating success and false indicating failure
	 */
	public boolean verifyCredentials(String user, String pw) throws IOException {
		
		// read CredentialsDB.txt file, which contains valid username/password combos
		BufferedReader newReader = new BufferedReader(new FileReader("CredentialsDB.txt")); // is it ok if file name is hard-coded or should it be passed as a parameter?
		
		//initializing variables
		boolean result = false;
		String checkUser = newReader.readLine();
		String checkPW = newReader.readLine();
		
		// loop through lines of the CredentialsDB file until a matching username/password pair is found
		while (checkUser != null && result == false) {
			if (checkUser.equals(user) && checkPW.equals(pw)) result = true;
			
			checkUser = newReader.readLine();
			checkPW = newReader.readLine();
		}
		
		newReader.close();
		return result;
	}
}

