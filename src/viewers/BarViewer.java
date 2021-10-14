
package viewers;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/** 
 * Implements the Bar Chart.
 *  
 * <p>
 * Provides the basic functionality for
 * adding/displaying a Bar Chart.
 * 
 * Date:         4/3/2021
 * @author	     Cameron Arnold
 * 
 * 
 */

public class BarViewer extends ConcreteViewer {

	
	/** 
	 * Creates a Bar Chart using the base
	 * data found in ConcreteViewer, and
	 * adds it to the provided UI panel.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies myPanel (adds a chartPanel)
	 * </ul>
	 * 
	 *  @param myPanel The UI panel that the Bar
	 *                 Chart will be added to.
	 */
	
	public void updateGraphics(JPanel myPanel) {
		
		
		System.out.println("[BarViewer] Updating Graphics.");
		
		//========= Create and Fill DataSet ==========//

		// Add to class datasets
		// datasets[0] represents the left y axis
		// datasets[1] represents the right y axis
		ArrayList<DefaultCategoryDataset> datasets = 
				new ArrayList<DefaultCategoryDataset>();	// Initialize dataset
		datasets.add(new DefaultCategoryDataset());			// Add left axis dataset
		datasets.add(new DefaultCategoryDataset());			// Add right axis dataset
		
		// Maximum indexes
		int maxTypeIndex = this.dataNames.size();
		int maxYearIndex = this.years[1] - this.years[0];
		

		/*
		 * NOTE:
		 * 		If your graph is 1 or 2 series, take the
		 *      body of "if (maxTypeIndex = 0 or 1)" and use
		 *      that to replace the big if block
		 */

		// For each data type (ex. Mortalities)
		for (int i = 0; i < maxTypeIndex; i++) {

			String dataName = this.dataNames.get(i);				// Data type
			
			// For each year (ex. Mortalities in 2016), append
			for (int j = 0; j <= maxYearIndex; j++) {
				
				double dataPoint = this.data.get(i).get(j);			// Data for year
				String year = Integer.toString(this.years[0] + j);	// Year
				
				// Log Message
				//System.out.println("Adding " + 
				//		dataPoint + "  " +
				//		dataName  + "  " +
				//		year + " to Bar Graph.");
				
				// [i % 2] => Add to left, then right, then left...
				datasets.get(i % 2).addValue(dataPoint, dataName, year);
			
			}
		}
		
		
		
		//========= Fun Axis/Bar Stuff ===========//
		
		
		CategoryPlot plot = new CategoryPlot();			// Plot
		BarRenderer lBarRenderer = new BarRenderer();	// Left BR
		BarRenderer rBarRenderer = new BarRenderer();	// Right BR
		
		// Initialize X Axis
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		// Initialize left Y Axis
		plot.setDataset(0, datasets.get(0));
		plot.setRenderer(0, lBarRenderer);
		plot.setRangeAxis(new NumberAxis(""));
		
		// Initialize right Y Axis
		plot.setDataset(1, datasets.get(1));
		plot.setRenderer(1, rBarRenderer);
		plot.setRangeAxis(1, new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);	// Dataset #1 to left
		plot.mapDatasetToRangeAxis(1, 1); 	// Dataset #2 to right
		

		// Create Actual Bar Chart
		JFreeChart barChart = new JFreeChart(this.analysisName,
				new Font("Serif", java.awt.Font.PLAIN, 18), plot, true);

		
		
		//========= Fun Formatting Stuff ===========//
		
		// NOTE: I dont think the frame automatically changes
		//       the graph sizes to fit each of them.  Might have
		//       to add a way to resize each viewer so that they
		//       all fit right?  Depends how the viewers look after
		//       we add all the classes and run the code.
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		((BarRenderer)plot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer)barChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.getHSBColor(141, 169, 196));
		
		// Add to Panel
		myPanel.revalidate();
		myPanel.add(chartPanel);
	}
}












