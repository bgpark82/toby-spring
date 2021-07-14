package springbook.leanringtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {

    Calculator calculator;
    String path;

    @Before
    public void setUp() throws Exception {
        this.calculator = new Calculator();
        this.path = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(path);
        assertThat(sum, is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        int multiply = calculator.calcMultiply(path);
        assertThat(multiply, is(24));
    }
}
