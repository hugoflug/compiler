package se.kth.hugosa.compiler;

public class Indenter {
    public static String indent(String s) {
        int tabs = 0;
        StringBuilder out = new StringBuilder();
        String lines[] = s.split("\\r?\\n");

        for (String line : lines) {
            if (line.matches(".*}|\\].*") && !line.matches(".*\\{}.*")) {
                tabs -= 1;
            }
            for (int i = 0; i < tabs; i++) {
                out.append("\t");
            }
            out.append(line + "\n");
            if (line.matches(".*\\{|\\[.*") && !line.matches(".*\\{}.*")) {
                tabs += 1;
            }
        }
        return out.toString();
    }
}
