package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class LoginTestNotPaged {
    private RemoteWebDriver driver;

    @BeforeTest
    public void openLoginPage() {
       /* WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        *//*final String[] args = { "--remote-debugging-port=9222" };
        options.addArguments(args);*//*
        //options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("83.0");
        capabilities.setCapability("enableVNC", false);
        capabilities.setCapability("enableVideo", false);

         driver = null;
        try {
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(40000, TimeUnit.MILLISECONDS);
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

    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
