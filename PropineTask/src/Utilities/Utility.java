package Utilities;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.io.File; 
import java.io.IOException; 
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Attr;

public class Utility {
	//Gets the Node data from a xml file.
	public static Node getNodeData(String testScenario)
	{
		Node testdata = null;
		try
		{
		  //creating a constructor of file class and parsing an XML file  
		  File fileName = new File(".//TestData//testData.xml");
	      //an instance of factory that gives a document builder  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  //an instance of builder to parse the specified xml file  
		  DocumentBuilder db = dbf.newDocumentBuilder();  
		  Document doc = db.parse(fileName);		
		  doc.getDocumentElement().normalize();  
		  NodeList nodeList = doc.getElementsByTagName("TestCase");
		  for (int itr = 0; itr < nodeList.getLength(); itr++)
		  {
			 testdata = nodeList.item(itr);
			 if (testdata.getNodeType() == Node.ELEMENT_NODE)   
			 {
			    if(testScenario.equals(testdata.getAttributes().getNamedItem("Title").getNodeValue())) break;
			 }
		  }
		
		 }
		 catch(Exception ex)
		 {
			throw new RuntimeException(ex.getMessage());
		 }
		 System.out.println("Test data got extracted.");
		 return testdata;
	}
	
	//Gets the Data from a element.
	public static String getData(Element e,String tagName)
	{
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	//Waits for the Page Load.
	public static void waitForPageLoad(WebDriver browser,long waitTimer)
	{
		JavascriptExecutor js = ((JavascriptExecutor)browser);
		WebDriverWait wait = new WebDriverWait(browser, 1000);
		String readyState = "";
		for(int i =0;i<5;i++)
		{
			browser.manage().timeouts().implicitlyWait(waitTimer, TimeUnit.MILLISECONDS);
			js.executeScript("return document.readyState",readyState);
			if(readyState == "complete") break;
			
		}		
	}
	
	//Formats the Date with the specified formats.
	public static String DateFormatter(String date,String dateFormat)
	{		
		String formattedDate ="";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			formattedDate = formatter.format(sDate);
		}
		catch(Exception ex)
		{
			Assert.fail("Failed due to conversion failure:"+ex.getMessage());
		}
    	return 	formattedDate;
	}
	
	//Converts the string file to xml document file.
	public static Document toXmlDocument(String str)
	{	
		Document document = null;
		try
		{
		   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	       DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	       document = docBuilder.parse(new InputSource(new StringReader(str)));
		}
		catch(Exception ex)
		{
			System.out.println("XML Document parsing from string to Document got failed with the following message:"+ex.getMessage());
		}
		return document;
	}
	
	//Creates the xml document file.
	public static Document CreateXMLDocument(String scenario)
	{
		Document document = null;
		 try
			{
			     
			     DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		         document = documentBuilder.newDocument();
		         // root element
				 Element root = document.createElement("TestResult");
				 document.appendChild(root);
				 // employee element
		         Element testScenario = document.createElement(scenario);
		         Attr attr = document.createAttribute("id");
		         attr.setValue("scenario");
		         testScenario.setAttributeNode(attr);    
		         root.appendChild(testScenario);
		         
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		 return document;
	}
	
	//Adds the testrun information to the xml file.
	public static Document  AddTestResults(Document document,String scenario,String dateFormat,String actualResult,String expectedResult,String testResult)
	{
		try {
			  Element testScenario = (Element)document.getElementsByTagName(scenario).item(0);
			  Element etestRun = document.createElement("TestRun");			  
			         
			  Element edateFormat = document.createElement("DateFormat");
			  edateFormat.appendChild(document.createTextNode(dateFormat));			
			  etestRun.appendChild(edateFormat);
					  
			  Element eactualResult = document.createElement("ActualResult");
			  eactualResult.appendChild(document.createTextNode(actualResult));
			  etestRun.appendChild(eactualResult);
					
			  Element eexpectedResult = document.createElement("ExpectedResult");
			  eexpectedResult.appendChild(document.createTextNode(expectedResult));
			  etestRun.appendChild(eexpectedResult);
					
			  Element etestResult = document.createElement("TestResult");
			  etestResult.appendChild(document.createTextNode(testResult));
			  etestRun.appendChild(etestResult);	
			  
			  testScenario.appendChild(etestRun);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}		
		return document;
	}
	
    //Adds the test run result to the document.
	public static Document AddTestRunResult(Document document,String testRunResult)
	{
		try
		{
		  Element testScenario = (Element)document.getElementsByTagName("TestResult").item(0);
		  Element etestRunResult = document.createElement("TestRunResult");
		  etestRunResult.appendChild(document.createTextNode(testRunResult));		
		  testScenario.appendChild(etestRunResult);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	
	//Saves the XML document with the formatted specified file name.
	public static void SaveXMLDocument(String scenario,Document document)
	{
		try
		{
			 // Get current date time
			 LocalDateTime currentDateTime = LocalDateTime.now();
			 // Inbuilt format
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");			
			 // Format LocalDateTime
			 String formattedDateTime = currentDateTime.format(dateFormatter);
		 
		     String testReportDirectory = System.getProperty("user.dir");
		     String xmlFilePath = testReportDirectory+"\\TestReport\\"+scenario+formattedDateTime+".xml";
			 
	         // create the xml file
	         //transform the DOM Object to an XML File
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource domSource = new DOMSource(document);
	         StreamResult streamResult = new StreamResult(new File(xmlFilePath));
			 				 
			 transformer.transform(domSource, streamResult);
	         System.out.println("Done creating XML File");
			
		} catch (TransformerException tfe) {
	        tfe.printStackTrace();
	    }	
	}
}
