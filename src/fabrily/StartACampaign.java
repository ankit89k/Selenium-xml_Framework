package fabrily;

import org.testng.annotations.Test;

import weboniseCore.WeboAutomation;

public class StartACampaign extends WeboAutomation {

	@Test(dataProvider = "xml")
	public void startCampaign(Integer iteration, Boolean expectedResult)throws InterruptedException {
		updateTCData(iteration);
			actions.click(getObjID(""));
			actions.sendKeys(getObjID(""),getValue(""));
	}
	

	}
	
}