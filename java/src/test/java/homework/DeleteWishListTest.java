package homework;

import chapterSix.TestShopScenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DeleteWishListTest extends TestShopScenario {

    private List<WebElement> columns;
    private List<String> columnNames;
    private List<WebElement> rows;
    private List<String> rowNames;
    private String listToDelete = "Feel the pain";

    @Test
    public void deleteWishListTest() throws Exception {
        // Login and go to wish lists
        login("welmoed@bruring-looge.com","1qazxsw2");
        driver.findElement(By.cssSelector(".icon-heart")).click();

        deleteWishList(listToDelete);
        //TODO Write assertion to check that wish list is successfully deleted & no longer exists
//        Assertions.assertThat(rowNames.)
        //TODO Write addWishList
        addWishList(listToDelete);
        //TODO Write assertion to check that list is successfully added --> findWishList class that can be used by both deleteWishList and the assertions?

    }

    private void addWishList(String listToDelete) {

    }

    private void deleteWishList(String listToDelete) throws Exception {
        boolean found = false;

        getColumns();
        getRows();
        // For each row i in the table body (tbody)
        for(int i = 0; i<rows.size(); i++) {
            WebElement row = driver.findElement(By.xpath("(//tbody/tr)["+(i+1)+"]"));
            System.out.println(row.getText());
            if(row.getText().contains(listToDelete)){
                System.out.println("This is the row you are looking for!");
                // For each cell in row i

                WebElement deleteButton = driver.findElement(By.xpath(
                        "(//tbody/tr)[" + (i + 1) + "]/td[@class='wishlist_delete']/a"));
                deleteButton.click();
                driver.switchTo().alert().accept();
                found = true;
                break;
            }
            if(found){
                break;
            } else {
                throw new Exception("The wish list you were looking for does not exist!");
            }
        }
    }

    // Counts the number of rows in the table and saves their text to a list
    private int getRows() {
        // Get number of rows
        rows = driver.findElements(By.xpath("//tbody/tr"));
        getRowNames();

        System.out.println("There are "+ rows.size() + " rows: " + rowNames);
        return rows.size();
    }

    private void getRowNames() {

        rowNames = new ArrayList<>();

        for(int i = 0; i< rows.size(); i++) {
            String text = rows.get(i).getText();
            rowNames.add(text);
        }
    }

    // Counts the number of columns in the table and saves their names to a list
    private int getColumns() {
        // Get number of columns & column headers
        columns = driver.findElements(By.xpath("//th"));
        columnNames = new ArrayList<>();
        for(int i = 0; i < columns.size(); i++) {
            String text = columns.get(i).getText();
            columnNames.add(text);
        }
        System.out.println("There are "+ columns.size() + " columns: " + columnNames);
        return columns.size();
    }

    private void login(String email, String password) {
        WebElement loginButton = driver.findElement(By.className("login"));
        loginButton.click();

        // Enter credentials
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);

        // Submit login
        driver.findElement(By.id("SubmitLogin")).click();
    }
}