package webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

public class Topic_10_Iteractions {
	WebDriver driver;
	Actions action;
    String projectPath = System.getProperty("user.dir");
    String DragandDrop = projectPath + "\\DragandDrop\\drag_and_drop_helper.js" ;
    String Jsfile = projectPath + "DragandDrop\\jquery_load_helper.js" ;
    JavascriptExecutor jsExecutor;
	
    
	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\Browserdriver\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "/Browserdriver/chromedriver.exe");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		System.out.println(DragandDrop);
	}

	
	public void TC_01_HoverToElement01() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		SleepinSec(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
		
		driver.get("https://tiki.vn/");
		SleepinSec(2);
		
		// Thu nho kich co cua trang bang cach dung interactions
		WebElement html = driver.findElement(By.tagName("html"));
		action.sendKeys(html, Keys.CONTROL, Keys.ADD, Keys.NULL).perform();
		
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();
		
		SleepinSec(2);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder='Số điện thoại']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Đăng nhập hoặc Tạo tài khoản']")).isDisplayed());
	}

	public void TC_02_HoverToElement02() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"))).perform();
		SleepinSec(2);
		
		driver.findElement(By.xpath("//li[@data-reactid ='357']//a[text()='Dresses']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Girls Dresses']")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/girls-dresses");
		
		driver.get("https://hn.telio.vn/");
	    By xpath = By.xpath("//main[@id='maincontent']//ul[@class='groupmenu']//span[text()='Đồ ăn vặt']");
	    
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(xpath));
		action.moveToElement(driver.findElement(By.xpath("//main[@id='maincontent']//ul[@class='groupmenu']//span[text()='Đồ ăn vặt']")));

		SleepinSec(2);
		driver.findElement(By.xpath("//main[@id='maincontent']//ul[@class='groupmenu']//a[text()='Bắp rang bơ']")).click();
		
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(By.xpath("//main[@id='maincontent']//span[text()='Bắp rang bơ']")).isDisplayed());
		
	}
	
	public void TC_03_HoldandClick() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> number = driver.findElements(By.cssSelector(".ui-state-default"));
		SleepinSec(2);
		action.clickAndHold(number.get(0))
		.moveToElement(number.get(3))
		.release()
		.perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selected")).size(), 4);
		SleepinSec(2);
		driver.navigate().refresh();
		List <WebElement> number1 = driver.findElements(By.cssSelector(".ui-state-default"));
		SleepinSec(5);
		action.keyDown(Keys.CONTROL).perform();
		
		SleepinSec(2);
		// chon cac phan tu can click
		action.click(number1.get(0))
		
		.click(number1.get(7)).perform();
		// tha phim ctr ra
		action.keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-state-default.ui-selected")).size(), 2);
	}
	
	public void TC_04_HoldandClickRandom() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List <WebElement> number = driver.findElements(By.cssSelector(".ui-state-default"));
		// nham phim Ctrl xuong
		action.keyDown(Keys.CONTROL).perform();
		// chon cac phan tu can click
		action.click(number.get(0)).click(number.get(5))
		.click(number.get(6))
		.click(number.get(10))
		.perform();
		// tha phim ctr ra
		action.keyUp(Keys.CONTROL);
		
		Assert.assertEquals(driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]")).size(), 4);
		
		SleepinSec(2);
		driver.navigate().refresh();
		List <WebElement> number1 = driver.findElements(By.cssSelector(".ui-state-default"));
		SleepinSec(2);
		action.clickAndHold(number1.get(1)).moveToElement(number1.get(11)).release().perform();
	}

	
	public void TC_05_DoubleClick() throws AWTException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		SleepinSec(5);
		//Robot robot = new Robot();
		//robot.keyPress(KeyEvent.VK_CONTROL);
		//robot.keyPress(KeyEvent.VK_END);
		//robot.keyRelease(KeyEvent.VK_END);
		//robot.keyRelease(KeyEvent.VK_CONTROL);
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		//action.moveToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		SleepinSec(3);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
	}
	
	public void TC_06_RightClick() {
	 driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	 SleepinSec(2);
	 WebElement ClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
	 
	 SleepinSec(3);
	 action.contextClick(ClickButton).perform();
	 
	 //Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible)")).isDisplayed());
	 SleepinSec(2);
	 
	 action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	// Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible)")).isDisplayed());
	 
	 
	 action.click(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
	 SleepinSec(2);
	 
	 driver.switchTo().alert().accept();
	}

	public void TC_07_DragandDropHTMK04() {
		 driver.get("https://automationfc.github.io/kendo-drag-drop/");
		 SleepinSec(2);
		 WebElement source = driver.findElement(By.xpath("//div[@id='example']//div[@id='draggable']"));
		 WebElement target = driver.findElement(By.xpath("//div[@id='example']//div[@id='droptarget']"));
		 action.dragAndDrop(source, target).perform();
		 
		 SleepinSec(2);
         Assert.assertEquals(driver.findElement(By.xpath("//div[@id='example']//div[@id='droptarget']")).getText(), "You did great!");
         
         // chuyen toi ma mau tu Rgb sang ma Hex 
         String color = driver.findElement(By.xpath("//div[@id='droptarget']")).getCssValue("background-color");
         System.out.println(color);
         String hex = Color.fromString(color).asHex();
         
         Assert.assertEquals(hex, "#03a9f4");
         
         
		 
		}
	
	@Test
	public void TC_08_DragandDropHTML05() throws IOException {
		 driver.get("https://automationfc.github.io/drag-drop-html5/");
		 SleepinSec(2);
		 
		 String sourceCss = "#column-a";
		 String targetCss = "#column-b";
	     String readfile = readFile(DragandDrop);
	     readfile = readfile + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(readfile);
		SleepinSec(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
	}
	

	public void TC_09_DragandDropHTML05() throws AWTException {
		 driver.get("https://automationfc.github.io/drag-drop-html5/");
		 SleepinSec(2);
		 drag_the_and_drop_html5_by_xpath("//div[@id='column-a']","//div[@id='column-b']");
		 
		SleepinSec(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		
		SleepinSec(2);
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']","//div[@id='column-b']");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
	}
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	// ham de drapanddrop apply for CSS, xpath
	// fullsize man hinh = 100% se work, neu #100% ko work
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void SleepinSec(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
		