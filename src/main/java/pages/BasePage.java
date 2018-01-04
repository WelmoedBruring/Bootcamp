package pages;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

    protected WebDriver driver;

    @FindBy(id="contact-link")
    private WebElement contactButton;

    @FindBy(className="login")
    private WebElement signInButton;

    @FindBy(className = "logout")
    private WebElement signOutButton;

    public AuthenticationPage goToAuthenticationPage() {
        signInButton.click();
        Assertions.assertThat(driver.findElement(By.className("page-heading")).getText())
                .isEqualToIgnoringCase("AUTHENTICATION");
        return new AuthenticationPage(driver);
    }

    public boolean isLoggedIn() {
        boolean isLoggedIn = driver.findElements(By.className("logout")).size() > 0;
        return isLoggedIn;
    }

    public void signOut() {
        signInButton.click();
        Assertions.assertThat(!isLoggedIn());
    }

    public ContactUsPage goToContactUsPage() {
        contactButton.click();
        return new ContactUsPage(driver);
    }

}