package userInterface.mainUI;


import javax.swing.JPanel;

import analysis.Analysis;
import analysis.getAnalysisFactory;
import java.util.Vector;

/** 
 *  A class that stores a set of
 *  values selected by the user, now verified.
 * 
 * <p>
 * This class provides getter and setter methods to 
 * be used by analysis component
 * 
 * Date:         4/2/2021
 * @author	     Olena Orlova-Kurylova
 * 
 */

public class systemSelection {
	/**
	 * @file: systemSelection.java
	 * @author: Olena Orlova-Kurylova, CS2212 GUI
	 * @date: 04/01/2021
	 * @description: stores data selected by the user
	 */
	private String analysis = "CO2 Emissions vs Energy Use vs PM2.5 Air Pollution";
	private int[] years = new int[2];
	private String country = "Canada";
	private Vector<String> viewers = new Vector<String>();
	private JPanel viewerPanel = null;
	
	
	public String getSelectedCountry() {
		return country;
	}
	
	public int[] getSelectedYears() {
		return years;
	}
	
	public String getSelectedAnalysis() {
		return analysis;
	}
	
	public Vector<String> getSelectedViewers(){
		return viewers;
	}
	
	
	public JPanel getSelectedPanel(){
		return viewerPanel;
	}
	
	public void performAnalysis() {
		getAnalysisFactory analysisFactory= new getAnalysisFactory();
		Analysis analysis = analysisFactory.getAnalysis(this.analysis);
		
		analysis.setPanel(this.viewerPanel);
		analysis.calculate(this);
	}
	
	public systemSelection() {
		years[0] = 2010;
		years[1] = 2010;
	}
	
	public void setSystemCountry(String country) {
		
		this.country = country;
		System.out.println(this.country+ " selected in sysSelection");
		
	
	}
	
	public void setSystemYears(int startYear,int endYear) {
		
		years[0] = startYear;
		years[1] = endYear;
		
		System.out.println(this.years[0]+"  "+this.years[1]+" selected in sysSelection");
		
		
	}
	public void setSystemAnalysis(String analysis) {
		
		this.analysis = analysis;
		
		System.out.println(this.analysis+ " selected in sysSelection");
		//System.out.println("analysis in selection: "+this.analysis);
		
	}

	public void setSystemViewers(Vector<String> viewerList) {
		viewers = viewerList;
		
		System.out.println(this.viewers+ " selected in sysSelection");
		// TODO Auto-generated method stub
		
	}
	
	
	public void setSystemPanel(JPanel panel) {
		viewerPanel = panel;
	}
	
	
	

}
