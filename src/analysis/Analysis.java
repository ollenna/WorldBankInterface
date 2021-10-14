
package analysis;

import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JPanel;

import viewers.ConcreteViewer;
import viewers.ViewerFactory;

import userInterface.mainUI.systemSelection;



/** 
 * The base analysis class.
 * 
 * <p>
 * An abstract class that each Analysis will be
 * built off of. This class contains basic information
 * needed for each analysis, including the list of
 * viewers to be displayed, the UI panel they will be
 * displayed in, and an abstract calculate() method that
 * must be implemented by each child of this class.
 * 
 * Date:         4/2/2021
 * @author	     Olena Orlova, Cameron Arnold
 * 
 * @see A1_CO2VsEnergyVsAir
 * @see A2_AirVsForest
 * @see A3_CO2VsGDP
 * @see A4_Forest
 * @see A5_ExpEdPercentGDP
 * @see A6_HospitalVsHealthCosts
 * @see A7_HealthCostsVsInfants
 * @see A8_EdCosts
 */


public abstract class Analysis {
	
	// The panel in the MainUI that the viewers
	// will be added to
	JPanel viewerPanel;
	
	// List of Viewers
	ArrayList<ConcreteViewer> viewers;
	
	
	/** 
	 * Sets the Panel that the viewers will be displayed in.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.viewerPanel
	 * </ul>
	 * 
	 *  @param viewerPanel The UI Panel that viewers will
	 *                     display the viewers.
	 */
	
	public void setPanel(JPanel viewerPanel) {
		
		this.viewerPanel = viewerPanel;
	}
	
	
	
	/** 
	 * Creates the Viewers to be be populated with data.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies this.viewers
	 * </ul>
	 * 
	 *  @param viewerList A list of strings signifying the
	 *                    names of each viewer to add
	 *                    ("Report", "Scatter Chart", etc).
	 */
	
	public void setViewers(Vector<String> viewerList) {
		
		ViewerFactory vf = new ViewerFactory();
		this.viewers = new ArrayList<ConcreteViewer>();
		
		for (String viewerName : viewerList) {
			
			this.viewers.add(vf.getViewer(viewerName));
		}
		
	}
	
	
	
	/** 
	 * Returns whether the provided data list is valid
	 * (ie. not all zeros or NaN)
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param dataList The list of data points.
	 */
	
	public boolean dataIsValid(ArrayList<Double> dataList) {
		
		for (double dataPoint : dataList) {
			
			// Point exists thats not double or NaN? Return true
			if (dataPoint != 0 && !Double.isNaN(dataPoint)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/** 
	 * Performs the analysis calculation.
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
	
	public abstract void calculate(systemSelection selection);
	
}




