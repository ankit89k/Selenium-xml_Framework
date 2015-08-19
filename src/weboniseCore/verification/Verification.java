package weboniseCore.verification;

import org.openqa.selenium.By;

import weboniseCore.Actions;

public class Verification {
	
	Actions actions = new Actions();
	
	public boolean checkString(By byObj, String textToCheck){
		if(textToCheck.equals(actions.getText(byObj))){
		System.out.println(textToCheck +" String Verified");
			return true;
		}
		return false;
	}

	
	public boolean checkTitle(String textToCheck){
		System.out.println("Title is : " + actions.getTitle());
		if(textToCheck.equals(actions.getTitle())){
			System.out.println(textToCheck + " Tittle Verified");
			return true;
		}
			
		return false;
	}
}
