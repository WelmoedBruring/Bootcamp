package chapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignOutTest {

    WebDriver driver;
    String authenticationHeading;

    @BeforeMethod
    public void setUp() {

        // Start browser
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test
    private void signOutTest() {

        // Open URL
        driver.get("https://techblog.polteq.com/testshop/index.php");

        // Find and click the login button
        driver.findElement(By.className("login")).click();

        // Check that the authentication page is opened
        authenticationHeading = driver.findElement(By.className("page-heading"))
                .getText();
        Assertions.assertThat(authenticationHeading)
                .as("Authentication page should be displayed")
                .isEqualToIgnoringCase("AUTHENTICATION");

        // Enter email address
        driver.findElement(By.id("email"))
                .sendKeys("welmoed.bruring@polteq.com");

        // Enter password
        driver.findElement(By.id("passwd"))
                .sendKeys("testw8woord");

        // Click login button
        driver.findElement(By.id("SubmitLogin")).click();

        // Check that the My Account-page is opened
        String acountPageHeading = driver.findElement(By.className("page-heading"))
                .getText();
        Assertions.assertThat(acountPageHeading)
                .as("My Account page should be displayed")
                .isEqualToIgnoringCase("MY ACCOUNT");

        // Find and click the logout button
        driver.findElement(By.className("logout")).click();

        // Check that the logout is succesful and the authentication page is opened
        authenticationHeading = driver.findElement(By.className("page-heading"))
                .getText();
        Assertions.assertThat(authenticationHeading)
                .as("Authentication page should be displayed")
                .isEqualToIgnoringCase("AUTHENTICATION");
    }

    @AfterMethod
    public void cleanUp() {

        driver.quit();
    }
}
