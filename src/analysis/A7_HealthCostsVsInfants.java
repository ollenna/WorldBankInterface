package analysis;

import java.util.ArrayList; 
import fetchData.DataObject;
import fetchData.DataReader;
import viewers.ConcreteViewer;

import java.util.Vector;

import viewers.ResultObject;
import userInterface.mainUI.systemSelection;
import userInterface.mainUI.MainUI;

/** 
 *  Implementation of Analysis #7
 *  
 * <p>
 * This class inherits the base Analysis class and
 * implements Analysis #7, aka
 * Current health expenditure per capita vs 
 * Mortality rate, infant
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 * 
 * @see ResultObject
 * 
 */


public class A7_HealthCostsVsInfants extends Analysis {

	/** 
	 * Performs the calculation for Analysis #7.
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
		
		//Retrieve health cost
		DataObject healthCost = dr.getData("SH.XPD.CHEX.PC.CD");
		
		
		//Retrieve infant mortality
		DataObject infantMortality = dr.getData("SP.DYN.IMRT.IN");
		
		ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
		
		dataList.add(healthCost.getData());
		dataList.add(infantMortality.getData());
		
		//perform calculation
		int maxIndex = selection.getSelectedYears()[1] - selection.getSelectedYears()[0];
		boolean allZero = true;
		for(int i=0;i<=maxIndex;i++) {
			double ratio = healthCost.getData().get(i)/infantMortality.getData().get(i);
			
			if (ratio!=0 && Double.isNaN(ratio)==false) {
				allZero = false;
			}
		}
						
		if(!allZero) {
		
			//========= Prepare Identifying Info =========//
			
			
			
			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());
					
			// Set years
			result.setYears(selection.getSelectedYears());
			
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add(healthCost.getName());
			dataNames.add(infantMortality.getName());
			
			result.setDataNames(dataNames);
			
			// Set Panel that viewers will belong to
			System.out.println("Setting Viewer Panel");
			result.setPanel(this.viewerPanel);
			
			System.out.println("Sending Data to Result Object.");
			result.setResult(dataList);
		
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


