package Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.browser;
import Pages.refersionHome;

public class PerformTestOrder {

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
    	Assert.assertTrue(homePage.SignInMenuDisplayed());
    }
	
    @Test (priority = 1, description = "Verify Sign in page is displayed" )
    public void MerchantLogInPage() {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.ClickMerchantDashBoard();
    	Assert.assertTrue(driver.getCurrentUrl().contains("https://www.rfsndev.com/base"));
    }
    
    @Test (priority = 2, description = "Verify that the merchant is available to sign in" )
    public void SignIn() {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.SignIn(Validmail, ValidPass);
    	Assert.assertTrue(driver.getCurrentUrl().contains("https://www.rfsndev.com/base"));
    	Assert.assertEquals(MerchantName, homePage.getMerchantInfo());
    }
    @Test (priority = 3, description = "Perform Test Order")
    public void Perform_test_order() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.clickPerformTestOrder();
    	Assert.assertTrue(homePage.TingleModalDisplayed());
    	
    }
    @Test (priority = 4, description = "Get Test Link")
    public void Get_Test_Link() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.selectAffiliate();
    	Assert.assertEquals(driver.getCurrentUrl(), homePage.gotolink());
    }
}
