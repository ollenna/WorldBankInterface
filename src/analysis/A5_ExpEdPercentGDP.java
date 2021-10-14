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
 * This class inherits the base Analysis class and implements Analysis #5, aka
 * Average of Government expenditure on education, total (% of GDP) for the
 * selected years 
 * Date:	 	04/02/2021
 * @author 		Jessica Li
 */
public class A5_ExpEdPercentGDP extends Analysis {
	/**
	 * Performs the calculation for Analysis #5.
	 * <p>
	 * Performs the following:
	 * <ol>
	 * <li>Requests the data from the World Bank Database
	 * <li>Performs the necessary calculations on the retrieved data (for example,
	 * gdp / population)
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
		System.out.println("Beginning ExpEd%GDP");

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

		// ========= Prepare WBAPI Data =========//

		// the data reader
		DataReader dr = new DataReader(selection);

		// Retrieve Gov't expenditure on education
		DataObject edu = dr.getData("SE.XPD.TOTL.GD.ZS");
		ArrayList<Double> eduList = edu.getData();

		// Calculate total edu for all years
		double average = 0;
		int listSize = 0;
		for (double dataPoint : eduList) {
			if (dataPoint != 0) {
				average += dataPoint;
				listSize += 1;
			}
		}

		boolean allZero = false;
		
		if (listSize != 0) {
			// Get average
			average = average / listSize;			
		}
		else {
			allZero = true; // can't divide by 0
		}


		if (!allZero) {
			// Place average back in data list
			eduList.clear();
			eduList.add(average);
			eduList.add(100 - average);
			
			// Add to new datalist for Result Object
			ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
			dataList.add(eduList);
			
			// ========= Prepare Identifying Info =========//

			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());

			// Set years
			result.setYears(selection.getSelectedYears());

			// List of names for the axes corresponding to each
			// list of data in the dataList
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add("Average " + edu.getName());
			dataNames.add("Total Government Expenditure (% of GDP)");
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
