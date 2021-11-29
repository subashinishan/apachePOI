package datadrivenusingforloop;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datadrivenusingexcelfile.XLUtility;

public class LoginPage {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\suba\\eclipse-workspace\\ApachePOI\\driver96\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "LoginData")
	public void loginTest(String User, String Psw, String Exp) {
		driver.get("https://www.instagram.com/accounts/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[1]/div/label/input")).sendKeys(User);
		driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[2]/div/label/input")).sendKeys(Psw);
		driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[3]/button")).click();
		driver.getTitle();
	}

	@DataProvider(name = "LoginData")
	public void validData() throws Throwable {
		String path = ".\\datafile\\Ford.xlsx";
		XLUtility xlu = new XLUtility(path);
		//////////////////////////////////////
		/////////////////////////////////////
		////////////////////////////////////

	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
}
