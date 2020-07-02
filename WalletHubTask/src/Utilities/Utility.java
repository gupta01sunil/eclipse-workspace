package Utilities;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.concurrent.TimeUnit; 

public class Utility {
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
	
	public static String getData(Element e,String tagName)
	{
		return e.getElementsByTagName(tagName).item(0).getTextContent();
	}
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

}
