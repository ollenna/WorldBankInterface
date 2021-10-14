package verification;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import userInterface.mainUI.MainUI;

/** 
 * Implements the verifyViewers.
 *  
 * <p>
 * Provides the basic functionality for
 * verifying a set of Viewers.
 * 
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 * 
 * 
 */
public class verifyViewers extends verificationFactory  {
	/** 
	 * Implements verification using the base
	 * data found in selectionFacade.
	 * 
	 */
	static String badViewer;
	
	@Override
	public boolean factoryMethod(Vector<String> viewers, String analysis) {
		System.out.println("verifyViewers");
		String inv = "invalidViewers";
		boolean invalid = false;
		try {
		      File myObj = new File(inv);
		      Scanner myReader = new Scanner(myObj);
		      
		      
		      while (myReader.hasNextLine()) {
		    	  String invalidAnalysis = myReader.nextLine();
		    	  String invalidViewer = myReader.nextLine();
		    	  
		    	  if (analysis.compareTo(invalidAnalysis)==0) {
		    		  
		    		  if (viewers.contains(invalidViewer)) {
		    			  
			    		  invalid = true;
			    		  this.badViewer = invalidViewer;
			    	  }
		    		  
		    		  
		    	  }
		    	 
		      }
		          
		      myReader.close();
		    } catch (FileNotFoundException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
		    }
        
        
        
        if (invalid) {
        	
        	
        	return false;
        }
        
        
        return true;
		
	}
	
	public static String getBadViewer() {
		return badViewer;
	}

	@Override
	public boolean factoryMethod(String item, String analysis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean factoryMethod(int startYear, int endYear, String country) {
		// TODO Auto-generated method stub
		return false;
	}

	



}
