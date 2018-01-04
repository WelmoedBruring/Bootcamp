package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends BasePage {

    @FindBy(css = ".icon-heart")
    private WebElement wishListButton;

    public MyAccountPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(super.driver, this);
    }

    public WishListPage goToWishListPage() {
        wishListButton.click();
        return new WishListPage(driver);
    }

    @Deprecated
    /**
     * Navigates to the Wish List Page
     * @return New OLD_WishListPage
     */
    public OLD_WishListPage goToOLDWishListsPage() {
        wishListButton.click();
        return new OLD_WishListPage(driver);
    }
}

