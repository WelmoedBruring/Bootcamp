package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

    public enum SubjectHeading {
        CUSTOMER_SERVICE,
        WEBMASTER;
    }

    @FindBy(css = ".alert.alert-danger")
    private WebElement error;

    @FindBy(id="id_contact")
    private WebElement select;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="id_order")
    private WebElement orderReference;

    @FindBy(id="message")
    private WebElement messageField;

    @FindBy(id="submitMessage")
    private WebElement submitButton;

    @FindBy(className = "alert-success")
    private WebElement successMessage;

    @FindBy(css = ".form-error")
    private WebElement formError;

    public ContactUsPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage submitForm() {
        submitButton.click();
        return this;
    }

    public ContactUsPage clearForm() {
        emailField.clear();
        messageField.clear();
        orderReference.clear();
        return this;
    }

    public ContactUsPage selectSubject(SubjectHeading subject) {
        Select subjectHeading = new Select(select);

        switch(subject) {
            case WEBMASTER:
                subjectHeading.selectByVisibleText("Webmaster");
            case CUSTOMER_SERVICE: default:
                subjectHeading.selectByVisibleText("Customer service");
        }
        return this;
    }

    public ContactUsPage fillInMessage(String message) {
        messageField.sendKeys(message);
        return this;
    }

    public ContactUsPage fillInReference(String reference) {
        orderReference.sendKeys(reference);
        return this;
    }

    public ContactUsPage fillInEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}