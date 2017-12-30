package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

    private WebDriver driver;

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

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void submitForm(String subject, String email, String reference, String message) {
        Select subjectHeading = new Select(select);

        subjectHeading.selectByVisibleText(subject);
        emailField.sendKeys(email);
        orderReference.sendKeys(reference);
        messageField.sendKeys(message);

        submitButton.click();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }





}