package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_0405_Xpath_exercise {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_LoginemptyUsernamePw() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		SleepinSec(3);
	}
     
	@Test
	public void TC_02_LoginInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@es12");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		SleepinSec(3);
	}
	@Test
	public void TC_03_LoginInvalidPw() {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xuanbttes12@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		SleepinSec(3);
	}

	@Test
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
		driver.findElement(By.id("firstname")).sendKeys("spring");
		driver.findElement(By.id("lastname")).sendKeys("Bui");
		driver.findElement(By.id("email_address")).sendKeys("xuanbt4@gmail.com");
		driver.findElement(By.id("password")).sendKeys("1234567");
		driver.findElement(By.id("confirmation")).sendKeys("1234567");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText(), "Thank you for registering with Main Website Store.");
		SleepinSec(3);
		
		driver.findElement(By.xpath("//header[@id ='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		driver.navigate().back();
		//Assert.assertEquals(driver.navigate().back(), "http://live.demoguru99.com/index.php/");
	}
@Test
public void TC_06_LogivalidUsernamePw() {
	driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
	driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xuanbt4@gmail.com");
	driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234567");
	driver.findElement(By.xpath("//button[@title='Login']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).getText(), "MY DASHBOARD");

	SleepinSec(3);
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