package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import java.util.List;

public class Topic08_Part01_CustomDrop {
	WebDriver driver;
	String[] firstSelect = {"May","June","July"};
	String[] secondSelect ={"May","June","July","October"};
	String[] thirdSelect ={"May","June","July","October","November","January","February","March","August","September","December"};
	
	
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	@BeforeClass
	public void beforeClass() {
	//	driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","Browserdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInDropList("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","8");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "8");
		SleepinSec(1);
		selectItemInDropList("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","12");
		SleepinSec(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),"12");
		selectItemInDropList("//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]","//ul[@id='number-menu']//div","10");
		SleepinSec(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),"10");
	}

	
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropList("//i[@class='dropdown icon']","//div[@class='visible menu transition']//span","Elliot Fu");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-atomic='true']")).getText(), "Elliot Fu");
		
	    SleepinSec(1);
	    
	    selectItemInDropList("//i[@class='dropdown icon']","//div[@class='visible menu transition']//span","Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-atomic='true']")).getText(), "Stevie Feliciano");	
	}
	

	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropList("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
		
		selectItemInDropList("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}

	@Test
	public void TC_04_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInDropList("//span[@aria-owns=\"games_options\"]","//div[@class='e-content e-dropdownbase']//li","Badminton");
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"), "Badminton");
		
	    driver.get("http://indrimuska.github.io/jquery-editable-select/");
	    //selectIteminEditDropdown("//div[text()='Default']//parent::div//input","//div[text()='Default']//parent::div//li","Jaguar");
	    
	    driver.findElement(By.cssSelector("#default-place input")).sendKeys("BMW");
	    
	    driver.findElement(By.cssSelector("#default-place input")).sendKeys(Keys.TAB);
	    
	    SleepinSec(2);
	    String Actual = (String) jsExecutor.executeScript("return document.querySelector(\"#default-place input\").value");
	    Assert.assertEquals(Actual, "BMW");
	}
	
     
	public void multiSelect() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		SleepinSec(2);
		selectMultiValueDropdown("(//button[@class='ms-choice'])[1]","//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span",firstSelect);
	    areSelectedItems(firstSelect);	
	    driver.navigate().refresh();
		SleepinSec(2);
		selectMultiValueDropdown("(//button[@class='ms-choice'])[1]","//div[@class='form-group row'][2]//div[@class='ms-drop bottom']//li//span",secondSelect);
		areSelectedItems(secondSelect);	
	}
	

	public void TC_06_EditableDropwList() {
	   driver.get("https://react.semantic-ui.com/maxsimize/dropdown-example-search-selection/");
	   SleepinSec(2);
		selectIteminEditDropdown("//input[@class='search']","//div[@class='visible menu transition']//span","Afghanistan");
		SleepinSec(2);
			//selectIteminEditDropdown(".//*[@id='default-place']/input","//div[@data-effects = 'default']//ul[@class='es-list']//li","Citroen");
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]//div[@class='divider text' and text()='Afghanistan']")).isDisplayed());
		}
	public void selectItemInDropList(String parentXpath , String childXpath ,String expectItem) {
		System.out.println ("giá tri 123");
		driver.findElement(By.xpath(parentXpath)).click();
		SleepinSec(3);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		SleepinSec(3);
		for (WebElement item : allItems) {
			System.out.println(" gia tri cac item" + item.getText());
			if (item.getText().trim().equals(expectItem)){
				if (!item.isDisplayed())
					System.out.println(" gia tri cac item" + item.getText());
				{
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
					SleepinSec(1);	
					//item.click();
				}
				item.click();
				break;
			}
		}
		
	}
	public void selectMultiValueDropdown(String parentXpath, String childXpath, String[] expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		for(WebElement childElement : allItems) {
			for (String item : expectedItem ) {
				if (childElement.getText().equals(item)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					SleepinSec(1);
					childElement.click();
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item is seleted:" + itemSelected.size() );
					if(expectedItem.length ==itemSelected.size()) {
						break;
					}	
				}
			}		
		}		
	}
	
	public boolean areSelectedItems(String []month) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberSelectedItem = itemSelected.size();
		String allItemSelected = driver.findElement(By.xpath("//button[@class='ms-choice']/span[1]")).getText();
		System.out.println("Month is selected:" + driver.findElement(By.xpath("//button[@class='ms-choice']/span[1]")).getText());
		System.out.println("Item duoc chon:" + allItemSelected );
		if (numberSelectedItem <=3 && numberSelectedItem > 0) {
			boolean status = true;
			for (String item : month)
			{	
				if(!allItemSelected.contains(item))
				{
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberSelectedItem >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberSelectedItem > 3 && numberSelectedItem < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberSelectedItem + " of 12 selected']")).isDisplayed();
		}else {
			return false;
		}
	}
	
	public void selectIteminEditDropdown(String parentXpath, String childXpath, String ExpectedItem) {
		driver.findElement(By.xpath(parentXpath)).clear();
		SleepinSec(1);
		driver.findElement(By.xpath(parentXpath)).sendKeys(ExpectedItem);
		SleepinSec(1);
		// cho all item duoc load ra
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		// duyet qua tat ca cac item trong list 
		for(WebElement item : allItems) {
			// Moi lan duyet qua se kiem tra cac item text cua no co bang voi item minh dang can
			// chon hay khong
			if(!item.getText().trim().equals(ExpectedItem)) {
				// Neu tim thay thi click vao item, neu ko scroll de tim kiem item
					jsExecutor.executeAsyncScript("arguments[0].scrollIntoView(true);", item);
					item.click();
					item.sendKeys(Keys.TAB);
					break;
			}
		}
		
	}
	public void inputAndTabEditDropdown(String parentXpath, String ExpectedItem) {
		driver.findElement(By.xpath(parentXpath)).clear();
		SleepinSec(1);	
		driver.findElement(By.xpath(parentXpath)).sendKeys(ExpectedItem);
	    SleepinSec(1);
	    driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
	}
	public void SleepinSec(long sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//}
	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}