package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_UploadFile {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String DesertFile = "Desert.jpg" ;
	String googleFile = "google1.png" ;
	String tulipsFile = "Tulips.jpg" ;
	
	String Desertpath = projectPath + "\\Files\\" + DesertFile ;
	String googlepath = projectPath + "\\Files\\" + googleFile ;
	String tulipspath = projectPath + "\\Files\\" + tulipsFile ;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciteWait;
	String firefoxAutoITpath = projectPath + "\\AutoIT\\firefoxUploadOneTime.au3";
	String ChromeAutoITpath = projectPath + "\\AutoIT\\chromeUploadOneTime.au3";
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\Browserdriver\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\Browserdriver\\geckodriver.exe");
		//driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		expliciteWait = new WebDriverWait(driver,15);
		
	}

	
	public void TC_01_Upload_file_By_SendKeys() throws InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Desertpath);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '" + DesertFile + "']")).isDisplayed());
		
		System.out.println(driver.findElement(By.xpath("//p[text()= '" + DesertFile + "']")).getText());
		
		driver.findElement(By.cssSelector("table button.start")).click();
		
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table button.delete")).click();
		SleepinSec(2);
	//	Assert.assertFalse(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Desertpath + "\n" + googlepath + "\n" + tulipspath );
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
		for(WebElement startButton : startButtons) {
			startButton.click();
			SleepinSec(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + tulipsFile + "']")).isDisplayed());
		
		
	}

	
	public void TC_02_UploadFlow() throws InterruptedException {
		driver.get("https://gofile.io/?t=uploadFiles");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Desertpath + "\n" + googlepath + "\n" + tulipspath );
		//By pageLoad = By.cssSelector("table a#rowUploadSuccess-downloadPage");
		//Wait to download page is loaded
		//SleepinSec(5);
		expliciteWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table a#rowUploadSuccess-downloadPage")));
		//verify downloag page display
		
		//Assert.assertTrue(driver.findElement(pageLoad).isDisplayed());
		
		driver.findElement(By.cssSelector("table a#rowUploadSuccess-downloadPage")).click();
		
		switchtoWindownID(parentID);
		//verify 
		SleepinSec(2);
		driver.findElement(By.xpath("//span[text()='"+ DesertFile + "']//parent::a//parent::div//following-sibling::div//button")).click();
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+ DesertFile + "']//parent::a//parent::div//following-sibling::div//button//following-sibling::div/a[text()='Download']")).isDisplayed());
		
	}
	@Test
	public void TC_03_UploadFile_By_AutoIT() throws InterruptedException, IOException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        SleepinSec(2);
        
        System.out.println(ChromeAutoITpath);
        driver.findElement(By.cssSelector(".btn-success")).click();
		
		if(driver.toString().contains("firefox"))
		{
			Runtime.getRuntime().exec(new String[] { firefoxAutoITpath , Desertpath});
		}
		if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { ChromeAutoITpath , Desertpath});
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= '" + DesertFile + "']")).isDisplayed());
		
		System.out.println(driver.findElement(By.xpath("//p[text()= '" + DesertFile + "']")).getText());
		
		driver.findElement(By.cssSelector("table button.start")).click();
		
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		
		driver.findElement(By.cssSelector("table button.delete")).click();
		SleepinSec(2);
	//	Assert.assertFalse(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Desertpath + "\n" + googlepath + "\n" + tulipspath );
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
		
		for(WebElement startButton : startButtons) {
			startButton.click();
			SleepinSec(1);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + DesertFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + googleFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + tulipsFile + "']")).isDisplayed());
		
		
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}

	public void TC_04_LoginFormDisplayed() {
	
	}
		// ham Sleep cho cac
	public void SleepinSec(long sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}
	// ham click JS by Executor
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	public void scrollToElement(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public String generateNumber() {
		Random random = new Random();
		return random.nextInt(9999) + "@mail.vn";
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
	//}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
}