import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 1) {
            System.err.println("javac Main.java");
            System.err.println("java Main <Java file>");
            System.exit(-1);
        } 
        
        if(!args[0].endsWith(".java")) {
            System.err.println("The file must have the .java extension!");
            System.exit(-1);
        }

        LexicalAnalyzer lex = new LexicalAnalyzer(args[0]);
        lex.analyze();
    }
}
