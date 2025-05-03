package AlmosaferRealCaseProject.AlmosaferRealCaseProject;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	Random random = new Random();

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get("https://www.almosafer.com/en");
		driver.findElement(By.cssSelector(".sc-jTzLTM.cta__button.cta__saudi.btn.btn-primary")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled = true)
	public void checkWebsiteLanguage(String expectedResult) {
		String actualResult = driver.findElement(By.tagName("html")).getDomAttribute("lang");

		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 2)
	public void checkCurrency() {
		String actualResult = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG")).getText();
		System.out.println(actualResult);
		String expectedResult = "SAR";
		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 3)
	public void checkContactNumber() {
		String actualResult = driver.findElement(By.linkText("+966554400000")).getText();
		System.out.println(actualResult);
		String expectedResult = "+966554400000";
		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 4)
	public void verifyQitafLogoISDisplayed() {
		WebElement footer = driver.findElement(By.tagName("footer"));
		WebElement qitafLogo = footer.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));
		boolean actualResult = qitafLogo.isDisplayed();
		System.out.println(actualResult);

		Assert.assertEquals(actualResult, true);

	}

	@Test(priority = 5)
	public void verifySearchTabIsNotSelectedByDefault() throws InterruptedException {
//		driver.findElement(By.linkText("Stays")).click();
		Thread.sleep(2000);
		WebElement stayesTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String isSelected = stayesTab.getDomAttribute("aria-selected");
		String expectedResult = "false";
		System.out.println(isSelected);

		Assert.assertEquals(isSelected, expectedResult);

	}

	@Test(priority = 6)
	public void checkFlightDepatureDateTodayPlusOneDay() {
		List<WebElement> getDates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
				
String actualResult=getDates.get(0).getText();
		LocalDate currentDate = LocalDate.now();

		int plusOneDay = currentDate.plusDays(1).getDayOfMonth();
		String expectedResult= String.format("%02d", plusOneDay);
		System.out.println("Hellllllooooooo" + 				
				actualResult+" "+expectedResult);
		

		Assert.assertEquals(actualResult, expectedResult);
	}

	@Test(priority = 7)
	public void checkFlightReturnDateTodayPlusTowDays() {
		List<WebElement> getDates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
				
String actualResult=getDates.get(1).getText();
		LocalDate currentDate = LocalDate.now();

		int plusTowDays = currentDate.plusDays(2).getDayOfMonth();
		String expectedResult= String.format("%02d", plusTowDays);
		System.out.println("Hellllllooooooo" + 				
				actualResult+" "+expectedResult);
		

		Assert.assertEquals(actualResult, expectedResult);
	}
	
	@Test(priority = 8, invocationCount = 9)
	public void randomChangingLanguage() {
		
		String[] webSiteLangs= {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
	Random random =new Random();
		int index= random.nextInt(webSiteLangs.length);
		driver.get(webSiteLangs[index]);
		
		if (driver.getCurrentUrl().contains("en"))
		{
			checkWebsiteLanguage("en");
		}
		else {

			checkWebsiteLanguage("ar");	
		}
	}

	
	@AfterTest
	public void endTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
