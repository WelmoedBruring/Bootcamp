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

public class WishListPage extends BasePage {

    @FindBy(className = "table-bordered")
    public WebElement wishListTable;

    @FindBy(css = "#name")
    private WebElement textbox;

    @FindBy(css = "#submitWishlist")
    private WebElement saveButton;

    public WishListPage(WebDriver driver) {
        super.driver = driver;
        PageFactory.initElements(super.driver, this);
    }

    public WishListPage deleteWishList(String listToDelete) {
        if(!hasWishList(listToDelete)) {
            throw new NoSuchElementException("The target wish list does not exist!");
        }

        int rowIndex;

        // For each row in the list of rows in the table...
        for (WebElement row : getRows()) {
            rowIndex = getRows().indexOf(row);
            System.out.println("Row " + rowIndex + "= " + row.getText());

            // ... check if it contains the target wish list. If it does...
            if(row.getText().contains(listToDelete)) {
                int columnIndex;

                // ... search the columns for the one with the delete-buttons. If found...
                for (WebElement column : getColumns()) {
                    columnIndex = getColumns().indexOf(column);

                    //... go to the delete-button in the row with the target wish list and delete it.
                    if(column.getText().equals("Delete")) {
                        System.out.println(wishListTable.findElement(By.xpath(
                                "//tbody/tr[" + (rowIndex + 1) + "]/td[" + (columnIndex + 1) + "]")));
                        wishListTable.findElement(By.xpath(
                                "//tbody/tr[" + (rowIndex + 1) + "]/td[" + (columnIndex + 1) + "]/a")).click();
                        break;
                    }
                }
                break;
            }
        }

        // Wait for the alert and confirm the deletion of the target wish list
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        driver.navigate().refresh();

        return this;
    }

    public WishListPage createWishList(String listToCreate) {
        textbox.sendKeys(listToCreate);
        saveButton.click();
        driver.navigate().refresh();
        return this;
    }

    public boolean hasWishList(String targetList) {
        return wishListTable.getText().contains(targetList);
    }

    public List<WebElement> getRows() {
        return wishListTable.findElements(By.xpath("//tbody/tr"));
    }

    public List<WebElement> getColumns() {
        return wishListTable.findElements(By.xpath("//th"));
    }
}

