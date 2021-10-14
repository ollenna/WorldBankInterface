package verification;

import java.io.File;  
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import userInterface.mainUI.MainUI;

/** 
 * Implements the verifyCountry.
 *  
 * <p>
 * Provides the basic functionality for
 * verifying a country.
 * 
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 * 
 * 
 */
public class verifyCountry extends verificationFactory{
	/** 
	 * Implements verification using the base
	 * data found in selectionFacade.
	 * 
	 */
	
	public boolean factoryMethod(String item,String analysis) {
		
		String inv = "invalidCountries";
        try {
		      File myObj = new File(inv);
		      Scanner myReader = new Scanner(myObj);
		    
		      
		      while (myReader.hasNextLine()) {
		    	  String analysisType = myReader.nextLine();
		    	  String country = myReader.nextLine();
		    	  
		    	 
		    	  if (analysisType.compareTo(analysis) == 0 && country.compareTo(item)==0) {
		    		  
		    		 
		    		  
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
	public boolean factoryMethod(int startYear, int endYear, String country) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean factoryMethod(Vector<String> viewers, String analysis) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
