package springbook.leanringtest.junit;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class JUnitTest {

    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();

    @Test
    public void test1() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test3() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }
}
