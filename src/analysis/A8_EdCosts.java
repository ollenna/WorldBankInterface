package analysis;

import java.util.ArrayList; 
import java.util.Vector;

import fetchData.DataObject;
import fetchData.DataReader;
import viewers.ConcreteViewer;

import viewers.ResultObject;
import userInterface.mainUI.systemSelection;
import userInterface.mainUI.MainUI;
/** 
 *  Implementation of Analysis #8
 *  
 * <p>
 * This class inherits the base Analysis class and
 * implements Analysis #8, aka
 * Ratio of Government expenditure on education vs 
 * Current health expenditure
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 * 
 * @see ResultObject
 * 
 */
public class A8_EdCosts extends Analysis {

	/** 
	 * Performs the calculation for Analysis #8.
	 * <p>
	 * Performs the following:
	 * <ol>
	 * <li> Requests the data from the World Bank Database
	 * <li> Performs the necessary calculations on the
	 *      retrieved data 
	 * <li> Creates the ResultObject and populates it with
	 *      the calculated data
	 * <li> Triggers the ResultObject to create the viewers,
	 *      fill them with data, and display them in the
	 *      ViewerPanel in the MainUI
	 * </ol>
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Creates the ResultObject
	 * <li> Modifies the Viewer Panel in the MainUI
	 * </ul>
	 * 
	 *  @param selection The data selected by the user
	 *                   (country, years, viewers, etc)
	 */
	@Override
	public void calculate(systemSelection selection) {
		
		// Get selection parameters
		Vector<String> viewers = selection.getSelectedViewers();
				
		// Create Result Object
		ResultObject result = new ResultObject();
		
		// Create viewers (stored in this.viewers)
		this.setViewers(viewers);
				
		// Set Analysis Name
		result.setName(selection.getSelectedAnalysis());
				
		for (ConcreteViewer viewer : this.viewers) {
			result.attach(viewer);
					
		}
				
				
		// Package data from World Bank API
		
		// Our reader
		DataReader dr = new DataReader(selection);
				
		// Retrieve cost of education
		DataObject educationCost = dr.getData("SE.XPD.TOTL.GD.ZS");
		ArrayList<Double> educationCostData = educationCost.getData();
				
		// Retrieve cost of healthcare
		DataObject healthCost = dr.getData("SH.XPD.CHEX.GD.ZS");
		ArrayList<Double> healthCostData = healthCost.getData();
					
		//new ArrayList holds values of the calculation
		ArrayList<Double> ratioEducationHealth = new ArrayList<Double>();
				
		//perform calculation
		int maxIndex = selection.getSelectedYears()[1] - selection.getSelectedYears()[0];
		boolean allZero = true;
		for(int i=0;i<=maxIndex;i++) {
			double ratio = educationCostData.get(i)/healthCostData.get(i);
			ratioEducationHealth.add(ratio);
			
			if(ratio!=0 && Double.isNaN(ratio)==false) {
				allZero = false;
			}
		}
				
		if(!allZero) {
				
			ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
			dataList.add(ratioEducationHealth);
					
					
	
			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());
					
			// Set years
			result.setYears(selection.getSelectedYears());
					
					
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add("Ratio of Government expenditure on education "
							+ "vs Current health expenditure");
					
			result.setDataNames(dataNames);
			
			// Set Panel that viewers will belong to
			System.out.println("Setting Viewer Panel");
			result.setPanel(this.viewerPanel);
			
			
			System.out.println("Sending Data to Result Object.");
			System.out.println(dataList);
			result.setResult(dataList);
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		} else {
			try {
				MainUI.getInstance().errorLabel.setText("All data is 0!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}





