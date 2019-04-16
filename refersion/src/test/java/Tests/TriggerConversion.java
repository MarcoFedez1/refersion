package Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.browser;
import Pages.refersionHome;

public class TriggerConversion {
	static WebDriver driver;
    static String Error;
    static String URL;
    static String homeURL;
    String Validmail = "fedez.marco1@gmail.com";
    String ValidPass = "Lenovo@01";
    String wrongMail = "fedez@gmail.com";
    String wrongPass = "Markos";
    String changePass = "Lenovo";
    String MerchantName = "Marco Fernandez";
    
    @BeforeTest
    public void setup() throws MalformedURLException{
    	driver = browser.LaunchBrowser("chrome");
    	homeURL = driver.getCurrentUrl();
    }
    

    @Test (priority = 0, description = "Verify Sign in menu is displayed" )
    public void DisplaySignInMenu() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.ClickSignIn();
    	homePage.ClickMerchantDashBoard();
    	homePage.SignIn(Validmail, ValidPass);
    	Assert.assertTrue(driver.getCurrentUrl().contains("https://www.rfsndev.com/base"));
    	Assert.assertEquals(MerchantName, homePage.getMerchantInfo());
    }
    
    @Test (priority = 1, description = "Trigger Conversion")
    public void Trigger_Conversion() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.clickConversionTrigger();
    	Assert.assertTrue(homePage.TingleModalDisplayed());
    }
    
    @Test (priority = 2, description = "Trigger Conversion Coupon type")
    public void Set_trigger_conversion_coupon() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.setAffiliateTrigger("Coupon Code", "KPLF4");
    	Assert.assertEquals(homePage.getTriggerAlert(), "Conversion Trigger Successfully created");
    }
    
    @Test (priority = 3, description = "Trigger Conversion email type")
    public void Set_trigger_conversion_email() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.clickConversionTrigger();
    	Assert.assertTrue(homePage.TingleModalDisplayed());
    	homePage.setAffiliateTrigger("Customer Email", "fedez@jdaqa.net");
    	Assert.assertEquals(homePage.getTriggerAlert(), "Conversion Trigger Successfully created");
    }
    
}
