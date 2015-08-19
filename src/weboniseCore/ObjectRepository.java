package weboniseCore;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ObjectRepository {

	private static Element root = null;
	private static NodeList tcNode = null;
	By we = null;
	public String[] tittleColor = new String[20];
	int colorPalet;
	String objectLocator = null;
	String locatorType = null;
	String locatorValue = null;

	public void initialize() {
		System.out.println("Initializing object repo");
		File file = new File(Configuration.objRepo);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc;
			doc = db.parse(file);
			doc.getDocumentElement().normalize();
			root = doc.getDocumentElement();
			System.out.println(" Root node is :  " + root.getNodeName());
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public By getObjID(String objName){
		
		//return objRepo.getObjID(objName);
		
		 objectLocator = getObjIDString(objName);
		//System.out.println("Object locator is - " + objectLocator);
		
		 locatorType = objectLocator.split("=")[0];
		 locatorValue = objectLocator.split("=")[1];
		
		switch(locatorType.toLowerCase()){
			case "id" : //System.out.println("Identifier type is ID");
						we =  By.id(locatorValue);
						break;
			case "name" : //System.out.println("Identifier type is name");
						we =  By.name(locatorValue);
						break;
			case "class" : //System.out.println("Identifier type is class");
						we =  By.className(locatorValue);
						break;
						
			case "linktext" : //System.out.println("Identifier type is linktext");
						we = By.linkText(locatorValue);
						break;
						
			case "xpath" : //System.out.println("Identifier type is xpath");
						//System.out.println(" Index of = is " + objectLocator.indexOf('='));
						locatorValue = objectLocator.substring(objectLocator.indexOf('=')+1);
						we = By.xpath(locatorValue);
						break;
			case "cssselector" : //System.out.println("Identifier type is cssSelector");
						we = By.cssSelector(locatorValue);
						break;
			default : System.out.println("Unknow type of identifier");		
		}
		
		return we;
		
	}
	
public By getObjID(String objName, int num){
		 String obj = getObjIDString(objName);
		 String objectLocator =obj.replace("${num}",""+Integer.toString(num));
		//System.out.println("Object locator is - " + objectLocator);
		 locatorValue = objectLocator.substring(objectLocator.indexOf('=')+1);
		 we = By.xpath(locatorValue);
		 return we;	
	}
	
	public void setTCNode(String tcName) {
		// System.out.println("TC NAME IS " + tcName);
		tcNode = root.getElementsByTagName(tcName);
		// System.out.println("TC Node is "+ tcNode.toString());
	}

	public String getObjIDString(String objName) {
		// System.out.println("VAR NAME IS " + objName);
		NodeList varList = tcNode.item(0).getChildNodes();

		for (int j = 0; j < varList.getLength(); j++) {

			Node varNode = varList.item(j);
			if (varNode.getNodeName() != "#text") {
				if (varNode.getNodeName().equals(objName)) {
					// System.out.println("Return Value is - " +
					// varNode.getTextContent());
					return varNode.getTextContent();
				}

			}
		}
		System.out.println("Return Value is - Invalid tcName or var name");
		return "Invalid tcName or var name";
	}
	
	/*public int getAttributesTittle(String byObj, String color){
		//String objstring = objectrepo.getObjIDString(byObj);
		tittleColor[0]=null;
		int colorIndex=0;
		we = getObjID(byObj);
		for (int i=1;i<=(we);i++){
			tittleColor[i] =  ((WebElement) we).getAttribute("title").toString();
		}
		for (int i=1;i<=tittleColor.length;i++){
			if(tittleColor[i].equalsIgnoreCase(color)){
				colorIndex = i ;
			}
		}
		return colorIndex;
	}*/

	
	


}
