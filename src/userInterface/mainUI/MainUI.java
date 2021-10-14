package userInterface.mainUI;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * GUI for WBDB
 * <p>
 * This is the main UI display for Country Statistics WBDB that the user
 * interacts with Date: 03/29/2021
 * 
 * @author Jessica Li, CS2212 GUI
 */
public class MainUI extends JFrame implements ActionListener { // ADDED BY OLENA (implements ActionListener)

	/* attribute declaration */
	private static String viewer = ""; // current selected viewer
	private static Vector<String> viewersList = new Vector<String>(); // list of viewers to be displayed (selected by
																		// user and verified system)
	private static String selAnalysis = ""; // keeps track of analysis type selected

	private static final long serialVersionUID = 1L;
	private static MainUI instance;

	public JLabel errorLabel = new JLabel();

	private selectionFacade facade = new selectionFacade(); // ADDED BY OLENA

	// singleton for UI
	public static MainUI getInstance() throws Exception {
		if (instance == null) {
			instance = new MainUI();
		}

		return instance;

	}

	// create main UI
	private MainUI() throws Exception {
		// Set window title
		super("Country Statistics");

		// initiate selection vectors
		Vector<String> countryNames = new Vector<String>();
		Vector<String> viewsNames = new Vector<String>();
		Vector<String> methodsNames = new Vector<String>();
		Vector<String> years = new Vector<String>();

		// fill in selection vectors
		countryNames = GUIreader.readCountries();
		viewsNames = GUIreader.readViewers();
		methodsNames = GUIreader.readMethods();
		years = GUIreader.readYears();

		// update the drop-down boxes
		JComboBox<String> countriesList = new JComboBox<String>(countryNames);
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);
		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		// Set country in top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");

		// generate start and end years labels
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");

		// add to north panel
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// error message that gets displayed due to invalid selections
		errorLabel.setForeground(Color.RED);
		errorLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
		north.add(errorLabel);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		JLabel viewsLabel = new JLabel("Available Views: ");

		// analysis method
		JLabel methodLabel = new JLabel("Choose analysis method: ");

		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		addView.setActionCommand("+");
		removeView.setActionCommand("-");

		// add to south
		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts (viewers to be displayed) region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		emptyUI(west); // empty the display (blank display when initial start)

