
package fetchData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import userInterface.mainUI.systemSelection;



/** 
 *  A class used to retrieve data from the World Bank Server.
 * 
 * <p>
 * The primary purpose of this class is, given a 
 * data type, country, and range of years, to
 * retrieve a DataObject containing the values
 * corresponding to the information available
 * on the World Bank Server.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 * 
 * @see DataObject
 * 
 */

public class DataReader {

	String countryISO;		// Country Name (ISO)
	int[] years;			// [startYear, endYear]
	
	
	
	/** 
	 * Constructor
	 * <p>
	 * Creates the DataReader country and year range
	 * defined in the provided systemSelection object.
	 * Certain data types (ex. CO2 Emissions) can be
	 * further retrieved for the country using the
	 * getData() method defined below.
	 * 
	 *  @param selection The selection object containing the
	 *                   country name and years range needed
	 *                   to construct the DataReader.
	 *  
	 */
	
	public DataReader(systemSelection selection) {
		
		this.countryISO = findCountryISO(selection.getSelectedCountry());
		this.years = selection.getSelectedYears();
	}
	
	
	
	
	/** 
	 * Returns the ISO name corresponding to the
	 * provided country name, using the list of
	 * country ISOs found in countryISO.txt
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param country The name of the country to
	 *                 retrieve the ISO name for.
	 *  
	 *  @return The ISO name of the provided country.
	 */
	
	public String findCountryISO(String country) {
		
		// Name of file
		String COUNTRYISO = "countryISO.txt";
		
		try {
		
			File cisoFile = new File(COUNTRYISO);			// CountryISO File
		    Scanner cisoReader = new Scanner(cisoFile);		// File Scanner
		    
		    // Loop through file until country token is found
			while (cisoReader.hasNext() &&
					!(cisoReader.next()).equals(country)) {}

			// Once token is found, next token is ISO name

			// Validate next token exists
			if (!cisoReader.hasNext()) {
				cisoReader.close();					// Close reader
				throw new FileNotFoundException();	// Throw error
			}
			
			// Get next token
			String isoString = cisoReader.next();
			cisoReader.close();					// Reading is done; close Reader
			return isoString;					// Return ISO string
			
		} catch (FileNotFoundException e) {
		    System.out.println("Country ISO not found.");
		    e.printStackTrace();
		    return "Invalid Country";
		}
	}
	
	
	
	
	/** 
	 * Returns a DataObject containing the data retrieved
	 * from the World Bank Server corresponding to the
	 * provided data type (GDP, population, etc), country,
	 * starting year, and final year.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param dataType The WBAPI indicator for the
	 *                  requested data type (ex.
	 *                  "SP.POP.TOTL" for total population)
	 *                       
	 *  @return A DataObject containing the data retrieved
	 *          from the World Bank API
	 */
	
	public DataObject getData(String dataType) {
		
		// Create url string
		String urlString = "http://api.worldbank.org/v2" +		// Base URL
				"/country/"   + this.countryISO + 				// Country tag
				"/indicator/" + dataType +						// DataType tag
				"?date="  + Integer.toString(this.years[0]) +	// Start year
				":"       + Integer.toString(this.years[1]) +	// End year
				"&format=json";									// Return type

		
		// Data object to hold data
		DataObject dataObj = new DataObject();
		
		
		// Get Data
		try {
			
			URL reqURL = new URL(urlString);	// URL object
			
			// Open URL connection
			HttpURLConnection conn = (HttpURLConnection) reqURL.openConnection();
			
			// Send request
			conn.setRequestMethod("GET");
			conn.connect();
			
			// Response code
			int responsecode = conn.getResponseCode();
			
			if (responsecode == 200) {
				
				String inline = "";		// Holds plain JSON data
				Scanner sc = new Scanner(reqURL.openStream());	// To scan with
				
				// Fill inline with web data
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				
				sc.close();				// Close scanner
				
				// Parse plain JSON data in inline
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				
				// Parse results
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				
				
				// Get starting year for data object
				int startYear = jsonArray.get(1).getAsJsonArray().get(sizeOfResults - 1).getAsJsonObject()
						.get("date").getAsInt();
				
				dataObj.setFirstYear(startYear);

				
				// Get data name for data object
				String name = jsonArray.get(1).getAsJsonArray().get(0).getAsJsonObject()
						.get("indicator").getAsJsonObject().get("value").getAsString();
				
				dataObj.setName(name);

				
				// Populate data object with values
				for (int i = sizeOfResults - 1; i >= 0; i--) {
					
					// Value not exist for year?
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						dataObj.pushYear(0);
					
					// Value does exist?
					else {
						double yearValue = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsDouble();
						dataObj.pushYear(yearValue);
						
					}
				}
			}
			
			
		// Exception catch
		} catch (IOException e) {
			System.out.println("Invalid request to WBAPI.");
		    e.printStackTrace();
		}
		
		
		// Return Data Object
		return dataObj;

	}
	
}



