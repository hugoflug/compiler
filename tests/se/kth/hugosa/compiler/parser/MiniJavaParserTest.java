package se.kth.hugosa.compiler.parser;

import java.io.InputStream;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
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
        InputStream stream = new FileInputStream("tests/MiniJavaTest1.mj");
        MiniJavaParser parser = new MiniJavaParser(stream);
        parser.Program();
    }
}
