package se.kth.hugosa.compiler.table;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackMapTest {
    @Test
    public void testStackMap() throws Exception {
        StackMap<String, String> testMap = new StackMap<String, String>();

        try {
            String value = testMap.get("asdfdafdsg");
            Assert.fail("NoSuchElementException should be thrown. Instead returned: " + value);
        } catch (NoSuchElementException e) {}

        testMap.insert("key", "value");
        testMap.insert("foo", "bar");


        Assert.assertEquals(testMap.get("key"), "value");
        Assert.assertEquals(testMap.get("foo"), "bar");

        try {
            String value = testMap.get("asdfdafdsg");
            Assert.fail("NoSuchElementException should be thrown. Instead returned: " + value);
        } catch (NoSuchElementException e) {}

        testMap.insert("foo", "cola");
        Assert.assertEquals(testMap.get("foo"), "cola");

        testMap.insert("foo", "zola");
        Assert.assertEquals(testMap.get("foo"), "zola");

        testMap.pop("foo");
        Assert.assertEquals(testMap.get("foo"), "cola");

        testMap.pop("foo");
        Assert.assertEquals(testMap.get("foo"), "bar");

        testMap.pop("foo");

        try {
            String value = testMap.get("foo");
            Assert.fail("NoSuchElementException should be thrown. Instead returned: " + value);
        } catch (NoSuchElementException e) {}

        try {
            testMap.pop("foo");
            Assert.fail("NoSuchElementException should be thrown.)");
        } catch (NoSuchElementException e) {}

    }
}
