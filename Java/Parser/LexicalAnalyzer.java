import java.util.*;
import java.io.*;

class LexicalAnalyzer {
    /* Character classes */
    final int LETTER = 0;
    final int DIGIT = 1;
    final int UNKNOWN = 99;

    /* Token codes */
    final int INT_LIT = 10;
    final int IDENT = 11;
    final int ASSIGN_OP = 20;
    final int ADD_OP = 21;
    final int SUB_OP = 22;
    final int MULT_OP = 23;
    final int DIV_OP = 24;
    final int LEFT_PAREN = 25;
    final int RIGHT_PAREN = 26;

    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("javac LexicalAnalyzer.java");
            System.err.println("java LexicalAnalyzer <source code>");
            System.exit(-1);
        } 
    }
}