package fetchData;

import java.util.ArrayList;


/** 
 *  A stack-based class that stores a set of
 *  values that correspond to a range of years.
 * 
 * <p>
 * Similar to a stack, this class can (provided a
 * starting year) push and pop values.  Years are
 * added/removed at the end of the range, thus
 * if the range is 1993-2008 and a value is pushed,
 * it corresponds to the value in 2009.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 * 
 */

public class DataObject {
	
	private int firstYear;			// Starting year for data
	private int lastYear;			// Final year for data

	// List of values corresponding to [startingYear...finalYear]
	private ArrayList<Double> values = new ArrayList<Double>();
	
	// Name of data
	private String dataName;
	
	
	/** 
	 * Pushes an integer value corresponding to
	 * the current finalYear + 1, and updates the
	 * final Year and returns its value.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> May modify member finalYear
	 * <li> May modify member values
	 * </ul>
	 * 
	 *  @param val The value to be appended
	 *  
	 *  @return The year corresponding to the provided data.
	 */
	
	public int pushYear(double val) {
		values.add(val);
		lastYear += 1;
		return lastYear;
	}
	
	

	/** 
	 * Pops and returns the integer value
	 * corresponding to the latest finalYear,
	 * and decrements the finalYear by 1
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> May modify member finalYear
	 * <li> May modify member values
	 * </ul>
	 * 
	 *  @return The popped value of the latest year
	 */
	
	public double popYear() {
		
		// Index of year to remove
		int index = lastYear - firstYear;

		// Get, remove, and return value
		double yearValue = values.get(index);
		values.remove(index);
		lastYear -= 1;
		return yearValue;
	}
	
	
	
	/** 
	 * Returns the integer value corresponding
	 * to the provided year.  If the provided
	 * year is not valid, -1 is returned instead.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @param year The year that the data will be
	 *              retrieved for.
	 *  
	 *  @return The value corresponding to the
	 *          provided year.
	 */
	
	public double getYear(int year) {
		
		int index = year - firstYear;
		
		if (index < 0 || index > (lastYear - firstYear)) {
			return -1;
		}
		
		return values.get(index);
	}
	
	
	/** 
	 * Sets the name of the data.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies member dataName.
	 * </ul>
	 *  
	 *  @param name The new data name.
	 */
	
	public void setName(String dataName) {

		this.dataName = dataName;
	}
	
	
	
	
	/** 
	 * Returns the name of the data
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 *  
	 *  @return The name of the data.
	 */
	
	public String getName() {

		return this.dataName;
	}
	
	
	
	/** 
	 * Sets the first year to be used.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies member firstYear.
	 * </ul>
	 * 
	 *  @param year The new starting year.
	 */
	
	public void setFirstYear(int year) {
		firstYear = year;
		lastYear = firstYear + values.size();
	}
	
	
	
	/** 
	 * Returns the earliest year in the range.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The starting year
	 */
	
	public int getFirstYear() {
		return firstYear;
	}
	
	
	
	/** 
	 * Returns the latest year in the range.
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The final year
	 */
	
	public int getLastYear() {
		return lastYear;
	}
	
	
	/** 
	 * Returns the array of data
	 * 
	 * <br><br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> None.
	 * </ul>
	 * 
	 *  @return The ArrayList of values
	 */
	
	public ArrayList<Double> getData() {
		return values;
	}

	
}





