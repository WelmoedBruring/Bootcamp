package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WishListPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#name")
    private WebElement textbox;

    @FindBy(css = "#submitWishlist")
    private WebElement saveButton;

    public WishListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 15);
    }

    /**
     * Adds a wishList to the table of wishLists
     * @param listToAdd Name of the wishList to add
     */
    public void addWishList(String listToAdd) {
        textbox.sendKeys(listToAdd);
        saveButton.click();
        driver.navigate().refresh();
    }

    /**
     * Deletes a given wishList from the table of wishLists.
     * @param listToDelete Name of the wishList to delete
     */
    public void deleteWishList(String listToDelete) {
        int index = findWishList(listToDelete);
        if (index > 0) {
            WebElement deleteButton = driver.findElement(By.xpath(
                    "(//tbody/tr)[" + (index) + "]/td[@class='wishlist_delete']/a"));
            deleteButton.click();
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
            driver.navigate().refresh();
        } else {
            throw new NoSuchElementException("The wishlist you want to delete does not exist!");
        }
    }

    /**
     * Checks whether the wishList passed as parameter is present in the table
     * @param wishList String wishList to look for
     * @return 0 if the wishList was not found, index > 0 if the wishList was found
     */
    public int findWishList(String wishList) {
        int index = 0;  // Index is initialized at 0; if returned with value 0 it means that the wish list was not found
        int numberOfRows = getRows();
        // For each row i in the table body, check if it contains the name of the argument wish list
        for (int i = 0; i < numberOfRows; i++) {
            WebElement row = driver.findElement(By.xpath("(//tbody/tr)[" + (i + 1) + "]"));
            if (row.getText().contains(wishList)) {
                index = i + 1; // +1 Because xpath is not 0-based
                break;
            }
        }

        return index;
    }

    /**
     * Counts the number of rows in the table by putting all rows in a list and returning the size of the list
     * @return int number of rows (size of the list.
     */
    public int getRows() {
        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
        System.out.println("There are " + rows.size() + " rows");
        return rows.size();
    }
}
