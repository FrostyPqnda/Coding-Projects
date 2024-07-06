import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class LexicalAnalyzer {
    String file;
    private List<String> keywords;
    private List<String> operators;

    private List<String> parsedKeywords;
    private List<String> parsedOperators;
    private List<String> parsedLiterals;
    private List<String> parsedVariables; 
    
    public LexicalAnalyzer(String file) throws FileNotFoundException {
        this.file = file;
        keywords = scanFile("keywords.txt");
        operators = scanFile("operators.txt");

        parsedKeywords = new ArrayList<>();
        parsedOperators = new ArrayList<>();
        parsedLiterals = new ArrayList<>();
        parsedVariables = new ArrayList<>();
    }

    public void analyze() {
        try {
            System.out.printf("Lexical Analysis of %s\n", file);
            Scanner sc = new Scanner(new File(file));
            while(sc.hasNextLine()) {
                parseLine(sc.nextLine());
            }
            
            System.out.println("Extracted keywords:");
            for(String key : parsedKeywords) {
                System.out.printf("\t%s\n", key);
            }

            System.out.println("Extracted operators:");
            for(String op : parsedOperators) {
                System.out.printf("\t%s\n", op);
            }

            System.out.println("Extracted variables:");
            for(String var : parsedVariables) {
                System.out.printf("\t%s\n", var);
            }

            System.out.println("Extracted literals:");
            for(String lit : parsedLiterals) {
                System.out.printf("\t%s\n", lit);
            }
            sc.close();
        } catch(FileNotFoundException f) {
            f.printStackTrace();
        }
    }

    private void parseLine(String line) {
        String[] tokens = tokenize(line);      
        if(tokens.length <= 1)
            return;

        String numericalRegex = "[+-]?([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false)";
        String stringRegex = "\".*\"";
        String charRegex = "\'.{1}\'";
        String regex = String.format("(%s|%s|%s|%s)", numericalRegex, booleanRegex, stringRegex, charRegex);
        Pattern pattern = Pattern.compile(regex);

        for(String token : tokens) {
            if(keywords.contains(token)) {
                parsedKeywords.add(token);
            } else if(operators.contains(token)) {
                parsedOperators.add(token);
            } else if(pattern.matcher(token).matches()) {
                parsedLiterals.add(token);
            } else {
                parsedVariables.add(token);
            }
        }
    }

    private final List<String> scanFile(String file) {
        List<String> tokens = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(file)); 
            while(sc.hasNextLine())
                tokens.add(sc.nextLine());
            sc.close();
        } catch(FileNotFoundException f) {
            f.printStackTrace();
        }
        return tokens;
    }

    private String[] tokenize(String line) {
        String puncuatorRegex = "\\;|\\{|}|\\(|\\)|\\,|\\[|\\]";
        line = line.replaceAll(puncuatorRegex, " ");
        line = line.replaceAll("\\s+", " ").trim();
        String[] sp = line.split(" ");          
        String incdec = "";



        return sp;
    }
}