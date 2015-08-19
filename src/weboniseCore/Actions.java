package weboniseCore;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
//import com.relevantcodes.extentreports.LogStatus;


public class Actions {

	static WebDriver driver;
	public static ArrayList<String> dropDownList;
	public static WebElement ulContainer;
	String[] errorArray;

	ObjectRepository objectrepo = new ObjectRepository();

	protected void setDriver(WebDriver mydriver) {

		driver = mydriver;
	}

	public void launchBrowser(String url) {
		driver.get(url);
		System.out.println("Launched browser with URL " + url);
		driver.manage().window().maximize();
		System.out.println("Window maximized");
	}
	
	public void selectDate(By byObj){
		try{
			//driver.findElement(byObj).click();
			
			List<WebElement> we =  driver.findElements(byObj);
			
			
			System.out.println("Size of array is " + we.size());
			for(int i=0;i< we.size();i++){
				System.out.println("Text of "+ i +" is " + we.get(i).getText());
				System.out.println("Enabled status of "+ i +" is " + we.get(i).isEnabled());
				System.out.println("Enabled status of "+ i +" is " + we.get(i).getAttribute("class"));
			}
			we.get(0).click();		
					
			//System.out.println("Clicked on " + byObj.toString());
			//WeboAutomation.extent.log(LogStatus.INFO,"Clicked on " + byObj.toString());
			Reporter.log("Clicked on " + byObj.toString(),true);
		}
		catch(Exception e){
			//WeboAutomation.extent.log(LogStatus.ERROR, "Clicked on " + byObj.toString());
		//	WeboAutomation.extent.log(LogStatus.INFO, "<pre>"+ e.toString() +"</pre>");
			
			takeSS();
			e.printStackTrace();
		}
	}

	public void click(By byObj) {
		try {
			driver.findElement(byObj).click();
			Thread.sleep(2000);
			System.out.println("Clicked on " + byObj.toString());
		} catch (Exception e) {
			takeSS();
			e.printStackTrace();
		}
	}

	public void sendKeys(By byObj, String textToSet) {
		try {
			driver.findElement(byObj).sendKeys(textToSet);
			System.out.println("Setting text \"" + textToSet + "\" on "
					+ byObj.toString());
		} catch (Exception e) {
			takeSS();
			e.printStackTrace();
		}
	}

	public String getText(By byObj) {
		return driver.findElement(byObj).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	private void takeSS() {
		System.out.println("Take screenshot");

	}

	public int getDropDownItem(By byObj, String locator) {
		try {
			dropDownList = new ArrayList<String>();
			ulContainer = driver.findElement(byObj);
			List<WebElement> dropDownvalue = ulContainer.findElements(By
					.tagName(locator));
			for (WebElement firstElement : dropDownvalue)
				dropDownList.add(firstElement.getText());
			// System.out.println(dropDownList);
		} catch (Exception e) {
			takeSS();
			e.printStackTrace();
		}
		return dropDownList.size();
	}

	public void select(By byObj, String textToSet) {
		try {
			WebElement dropDown = driver.findElement(byObj);
			Select element = new Select(dropDown);
			element.selectByVisibleText("textToSet");
			System.out.println(textToSet + " text selected");
		} catch (Exception e) {
			takeSS();
			e.printStackTrace();
		}
	}

	public boolean verifyErrorMessage(By byObj) {
		boolean a = false;
		int i;
		System.out.println("Inside VerifyErrorMessage : " + byObj.toString());
		List<WebElement> errorList = driver.findElements(byObj);
		try {
			for (i = 0; i < errorList.size(); i++) {
				errorArray[i] = errorList.get(i).getText();
				System.out.println(errorArray[i] + "Printed");
			}
			if (errorArray == null) {
				a = true;
				System.out.println("a value " + a);
			}
		} catch (Exception e) {
			takeSS();
			e.printStackTrace();
		}
		return a;
	}
	
	
}
