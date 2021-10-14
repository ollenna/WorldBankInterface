

package fetchData;
import userInterface.mainUI.systemSelection;


/** 
 *  A class used to test the DataReader Class.
 * 
 * <p>
 * By editing the systemSelection object in this class, you
 * can test the validity of the DataReader class.  Additionally,
 * this can be used to print out an organized list of data
 * from the WorldBank API to use when verifying the graphs.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 * 
 */

public class testDataReader {


	public static void main(String[] args) {
		
		systemSelection ss = new systemSelection();
		ss.setSystemCountry("Canada");
		ss.setSystemYears(1995, 2001);
		
		DataReader dr = new DataReader(ss);
		DataObject dobj = dr.getData("SP.POP.TOTL");
		
		for (int i = 0; i < dobj.getLastYear() - dobj.getFirstYear(); i++) {
			
			int year = dobj.getFirstYear() + i;
			double val = dobj.getYear(year);
			
			System.out.println(dobj.getName());
		}
	}
}
