
package viewers;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Implements the Report Chart.
 * 
 * <p>
 * Provides the basic functionality for adding/displaying a Report Chart.
 * 
 * Date: 4/3/2021
 * 
 * @author Cameron Arnold
 * 
 * 
 */

public class ReportViewer extends ConcreteViewer {

	/**
	 * Creates a Report Chart using the base data found in ConcreteViewer, and adds
	 * it to the provided UI panel.
	 * 
	 * <br>
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li>Modifies myPanel (adds a chartPanel)
	 * </ul>
	 * 
	 * @param myPanel The UI panel that the Report Chart will be added to.
	 */

	public void updateGraphics(JPanel myPanel) {

		System.out.println("[ReportViewer] Updating Graphics.");

		// ========= Populate TextBox with Data ===========//

		// Create title
		String reportMsg = this.analysisName + "\n";
		for (int i = 0; i < this.analysisName.length(); i++)
			reportMsg += "=";
		reportMsg += "\n\n";

		// Maximum indexes
		int maxTypeIndex = this.dataNames.size();
		int maxYearIndex = this.years[1] - this.years[0];
		

		//================================================================//
		
		// For 1-series data based on an average
		// Formats "Average of Year ______ to Year _______"
		
		if (this.dataNames.get(0).startsWith("Average")) {
			
			System.out.println("\n'Average' analysis type detected\n");
			
			// Years subtitle (ex. 2017 to 2019:)
			reportMsg += "Years " +
						Integer.toString(this.years[0]) + 	// Start year
						" to " +
						Integer.toString(this.years[1]) +   // End year
						":\n";

			// Data body (ex.  Average Forest Area => 2.2322323)
			reportMsg += "       " + this.dataNames.get(0) 
						+ "\n\t=>   " + this.data.get(0).get(0)
						+ "\n";

			// Data body (ex.  Average Forest Area => 2.2322323)
			reportMsg += "       " + this.dataNames.get(1) 
						+ "\n\t=>   " + this.data.get(0).get(1)
						+ "\n";
			
			System.out.println(reportMsg);

			

		//================================================================//
		
		// For 2 and 3-series datas
		
		} else {

			// For each year (ex. 2010, 2011,.....)
			for (int i = 0; i <= maxYearIndex; i++) {

				int year = this.years[0] + i; // Current year
				reportMsg += "Year " + year + ":\n";

				// For data type (ex. Health costs)
				for (int j = 0; j < maxTypeIndex; j++) {

					// Create message line ("type => value")
					String dataName = this.dataNames.get(j);
					reportMsg += "       " + dataName + "\n\t=>   ";
					reportMsg += this.data.get(j).get(i) + "\t\n\n";
				}

				reportMsg += "\n"; // Line break
			}

			System.out.println(reportMsg);
		}


		// Set text formatting
		JTextPane reportTP = new JTextPane();
		reportTP.setEditable(false);
		reportTP.setPreferredSize(new Dimension(400, 300));
		reportTP.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		reportTP.setBackground(Color.white);
		reportTP.setContentType("text");
		reportTP.setText(reportMsg);

		// Set ScrollPane formatting
		JScrollPane scrollPane = new JScrollPane(reportTP);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Add to viewer pane
		myPanel.add(scrollPane);

	}
}





