package weboniseCore;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import weboniseCore.verification.Verification;


public class WeboAutomation {

	
	public static WebDriver driver;
	By we = null;
	String objectLocator;
	String locatorType;
	String locatorValue;
	private static DateFormat dateFormat;
    private static Date date;
	public static ObjectRepository objRepo = new ObjectRepository();
	static TestData testdata = new TestData();
	
	public static Actions actions = new Actions();
	public static Verification verify = new Verification();
	
	
	private static void initializeLocalBrowser(String type) throws Exception {
        if (type == null) {
            type = "phantomJs";
        }
        else if (type.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (type.equalsIgnoreCase("chrome")) {
        	System.setProperty("webdriver.chrome.driver", "D:\\Software\\Automation\\chromedriver_win32\\chromedriver.exe");
        	driver = new ChromeDriver();
        }else if (type.equalsIgnoreCase("phatomJs")){
        	 DesiredCapabilities DesireCaps = new DesiredCapabilities();
             DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\Software\\Automation\\browserEXE\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
             driver = new PhantomJSDriver(DesireCaps);
        }
        actions.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(30000, TimeUnit.MILLISECONDS);
    }
	
	@BeforeMethod(alwaysRun = true)
    public static void setUp(Method method) throws Exception {
    //public static void setUp(String methodName) throws Exception {
    	
    	System.out.println("Inside SetUp function");
    	
        if (driver == null) {
        	Configuration.initializeSettings();
        	objRepo.initialize();
        	testdata.initialize();
        	initializeLocalBrowser(Configuration.browser);
        	
            
        } else {
            System.out.println("Driver already initialized");
        }
        System.out.println("Method Name is : "+ method.getName());
        objRepo.setTCNode(method.getName());
        testdata.setTCNode(method.getName());
        
    }
    
    
    @AfterMethod
    public static void TearDown() throws Exception {
    	//System.out.println("Inside TearDown function");
    	System.out.println("Closing browser");
    	driver.close();
    	System.out.println("Browser closed");
    	System.out.println("Driver set to null");
    	driver = null;
    }
    

    public By getObjID(String objName,int n){
		
		return objRepo.getObjID(objName, n);
    }
    
	public By getObjID(String objName){
		return objRepo.getObjID(objName);
	}
	
	public void updateTCData(Integer iteration){
		testdata.updateTCData(iteration);
	}
	
	public String getValue(String varName){
		return testdata.getValue(varName);
	}
	
	/*public int getAttributesTittle(String byObj, String color){
		return actions.getAttributesTittle(byObj, color);
	}*/
	
	@DataProvider(name = "xml")	public static Object[][] getTestData(Method method) {
		try {
			setUp(method);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("in WeboAutomation.GetTCData");
	      //return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
		  return testdata.getTCData();
		
	   }
	
	public static String dateFormat() {
        dateFormat = new SimpleDateFormat("dd_HH_mm_ss");
        date = new Date();
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
	
	
//	
//	@Test 
//	@Parameters("myName")
//	private void login(String myName){
//		System.out.println("Parameterized value is : " + myName);
//	}
//	
	  
//	   @BeforeMethod
//	   public void initialize() {
//	      primeNumberChecker = new PrimeNumberChecker();
//	   }
		
//	   @DataProvider(name = "test1")
//	   public static Object[][] primeNumbers() {
//	       return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
//	   }
//
//	   // This test will run 4 times since we have 5 parameters defined
//	   @Test(dataProvider = "test1")
//	   public void testPrimeNumberChecker(Integer inputNumber, Boolean expectedResult) {
//		   
//		   System.out.println(inputNumber + " " + expectedResult);
//	      
//	   }
	
	
		
	

}
