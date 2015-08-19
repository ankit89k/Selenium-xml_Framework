package weboniseCore;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestData {
	
	
		private static Element root = null;
		private static NodeList tcNode = null;
		
		private static HashMap<String, String> hmap = new HashMap<String, String>();
		
		public void initialize(){
			System.out.println("Initializing Test data");
			File file = new File(Configuration.dataFile);
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document doc;
				doc = db.parse(file);
				doc.getDocumentElement().normalize();
				root = doc.getDocumentElement();
				//System.out.println(" Root node is :  " + root.getNodeName());
			}
			catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void setTCNode(String tcName){
			  
			  System.out.println("TC NAME IS " + tcName);
			  					
			  tcNode = root.getElementsByTagName(tcName);
			  System.out.println("TC Node is "+ tcNode.toString());
		}
		
		
		public Object[][] getTCData(){
			
			//System.out.println("in GetTCData");
			
			Integer num = tcNode.item(0).getChildNodes().item(1).getChildNodes().getLength();
			
			num = (num/2);
			//System.out.println("Number is : " + num);
			
			Object[][] ob =  new Object[num][2];
			
			for(int i=0;i<num;i++){
				ob[i][0]=i;
				ob[i][1]=true;
			}
			
			return ob;
			
			//return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
			
		}
		
		public void updateTCData(int iteration){
			  
			  System.out.println("Iteration : " + iteration);
			  
			  hmap.clear();
			  
			  //System.out.println("tcNode name : " + tcNode.item(0).getNodeName());
			  
			  NodeList datasetList = tcNode.item(0).getChildNodes().item(1).getChildNodes();
			  
			  //for (int j = 1; j < datasetList.getLength(); j = j + 2) {
				
			  	  Node datasetNode = datasetList.item((iteration*2)+1);
				  if (datasetNode.getNodeType() == Node.ELEMENT_NODE) {
						Element ele = (Element) datasetNode;
						for (int k = 1; k < ele.getChildNodes().getLength(); k = k + 2) {
							String key = ele.getChildNodes().item(k).getNodeName();
							//System.out.println("Key is : " + key);
							String value = ele.getChildNodes().item(k).getTextContent();
							//System.out.println("Value is : " + value);

							hmap.put(key, value);

						}
					}
				//}
			  
		}
		
		public String getValue(String varName){
			  if(hmap.containsKey(varName)){
				  return hmap.get(varName);
			  }
			  System.out.println("Return Value is - Invalid tcName or var name");
			  return "Invalid tcName or var name";
		}

}
