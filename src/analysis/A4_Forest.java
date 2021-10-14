package analysis;

import java.util.ArrayList;
import java.util.Vector;

import fetchData.DataObject;
import fetchData.DataReader;
import viewers.ConcreteViewer;
import viewers.ResultObject;
import userInterface.mainUI.MainUI;
import userInterface.mainUI.systemSelection;

public class A4_Forest extends Analysis {
	
	/*
	 * @file: A4_Forest.java
	 * @author: Jessica Schwarze
	 * @date: 04/02/2021
	 * @description: performs the analysis of type Average Forest Area as a % of Land Mass
	 */

	@Override
	public void calculate(systemSelection selection) {
		
		System.out.println("Beginning ForestArea Analysis");
		
		// Get selection parameters
		Vector<String> viewers = selection.getSelectedViewers();
		
		// Create Result Object
		ResultObject result = new ResultObject();
		
		
		//========= Prepare Viewers =========//
		
		// Create viewers (stored in this.viewers)
		this.setViewers(viewers);
		
		// Attach viewers to result object so that when
		// data is sent to the resultObject, it knows which
		// viewers to notify/update
		
		for (ConcreteViewer viewer : this.viewers) {
			result.attach(viewer);
		}
		
		
		//========= Prepare WBAPI Data =========//
		
		// Our reader
		DataReader dr = new DataReader(selection);
		
		// Retrieve forest area
		DataObject forestArea = dr.getData("AG.LND.FRST.ZS");
		ArrayList<Double> forestList = forestArea.getData();
		
		// Calculate total forest area for all years
		double average = 0;
		int listSize = 0;
		for (double dataPoint : forestList) {
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
			allZero = true;
		}
		
		
		if (!allZero) {
			
			// Place average and total back in data list
			forestList.clear();
			forestList.add(average);
			forestList.add(100 - average);

			// Add to new datalist for Result Object
			ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
			dataList.add(forestList);
			
			//========= Prepare Identifying Info =========//
			
			
			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());

			// Set years
			result.setYears(selection.getSelectedYears());

			// List of names for the axes corresponding to each
			// list of data in the dataList
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add("Average " + forestArea.getName());
			dataNames.add("Non-Forest area (% of land area rea)");
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

			// set/return result object
			
			// erase error message
			try {
				MainUI.getInstance().errorLabel.setText("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// if all data is zero, print error message
		else {
			try {
				MainUI.getInstance().errorLabel.setText("All data is 0!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
