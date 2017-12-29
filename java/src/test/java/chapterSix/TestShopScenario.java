package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestShopScenario {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String URL = "https://techblog.polteq.com/testshop/index.php";

    @BeforeMethod
    public void spawn() {

        // Open new driver
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();

        // Make new wait
        wait = new WebDriverWait(driver, 15);

        // Open the website
        driver.get(URL);

    }

    @AfterMethod
    public void harikiri() {

        //driver.quit();
    }
}
