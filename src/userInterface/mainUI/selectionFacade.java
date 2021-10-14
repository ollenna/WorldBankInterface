package userInterface.mainUI;

import java.util.ArrayList; 
import java.util.Vector;

import verification.verificationFactory;
import verification.verifyCountry;
import verification.verifyViewers;
import verification.verifyYears;

import userInterface.mainUI.MainUI;

/** 
 *  A class that stores a set of
 *  values selected by the user.
 * 
 * <p>
 * This class provides getter and setter methods to 
 * be used by analysis component and MainUI component
 * 
 * Date:         4/2/2021
 * @author	     Olena Orlova-Kurylova
 * 
 */

public class selectionFacade {
	/**
	 * @file: selectionFacade.java
	 * @author: Olena Orlova-Kurylova, CS2212 GUI
	 * @date: 04/01/2021
	 * @description: part of facade pattern
	 */
	
	//allGood = [yearsGood,analysisGood,countryGood]
	private boolean[] allGood = new boolean[3]; 
	private int startYear =2010;
	private int endYear=2010;
	private String analysis= "CO2 Emissions vs Energy Use vs PM2.5 Air Pollution";
	private String country= "Canada";
	private static systemSelection selection = new systemSelection();
	private Vector<String> viewers = new Vector<String>();
	
	selectionFacade(){
		allGood[0] = true;
		allGood[1] = true;
		allGood[2] = true;
	}
	
	public boolean[] getAllGood() {
		return allGood;
	}
	
	public systemSelection getSystemSelection() {
		return selection;
	}
	
	public void setViewer(Vector<String> viewersList) {
		viewers = viewersList;
		
			
		if(verifyViewers()==true) {
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selection.setSystemViewers(this.viewers);
			
		}else {
			try {
				MainUI.getInstance().errorLabel.setText("Invalid viewer for this analysis!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String badViewer = verifyViewers.getBadViewer();
			viewers.remove(badViewer);
		}
		
		
		
	}
	public void removeViewer(String viewer) {
		viewers.remove(viewer);
		selection.setSystemViewers(this.viewers);
		
		
		
	}
	
	public void clearViewers() {
		viewers.removeAllElements();
		selection.setSystemViewers(this.viewers);
	}
	
	

	public void setCountry(String country) {
		this.country=country;
		
		if(verifyCountry(country)==true && verifyYearsRange(startYear,endYear)==true) {
			allGood[2] = true;
			allGood[0] = true;
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
			allGood[2] = false;
			try {
				MainUI.getInstance().errorLabel.setText("Invalid country or years for this analysis!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		selection.setSystemCountry(country);
	}
	
	public void setYearsRange() {
		
		
		if(verifyCountry(country)==true && verifyYearsRange(startYear,endYear)==true) {
			
			allGood[0] = true;
			allGood[2] = true;
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			allGood[0] = false;
			try {
				MainUI.getInstance().errorLabel.setText("Invalid country or years for this analysis!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		selection.setSystemYears(startYear, endYear);
		
		
	}

	
	
	public void setAnalysis(String analysis) {
		this.analysis=analysis;
		
		
		if(verifyCountry(country)==true && verifyYearsRange(startYear, endYear) == true) {
			allGood[0] = true;
			allGood[1] = true;
			allGood[2] = true;
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			allGood[1] = false;
			try {
				
				MainUI.getInstance().errorLabel.setText("Invalid country or years for this analysis!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		selection.setSystemAnalysis(analysis);
		
	}
	
	
	
	public boolean verifyYearsRange(int startYear, int endYear) {
		
		verificationFactory verY = new verifyYears();
		
		return verY.factoryMethod(startYear,endYear,country);
	}
	
	
	
	public boolean verifyCountry(String country) {
		
		verificationFactory verC = new verifyCountry();
		
		return verC.factoryMethod(country,this.analysis);
	}
	
	
	private boolean verifyViewers() {
		
		verificationFactory verV = new verifyViewers();
		// TODO Auto-generated method stub
		return verV.factoryMethod(this.viewers, this.analysis);
	}
	
	
	
	public int getYearsStart() {
		return startYear;
		
	}

	public int getYearsEnd() {
		return endYear;
		
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
		
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	

}
