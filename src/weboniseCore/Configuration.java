package weboniseCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	public static String browser;
	public static String objRepo;
	public static String dataFile;
	private static String path;
	private static String configPath = "\\resources\\config.properties";
	static InputStream input = null;
 
	public static String getLocalPath() {
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

	public static void initializeSettings(){
		Properties prop = new Properties();
		getLocalPath();
		String configFilePath = path + configPath;
		System.out.println("Default path is : " + configFilePath);
		try {
			input = new FileInputStream(configFilePath);
			prop.load(input);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		// load a properties file
		System.out.println(prop.getProperty("objectRepository"));
		objRepo = prop.getProperty("objectRepository");
		System.out.println(prop.getProperty("testData"));
		dataFile = prop.getProperty("testData");
		System.out.println(prop.getProperty("browser"));
		browser = prop.getProperty("browser");
		
		//Add a better approach to read config file and update the variables
		
	}
	

}
