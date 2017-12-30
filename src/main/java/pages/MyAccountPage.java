package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    private WebDriver driver;

    @FindBy(css = ".icon-heart")
    private WebElement wishListButton;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the Wish List Page
     * @return New WishListPage
     */
    public WishListPage goToWishLists() {
        wishListButton.click();
        return new WishListPage(driver);
    }
}

