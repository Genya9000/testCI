package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginTestNotPaged {
    private WebDriver driver;

    @BeforeTest
    public void openLoginPage() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://github.com/");
        driver.findElement(By.xpath("//a[(contains(@class, 'HeaderMenu-link')) and (@href='/login')]")).click();
    }

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        driver.findElement(By.xpath("//input[@name='login']")).clear();
        driver.findElement(By.xpath("//input[@name='password']")).clear();
    }

    @DataProvider(name = "login form provider")
    public Object[][] dataProviderAuthUserType() {
        return new Object[][]{
                {"", ""},
                {"alhonchar", ""},
                {"", "Alex123456&*"},
                {"alhonchar", "alhonchar"},
                {"Alex123456&*", "Alex123456&*"},
                {"login with spaces", "Alex123456&*"},
        };
    }

    @Test(dataProvider = "login form provider")
    public void loginFormNegativeTest(String login, String pass) {
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

}
