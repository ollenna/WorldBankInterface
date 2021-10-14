
package viewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/** 
 * Implements the Bar Chart.
 *  
 * <p>
 * Provides the basic functionality for
 * adding/displaying a Bar Chart.
 * 
 * Date:         4/3/2021
 * @author	     Jessica Li
 * 
 * 
 */
public class PieViewer extends ConcreteViewer {
	
	/** 
	 * Creates a Pie Chart using the base
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
	
	// Data to be displayed
	DefaultPieDataset dataset;

	
	public void updateGraphics(JPanel myPanel) {
		
		System.out.println("[PieViewer] Updating Graphics.");
		

		// ========= Create and Fill DataSet ==========//

		// Init % list
		this.dataset = new DefaultPieDataset();

		// Maximum indexes
		int maxTypeIndex = this.dataNames.size();

		
		// For each type (ex. "Forest", "Total Land"), add data
		for (int i = 0; i < maxTypeIndex; i++) {

			String dataName = this.dataNames.get(i);
			double dataPoint = this.data.get(0).get(i);
			dataset.setValue(dataName, dataPoint);

		}

		
		// ========= Fun Formatting Stuff ===========//
		
		JFreeChart pieChart = ChartFactory.createPieChart(this.analysisName, 
                dataset, true, true, false);
        Color[] colours = new Color[] { new Color(129, 233, 121), new Color(141, 169, 196)};

        PiePlot plot = (PiePlot) pieChart.getPlot();
        int i = 0;
        for (Object key : dataset.getKeys()) {
            plot.setSectionPaint((Comparable) key, colours[i % colours.length]);    
            i++;
        }
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        myPanel.add(chartPanel);
	
	}

}