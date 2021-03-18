package lab2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.time.LocalDateTime;

import lab2.XmlParser;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 
 * @author Siyabend Revend 
 *
 */

/*
 * This class send a request to a given URL which then sends a response back in form o an Xml-document.
 */
public class ExtractXmlFromURL {

	private Document doc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	}

	/*
	 * The constructor
	 */
	public ExtractXmlFromURL() {

	}

	/**
	 * This method simply parses the Xml_document o a given city during a certain time.
	 * 
	 * @param city
	 * @param time
	 * @param document 
	 * @throws XPathExpressionException
	 */
	public void XPathParser(City city, String time, Document document)  {
		
		DateTimeFormatter dtfCheck = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00");

		LocalDateTime timeNowCheck = LocalDateTime.now();

		String dateTimeCheck = dtfCheck.format(timeNowCheck).toString();

		if (Integer.parseInt(time.substring(0, 2)) >= Integer.parseInt(dateTimeCheck.substring(11, 13)) - 2 ) {
		
		try {
		//Creating an instance of XPathFactory to be able to create an XPath which we later on will be using 
		//for parsing the Xml-document
		XPathFactory xpFactory = XPathFactory.newInstance();

		XPath xpath = xpFactory.newXPath();

		
		//Retrieving the current time and date in a certain format.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00");

		LocalDateTime timeNow = LocalDateTime.now();

		String dateTime = dtf.format(timeNow).toString();


		//Building the string so that it resembles the one in the Xml-document
		dateTime = dateTime.substring(0, 10) + "T" + time + ":00Z";

		String expression = "//time[@from='" + dateTime + "' and @to='" + dateTime + "']//temperature";

		
		//Gets the expressions from the document and creates a NodeList of them
		NodeList localityList = (NodeList) xpath.evaluate(expression, document, XPathConstants.NODESET);

		for (int i = 0; i < localityList.getLength(); i++) {

			//We loop through the NodeList by taking the first node/item, get its attributes, get the attribute/item amongst them that is named
			//"value" (this represents the temperature) and lastly converts it to text content.
			String temperature = localityList.item(i).getAttributes().getNamedItem("value").getTextContent();
			
		

			//We then store the temperature to the given city that was passed as an argument to this function
			city.setCityTemp(temperature);

		

		}
		}catch (XPathExpressionException e) {
			
			e.printStackTrace();
		}
		}
		else {
			
			JOptionPane.showMessageDialog(null, "No Xml was extracted due to exceeded time, try a later time!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * This method sends a HTTP request to the given URL and by creating a DOM_builder we then parse the inputStream and parse the document.
	 * @param city
	 * @param time
	 */
	public void extractXmlFromApi(City city, String time) {

		try {

			

			String createUrl = "https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=" + city.getLatitude() + "&lon=" + city.getLongitude();

			//Creating an URL object
			URL urlObj = new URL(createUrl);

			//Opening a connection to the HTTP address
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

			
			//We initiate that we specifically want to "GET"
			connection.setRequestMethod("GET");

			connection.setRequestProperty("User-Agent", "Firefox/4.0");
			InputStream inputStream = connection.getInputStream();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(inputStream);

			doc = document;

		
			
			XPathParser(city, time, document);
			
		

			
			
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	/**
	 * 
	 * @return document we extract for each city is then stored in cache.
	 */
	public Document getDoc() {

		return doc;
	}

}
