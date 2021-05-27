package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Textarea_Assigment {
	WebDriver driver;
	String URL = "http://demo.guru99.com/v4/" ;
	String EmailAddress, Password, UserID, LoginURL;
   // declare locator for new customer
	By CustomerNametextbox = By.name("name");
	By DOBtextbox = By.name("dob");
	By Addresstextarea = By.name("addr");
	By Citytextbox = By.name("city");
	By Statetextbox = By.name("state");
	By PINtextbox = By.name("pinno");
	By Telephonetextbox = By.name("telephoneno");
	By EmailIDtextbox = By.name("emailid");
	By Passwordtextbox = By.name("password");
	// Decelare varible for create new customer
	String CustName, DOB, Address, City, State, Pin, PhoneNum, CustomerID;
	String AddressEdit, CityEdit, StateEdit, PinEdit, EmailEdit, PhoneEdit;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		// Data for new form
		EmailAddress = "xuanbt"+generateNumber();	
		// Initialization data for create new customer
		CustName = "Bui Thi Xuan";
		DOB = "1989-07-21";
		Address = "78 Dich Vong Hau HN";
		City = "Ha Noi city";
		State = "Dong Da";
		Pin = "087123";
		PhoneNum = "0964938912";
		
		// data for edit form
		AddressEdit = "NGUYEN VAN NAM";
		CityEdit = "HO CHI MINH";
		StateEdit = "LE DUC THO";
		PinEdit = "978787";
		EmailEdit = "selenium" +  generateNumber();
		PhoneEdit = "0978090900";
	}
	
	@Test
	public void TC_01_Register() {
	LoginURL = driver.getCurrentUrl();
	driver.findElement(By.xpath("//a[text()='here']")).click();
	driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(EmailAddress);
	driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	//Get value of userID & password 
	UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	
	}

	@Test
	public void TC_02_Login() {
		driver.get(LoginURL);
		//send data to login form
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();
		
		//verify message after login successful
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
			
	}
	
	@Test
	public void TC_03_CreateNewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(CustomerNametextbox).sendKeys(CustName);
		driver.findElement(DOBtextbox).sendKeys(DOB);
		driver.findElement(Addresstextarea).sendKeys(Address);
		driver.findElement(Citytextbox).sendKeys(City);
		driver.findElement(Statetextbox).sendKeys(State);
		driver.findElement(PINtextbox).sendKeys(Pin);
		driver.findElement(Telephonetextbox).sendKeys(PhoneNum);
		driver.findElement(EmailIDtextbox).sendKeys(EmailAddress);
		driver.findElement(Passwordtextbox).sendKeys(Password);
		driver.findElement(By.name("sub")).click();
		
		//verify value after create new customer successful
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).getText(),"Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),CustName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),DOB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),Address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),PhoneNum);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),EmailAddress);
		
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_04_EditCustomer() {
		//click Edit customer link
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//verify value 
		//Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),CustName);
		//Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),Address);
		Assert.assertEquals(driver.findElement(CustomerNametextbox).getAttribute("value"), CustName);
		Assert.assertEquals(driver.findElement(DOBtextbox).getAttribute("value"), DOB);
		Assert.assertEquals(driver.findElement(Addresstextarea ).getText(), Address);
		Assert.assertEquals(driver.findElement(Citytextbox).getAttribute("value"), City);
		Assert.assertEquals(driver.findElement(Statetextbox).getAttribute("value"), State);
		Assert.assertEquals(driver.findElement(PINtextbox).getAttribute("value"), Pin);
		Assert.assertEquals(driver.findElement(Telephonetextbox).getAttribute("value"), PhoneNum);
		Assert.assertEquals(driver.findElement(EmailIDtextbox).getAttribute("value"), EmailAddress);
		//verify disable field
		Assert.assertFalse(driver.findElement(CustomerNametextbox).isEnabled());
		Assert.assertFalse(driver.findElement(DOBtextbox).isEnabled());
		
		
	   // input value for edit form
		driver.findElement(Addresstextarea).clear();
		driver.findElement(Addresstextarea).sendKeys(AddressEdit);
		
		driver.findElement(Citytextbox).clear();
		driver.findElement(Citytextbox).sendKeys(CityEdit);
		
		driver.findElement(Statetextbox).clear();
		driver.findElement(Statetextbox).sendKeys(StateEdit);
		
		driver.findElement(PINtextbox).clear();
		driver.findElement(PINtextbox).sendKeys(PinEdit);
		
		driver.findElement(Telephonetextbox).clear();
		driver.findElement(Telephonetextbox).sendKeys(PhoneEdit);
		
		driver.findElement(EmailIDtextbox).clear();
		driver.findElement(EmailIDtextbox).sendKeys(EmailEdit);
		
		driver.findElement(By.name("sub")).click();
		// verify value after edit successful
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), AddressEdit);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), AddressEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), CityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), StateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), PhoneEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), EmailEdit);	
		
		
	}

	public String generateNumber() {
		Random rand = new Random();
		return rand.nextInt((9999))+ "@mail.net";
	
	}
	public void SleepinSec (long a) {
		try {
			Thread.sleep(a * 1000);
			
		}catch (InterruptedException e ){
			e.printStackTrace();
		}
		
	}
		
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}