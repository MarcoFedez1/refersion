package Tests;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import Pages.browser;

public class refersionTest {
	static WebDriver driver;
    static String Error;
    static String URL;
    static String homeURL;
    String LoginMail = "fedezmarco2@gmail.com";
    String LoginPass = "Markito01";
    String wrongMail = "fedez@gmail.com";
    String wrongPass = "Markos";
    String changePass = "Lenovo@01";
    
    public void setup() throws MalformedURLException{
    	driver = browser.LaunchBrowser("firefox");
    	homeURL = driver.getCurrentUrl();
    }

    public void firstTest() throws MalformedURLException {
    	setup();
    	
    }
}
