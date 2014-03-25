package se.kth.hugosa.compiler.symboltable;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TableTest {
    @Test
    public void testTable() throws Exception {
        Table<String> table = new Table<String>();

        try {
            String value = table.get("asdasd");
            Assert.fail("Should have thrown NoSuchElementException. Instead returned: " + value);
        } catch (NoSuchElementException e) {}

        Assert.assertTrue(table.put("foo", "bar"));
        Assert.assertEquals(table.get("foo"), "bar");
        Assert.assertFalse(table.put("foo", "bar"));
        Assert.assertFalse(table.put("foo", "bar"));

        table.beginScope();
        Assert.assertTrue(table.put("foo", "zar"));
        Assert.assertEquals(table.get("foo"), "zar");
        Assert.assertFalse(table.put("foo", "car"));
        Assert.assertFalse(table.put("foo", "dar"));
        table.beginScope();
        Assert.assertTrue(table.put("goo", "gar"));
        Assert.assertTrue(table.put("foo", "tsar"));
        Assert.assertEquals(table.get("foo"), "tsar");
        Assert.assertEquals(table.get("goo"), "gar");
        Assert.assertFalse(table.put("foo", "car"));
        Assert.assertFalse(table.put("foo", "dar"));
        table.endScope();
        try {
            String value = table.get("goo");
            Assert.fail("Should have thrown NoSuchElementException. Instead returned: " + value);
        } catch (NoSuchElementException e) {}
        Assert.assertFalse(table.put("foo", "car"));
        Assert.assertEquals(table.get("foo"), "zar");
        table.endScope();
        Assert.assertEquals(table.get("foo"), "bar");
        Assert.assertFalse(table.put("foo", "car"));
    }
}
