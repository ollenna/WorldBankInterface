
package viewers;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;


public class LineViewer extends ConcreteViewer {
	

	 
    // Dataset for holding values
	// datasets[0] represents the left y axis
	// datasets[1] represents the right y axis
	// Different class depending on the viewer type
	
	ArrayList<DefaultCategoryDataset> datasets;
	
	public void updateGraphics(JPanel myPanel) {
		System.out.println("[ScatterViewer] Updating Graphics.");
	
		
		
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
		
		TimeSeriesCollection data = new TimeSeriesCollection();
		
	
		
		// For each data type (ex. Mortalities)
		for (int i = 0; i < maxTypeIndex; i++) {
			System.out.println("i "+ i);

			String dataName = this.dataNames.get(i);			// Data type
			TimeSeries series = new TimeSeries(dataName);
			
			// For each year (ex. Mortalities in 2016), append
			for (int j = 0; j <= maxYearIndex; j++) {
			
				double dataPoint = this.data.get(i).get(j);			// Data for year
				String year = Integer.toString(this.years[0] + j);	                    // Year
				series.add(new Year(Integer.parseInt(year)), dataPoint);
				System.out.println("data point "+dataPoint);
				// Log Message
				//System.out.println("Adding " + 
				//		dataPoint + "  " +
				//		dataName  + "  " +
				//		year + " to Bar Graph.");
				
				// [i % 2] => Add to left, then right, then left...
				
				datasets.get(i % 2).addValue(dataPoint, dataName, year);
			
			}
			data.addSeries(series);	
			
			}
			
		
		
		
		
		//========= Fun Scatter Stuff ===========//
		

		CategoryPlot plot = new CategoryPlot();			// Plot
		LineAndShapeRenderer itemrenderer1 = new LineAndShapeRenderer();
		LineAndShapeRenderer itemrenderer2 = new LineAndShapeRenderer();
		//CategoryItemRenderer itemrenderer2 = (CategoryItemRenderer) new XYLineAndShapeRenderer(false, true);
		
		// Initialize X Axis
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		
		

		// Initialize left Y Axis
		plot.setDataset(0, datasets.get(0));
		plot.setRenderer(0, itemrenderer1);
		plot.setRangeAxis(new NumberAxis(""));
		
		// Initialize right Y Axis
		plot.setDataset(1, datasets.get(1));
		plot.setRenderer(1, itemrenderer2);
		plot.setRangeAxis(1, new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		//create scatter chart
		JFreeChart lineChart = new JFreeChart(this.analysisName,
				new Font("Serif", java.awt.Font.PLAIN, 18), plot, true);

		//========= Fun Formatting Stuff ===========//
		
		// NOTE: I dont think the frame automatically changes
		//       the graph sizes to fit each of them.  Might have
		//       to add a way to resize each viewer so that they
		//       all fit right?  Depends how the viewers look after
		//       we add all the classes and run the code.
		
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		LineAndShapeRenderer r = (LineAndShapeRenderer)lineChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.getHSBColor(141, 169, 196));
		
		// Add to Panel
		myPanel.revalidate();
		myPanel.add(chartPanel);
}}












