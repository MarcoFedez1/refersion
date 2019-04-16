package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Aplication {
	WebDriver driver;
	
	public Aplication (WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
	
	@FindBy (id = "first_name")
	private WebElement fname;
	
	@FindBy (id = "last_name")
	private WebElement laname;
	
	@FindBy (id = "email")
	private WebElement email;
	
	@FindBy (id = "password")
	private WebElement password;
	
	@FindBy (id = "password2")
	private WebElement password2;
	
	@FindBy (xpath = "//input[@type='submit']")
	private WebElement apply;
	
	@FindBy (xpath = "//div[@class='alert alert-success']")
	private WebElement alert;
	
	
	public void setApplication(String first, String last,  String Email, String pass, String confpass)
	{
		  fname.clear();
		  fname.sendKeys(first);
		  laname.clear();
		  laname.sendKeys(last);
		  email.clear();
		  email.sendKeys(Email);
		  password.clear();
		  password.sendKeys(pass);
		  password2.clear();
		  password2.sendKeys(confpass);
		  apply.click();
	}
	public String getalert(){
		return alert.getAttribute("innerText");
	}
}
