package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(className = "page-heading")
    private WebElement pageHeading;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="passwd")
    private WebElement password;

    @FindBy(id="SubmitLogin")
    private WebElement signInButton;

    @FindBy(className = "alert-danger")
    private WebElement error;

    public AuthenticationPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 15);
    }

    public MyAccountPage login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        signInButton.click();
        ExpectedCondition<Boolean> signedIn = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return isLoggedIn();
            }
        };
        wait.until(signedIn);
        return new MyAccountPage(driver);
//        assertThat(pageHeading.getText()).isEqualToIgnoringCase("MY ACCOUNT");
    }

    public AuthenticationPage fillInEmail(String email) {
        this.email.sendKeys(email);
        return this;

    }
    
    public AuthenticationPage fillInPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }
    
    public AuthenticationPage submitIncorrectLogin() {
        signInButton.click();
        return this;
    }

    public String getErrorMessage() {
        return error.getText();
    }
}