		// update GUI
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);

		// button selection actions
		selectAnalysis(methodsList);
		selectCountry(countriesList, north);
		selectStartYr(fromList);
		selectEndYr(toList);
		selectViewer(viewsList);

		addViewer(addView);
		removeViewer(removeView);
		initRecalc(recalculate, west);
	}

	/**
	 * emptyUI() method clear UI (e.g. UI is initially empty because no views added)
	 * 
	 * @param west the west panel containing the viewers
	 */
	private void emptyUI(JPanel west) {
		JPanel blank = new JPanel(new BorderLayout());
		blank.setPreferredSize(new Dimension(1200, 600));
		getContentPane().add(blank);
	}

	/**
	 * selectAnalysis() method sends the user selected analysis to the selection
	 * facade (and gets verified). An error message is displayed if selection is
	 * invalid.
	 * 
	 * @param analysis the drop down containing the different analyses
	 */
	private void selectAnalysis(JComboBox<String> analysis) {
		analysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> action = (JComboBox<String>) event.getSource();
				String currAnalysis = (String) action.getSelectedItem();

				// this part will be used to fill in the analysis for the selection
				System.out.println("selected: " + action.getSelectedItem());

				// does current analysis = previous analysis?
				if (currAnalysis != selAnalysis) {
					System.out.println("selected new analysis");
					facade.clearViewers();
					viewersList.clear(); // clear list of viewers
				}

				selAnalysis = (String) action.getSelectedItem();
				facade.setAnalysis(selAnalysis);// ADDED BY OLENA

			}

		});
	}

	/**
	 * selectViewer() method this is where the user selects a viewer to add/remove
	 * 
	 * @param viewer
	 */
	private void selectViewer(JComboBox<String> view) {
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> action = (JComboBox<String>) event.getSource();

				// this part will be used to fill in the country for the selection

				viewer = (String) action.getSelectedItem(); // update current selected viewer

			}
		});
	}

	/**
	 * addViewer() method sends the user selected viewer to be added to the
	 * selection facade (and gets verified). An error message is displayed if
	 * selection is invalid. The user must select a viewer first before adding.
	 * 
	 * @param addView selected viewer to be verified and added to list of viewers
	 */
	private void addViewer(JButton addView) {
		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String action = event.getActionCommand();
				if (action.equals("+")) {
					// user must select a viewer type before adding
					if (viewer.isEmpty()) {
						try {
							MainUI.getInstance().errorLabel.setText("Please select a viewer before adding!");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (!viewersList.contains(viewer)) {
						try {
							MainUI.getInstance().errorLabel.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // reset error message
						viewersList.add(viewer); // add selected viewer to list
						facade.setViewer(viewersList);
						System.out.println(viewersList);
					} else if (viewersList.contains(viewer))
						try {
							MainUI.getInstance().errorLabel.setText("You have already selected this viewer!");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else
						try {
							MainUI.getInstance().errorLabel.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
	}

	/**
	 * removeViewer() method sends the user selected viewer to be removed to the
	 * selection facade (and gets verified). An error message is displayed if
	 * selection is invalid. The user must select a viewer first before removing.
	 * 
	 * @param removeView selected viewer to be verified and removed from list of
	 *                   viewers
	 */
	private void removeViewer(JButton removeView) {
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String action = event.getActionCommand();
				if (action.equals("-")) {
					// user must select a viewer type before removing
					if (viewer.isEmpty()) {
						try {
							MainUI.getInstance().errorLabel.setText("Please select a viewer!");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (viewersList.contains(viewer)) {
						try {
							MainUI.getInstance().errorLabel.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // reset error message
						viewersList.remove(viewer); // remove selected viewer from list
						facade.removeViewer(viewer);
						System.out.println(viewersList);
					} else if (!viewersList.contains(viewer))
						try {
							MainUI.getInstance().errorLabel.setText("You haven't selected this viewer!");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else
						try {
							MainUI.getInstance().errorLabel.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
	}

	/**
	 * selectCountry() method sends the user selected country to selection facade
	 * (and gets verified). An error message is displayed if selection is invalid.
	 * 
	 * @param countriesList the list of countries in the country drop down
	 */
	private void selectCountry(JComboBox<String> countriesList, JPanel north) {
		countriesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> action = (JComboBox<String>) event.getSource();

				// (it's to see if user has actually selected anything)
				if (action.getSelectedIndex() == -1) {
					System.out.println(action.getItemAt(0)); // default selection
				} else {

					facade.setCountry((String) action.getSelectedItem());

				}
			}
		});
	}

	/**
	 * selectStartYr() method sends the user selected start year to selection facade
	 * (and gets verified). An error message is displayed if selection is invalid.
	 * 
	 * @param startYear the available start years in start year drop down
	 */
	private void selectStartYr(JComboBox<String> startYear) {
		startYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> action = (JComboBox<String>) event.getSource();
				String selectedYearStart = (String) action.getSelectedItem();

				// fills in start year to years start
				facade.setStartYear(Integer.parseInt((String) action.getSelectedItem()));// ADDED BY OLENA
				facade.setYearsRange();// ADDED BY OLENA

			}
		});

	}

	/**
	 * selectEndYr() method sends the user selected end year to selection facade
	 * (and gets verified). An error message is displayed if selection is invalid.
	 * 
	 * @param EndYear the available end years in end year drop down
	 */
	private void selectEndYr(JComboBox<String> EndYear) {
		EndYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> action = (JComboBox<String>) event.getSource();
				String selectedYearEnd = (String) action.getSelectedItem();

				// fills in end year to years end
				facade.setEndYear(Integer.parseInt((String) action.getSelectedItem()));// ADDED BY OLENA
				facade.setYearsRange();// ADDED BY OLENA

			}
		});
	}

	/**
	 * initRecalc() method initiates the recalculation initiates the recalculation
	 * and displays the viewers
	 * 
	 * @param recalc
	 */
	private void initRecalc(JButton recalc, final JPanel west) {
		recalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String action = event.getActionCommand();

				// need to check if selection object is filled before we can start
				// recalculations though
				if (action.equals("Recalculate") && !viewersList.isEmpty()) {
					System.out.println("initiate recalculation!");
					systemSelection selection = facade.getSystemSelection();

					// Remove previous viewers
					west.removeAll();

					// Begin analysis
					selection.setSystemPanel(west);
					boolean beginCalculation = true;
					for (int i = 0; i < facade.getAllGood().length; i++) {
						if (facade.getAllGood()[i] == false) {
							System.out.println("allBad " + i);
							beginCalculation = false;
						}
					}

					if (beginCalculation) {
						try {
							MainUI.getInstance().errorLabel.setText("");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						selection.performAnalysis();
					} else {
						try {
							MainUI.getInstance().errorLabel.setText("Can't recalculate with invalid fields selected!");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					// update UI after displaying viewers
					getContentPane().add(west, BorderLayout.WEST);
					getContentPane().revalidate();
					getContentPane().repaint();
				} else {
					try {
						MainUI.getInstance().errorLabel.setText("Please select the viewer(s) before recalculating!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {

		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);

		frame.pack();
		frame.setVisible(true);

	}
	// TODO Auto-generated method stub

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
