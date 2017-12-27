package exercises;

import org.testng.annotations.Test;

public class AboutMethods {

    @Test
    private void printProduct() {
        int result = multiply(2,3);
        System.out.println(result);
    }

    private int multiply (int a, int b) {
        return a * b;
    }

}
