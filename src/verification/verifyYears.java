package verification;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import userInterface.mainUI.MainUI;

/** 
 * Implements the verifyYears.
 *  
 * <p>
 * Provides the basic functionality for
 * verifying a set of Years.
 * 
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 * 
 * 
 */
public class verifyYears extends verificationFactory {
	/** 
	 * Implements verification using the base
	 * data found in selectionFacade.
	 * 
	 */
	@Override
	public boolean factoryMethod(int startYear, int endYear, String country) {
		String inv = "invalidYears";
		try {
			File myObj = new File(inv);
			Scanner myReader = new Scanner(myObj);

			while (myReader.hasNextLine()) {
				int start = Integer.parseInt(myReader.nextLine());
				int end = Integer.parseInt(myReader.nextLine());
				String c = myReader.nextLine();

				if (c.compareTo(country) == 0) {

					if (startYear >= start && startYear <= end) {
						
						
						return false;

					}
					if (startYear > start && startYear < end) {
						
						
						return false;

					}
					if (startYear < start && start < endYear) {
						
						
						return false;

					}
				}
				// ADDED BY JESS LI
				// display error message if start year exceeds end year
				if (startYear > endYear) {
					
					
					return false;
				}

			}

			myReader.close();
		} catch (FileNotFoundException e1) {
			System.out.println("An error occurred.");
			e1.printStackTrace();
		}

		
		return true;

	}

	@Override
	public boolean factoryMethod(String item, String analysis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean factoryMethod(Vector<String> viewers, String analysis) {
		// TODO Auto-generated method stub
		return false;
	}

}
