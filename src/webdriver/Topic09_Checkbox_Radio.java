package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic09_Checkbox_Radio {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Boolean status;

	@BeforeClass
	public void beforeClass() {
		System.out.println(projectPath);
		System.setProperty("webdriver.gecko.driver", projectPath + "\\Browserdriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
	}

	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//div[@class='fhs_login_form']//a[text()='Đăng nhập']")).click();

		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("0975947812");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("1234567");

		Assert.assertTrue(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='fhs-popup-msg fhs-login-msg' and text()='Số điện thoại/Email hoặc Mật khẩu sai!']"))
				.isDisplayed());

		driver.navigate().refresh();
		SleepinSec(2);
		driver.findElement(By.xpath("//div[@class='fhs_login_form']//a[text()='Đăng nhập']")).click();
		// removeDisableAttributeByJS(By.xpath("//button[@class='fhs-btn-login']"));

		removeDisableAttributeByJS(By.xpath("//button[@class='fhs-btn-login']"));
		status = driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled();

		System.out.println("value cua status: " + status);
		SleepinSec(2);
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Default_Checkbox_and_Radio_button() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		SleepinSec(1);
		By Checkbox1 = By.xpath("//label[text()='Rear side airbags']//parent::li/input");
		By Checkbox2 = By.xpath("//label[text()='Luggage compartment cover']//parent::li/input");
		selectCheckboxButton(Checkbox1);
		SleepinSec(1);
		Assert.assertTrue(driver.findElement(Checkbox1).isSelected());
		SleepinSec(1);
		unselectCheckboxButton(Checkbox1);
		SleepinSec(1);
		Assert.assertFalse(driver.findElement(Checkbox1).isSelected());
		selectCheckboxButton(Checkbox2);
		Assert.assertTrue(driver.findElement(Checkbox2).isSelected());

		unselectCheckboxButton(Checkbox2);
		Assert.assertFalse(driver.findElement(Checkbox2).isSelected());

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		By radio1 = By.xpath("//label[text()='1.4 Petrol, 92kW']//parent::li/input");
		By radio2 = By.xpath("//label[text()='2.0 Petrol, 147kW']//parent::li/input");
		selectCheckboxButton(radio1);
		boolean status = driver.findElement(radio1).isSelected();
		System.out.println("Radio is choose:" + status);
		if (status) {
			selectCheckboxButton(radio2);
		}
	}

	// driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

	// By AllCheckbox = By.xpath("//h4[text()='Choose Extra
	// Equipment']//parent::div//input");
	// List<WebElement> AllCheckbox1 = driver.findElements(AllCheckbox);
	// for(WebElement checkbox : AllCheckbox1) {
	// if(!checkbox.isSelected())
	// {
	// SleepinSec(1);
	// checkbox.click();
	// }
	// }
	// }

	// Assert.assertTrue(checkbox.isSelected());

	public void TC_03_Custome_Checkbox_and_Radio_button() {
		driver.get("https://material.angular.io/components/radio/examples");
		By CustomRadio1 = By.xpath("//input[@value='Summer']");
		// The input is display, so can not click , span can click but not verify
		// so using JS click to click input
		clickByJS(CustomRadio1);
		Assert.assertTrue(driver.findElement(CustomRadio1).isSelected());
		if (!driver.findElement(CustomRadio1).isSelected()) {
			clickByJS(CustomRadio1);
		}

		driver.get("https://material.angular.io/components/checkbox/examples");
		By CheckBox1 = By.xpath("//span[contains(text(),'Checked')]//preceding-sibling::span/input");
		By Checkbox2 = By.xpath("//span[contains(text(),'Indeterminate')]//preceding-sibling::span/input");
		clickByJS(CheckBox1);
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(CheckBox1).isSelected());
		clickByJS(Checkbox2);
		SleepinSec(2);
		Assert.assertTrue(driver.findElement(Checkbox2).isSelected());

		// verify after uncheck

		clickByJS(CheckBox1);
		SleepinSec(2);
		Assert.assertFalse(driver.findElement(CheckBox1).isSelected());
		clickByJS(Checkbox2);
		SleepinSec(2);
		Assert.assertFalse(driver.findElement(Checkbox2).isSelected());

	}

	public void TC_04_ustome_Checkbox_and_Radio_button_02() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radioCantho = By.xpath("//span[text()='Cần Thơ']");

		By radioHN = By.xpath("//span[text()='Hà Nội']");
		driver.findElement(radioCantho).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(@class,'isChecked')]//div//span[text()='Cần Thơ']"))
						.isDisplayed());
		SleepinSec(2);
		driver.findElement(radioHN).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(@class,'isChecked')]//div//span[text()='Hà Nội']"))
						.isDisplayed());

		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked ='true']")).isDisplayed());

	}

	public void TC_04_LoginFormDisplayed() {

	}

	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);

	}

	public void selectCheckboxButton(By by) {
		WebElement element = driver.findElement(by);
		SleepinSec(3);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		if (!element.isSelected()) {
			element.click();

		}
	}

	public void unselectCheckboxButton(By by) {
		WebElement element = driver.findElement(by);
		SleepinSec(3);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		if (element.isSelected()) {
			element.click();

		}
	}

	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click()", element);
	}

	// }
	@AfterClass
	public void afterClass() {
		// driver.quit();
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