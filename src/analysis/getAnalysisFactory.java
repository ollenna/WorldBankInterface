package analysis;
/** 
 *  Creates new Analyses using the Factory Design Pattern.
 * 
 * Date:         4/2/2021
 * @author	     Olena Orlova-Kurylova
 * 
 */
public class getAnalysisFactory {

	/** 
	 * Given a string describing a type of
	 * analysis, returns an Analysis Object.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param analysisType A string describing the type of
	 *                      viewer to be returned. Must be one
	 *                      of ("CO2 Emissions vs Energy Use vs PM2.5 Air Pollution", 
	 *                      "PM2.5 Air Pollution vs Forest Area",
	 *                      "Ratio of CO2 Emissions and GDP", "Average Forest Area",
	 *                      "Hospital Beds and Current Health Expenditure", 
	 *                      "Average Government Expenditure on Education (GDP)",
	 *                      "Current Health Expenditure vs Mortality Rate," 
	 *                      "Ratio of Government Expenditure on Education vs Current Health Expenditure.")
	 *  
	 *  @return An object corresponding to the provided
	 *          analysis string. If the provided string does
	 *          not describe a valid viewer, NULL is returned
	 *          instead.
	 */
	
	public Analysis getAnalysis(String analysisType) {
		
		if(analysisType.compareTo("CO2 Emissions vs Energy Use vs PM2.5 Air Pollution")==0) {
			return new A1_CO2VsEnergyVsAir();
		}
		if(analysisType.compareTo("PM2.5 Air Pollution vs Forest Area")==0) {
			return new A2_AirVsForest();
		}
		if(analysisType.compareTo("Ratio of CO2 Emissions and GDP")==0) {
			return new A3_CO2VsGDP();
		}
		if(analysisType.compareTo("Average Forest Area")==0) {
			return new A4_Forest();
		}
		if(analysisType.compareTo("Average Government Expenditure on Education (GDP)")==0) {
			return new A5_ExpEdPercentGDP();
		}
		if(analysisType.compareTo("Hospital Beds and Current Health Expenditure")==0) {
			return new A6_HospitalVsHealthCosts();
		}
		if(analysisType.compareTo("Current Health Expenditure vs Mortality Rate Infant")==0) {
			return new A7_HealthCostsVsInfants();
		}
		if(analysisType.compareTo("Ratio of Government Expenditure on Education vs Current Health Expenditure")==0) {
			return new A8_EdCosts();
		}
		
		
		
		
		
		return null;
	}

}
