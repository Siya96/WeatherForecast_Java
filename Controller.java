package lab2.controller;

import lab2.XmlParser;

import java.io.IOError;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import lab2.City;
import lab2.CityModel;
import lab2.ExtractXmlFromURL;
import lab2.controller.Cache;

/**
 * 
 * @author Siyabend Revend 
 *
 */

/*
 * This class is the controller of the ModelViewController-setup and manipulates the model/("state") and eventually 
 * communicates with the GUI.
 */
public class Controller {
	
	
	private Cache cache = new Cache(10, 1);
	private XmlParser xp = new XmlParser();
	private ExtractXmlFromURL  ext = new ExtractXmlFromURL();
	
	private CityModel model;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
	}
	
	/**
	 * The constructor take a model as argument.
	 * @param model
	 */
	public Controller(CityModel model) {
		
		this.model = model;
		
	} 
	

	/**
	 * This method is the trigger-method for a number of other methods. When called upon it triggers a URL request, sets a new timeToLive,
	 * changes the state by changing the current city and lastly we save the retrieved information in cache.
	 * @param cityName
	 * @param time
	 * @param timeToLive
	 */
	public void updateTemperature(String cityName, String time, int timeToLive)  {
		
		cache.setNewTimeToLive(timeToLive);
		City c = getCity(cityName);
		model.setCurrentCity(c);
		
		if (cache.get(cityName) != null) {
			
			ext.XPathParser(c, time, (Document) cache.get(cityName));
			
			
			
			
		
			
		}
		
		else {

			

				ext.extractXmlFromApi(c, time);
				
				
				
				cache.put(c.getCityName(), ext.getDoc());
				
				

//				JOptionPane.showMessageDialog(null, "No Xml was extracted due to errors", "hej", JOptionPane.ERROR_MESSAGE);
			
	}
		model.setCurrentCity(c);
		
	
	}
	
	/**
	 * 
	 * @param cityName
	 * @return the city object when we pass in the city's String name.
	 */
	private City getCity(String cityName) {
		
		
		try {
		for (int i = 0; i < xp.getCityArr().length; i++) {
			
			
			if (xp.getCityArr()[i].getCityName() == cityName) {
				
				return xp.getCityArr()[i];
				
			}
				
			}
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		return null;
		
	}
	
	
	/**
	 * 
	 * This function where we extract the names of the cities from the cityArr and return them in a String array.
	 * @return
	 */
	public String[] getCityNames() {
		
		xp.setCityLatLon();
		
		
		String [] tempCityArr = new String[xp.getCityArr().length];
		
		for (int i = 0; i < xp.getCityArr().length; i++) {
			
			
			 
			
		
			tempCityArr[i] = xp.getCityArr()[i].getCityName();
		}
		return tempCityArr;
	
	
	
	}

}
