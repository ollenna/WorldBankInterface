
package viewers;

import java.util.ArrayList;
import javax.swing.JPanel;

import analysis.Analysis;
import fetchData.DataObject;


/** 
 * An object to hold the calculated data retrieved
 * by the World Bank Server, as well as trigger the
 * creation of the viewers to be displayed.
 * 
 * Date:         4/3/2021
 * @author	    Olena Orlova, Jessica Li,
 *              Jessica Schwarze, Cameron Arnold
 *              
 * @see Analysis
 * 
 */

public class ResultObject extends Subject {

	
	// An instance of ResultObject
	private static ResultObject instance;

	// Type of analysis that was performed
	private String analysisName;
	
	// Names of each axis
	private ArrayList<String> dataNames;
	
	// Range of years for data
	private int[] years;
	
	
	// Data from WebAPI that ResultObject contains
	private ArrayList<ArrayList<Double>> result;
	
	// Panel to add viewers to
	JPanel viewerPanel;
	
	
	
	/** 
	 * Used for testing to validate the ResultObject.
	 * Not used in the program outside of testing.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @author Jessica Schwarze
	 */
	
	public void initializeDummyData() {

		int[] yr = {2016, 2017, 2018, 2019, 2020};
		this.years = yr;
		
		this.analysisName = "fake analysis";
		
		// setting fake data
		ArrayList<Double> fakeSeries1 = new ArrayList<Double>();
		fakeSeries1.add(5.6);
		fakeSeries1.add(5.7);
		fakeSeries1.add(5.8);
		fakeSeries1.add(5.8);
		fakeSeries1.add(5.9);
		
		ArrayList<Double> fakeSeries2 = new ArrayList<Double>();
		fakeSeries2.add(10624.0);
		fakeSeries2.add(10209.0);
		fakeSeries2.add(9877.0);
		fakeSeries2.add(9491.0);
		fakeSeries2.add(9023.0);
		
		ArrayList<Double> fakeSeries3 = new ArrayList<Double>();
		fakeSeries3.add(2.92);
		fakeSeries3.add(2.87);
		fakeSeries3.add(2.77);
		fakeSeries3.add(2.8);
		fakeSeries3.add(2.83);
		
		ArrayList<ArrayList<Double>> fake1Series = new ArrayList<ArrayList<Double>>();
		fake1Series.add(fakeSeries1);
		
		ArrayList<ArrayList<Double>> fake2Series = new ArrayList<ArrayList<Double>>();
		fake2Series.add(fakeSeries1);
		fake2Series.add(fakeSeries2);
		
		ArrayList<ArrayList<Double>> fake3Series = new ArrayList<ArrayList<Double>>();
		fake3Series.add(fakeSeries1);
		fake3Series.add(fakeSeries2);
		fake3Series.add(fakeSeries3);
		
		//There are 3 options you can use for testing, 1-series of data, 2, or 3. Just un-comment whatever you need.
		this.result = fake1Series;
		//this.result = fake2Series;
		//this.result = fake3Series;
	}
	
	
	
	/** 
	 * Create a new instance of ResultObject
	 * (Singleton Design Pattern)
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Creates a new instance of this object.
	 * </ul>
	 * 
	 *  @return A new instance of ResultObject
	 */
	
	public static ResultObject ResultObjectInstance() {
		if (instance == null)
			instance = new ResultObject();

		return instance;
	}
	


	/** 
	 * Sets the name of the analysis that was performed.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.analysisName.
	 * </ul>
	 * 
	 *  @param analysisName The name of the analysis that
	 *                      was performed.
	 */
	
	public void setName(String analysisName) {
		this.analysisName = analysisName;
	}
	


	/** 
	 * Sets the range of years that the data is spanning.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.years
	 * </ul>
	 * 
	 *  @param years The range of years in the format
	 *               [firstYear, finalYear]
	 */
	
	public void setYears(int[] years) {
		this.years = years;
	}
	
	
	
	/** 
	 * Sets the names of the data that each
	 * ArrayList element in this.data refers to.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.dataNames
	 * </ul>
	 * 
	 *  @param names A list of names describing the
	 *               information in each element of
	 *               this.data
	 */
	
	public void setDataNames(ArrayList<String> names) {
		this.dataNames = names;
	}
	

	
	/** 
	 * Sets the result data calculated by the analysis class,
	 * and notifies all viewers to update their displayed data.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.rest
	 * <li> Modifies the MainUI (adds viewers)
	 * </ul>
	 * 
	 *  @param data A list containing the lists of data
	 *              calculated in the analysis.
	 */
	
	public void setResult(ArrayList<ArrayList<Double>> data) {

		System.out.println("[ResultObject] Data Recieved, notifying viewers.");
		this.result = data;
		notifyObservers();
	}
	
	

	/** 
	 * Sets the panel that the viewers will be added to.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.panel
	 * </ul>
	 * 
	 *  @param panel The panel that the viewers will be
	 *               added to.
	 */
	
	public void setPanel(JPanel panel) {
		this.viewerPanel = panel;
	}
	

	
	/** 
	 * Returns the name of the analysis that was performed.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The name of the analysis that was performed.
	 */
	
	public String getName() {
		return this.analysisName;
	}
	
	
	
	/** 
	 * Returns the range of years that the data spans.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The range of years that the data spans
	 *          in the format [firstYear, finalYear]
	 */
	
	public int[] getYears() {
		return this.years;
	}
	
	
	
	/** 
	 * Returns the list of names that the data in
	 * each element of this.data refers to.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return A list of names, where each name
	 *          describes the data found in
	 *          this.data[sameIndex]
	 */
	
	public ArrayList<String> getDataNames() {
		return this.dataNames;
	}
	

	
	/** 
	 * Returns the data calculated by the analysis class.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return A list of the data calculated by the
	 *          analysis class.
	 */
	
	public ArrayList<ArrayList<Double>> getResult() {
		return this.result;
	}
	
	
	
	/** 
	 * Returns the panel that the viewers will
	 * be displayed in.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The JPanel that the viewers will
	 *          be displayed in.
	 */
	
	
	public JPanel getPanel() {
		return this.viewerPanel;
	}
	
	
	
}




