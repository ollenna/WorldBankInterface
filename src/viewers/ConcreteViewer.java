package viewers;

import java.util.ArrayList;
import javax.swing.JPanel;

import analysis.Analysis;


/** 
 *  The Base Viewer class.
 *  
 * <p>
 * Implements the basic requirements for each Viewer object
 * including the title of the viewer (analysisName),
 * the axis name(s) (dataNames), the data that the viewer
 * will display, and the range of years that the data spans.
 * 
 * Date:         4/3/2021
 * @author	     Cameron Arnold
 *
 * @see BarViewer
 * @see LineViewer
 * @see PieViewer
 * @see ScatterViewer
 * @see ReportViewer
 * 
 */

public abstract class ConcreteViewer extends Observer {
	
	
	ArrayList<ArrayList<Double>> data;		// List of data lists
	String analysisName;					// Analysis Name
	ArrayList<String> dataNames;			// Data Names
	int[] years;							// Start/End years
	
	
	/** 
	 * Implemented form of the abstract notify() function
	 * in the viewer class.  Called by the ResultObject
	 * class (the Subject). Triggers the viewer to update
	 * its data (for instance, if it is a Pie Chart, update
	 * viewerData with data from the resultObject, then
	 * display the data in the chart.
	 * 
	 *  @param subject The Subject object that called this method
	 *                 (in this instance, the resultObject)
	 *                 
	 *  @see   Observer
	 *  @see   ResultObject
	 */
	
	public void notify(Subject whoFrom) {
		
		System.out.println("[Viewer] Notified by Result Object.");
		
		// Get data to display from Result Object
		ResultObject ro = (ResultObject) whoFrom;
		this.data = ro.getResult();
		this.analysisName = ro.getName();
		this.years = ro.getYears();
		this.dataNames = ro.getDataNames();
		
		// Display Data
		this.updateGraphics(ro.getPanel());
	}
	
	
	
	/** 
	 * Creates the graphics associated with the stored
	 * data, and displays it in the provided Panel.
	 * 
	 * <p>
	 * Called when the ResultObject triggers this.notify().
	 * Each type of viewer (scatter chart, report, etc)
	 * may implement this method differently.
	 * 
	 *  @param myPanel The Panel that this viewer will
	 *                 be added to ("west" in the MainUI)
	 *                 
	 */
	
	public abstract void updateGraphics(JPanel myPanel);
	
}





