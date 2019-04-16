package Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Aplication;
import Pages.browser;
import Pages.refersionHome;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;


public class rTest {
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

    @Test (priority = 3, description = "Verify that Affiliate Sign Up options is displayed" )
    public void AffiliateSignUp() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.clickAffiliateSignUp();
    	Assert.assertTrue(homePage.TingleModalDisplayed());
    	Assert.assertEquals(homePage.getModalTitle(), "Affiliate sign-up");
    }

    @Test (priority = 4, description = "Verify that user is available to select offer")
    public void SelectOffer() throws InterruptedException {
    	refersionHome homePage = new refersionHome(driver);
    	homePage.SelectOffer(0);
    	URL = homePage.copyRegistrationPageLink();
    	Assert.assertTrue(homePage.verifyCopiedRegLink());
    	homePage.openNewTab(URL);
    	Assert.assertEquals(driver.getCurrentUrl(), URL+ "/");
    
    }
    
    @Test (priority = 5, description = "Fill Program Application")
    public void Fill_program_application() throws InterruptedException {
    	Aplication AppliPage = new Aplication(driver);
    	AppliPage.setApplication("TAuto","Auto1","fedez.test17@gmail.com","Prueba@01","Prueba@01");
    	Assert.assertEquals(AppliPage.getalert(),"×\n" + 
    			" Success! Thank you for your registration. We are reviewing your account and will get back to you shortly.");
    	refersionHome homePage = new refersionHome(driver);
    	homePage.closeNewTabs();
    	homePage.closeModal();
   
    	
    }
}
