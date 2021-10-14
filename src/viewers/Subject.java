
package viewers;


import java.util.ArrayList;	// ArrayList
import java.util.List;		// List



/** 
 * The central broadcaster in the Observer Design Pattern.
 * <p>
 * Subject is the abstract base class for the broadcaster
 * object in the Observer Design Pattern.  Each Subject
 * contains a list of Observers, which can be broadcasted
 * to through the method <code>notifyObservers()</code>,
 * triggering all individual responses at once.
 * 
 * Date:         4/2/2021
 * @author	     Cameron Arnold
 */

public abstract class Subject {

	
	// List of observers this subject notifies
	private List<Observer> observers = new ArrayList<Observer>();


	/** 
	 * Attaches a new observer to the broadcast list.
	 * 
	 * <br><br>
	 * <b>Contract:</b>
	 * <ul>
	 *   <li> Observer -> void
	 * </ul>
	 * 
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies member <code>observers</code>
	 * </ul>
	 * 
	 *  @param ob The observer to be linked
	 *  @see   Observer
	 */
	
	public void attach(Observer ob) {
		observers.add(ob);
	}
	
	
	/** 
	 * Detaches an observer from the broadcast list.
	 * 
	 * <br><br>
	 * <b>Contract:</b>
	 * <ul>
	 *   <li> Observer -> void
	 * </ul>
	 * 
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> Modifies member <code>observers</code>
	 * </ul>
	 * 
	 *  @author Cameron Arnold
	 *  @param  ob The observer to be removed
	 *  @see    Observer
	 */
	
	public void detach(Observer ob) {
		observers.remove(ob);
	}
	
	

	/** 
	 * Calls <code>notify()</code> on each observer
	 * in the broadcast list.
	 * 
	 * <br><br>
	 * <b>Contract:</b>
	 * <ul>
	 *   <li> -> void
	 * </ul>
	 * 
	 * <br>
	 * <b>Effects:</b>
	 * <ul>
	 * <li> May modify one or more members
	 *      in <code>observers</code>
	 * </ul>
	 * 
	 *  @see   Observer
	 */
	
	public void notifyObservers() {
		
		for (Observer ob : observers) {
			ob.notify(this);
		}
	}
	
}


