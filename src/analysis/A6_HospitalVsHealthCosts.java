package analysis;

import java.util.ArrayList;
import java.util.Vector;

import fetchData.DataObject;
import fetchData.DataReader;
import viewers.ConcreteViewer;
import viewers.ResultObject;
import userInterface.mainUI.MainUI;
import userInterface.mainUI.systemSelection;

/**
 * Implementation of Analysis #5
 * <p>
 * This class inherits the base Analysis class and implements Analysis #6, aka
 * Ratio of Hospital beds (per 1,000 people) and Current health expenditure (per
 * 1,000 people) for the selected years Date: 04/02/2021
 * Date: 		04/02/2021
 * @author 		Jessica Li
 */
public class A6_HospitalVsHealthCosts extends Analysis {
	/**
	 * Performs the calculation for Analysis #8.
	 * <p>
	 * Performs the following:
	 * <ol>
	 * <li>Requests the data from the World Bank Database
	 * <li>Performs the necessary calculations on the retrieved data
	 * <li>Creates the ResultObject and populates it with the calculated data
	 * <li>Triggers the ResultObject to create the viewers, fill them with data, and
	 * display them in the ViewerPanel in the MainUI
	 * </ol>
	 * 
	 * <br>
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li>Creates the ResultObject
	 * <li>Modifies the Viewer Panel in the MainUI
	 * </ul>
	 * 
	 * @param selection The data selected by the user (country, years, viewers, etc)
	 */
	@Override
	public void calculate(systemSelection selection) {
		System.out.println("Beginning HospitalVsHealthCosts");

		Vector<String> viewers = selection.getSelectedViewers();

		// create result object
		ResultObject result = new ResultObject();

		// ========= Prepare Viewers =========//

		// Create viewers (stored in this.viewers)
		this.setViewers(viewers);

		// get selected viewers
		for (ConcreteViewer viewer : this.viewers) {
			result.attach(viewer);
		}

		// reader for API calls to get data
		DataReader dr = new DataReader(selection);

		// Retrieve hospital beds (per 1000 people)
		DataObject bed = dr.getData("SH.MED.BEDS.ZS");

		// Retrieve current health expenditure per capita (current US$)
		DataObject he = dr.getData("SH.XPD.CHEX.PC.CD");

		// add data
		ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
		dataList.add(bed.getData());
		dataList.add(he.getData());

		// for data manipulation
		ArrayList<Double> calcData = new ArrayList<Double>();

		int maxIndex = selection.getSelectedYears()[1] - selection.getSelectedYears()[0];
		// iterating through list for current health expenditure and compute ratio /1000

		boolean allZero = true;
		for (int i = 0; i <= maxIndex; i++) {
			double ratio_he = dataList.get(1).get(i)/1000;
			calcData.add(ratio_he);
			System.out.println(ratio_he);
			if (ratio_he!=0)
				allZero = false;
		}

		// update with new ratio for health expenditure
		dataList.remove(1);
		dataList.add(calcData);
		
		
		// ========= Prepare Identifying Info =========//
		if (!allZero) {
			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());
			
			// Set years
			result.setYears(selection.getSelectedYears());
			
			// List of names for the axes corresponding to each
			// list of data in the dataList
			
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add(bed.getName());
			dataNames.add(he.getName());
			result.setDataNames(dataNames);
			
			// Set Panel that viewers will belong to
			System.out.println("Setting Viewer Panel");
			result.setPanel(this.viewerPanel);
			
			// ========= Begin Generating Graphics =========//
			
			// Send off data from WBAPI to resultObject
			// NOTE: As soon as resultObject obtains the data,
			// it updates the viewers automatically
			System.out.println("Sending Data to Result Object.");
			result.setResult(dataList);
		} else {
			try {
				// display error if data is empty
				MainUI.getInstance().errorLabel.setText("All data is 0!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
