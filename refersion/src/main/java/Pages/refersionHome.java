package Pages;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class refersionHome {
	WebDriver driver;
	
	public refersionHome (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (xpath = "//div[@id='navbar-right']/ul/li[2]/a[@id='topnav-signup-menu']")
	private WebElement SignIn;
	
	@FindBy (xpath = "//a[@href='https://www.rfsndev.com/base/login']")
	private WebElement MerchantDB;
	
	@FindBy (xpath = "//a[@href='https://www.rfsndev.com/affiliate/login']")
	private WebElement AffiliateDB;
	
	//
	@FindBy (xpath = "//ul[@class='dropdown-menu']")
	private WebElement SignInDd;
	
	@FindBy (xpath = "//a[@href='https://www.rfsndev.com/signup']")
	private WebElement SignUp;
	
	//Sign In ---------------------------------------------------------------------------
	
	@FindBy (id = "password")
	private WebElement passSignIn;
	
	@FindBy (id = "email")
	private WebElement emailSignIn;
	
	@FindBy (xpath = "//input[@class='btn btn-large btn-primary']")
	private WebElement logInBnt;
	
	//HomePage --------------------------------------------------------------------------

	@FindBy (xpath = "//div[@class='dropdown-toggle']")
	private WebElement MerchantInfo;
	
	@FindBy (xpath = "//a[@data-modal-url='https://www.rfsndev.com/ajax/affiliate_registration_links']")
	private WebElement AffiliateSignUp;
	
	@FindBy (xpath = "//a[@data-modal-url='https://www.rfsndev.com/ajax/new_test_order/v2']")
	private WebElement PerformTestOrder;
	
	@FindBy (xpath = "//a[@data-modal-url='https://www.rfsndev.com/ajax/new_custom_link_v2']")
	private WebElement GenerateReferalLink;
	
	@FindBy (css = "#nav-main > ul > li.active > div > ul > li:nth-child(4) > a")
	private WebElement addConversiontrigger;
	
	@FindBy (xpath = "//body[@class='tingle-enabled']")
	private WebElement tingleModal;
	
	
	
	public boolean SignInMenuDisplayed() throws InterruptedException {
		Thread.sleep(500);
		if (SignInDd.isEnabled()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void ClickSignIn() {
		SignIn.click();
	}
	
	public void ClickMerchantDashBoard() {
		MerchantDB.click();
	}
	
	public void clickAffiliateDashBoard() {
		AffiliateDB.click();
	}
	
	public void SignIn(String email, String pass) {
		emailSignIn.clear();
		emailSignIn.sendKeys(email);
		passSignIn.clear();
		passSignIn.sendKeys(pass);
		logInBnt.click();
	}
	
	public String getMerchantInfo() {
		try {
			driver.findElement(By.className("dropdown-toggle")).getAttribute("innerText");
			return MerchantInfo.getAttribute("innerText");
		} catch (NoSuchElementException e) {
			System.out.println("Merchant Info is no present at this DOM " + driver.getCurrentUrl());
			return null;
		}
	}
	public void clickAffiliateSignUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", AffiliateSignUp);
	}
	
	public void clickTestOrder() {
		PerformTestOrder.click();
	}
	
	public void clickGenerateReferral() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", GenerateReferalLink);
	}
	
	public void clickConversionTrigger() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", addConversiontrigger);
	}
	
	public boolean TingleModalDisplayed() throws InterruptedException {
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		boolean aux = false;
		try {
			driver.findElement(By.className("tingle-enabled"));
			aux = true;
		} catch (NoSuchElementException e) {
			System.out.println("The element is no present at actual DOM " + driver.getCurrentUrl());
		}
		if (aux) {
			return true;
		}else {
			System.out.println("The tingle modal is no displeyed");
			return false;
		}
		
	}
	
	public String getModalTitle() throws InterruptedException {
		if (TingleModalDisplayed()) {
			return tingleModal.findElement(By.xpath("//h2[@style='margin-bottom:15px;']")).getAttribute("innerText");
		}else {
			return null;
		}
	}
	
	public void SelectOffer(int indexOffer) throws InterruptedException {
		if (TingleModalDisplayed()) {
			WebElement offer = tingleModal.findElement(By.id("offer_registration"));
			Select S_offer = new Select(offer);
			S_offer.selectByIndex(indexOffer);
		}else {
			System.out.println("The element is no present at actual DOM " + driver.getCurrentUrl());
		}
	}
	
	public void clickPerformTestOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", PerformTestOrder);
	
	}
	
	public String copyRegistrationPageLink() {
		WebElement RegPageLink = tingleModal.findElement(By.id("registration_page_link"));
		WebElement CopyButton = tingleModal.findElement(By.xpath("//button[@class='btn btn-link btn-copy']"));
		CopyButton.click();
		return RegPageLink.getAttribute("value");
	}
	
	public boolean verifyCopiedRegLink() throws InterruptedException {
		Thread.sleep(500);
		String bText = tingleModal.findElement(By.xpath("//button[@class='btn btn-link btn-copy']")).getAttribute("innerText");
		if (bText.equals("Copied!")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void openNewTab(String URL) throws InterruptedException{
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.open(arguments[0],'_blank')", URL);
		SwitchWinTabs();
	}
	
	
	public void closeModal(){
		tingleModal.findElement(By.className("tingle-modal__close")).click();
	}
	
	public void SwitchWinTabs() throws InterruptedException {
		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();
	    // This will return the number of windows opened by Webdriver and will return Set of St//rings
	 	Set<String>s1=driver.getWindowHandles();
	 	// Now we will iterate using Iterator
	 	Iterator<String> I1= s1.iterator();
	 	while(I1.hasNext())
	 	{
	 		
	 		String child_window=I1.next();
	   		// Here we will compare if parent window is not equal to child window then we            will close
	 		if(!parent.equals(child_window))	  {
	 			driver.switchTo().window(child_window);
	 		}
	 	}
	}
	
	public void closeNewTabs() {
		  // It will return the parent window name as a String
		String parent=driver.getWindowHandle();
	    // This will return the number of windows opened by Webdriver and will return Set of St//rings
	 	Set<String>s1=driver.getWindowHandles();
	 	// Now we will iterate using Iterator
	 	Iterator<String> I1= s1.iterator();
	 	while(I1.hasNext())
	 	{
	 		String child_window=I1.next();
	   		if(!parent.equals(child_window))	  {
	 			driver.switchTo().window(parent).close();
	 			driver.switchTo().window(child_window);
	 		}
	 	}
	}
	public void selectAffiliate() {
		WebElement sel= tingleModal.findElement(By.id("test_affiliate"));
		Select saffiliate=new Select(sel);
		saffiliate.selectByIndex(2);
		WebElement Getlink = tingleModal.findElement(By.id("get_test_link"));
		Getlink.click();
	}
	public String gotolink() {
		WebElement link = tingleModal.findElement(By.xpath("//a[@href='http://marco-first-test.myshopify.com?rfsn=37556426.ff27&rf_test=1']"));
		String linktxt=link.getAttribute("href");
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", link);
		return linktxt;
	}
	public void setAffiliateReferral() {
		WebElement sel2= tingleModal.findElement(By.id("affiliate"));
		Select saffiliate=new Select(sel2);
		saffiliate.selectByIndex(5);
		WebElement Getlink = tingleModal.findElement(By.id("get_custom_link"));
		Getlink.click();
	}
	
	public String copyReferalLink() {
		WebElement RegPageLink = tingleModal.findElement(By.xpath("//input[@class='input-xlarge bold']"));
		WebElement CopyButton = tingleModal.findElement(By.id("copy-custom"));
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("arguments[0].click()", CopyButton);
		return RegPageLink.getAttribute("value");
	}
	//TriggerType = Coupon Code or Customer Email
	public void setAffiliateTrigger(String TriggerType, String Trigger) {
		if (TriggerType.equals("Coupon Code")||TriggerType.equals("Customer Email")) {
			WebElement affi= tingleModal.findElement(By.id("affiliate"));
			Select saffiliate=new Select(affi); 
			saffiliate.selectByIndex(2);
			WebElement type = tingleModal.findElement(By.id("type"));
			Select Type = new Select(type);
			switch (TriggerType) { 
			case"Coupon Code":
				Type.selectByIndex(0);
				break;
			case"Customer Email":
				Type.selectByIndex(1);
				break;
			}
			WebElement trigger = tingleModal.findElement(By.id("trigger"));
			trigger.sendKeys(Trigger);
			WebElement AddTrigger = tingleModal.findElement(By.id("add_trigger"));
			JavascriptExecutor js = (JavascriptExecutor) driver;  
			js.executeScript("arguments[0].click()", AddTrigger);
		}else {
			System.out.println("Trigger type must be 'Coupon Code' or 'Customer Email'");
		}
	}
	public String getTriggerAlert() {
		WebElement alert = tingleModal.findElement(By.id("notie-alert-text"));
		return alert.getAttribute("innerText");
	}
	
}