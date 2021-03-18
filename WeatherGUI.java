package lab2.gui;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import lab2.ExtractXmlFromURL;
import lab2.controller.*;
import lab2.City;
import lab2.CityModel;

/**
 * 
 * @author siyarevend
 *
 */

/*
 * This is the GUI-class, and is also the view of this program. The class WeatherGUI also implements Observer.
 */
public class WeatherGUI implements Observer{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		}
	
	
	private JLabel label;
	private Controller controller;
	private ExtractXmlFromURL extractXmlFromUrl;

	
	

	 
	 /**
	  * The GUI will observe the  observable ("CityModel" in this case), there fore we pass in the "CityModel" as a parameter and 
	  * then add its observer (this GUI --> "WeatherGUI").
	  * There also needs to be a controller to this class, therefore we connect a specific controller to the GUI.
	  * @param controller
	  * @param model
	  */
	public WeatherGUI(Controller controller, CityModel model) {
		
		
		this.controller = controller;

		model.addObserver(this);
		
	
		JFrame frame = new JFrame("WeatherForecast");
		
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		
		int frameWidth = 400;
		int frameHeight = 450;
		
		//set position 
		
		frame.setBounds(center.x - frameWidth / 2, center.y - frameHeight / 2, 700, frameHeight);
		
		
		
		
			
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
//		contentPane.setBackground(Color.black);
		
		// Creating a panel for the components which we then add to the JPanel "contentPane".
		JPanel compPanel = new JPanel();
		compPanel.setBackground(Color.BLUE);
		
		
	
		
		//Creating a JPanel for the JComboBoxes
		JPanel buttons = new JPanel();
		buttons.setBackground(Color.BLUE);
		
		
		JPanel cityBox = new JPanel();
		cityBox.setBackground(Color.BLUE);
		
		JPanel timeBox = new JPanel();
		timeBox.setBackground(Color.BLUE);
		
		JPanel timeBox2 = new JPanel();
		timeBox2.setBackground(Color.BLUE);
		
		label = new JLabel("Check today's weather!");
	
		
		//JComboBox for the city names.
		JComboBox<String> cityComboBox = new JComboBox<>(controller.getCityNames());
		
		
		
		
		String [] timeArr = new String[24];
		for (int i = 0; i <= 23; i++ ) {
			
			if( i < 10) {
				
				timeArr[i] = "0" + Integer.toString(i) + ":00";
			}
			else {
				timeArr[i] = Integer.toString(i) + ":00";
				
			}
			
			
		}
		//JComboBox for the time
		JComboBox<String> timeComboBox = new JComboBox<>(timeArr);
		
		
		
		
		
		String [] timeToLiveArr = new String[20];
		for (int i = 1; i <= 20; i++ ) {
			
			
			timeToLiveArr[i-1] = Integer.toString(i);
			
		}
		//JComboBox for the time to live
		JComboBox<String> timeToLiveBox = new JComboBox<>(timeToLiveArr);
//		timeToLiveBox.setSelectedIndex(1);
		
		
		JButton button = new JButton("Get temperature!");
		
		
		//Anonymous class "ActionListener" which we connect to a button and will, when running the program, give the button the ability
		//that is written in the method "actionPerformed".
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				

				controller.updateTemperature(cityComboBox.getSelectedItem().toString(), timeComboBox.getSelectedItem().toString(), Integer.parseInt(timeToLiveBox.getSelectedItem().toString()));

				
			}
			
			
		});
		
		
		
		
		
		
		
		
		
		
		
		button.setEnabled(true);
		
		timeBox2.add(timeToLiveBox);
		timeBox.add(timeComboBox);
		cityBox.add(cityComboBox);
		buttons.add(button);
		
		compPanel.add(label);
		compPanel.add(buttons);
		compPanel.add(cityBox);
		compPanel.add(timeBox);
		compPanel.add(timeBox2);
		
		
		
		SpringLayout springLayout = new SpringLayout();
		
		springLayout.putConstraint(SpringLayout.NORTH, compPanel, 40, SpringLayout.SOUTH, contentPane);
		
		
		
		springLayout.putConstraint(SpringLayout.WEST, cityBox, 135, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.WEST, timeBox, 145, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.WEST, timeBox2, 30, SpringLayout.EAST, timeBox);
		
		springLayout.putConstraint(SpringLayout.WEST, buttons, 120, SpringLayout.WEST, contentPane);
		
		
		
		springLayout.putConstraint(SpringLayout.SOUTH, timeBox, 40, SpringLayout.NORTH,  contentPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, timeBox2, 40, SpringLayout.NORTH,  contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, cityBox, 60, SpringLayout.NORTH, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttons, 50, SpringLayout.SOUTH, cityBox);
		
		springLayout.putConstraint(SpringLayout.WEST, label, 140, SpringLayout.WEST, contentPane);
		
		springLayout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.SOUTH, buttons);
		

	
		
		
	
		//Adding the JPanel with the components to another JPanel.
		contentPane.add(compPanel);
		
		
		
		
		compPanel.setLayout(springLayout);
		

		
		
		frame.setContentPane(contentPane);
		frame.pack();

		frame.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * We set the thing that eventually will change in our view in the method "update".
	 */
	public void update(Observable o, Object arg) {
		if(o instanceof CityModel) {
			CityModel c = (CityModel) o;
			
			//Changing the text to the city's temperature when the actionListener is called (when the button in the view is pressed) which in return will call
			//the method UpdateTemperature which will call the method "extractXmlFromApi(c, time)" which will set a new temperature for a given city and time
			//and change the currentCity in the CityModel.
			label.setText(c.getCurrentCity().getCityTemp());
		}
		
	}

}
