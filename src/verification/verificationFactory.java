package verification;

import java.util.Vector;


/** 
 *  Creates new Verifications using the Factory Design Pattern.
 * 
 * Date:         4/2/2021
 * @author	     Olena Orlova-Kurylova
 * 
 */
public abstract class verificationFactory {
	public abstract boolean factoryMethod(String item, String analysis);

	public abstract boolean factoryMethod(int startYear, int endYear, String country);
	public abstract boolean factoryMethod(Vector<String> viewers, String analysis);


}
