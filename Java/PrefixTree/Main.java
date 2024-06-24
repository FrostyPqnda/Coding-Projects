public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("javac Main.java");
            System.err.println("java Main <Word 1> ... <Word n>");
            System.exit(-1);
        }

        Trie pt = new Trie();
        for(String word : args)
            pt.insert(word);
        pt.print();

        pt.remove("train"); 

        System.out.println();
        pt.print();
    }
}
