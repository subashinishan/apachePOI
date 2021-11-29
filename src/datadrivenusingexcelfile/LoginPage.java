package datadrivenusingexcelfile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	public void loginTest(String User, String Psw, String Exp) throws Throwable {
		driver.get("https://www.instagram.com/accounts/login/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement Username = driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[1]/div/label/input"));
		Username.clear();
		Username.sendKeys(User);
		WebElement Password = driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[2]/div/label/input"));
		Password.clear();
		Password.sendKeys(Psw);
		driver.findElement(By.xpath("//*[@id=\'loginForm\']/div/div[3]/button")).click();
		String expTitle = "Instagram";
		System.out.println(expTitle);
		/*String actTitle = driver.getTitle();		
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
	public String[][] getData() throws IOException {

		String path = ".\\datafile\\Ford.xlsx";
		XLUtility xlu = new XLUtility(path);
		int totalRows = xlu.getRowCount("Sheet1");
		int totalCols = xlu.getCellCount("Sheet1", 1);
		String logingData[][] = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				logingData[i - 1][j] = xlu.getCellData("Sheet1", i, j);
			}
		}
		return logingData;
	}

	@AfterClass
	void tearDown() {
		driver.close();
	}
}
