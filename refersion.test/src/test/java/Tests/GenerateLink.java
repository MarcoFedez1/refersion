package Tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.browser;
import Pages.refersionHome;

public class GenerateLink {
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
    @Test (priority = 1, description = "Generate Referral Link")
    public void Generate_Referral_Link() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.clickGenerateReferral();
    	Assert.assertTrue(homePage.TingleModalDisplayed());
    }
    @Test (priority = 2, description = "Select Affiliate2")
    public void Select_affiliate2() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.setAffiliateReferral();
    	URL = homePage.copyReferalLink();
    	homePage.openNewTab(URL);
    	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    	Assert.assertEquals(driver.getCurrentUrl(), URL);
    }

}
