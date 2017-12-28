package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class AdjustPersonalInfoTest extends TestShopScenario {

    private String authenticationHeading;
    private String initialName = "Welmoed";
    private String changedName = "Tester";
    private String nameBeforeChange;
    private String password = "testw8woord";

    @Test
    private void adjustPersonalInfoTest() {

        WebElement loginButton = driver.findElement(By.className("login"));

        // If no user is logged in, log in.
        if (loginButton.isDisplayed()) {

            loginButton.click();

            // Check that the authentication page is opened
            authenticationHeading = driver.findElement(By.className("page-heading"))
                    .getText();
            Assertions.assertThat(authenticationHeading)
                    .as("Authentication page should be displayed")
                    .isEqualToIgnoringCase("AUTHENTICATION");

            // Enter credentials
            driver.findElement(By.id("email"))
                    .sendKeys("welmoed.bruring@polteq.com");
            driver.findElement(By.id("passwd"))
                    .sendKeys(password);

            // Submit login
            driver.findElement(By.id("SubmitLogin")).click();
        }

        List<WebElement> pageHeadings = driver.findElements(By.className("page-heading"));

        /** Checks whether the next if-statement works
        List<String> string = new ArrayList<String>();
        for (int i = 0; i < pageHeadings.size(); i++ ) {
            String title = pageHeadings.get(i).getText();
            string.add(title);
        }

        System.out.println(string);**/

        // If the pageHeadings-list is empty OR the pageHeadings list is not empty but the pageHeading found is not
        // 'MY ACCOUNT' then go to the MY ACCOUNT page
        if (pageHeadings.size()==0
                || pageHeadings.get(0).getAttribute("title").equalsIgnoreCase("MY ACCOUNT")) {
            driver.findElement(By.cssSelector("[title='View my customer account']")).click();
        }

        // Go to Personal Information page
        driver.findElement(By.className("icon-user")).click();
        Assertions.assertThat(driver.findElement(By.className("page-subheading"))
                .getText()
                .equalsIgnoreCase("YOUR PERSONAL INFORMATION"));

        // Change name
        WebElement firstName = driver.findElement(By.cssSelector("#firstname"));
        if (firstName.getAttribute("value").equals(initialName)) {
            nameBeforeChange = initialName;
            firstName.clear();
            firstName.sendKeys(changedName);
        } else {
            nameBeforeChange  = changedName;
            firstName.clear();
            firstName.sendKeys(initialName);
        }
        driver.findElement(By.id("old_passwd")).sendKeys(password);
        driver.findElement(By.name("submitIdentity")).click();

        // Validate successmessage
        String successMessage = driver.findElement(By.className("alert-success")).getText();
        Assertions.assertThat(successMessage.contains("Your personal information has been successfully updated."));
    }
}
