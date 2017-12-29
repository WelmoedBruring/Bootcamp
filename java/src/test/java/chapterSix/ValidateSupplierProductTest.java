package chapterSix;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class ValidateSupplierProductTest extends TestShopScenario {

    @Test
    public void validateSupplierProductTest() {
        // Select AppleStore from supplier list drop-down
        Select supplierList = new Select(driver.findElement(By.name("supplier_list")));
        supplierList.selectByVisibleText("AppleStore");

        // Make list of all products found
        List<WebElement> allAppleProducts =
                driver.findElements(By.xpath("//ul[@id='product_list']//*[@class='product-name']"));

        // Create empty string list for the product names
        List<String> productNames = new ArrayList<String>();

        // Add the name of each found Apple product to the productNames list
        for (int i = 0; i < allAppleProducts.size(); i++) {
            String title = allAppleProducts.get(i).getAttribute("title");
            productNames.add(title);
        }

        System.out.println(productNames);

        // Assert that 'MacBook Air' is in the productNames list
        Assertions.assertThat(productNames).as("List should contain 'MacBook Air'").contains("MacBook Air");
    }
}
