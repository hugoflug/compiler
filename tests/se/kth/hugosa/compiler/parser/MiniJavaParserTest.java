package se.kth.hugosa.compiler.parser;

import java.io.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.hugosa.compiler.Indenter;
import se.kth.hugosa.compiler.ast.Program;

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
        File dir = new File("tests/minijava/parse/positive");
        for (File file : dir.listFiles()) {
            InputStream stream = new FileInputStream(file);
            MiniJavaParser parser = new MiniJavaParser(stream);
            Program program = parser.parse();
            System.out.println(file.getName());
            System.out.println("============");
            System.out.println(Indenter.indent(program.toString()));
            System.out.println("\n\n");
        }
    }

    @Test
    public void testParseNegative() throws Exception {
        File dir = new File("tests/minijava/parse/negative");
        for (File file : dir.listFiles()) {
            InputStream stream = new FileInputStream(file);
            MiniJavaParser parser = new MiniJavaParser(stream);

            boolean throwException = false;
            try {
                Program program = parser.parse();
                throwException = true;
            } catch (Exception e) {

            }
            if (throwException) {
                throw new Exception("File: " + file + " was parsed but shouldn't have been.");
            }
        }
    }

}
