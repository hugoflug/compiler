package se.kth.hugosa.compiler;

import org.junit.Test;

public class IndenterTest {
    @Test
    public void testIndent() throws Exception {
        String s = "hej{\nho\n}\nhallo{\nhej{\njo\n}\n}\nko{}list[\n]";
        System.out.println(s);
        System.out.println(Indenter.indent(s));
    }
}
