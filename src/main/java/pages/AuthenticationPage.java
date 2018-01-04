package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {
    
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
    }

    public MyAccountPage login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        signInButton.click();
        return new MyAccountPage(driver);
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
