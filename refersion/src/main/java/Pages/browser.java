package Pages;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class browser {
	private static String prod = "https://www.rfsndev.com/";
	public static String appUrl = prod;
	//Method to create driver 
	public static WebDriver LaunchBrowser(String browserName) throws MalformedURLException {
        WebDriver driver= null;
		
        switch(browserName) {	        
		case "firefox": 
			//gecko driver necessary to run Eclipse3
		   	System.setProperty("webdriver.gecko.driver", "");
			//Set up private FF windows
		   	FirefoxOptions opts = new FirefoxOptions();
			opts.addArguments("-private");			
			//Create FF Driver
			driver = new FirefoxDriver(opts);
			break;
		
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/testjdaqa/eclipse-workspace/refersion/chromedriver");
			//set up incognito chrome windows
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			//create Chrome driver
			driver = new ChromeDriver(options);
			break;
		}
		driver.get(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
	}
}
