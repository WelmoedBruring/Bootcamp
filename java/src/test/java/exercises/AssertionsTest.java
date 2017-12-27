package exercises;

import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;
import org.testng.asserts.Assertion;

public class AssertionsTest {

    @Test
    public void multiplicationAssertion() {
        Assertions.assertThat(3*3).as("Multiplication assertion").isEqualTo(9);
    }

    @Test
    public void booleanAssertion() {
        Assertions.assertThat(true).as("Boolean assertion").isEqualTo(true);
    }

    @Test
    public void textAssertion() {
        Assertions.assertThat("Hello world!").as("Text assertion").contains("Hello");
    }
}
