package userInterface.mainUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * The GUIreader is a helper class to MainUI.java which dynamically reads and
 * populates the drop down selection menus of main UI
 * 
 * @author Jessica Li
 * Date: 03/29/2021
 */
public class GUIreader {
	// initiate selection drop down as vectors
	private static Vector<String> countryNames = new Vector<String>();
	private static Vector<String> viewsNames = new Vector<String>();
	private static Vector<String> methodsNames = new Vector<String>();
	private static Vector<String> years = new Vector<String>();

	/**
	 * readCountries() method
	 * @return countryNames for country drop down list
	 * @throws IOException if invalid file
	 */
	public static Vector<String> readCountries() throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("countries.txt"));
			String country;
			while ((country = in.readLine()) != null) {
				countryNames.add(country);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is invalid.");
		}
		return countryNames;
	}

	/**
	 * readViewers() method
	 * @return viewsNames for viewer drop down list
	 * @throws IOException if invalid file
	 */
	public static Vector<String> readViewers() throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("views.txt"));
			String viewer;
			while ((viewer = in.readLine()) != null) {
				viewsNames.add(viewer);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is invalid.");
		}
		return viewsNames;
	}

	/**
	 * readMethods() method
	 * @return methodsNames for methods drop down list
	 * @throws IOException if invalid file
	 */
	public static Vector<String> readMethods() throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("analysisTypes.txt"));
			String method;
			while ((method = in.readLine()) != null) {
				methodsNames.add(method);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is invalid.");
		}
		return methodsNames;
	}

	/**
	 * readYears() method
	 * @return years for years drop down list
	 * @throws IOException if invalid file
	 */
	public static Vector<String> readYears() throws IOException {
		try {
			BufferedReader in = new BufferedReader(new FileReader("years.txt"));
			String yearStart;
			String yearEnd;

			yearStart = in.readLine(); // read start
			yearEnd = in.readLine(); // read end

			// generate years range
			for (int i = Integer.parseInt(yearStart); i <= Integer.parseInt(yearEnd); i++) {
				years.add("" + i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File is invalid.");
		}
		return years;
	}

//	public static Vector<String> readGUIData() throws IOException {
//		// read data related to GUI files
//		File dir = new File("GUIFiles");
//		File[] files = dir.listFiles();
//		
//		for (File file : files) {
//			try {
//				if (file.getName().equals("countries.txt")) {
//					BufferedReader in = new BufferedReader(new FileReader("countries.txt"));
//					String country;
//					while ((country = in.readLine()) != null) {
//						countriesNames.add(country);
//					}
//					countriesNames.sort(null);
//				} else if (file.getName().equals("views.txt")) {
//					BufferedReader in = new BufferedReader(new FileReader("views.txt"));
//					String line;
//					while ((line = in.readLine()) != null) {
//						viewsNames.add(line);
//					}
//				} else if (file.getName().equals("analysisTypes.txt")) {
//					BufferedReader in = new BufferedReader(new FileReader("analysisTypes.txt"));
//					String line;
//					while ((line = in.readLine()) != null) {
//						methodsNames.add(line);
//					}
//				}
//				
//			} catch (FileNotFoundException e) {
//				System.out.println("File is invalid.");
//				// e.printStackTrace();
//			}
//		}
//		return countriesNames;
//		
//	}

}
