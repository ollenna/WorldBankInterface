

package viewers;


/** 
 *  Creates new Viewers using the Factory Design Pattern.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 * 
 */

public class ViewerFactory {

	
	/** 
	 * Given a string describing a type of
	 * viewer, returns a reference to the
	 * corresponding Viewer Object.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param viewerType A string describing the type of
	 *                    viewer to be returned. Must be one
	 *                    of ("Bar Chart", "Line Chart",
	 *                    "Pie Chart", "Scatter Chart",
	 *                    "Report Chart").
	 *  
	 *  @return An object corresponding to the provided
	 *          viewer string.  If the provided string does
	 *          not describe a valid viewer, NULL is returned
	 *          instead.
	 */
	
	
	public ConcreteViewer getViewer(String viewerType) {
		
		// Pie Chart
		if (viewerType.compareTo("Pie Chart") == 0) {
			return new PieViewer();
			
		// Line Chart
		} else if (viewerType.compareTo("Line Chart") == 0) {
			return new LineViewer();
			
		// Bar Chart
		} else if (viewerType.compareTo("Bar Chart") == 0) {
			return new BarViewer();
			
		// Scatter Chart
		} else if (viewerType.compareTo("Scatter Chart") == 0) {
			return new ScatterViewer();
		
		// Report
		} else if (viewerType.compareTo("Report") == 0) {
			return new ReportViewer();
		}
		
		
		return null;
	}

}


