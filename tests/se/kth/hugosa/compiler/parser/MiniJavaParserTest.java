package se.kth.hugosa.compiler.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by hugo on 2/27/14.
 */
public class MiniJavaParserTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testStart() throws Exception {
        InputStream stream = new ByteArrayInputStream("public class { a = 5; b = 7; z = 3; }".getBytes("UTF-8"));
        MiniJavaParser parser = new MiniJavaParser(stream);
        parser.start();
    }
}
