package analysis;

import java.util.ArrayList;
import java.util.Vector;

import fetchData.DataObject;
import fetchData.DataReader;
import viewers.ConcreteViewer;
import viewers.ResultObject;
import userInterface.mainUI.MainUI;
import userInterface.mainUI.systemSelection;

public class A3_CO2VsGDP extends Analysis {
	
	/*
	 * @file: A3_CO2VsGDP.java
	 * @author: Jessica Schwarze
	 * @date: 04/02/2021
	 * @description: performs the analysis of type CO2 emissions vs GDP
	 */

	@Override
	public void calculate(systemSelection selection) {
		
		System.out.println("Beginning CO2vsGDP Analysis");

		//getting selection parameters
		Vector<String> viewers = selection.getSelectedViewers();
		int[] years = selection.getSelectedYears();

		// create ResultObject
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
				
		// New reader
		DataReader dr = new DataReader(selection);
				
		// Retrieve CO2 data
		DataObject co2 = dr.getData("EN.ATM.CO2E.PC");
				
		// Retrieve GDP data
		DataObject gdp = dr.getData("NY.GDP.PCAP.CD");
			
		
		
		//========= Perform Calculation =========//
		
		// ArrayList to store calculated result
		ArrayList<Double> calcData = new ArrayList<Double>();
		
		// perform calculation
		int maxIndex = years[1] - years[0];
		boolean allZero = true;
		for (int i = 0; i <= maxIndex; i++) {
			double ratio = co2.getData().get(i) / gdp.getData().get(i);
			
			// add calculated data to arrayList. Set NaN data to 0.
		 	if (Double.isNaN(ratio) == false) calcData.add(ratio);
		 	else {
		 		ratio = 0;
		 		calcData.add(ratio);
		 	}
			
			//check for all zero data
			if (ratio != 0) {
				allZero = false;
			}
		}
		
		// send analysis to result object for display is there is non-zero data
		if (!allZero) {
			
			// add calcData to an arrayList
			ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
			dataList.add(calcData);
			
			
			//========= Prepare Identifying Info =========//
			
			// Set Analysis Name
			result.setName(selection.getSelectedAnalysis());
			
			// Set years
			result.setYears(selection.getSelectedYears());
			
			// Set list of names for the axes corresponding to each list of data in the dataList
			ArrayList<String> dataNames = new ArrayList<String>();
			dataNames.add("Ratio of CO2 emissions (metric tons per capita) and GDP per capita (current US$)");
			result.setDataNames(dataNames);
			
			// Set Panel that viewers will belong to
			System.out.println("Setting Viewer Panel");
			result.setPanel(this.viewerPanel);
			
			
			//========= Begin Generating Graphics =========//
			
			// Send off data from WBAPI to resultObject
			// NOTE:  As soon as resultObject obtains the data,
			//        it updates the viewers automatically
			System.out.println("Sending Data to Result Object.");
			result.setResult(dataList);
			
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