
package viewers;

/** 
 *  The central receiver in the Observer Design Pattern.
 * 
 * <p>
 * Observer is the abstract base class for the receiver object
 * in the Observer Design Pattern. Each class inheriting from
 * the Observer base class may have a different implemented 
 * notify() method, which handles how broadcasts from the Subject
 * class are interpreted.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 * 
 */

public abstract class Observer {

	
	/** 
	 * Called by the Subject object that contains a link to
	 * this observer. Each object that inherits this class
	 * may have a different implemented notify() function.
	 * 
	 * <br><br>
	 * <b>Contract:</b>
	 * <ul>
	 *   <li> Subject -> void
	 * </ul>
	 * 
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> May modify members of the object that inherits this
	 * </ul>
	 * 
	 *  @param subject The Subject object that called this method
	 *  @see   Subject
	 */
	

	public abstract void notify(Subject subject);
	
}
