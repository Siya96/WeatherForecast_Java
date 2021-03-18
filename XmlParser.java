package lab2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Siyabend Revend 
 *
 */

/*
 * This class parses the Xml-document that we  import to the project.
 */
public class XmlParser {

	private City[] cityArr;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		XmlParser x1 = new XmlParser();
//		
//		x1.setCityLatLon();

		


	
	}
	
	//Constructor of the class XmlParser
	public XmlParser() {
		

		
	}
	
	
	
	  
	 /**
	  * This method parses the city name, latitude, longitude and stores them in an array "cityArr".
	  */
	public void setCityLatLon() {
		
		
		
			// Declaring an instance of type DocumentBuilderFactory which enables us
			// to define a factory API that enables applications to obtain a parser.
			DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
			
			try {
				// Declaring an object "builder"  which defines the API to obtain
				// DOM (document object model) document instances from an XML document.
				DocumentBuilder builder = factory.newDocumentBuilder();
				
				// Parsing the content of the given URI as an XML document and later on 
				// returns a document object which is stored in "document"
				Document document = builder.parse("places.xml");
				
				// This line returns a node-list of all elements with the same tag
				// of the type NodeList
				NodeList localityList = document.getElementsByTagName("locality");
				for (int i =  0; i < localityList.getLength(); i++) {
					
					// Returns an item of type Node at the specific index i
					Node nodeItem = localityList.item(i);
					
					// Checking if the type of the underlying node/object is an element-type
					if ( nodeItem.getNodeType() == Node.ELEMENT_NODE) {
						
						
						// Type-casting the variable "nodeItem" since it's declared as a "Node"-type
						// ,but that node is also an element so with the type-casting we're basically
						// telling the computer: "trust me this variable/Node is an element of type Element
						Element location = (Element) nodeItem;
						
						// Gets the name of the location (the city name)
						String city = location.getAttribute("name");
						
						
						
						// ChildNodes are the lines indented in the XML-file below the "locality"-line
						// NodeList coordinateList = location.getChildNodes();
						
							
						// New nodeList with elements containing the tag "location" so that we may extract the coordinates
						NodeList locationList = document.getElementsByTagName("location");
						
						

							
							Node nodeItem2 = locationList.item(i);
						
							if ( nodeItem2.getNodeType() == Node.ELEMENT_NODE ) {
								
								
								
								Element coordinates = (Element) nodeItem2;
								
								String latitude = coordinates.getAttribute("latitude");
								
								String longitude = coordinates.getAttribute("longitude");
								
								
								
								City newCity = new City(city, latitude, longitude);
								
								
								 
								cityArrList(newCity);
								

								
								
						
							
							
						}
						
						
							
			
				}
					
				}
				
			}
			 catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
	}
	
	/**
	 * This method works like and arrayList, whenever an object is inserted a new places i made for it.
	 * @param c
	 */
	private void  cityArrList(City c) {

		if (cityArr == null) {
			
			City[] temp = new City[1];
			temp[0] = c;
			cityArr = temp;
			
			}
		
		else {

			City[] temp = new City[cityArr.length + 1];
			for (int i = 0; i < cityArr.length; i++) {

				temp[i] = cityArr[i];

			}
			temp[temp.length - 1] = c;
			cityArr = temp;

		}
	}
	
	
	/**
	 * 
	 * @return the cityArr
	 */
	public City[] getCityArr() {

		return cityArr;
	}
		
	
}
	
