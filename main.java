package lab2.main;

import lab2.gui.WeatherGUI;
import lab2.CityModel;
import lab2.ExtractXmlFromURL;
import lab2.XmlParser;
import lab2.controller.Controller;

/**
 * 
 * @author Siyabend Revend
 *
 */

/*
 * This is the main-class
 */
public class main {

	/**
	 * The main-class where the model, controller and GUI is created.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		CityModel model = new CityModel();
		//ExtractXmlFromURL newExtraction = new ExtractXmlFromURL();
		Controller controller = new Controller(model);
		WeatherGUI newGUI = new WeatherGUI(controller, model);
		
	}

}
