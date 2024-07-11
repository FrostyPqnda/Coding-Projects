public class Main {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("javac Main.java");
            System.err.println("java Main <Java file>");
            System.exit(-1);
        } 

        LexicalAnalyzer lex = new LexicalAnalyzer(args[0]);
        lex.analyze();
    }
}