package webdriver;

import static org.junit.Assert.assertArrayEquals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic07_DefaultDropdownlist {
	WebDriver driver;
	Select select;
	JavascriptExecutor JExecutor;
	String firstName, lastName, Day, Month, Year, Email, company;
	 @BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		JExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		firstName = "Xuan";
		lastName = "Bui";
		Day = "21";
		Month = "July";
		Year = "1989";
		Email = "xuanbt" + generateNumber();
		company = "Vpbank Company";
	}
    @Test
	public void TC_01_RegisterNopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector(".ico-register")).click();
	   //send data
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText(Day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),Day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
	    select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText(Month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),Month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText(Year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),Year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys("1234567");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("1234567");
		
		
		clickByJS(By.id("register-button"));
       // Verify message 
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		//click My account
		//clickByJS(By.cssSelector(".ico-account"));
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		//verify data in My account
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), Email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"), Day);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),Month);
		
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), Year);
		SleepinSec(3);
	}
    @Test
	 public void TC_02_ValidateValueDropdownlist() {
		driver.get("https://demo.nopcommerce.com/register");
		SleepinSec(5);
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		
		 List<WebElement> options = select.getOptions();
		 List<String> expectValue = new ArrayList<String>();
		
		 for (WebElement item : options) {
		   expectValue.add(item.getText());
	    	System.out.println(item.getText());
	    }
		 
    }
	 @Test
	public void TC_03_AutomationFC() {
		
		 driver.get("https://automationfc.github.io/basic-form/index.html");
		   select = new Select(driver.findElement(By.cssSelector("#job1")));
		   Assert.assertFalse(select.isMultiple());
		   
		   select.selectByVisibleText("Mobile Testing");
		   Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		   select.selectByValue("manual");
		   Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		   select.selectByIndex(9);
		   Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		   Assert.assertEquals (select.getOptions().size(),10);
		   
		   select = new Select(driver.findElement(By.cssSelector("#job2")));
		   
		   String Job2[] = {"Automation","Mobile","Desktop"};
		   	 	for (String value: Job2) {
		   	 		select.selectByVisibleText(value);
		   	 	
		   	 	}
		   	 List<WebElement> selectoption = select.getAllSelectedOptions();
		   	 
		   	 Assert.assertEquals(selectoption.size(), 3);
		   	 ArrayList<String> Actualtvalue = new ArrayList <String>();
		   	 for (WebElement option : selectoption) {
		   	 	Actualtvalue.add(option.getText());
		   	 	System.out.println(Actualtvalue);
		   	 	}
		   	 	List<String> Expectvalue = Arrays.asList(Job2);
		   	 	Assert.assertEquals(Actualtvalue, Expectvalue);
		 }
	
		public String generateNumber() {
			Random random = new Random();
			return random.nextInt(9999) + "@mail.vn";
		}
		public void clickByJS(By by) {
			JExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
		public void SleepinSec(long sec) {
			try {
				Thread.sleep(sec * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}