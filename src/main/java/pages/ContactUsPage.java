package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage extends BasePage {

    private boolean formErrorPresent;

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

    public void submitForm(SubjectHeading subject, String email, String reference, String message) {
        fillInForm(subject, email, reference, message);
        submitButton.click();
    }

    private void fillInForm(SubjectHeading subject, String email, String reference, String message) {
        selectSubject(subject);
        fillInEmail(email);
        fillInReference(reference);
        fillInMessage(message);
    }

    public void partiallyFillInForm(SubjectHeading subject, String email) {
        fillInEmail(email);
        selectSubject(subject);
    }

    private void selectSubject(SubjectHeading subject) {
        Select subjectHeading = new Select(select);

        switch(subject) {
            case WEBMASTER:
                subjectHeading.selectByVisibleText("Webmaster");
            case CUSTOMER_SERVICE: default:
                subjectHeading.selectByVisibleText("Customer service");
        }
    }

    public boolean formErrorPresent() {
        if(formError.isDisplayed()){
            formErrorPresent = true;
        }
        return formErrorPresent;
    }

    private void fillInMessage(String message) {
        messageField.clear();
        messageField.sendKeys(message);
    }

    private void fillInReference(String reference) {
        orderReference.clear();
        orderReference.sendKeys(reference);
    }

    private void fillInEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getErrorMessage() {
        return error.getText();
    }
}