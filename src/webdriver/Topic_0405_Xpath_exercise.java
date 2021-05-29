package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_0405_Xpath_exercise {
	WebDriver driver;
	String EmailAddress, Firstname, Lastname ;
	String Password;

	String Fullname ;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","Browserdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		EmailAddress = "xuanbt" + GenerateEmail();
		Firstname = "Spring" ;
		Lastname = "Bui" ;
		Password = "898989" ;
		Fullname = Firstname + " " + Lastname;
	}

	//@Test
	public void TC_01_LoginemptyUsernamePw() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		SleepinSec(3);
	}
     
	//@Test
	public void TC_02_LoginInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@es12");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		SleepinSec(3);
	}
	//@Test
	public void TC_03_LoginInvalidPw() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xuanbttes12@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		SleepinSec(3);
	}

	//@Test
	public void TC_04_LoginIncorrectEmailPw() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xuanbttes12@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
		SleepinSec(3);
	}
@Test
	public void TC_05_CreateNewAccount() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(Firstname);
		driver.findElement(By.id("lastname")).sendKeys(Lastname);
		driver.findElement(By.id("email_address")).sendKeys(EmailAddress);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.id("confirmation")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");

		//Verify data after create new account successful
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(), '"+ Fullname +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'"+ EmailAddress +"')]")).isDisplayed());
		//cach 2
		String ContextInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(ContextInfor.contains(Fullname));
		Assert.assertTrue(ContextInfor.contains(EmailAddress));
		
		//Logout 
		driver.findElement(By.cssSelector(".skip-account")).click();
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		//verify hompage after logout successful
		//Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/");

	}
@Test
public void TC_06_LogivalidUsernamePw() {
	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	driver.findElement(By.id("email")).sendKeys(EmailAddress);
	driver.findElement(By.id("pass")).sendKeys(Password);
	driver.findElement(By.xpath("//button[@title='Login']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).getText(), "MY DASHBOARD");
	String Login = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
	Assert.assertTrue(Login.contains(Fullname));
	Assert.assertEquals(Login, "Hello, " + Fullname + "!");
	String ContextInfor = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	Assert.assertTrue(ContextInfor.contains(Fullname));
	Assert.assertTrue(ContextInfor.contains(EmailAddress));

	SleepinSec(3);
}
		
	
@Test
public String GenerateEmail() {
	Random rand = new Random();
	return rand.nextInt(9999) + "@mail.net" ;
}
	
	@Test
	public void SleepinSec(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}