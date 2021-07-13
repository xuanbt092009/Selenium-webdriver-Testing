package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic11_HandlePopup {
	WebDriver driver;
	Alert alert;
	Actions action;
	JavascriptExecutor jsExecutor;
	 String projectPath = System.getProperty("user.dir");
	 Select select;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/Browserdriver/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\Browserdriver\\geckodriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Fix_Popup() {
		driver.get("https://www.zingpoll.com/");
		 SleepinSec(3);
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		By siginPopup = By.cssSelector(".modal_dialog_custom");
		Assert.assertTrue(driver.findElement(siginPopup).isDisplayed());
		
		driver.findElement(By.id("loginEmail")).sendKeys("xuanbt");
		driver.findElement(By.id("loginPassword")).sendKeys("123456");
	     SleepinSec(3);
		//driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		driver.findElement(By.cssSelector("#Login .modal-content .close")).click();
		
		Assert.assertFalse(driver.findElement(siginPopup).isDisplayed());
		
	}
	
	public void TC_02_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		SleepinSec(30);
		By randomPopup = By.cssSelector("#mailch-bg .mailch-wrap");
		if (elementisDisplayed(randomPopup)) {
			driver.findElement(By.cssSelector(".close-mailch")).click();
			SleepinSec(3);
		}

			driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
			SleepinSec(3);
			driver.findElement(By.cssSelector("#search-2 .glass")).click();
			SleepinSec(3);
			List<WebElement> postTitles = driver.findElements(By.cssSelector(".post-title"));
			
			for(WebElement postTitle : postTitles)
			{
				Assert.assertTrue(postTitle.getText().contains("Selenium"));
			
		}
	}

	public void TC_03_Random_Not_In_Dom() {
	 driver.get("https://shopee.vn/");
	 By imgPopup = By.cssSelector(".shopee-popup__container");
	 
	 if (elementisDisplayed(imgPopup))
	 {
		 driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		 Assert.assertFalse(elementisDisplayed(imgPopup));
	 }
	 
	 driver.findElement(By.cssSelector("shopee-searchbar-input")).sendKeys("Ipad");
	 	driver.findElement(By.xpath("//button[contains(@class,'btn btn-solid-primary')]")).click();
	 	
	}

	
	public void TC_04_Random_Popup_Not_in_Dom() {
		driver.get("https://kyna.vn/");
		SleepinSec(10);
		//driver.findElement(By.cssSelector("#logo")).click();
		
		driver.findElement(By.xpath("//div[@class='navigation__inner']//button[contains(@class,'toggle ml-10')]")).click();
		driver.findElement(By.xpath("//div[@class='n-menu__dropdown']//a[text()='Doanh Nhân']")).click();
		
		
	}
	
	public void TC_05_Iframe() {
		driver.get("https://kyna.vn/");
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		WebElement Iframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		Assert.assertTrue(Iframe.isDisplayed());
		SleepinSec(2);
	    driver.switchTo().frame(Iframe);
	    WebElement Iframetext = driver.findElement(By.xpath("//div[@class='_1drq']"));
	    System.out.println("so luong like" + Iframetext.getText());
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(),"169K lượt thích");
	    SleepinSec(2);
	    driver.switchTo().defaultContent();
	    
	    driver.switchTo().frame("cs_chat_iframe");
	    driver.findElement(By.xpath("//body[@class='ng-scope']")).click();
	    
	    driver.findElement(By.xpath("//input[@ng-model=\"login.username\"]")).sendKeys("xuanbt");
	    driver.findElement(By.xpath("//input[@ng-model=\"login.phone\"]")).sendKeys("0975912912");
	    
	    select = new Select (driver.findElement(By.id("serviceSelect")));
	    select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
	    driver.findElement(By.xpath("//textarea[@ng-model=\"login.content\"]")).sendKeys("xuanbt test");
	    driver.findElement(By.xpath("//input[@ng-click=\"sendOffline()\"]")).click();
	    
	    SleepinSec(3);
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form_container']//label[@class='logged_in_name ng-binding']")).getText(), "xuanbt");
	    
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form_container']//label[@class='logged_in_phone ng-binding']")).getText(), "0975912912");
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='form_container']//textarea[@name='message']")).getText(), "xuanbt test");
		
	    SleepinSec(3);
	    driver.switchTo().defaultContent();
	    driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
	    driver.findElement(By.cssSelector(".search-button")).click();
	    
	    List <WebElement> searchExpect = driver.findElements(By.xpath("//div[@class='content']//h4"));
	    System.out.println("so luong search:" +searchExpect.size());
	    for(WebElement search : searchExpect)
	    {
	    	System.out.println("Title cua sach" + search.getText());
	    	Assert.assertTrue(search.getText().toLowerCase().contains("Excel"));
	    
	    	SleepinSec(3);
	    }
	    
	}
	 
	public void TC_06_Frame() {
	  driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
	  String parentID = driver.getWindowHandle();
	  driver.findElement(By.xpath("(//div[@class='pdtb25 text-center']//a[text()='Continue to NetBanking'])[1]")).click();
	  driver.switchTo().frame("login_page");
	  driver.findElement(By.xpath("//input[@class='input_password']")).sendKeys("xuanbt");
	  driver.findElement(By.xpath("//td[@colspan='2']//img[@alt='continue']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='IPIN (Password)']")).isDisplayed());
	  
	  driver.switchTo().defaultContent();
	  
	  driver.switchTo().frame("footer");
	  driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
	  
	  switchtoWindownID(parentID);
	
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='HDFC Bank']")).isDisplayed());
	  
	
	}
	
	public void TC_07_Windown_Github_page () {
		 driver.get("https://automationfc.github.io/basic-form/index.html");
		 String parentID = driver.getWindowHandle();
		 driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		 SleepinSec(2);
		 switchtoWindownID(parentID);
		 String childrentID = driver.getWindowHandle();
		 String GoogleTitle = driver.getTitle();
		 System.out.println("page Tile:" + GoogleTitle);
		 Assert.assertEquals(GoogleTitle, "Google");
		 
		 switchtoWindownID(childrentID);
		 driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		 SleepinSec(2);
		 switchtoWindownID(parentID);
		 String facebookWindown = driver.getWindowHandle();
		 
		 Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		 
		 switchtoWindownID(facebookWindown);
		 driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		 SleepinSec(2);
		 switchtoWindownID(parentID);
         //String tikiWindown = driver.getWindowHandle();
		 
		 Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		 closeWindown(parentID);
	 }
	 
	 public void TC_08_Windown_Kyna_page()
	 {
		 driver.get("https://kyna.vn/");
		 driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		 String parentID = driver.getWindowHandle();
		 driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		 SleepinSec(2);
		 switchtoWindownID(parentID);
		 String facebookWindown = driver.getWindowHandle();
		 Assert.assertEquals(driver.getTitle(), "Đăng nhập Facebook");
		
		 switchtoWindownID(facebookWindown);
		 SleepinSec(2);
		 driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='Cách thanh toán học phí']")).click();
		 
		 switchtoWindownTile("Câu hỏi thường gặp - Kyna.vn");
		 SleepinSec(2);
		 Assert.assertEquals(driver.getTitle(), "Câu hỏi thường gặp - Kyna.vn");
		 
		 SleepinSec(2);
		 closeWindown(parentID);
		 
	 }
	 @Test
	 public void TC_09_Windown_Guru_page()
	 {
		 driver.get("http://live.demoguru99.com/index.php/");
		 driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		 
		 String parentID = driver.getWindowHandle();
		 driver.findElement(By.xpath("//a[text()='IPhone']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());	 
		 driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		 
		 SleepinSec(2);
		 driver.findElement(By.xpath("//span[text()='Compare']")).click();
		 
		 switchtoWindownID(parentID);
		 SleepinSec(2);
		 Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		 
		 SleepinSec(2);
		 closeWindown(parentID);
		 SleepinSec(10);
		 By Clearall = By.xpath("//a[text()='Clear All']");
		 clickByJs(Clearall);
		 
		 driver.switchTo().alert().accept();
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		 
		 
		 
		 
		 
	 }
		public void SleepinSec(long milisecond) {
			try {
				Thread.sleep(milisecond * 1000);
			}
			catch (InterruptedException e){
				e.printStackTrace();
	
			}
		}
		public boolean elementisDisplayed(By by)
		{   
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> elements = driver.findElements(by);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (elements.size() == 0) return false;
			if (elements.size() > 0 && !elements.get(0).isDisplayed()) return false;
			else {
					return true;
			} 
		}
		public void switchtoWindownID( String parentWindown )
		{
			Set<String> childrentWindown = driver.getWindowHandles();
			for(String id : childrentWindown) {
				if(!id.equals(parentWindown)) {
					driver.switchTo().window(id);
					break;
				}
			}
		}
		public void clickByJs(By by) {
			WebElement element = driver.findElement(by);
			jsExecutor.executeScript("arguments[0].click();", element);
		}
		public void switchtoWindownTile( String parentTile) {
			Set <String> childrentTitle = driver.getWindowHandles();
			for(String id: childrentTitle) {
				driver.switchTo().window(id);
				String currentID = driver.getTitle();
				if (currentID.equals(parentTile)) {
					break;
				}
					
			}
		}
		public void closeWindown(String parentID) {
			Set<String> closeWindowns = driver.getWindowHandles();
			for(String id : closeWindowns ) {
				if(!id.equals(parentID)) {
					driver.switchTo().window(id);
					driver.close();
				}
				driver.switchTo().window(parentID);
			}
			
		}
	//}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
}