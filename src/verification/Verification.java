package verification;

import viewers.BarViewer;
import viewers.LineViewer;
import viewers.PieViewer;
import viewers.ReportViewer;
import viewers.ScatterViewer;

/** 
 *  The Base Analysis class.
 *  
 * <p>
 * Implements the basic verification requirements 
 * for each Verification object
 * 
 * Date:         4/3/2021
 * @author	     Olena Orlova-Kurylova
 *
 * @see verifyCountry
 * @see verifyYears
 * @see verifyViewers
 * 
 */
public abstract class Verification {
	
	
	public class verifyCountry extends Verification{}
	public class verifyYears extends Verification{}
	public class verifyViewers extends Verification{}
}
