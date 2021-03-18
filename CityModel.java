package lab2;

import java.util.Observable;


/**
 * 
 * @author Siyabend Revend
 *
 */

/*
 * This class is the model of the ModelViewController-setup and resembles the state of the program.
 * This class also extends observable and the methods setChanged(); and notifyObservers();
 */
public class CityModel extends Observable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private City currentCity;
	 
	/**
	 * This changes the state, sets changes and notifies the observer.
	 * @param newCity
	 */
	public void setCurrentCity(City newCity) {
		currentCity = newCity;
		change();
	}
	
	/**
	 * 
	 * @return the current city.
	 */
	public City getCurrentCity() {
		return currentCity;
	}

	
	/*
	 *Calls on the methods setChanged(); and notifyObservers();
	 */
	private void change() {
		setChanged();
		//NotifyObservers calls on the update-method in the GUI
		notifyObservers();
	}

}
