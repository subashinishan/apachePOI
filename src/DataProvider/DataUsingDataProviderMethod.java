package DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataUsingDataProviderMethod {

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
	public void loginTest(String User, String Psw, String Exp) throws Throwable {
		driver.get("https://www.instagram.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement Username = driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[1]/div/label/input"));
		Username.clear();
		Username.sendKeys(User);
		WebElement Password = driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[2]/div/label/input"));
		Password.clear();
		Password.sendKeys(Psw);
		driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[3]/button")).click();
				String actTitle = driver.getTitle();
		System.out.println(actTitle);
		/*String expTitle = "Instagram";
		if (expTitle.equals("Valid")) {
			if (expTitle.equals(actTitle)) {
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}*/
	}

	@DataProvider(name = "LoginData")
	public String[][] getData() {
		String LoginData[][] = { 
				{ "ms_july8", "pits1246", "Valid" },
				{ "Shantahkumari", "Shanthi", "Invalid" },
				{ "Manikandan", "Mani", "Invalid" }, 
				{ "Shanmugam", "Shanmu", "Invalid" } 
				};
		return LoginData;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
}
