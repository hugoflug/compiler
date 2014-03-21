package se.kth.hugosa.compiler.parser;

import java.io.InputStream;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.hugosa.compiler.Indenter;
import se.kth.hugosa.compiler.ast.Program;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MiniJavaParserTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception {
        InputStream stream = new FileInputStream("tests/MiniJavaTest1.mj");
        MiniJavaParser parser = new MiniJavaParser(stream);
        Program program = parser.parse();
        System.out.println(Indenter.indent(program.toString()));
    }
/*
    @Test(expected = Exception.class)
    public void negativeTestParse() throws Exception {
        InputStream stream = new FileInputStream("tests/MiniJavaTest1_negative.mj");
        MiniJavaParser parser = new MiniJavaParser(stream);
        Program program = parser.parse();
        System.out.println(program.toString());
    }
*/
}
