package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.TestNGException;

public class Topic06_Webdriver {
	WebDriver driver;
	By MyAcc1 = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By CreateAcc1 =  By.xpath("//span[text()='Create an Account']");
	String URL = "http://live.guru99.com/";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver","Browserdriver\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_VerifyURL() {
		driver.get(URL);
		driver.findElement(MyAcc1).click();	
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(CreateAcc1).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		SleepinSec(3);
	}

	@Test
	public void TC_02_VerifyTitle() {
		driver.get(URL);
		driver.findElement(MyAcc1).click();
		SleepinSec(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(CreateAcc1).click();
		SleepinSec(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void TC_03_NagativeFuction() {
		driver.get(URL);
		driver.findElement(MyAcc1).click();
		SleepinSec(3);
		driver.findElement(CreateAcc1).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		SleepinSec(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		SleepinSec(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_04_GetPageSourceCode() {
		driver.get(URL);
		driver.findElement(MyAcc1).click();
		SleepinSec(3);
		String PageSource = driver.getPageSource();
		Assert.assertTrue(PageSource.contains("Login or Create an Account"));
		driver.findElement(MyAcc1).click();
	    PageSource = driver.getPageSource();
		Assert.assertTrue(PageSource.contains("Create an Account"));
		
	}

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