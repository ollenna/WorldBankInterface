package analysis;

import java.util.Vector;
import java.util.ArrayList;
import fetchData.*;
import viewers.ConcreteViewer;
import viewers.ResultObject;
import userInterface.mainUI.MainUI;
import userInterface.mainUI.systemSelection;


/** 
 *  Implementation of Analysis #1
 *  
 * <p>
 * This class inherits the base Analysis class and
 * implements Analysis #1, aka
 * CO2 emissions (metric tons per capita) vs 
 * Energy use (kg of oil equivalent per capita) vs 
 * PM2.5 air pollution, mean annual exposure 
 * (micrograms per cubic meter)
 * 
 * Date:         4/3/2021
 * @author	     Cameron Arnold
 * 
 * @see ResultObject
 * 
 */


public class A1_CO2VsEnergyVsAir extends Analysis {

	
	/** 
	 * Performs the calculation for Analysis #1.
	 * <p>
	 * Performs the following:
	 * <ol>
	 * <li> Requests the data from the World Bank Database
	 * <li> Performs the necessary calculations on the
	 *      retrieved data (for example, gdp / population)
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
		
		System.out.println("Beginning CO2vsEnergyvsPollution Analysis");

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
		
		// Retrieve CO2
		DataObject co2 = dr.getData("EN.ATM.CO2E.PC");
		
		// Retrieve Energy
		DataObject energy = dr.getData("EG.USE.PCAP.KG.OE");
		
		// Retrieve Air
		DataObject air = dr.getData("EN.ATM.PM25.MC.M3");
		

		// Create dataset for resultObject

		/*
		If your analysis involves calculations, then add:
		
		ArrayList<Double> calcData = new ArrayList<Double>();
		
		for (double dataPoint : DataObjectName) {
		     calcData.add(calculation [ex. dataPoint / 1000]);
		}
		 
		
		OR if you need to get the calculation between two dataobjects, do
		
		int maxIndex = years[1] - years[0];
		for (int i = 0; i < maxIndex; i++) {
		 	   calcData.add(calculation [ex. do1.get(i) / do2.get(i)]);
		}
		
		Then add calcData to dataList below
		Feel free to delete this after adding your code
		*/
		
		ArrayList<ArrayList<Double>> dataList = new ArrayList<ArrayList<Double>>();
		dataList.add(co2.getData());
		dataList.add(energy.getData());
		dataList.add(air.getData());
		
		
		// Validate data
		for (ArrayList<Double> dl : dataList) {
			if (!this.dataIsValid(dl)) {
				
				try {
					MainUI.getInstance().errorLabel.setText(
							"All data is 0!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		

		//========= Prepare Identifying Info =========//
		
		// Set Analysis Name
		result.setName(selection.getSelectedAnalysis());
		
		// Set years
		result.setYears(selection.getSelectedYears());
		
		// List of names for the axises corresponding to each
		// list of data in the dataList
		// So len(dataList) == len(names)
		
		ArrayList<String> dataNames = new ArrayList<String>();
		dataNames.add(co2.getName());
		dataNames.add(energy.getName());
		dataNames.add(air.getName());
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
		
	}

}
