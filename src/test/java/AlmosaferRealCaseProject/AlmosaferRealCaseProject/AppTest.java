package AlmosaferRealCaseProject.AlmosaferRealCaseProject;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
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

	@Test(priority = 1)
	public void checkWebsiteLanguage() {
		String actualResult = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		String expectedResult = "en";
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

	@Test(priority = 4)
	public void verifySearchTabIsNotSelectedByDefault() {
		boolean stayesTab = driver.findElement(By.linkText("Stays")).isSelected();
		String actualResult = driver.findElement(By.linkText("+966554400000")).getText();

		System.out.println(stayesTab);

		Assert.assertEquals(stayesTab, false);

	}

	@Test(priority = 5)
	public void checkFlightDepatureDateTodayPlusOneDay() {
		String actualResult = driver.findElement(By.tagName(".sc-bYTsla.sc-dlyikq.XbXIU")).findElement(By.cssSelector(".sc-bEufUU.kyMhih")).getText();

		LocalDate currentDate = LocalDate.now() ;

		int expectedResult = currentDate.getDayOfMonth();
		System.out.println("Hellllllooooooo"+actualResult);

	

		//Assert.assertEquals(actualResult, expectedResult+1);
	}

	@AfterTest
	public void endTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
