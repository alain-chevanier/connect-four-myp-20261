package tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SumTest
 * Requirements:
 *  ▶ Empty string returns 0.
 *  ▶ Single number returns the number.
 *  ▶ Two numbers, comma delimited, returns the sum.
 */
public class StringCalculatorTest {
    @Test
    public void test_nullString() {
        var calculator = new StringCalculator();
        assertThat(calculator.add(null), is(0));
    }

    @Test
    public void test_emptyString() {
        var calculator = new StringCalculator();
        assertThat(calculator.add(""), is(0));
    }

    @Test
    public void test_singleNumber() {
        var calculator = new StringCalculator();
        assertThat(calculator.add("1234"), is(1234));
    }

    @Test void test_multipleNumbers() {
        var calculator = new StringCalculator();
        assertThat(calculator.add("234,234"), is(468));
    }
}
