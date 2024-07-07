import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class LexicalAnalyzer {
    String file;
    private List<String> keywords;
    private List<String> operators;
    private List<String> separators;

    private List<String> parsedKeywords; // Parsed keywords, i.e class
    private List<String> parsedOperators; // Parsed operators, i.e +
    private List<String> parsedLiterals; // Parsed literals, i.e. true
    private List<String> parsedidentifiers; // Parsed variables, i.e. maxHeight
    private List<String> parsedSeparators; // Parsed delimiters., i.e. {} and ;
    //private List<String> parsedComments; // Parsed comments

    private List<String> extractedLiterals; // Extract the literals from the original file

    public LexicalAnalyzer(String file) throws FileNotFoundException {
        this.file = file;
        keywords = scanFile("keywords.txt");
        operators = scanFile("operators.txt");
        separators = scanFile("separators.txt");

        parsedKeywords = new ArrayList<>();
        parsedOperators = new ArrayList<>();
        parsedLiterals = new ArrayList<>();
        parsedidentifiers = new ArrayList<>();
        parsedSeparators = new ArrayList<>();
        //parsedComments = new ArrayList<>();
    }

    public void analyze() {
        try {
            System.out.printf("Lexical Analysis of %s\n", file);
            Scanner sc = new Scanner(new File(file));
            while(sc.hasNextLine()) {
                parseLine(sc.nextLine());
            }
            
            System.out.println("Extracted keywords:");
            //System.out.printf("\t%s\n", parsedKeywords);
            parsedKeywords.forEach(keyword -> System.out.printf("\t%s\n", keyword));

            System.out.println("Extracted operators:");
            //System.out.printf("\t%s\n", parsedOperators);
            parsedOperators.forEach(operator -> System.out.printf("\t%s\n", operator));

            System.out.println("Extracted identifiers:");
            //System.out.printf("\t%s\n", parsedidentifiers);
            parsedidentifiers.forEach(identifier -> System.out.printf("\t%s\n", identifier));

            System.out.println("Extracted literals:");
            //System.out.printf("\t%s\n", parsedLiterals);
            parsedLiterals.forEach(literal -> System.out.printf("\t%s\n", literal));
            
            System.out.println("Extracted separators:");
            //System.out.printf("\t%s\n", parsedSeparators);
            parsedSeparators.forEach(separator -> System.out.printf("\t%s\n", separator));

            sc.close();
        } catch(FileNotFoundException f) {
            f.printStackTrace();
        }
    }

    public List<String> getKeywords() {
        return parsedKeywords;
    }

    public List<String> getOperators() {
        return parsedOperators;
    }

    public List<String> getIdentifiers() {
        return parsedidentifiers;
    }

    public List<String> getLiterals() {
        return parsedLiterals;
    }

    public List<String> getSeparators() {
        return parsedSeparators;
    }

    private void parseLine(String line) {
        if(line.trim().equals(""))
            return;

        String[] tokens = tokenize(line);
        String numericalRegex = "[+-]?([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false)";
        String stringRegex = "\".*\"";
        String charRegex = "\'.{1}\'";
        String regex = String.format("(%s|%s|%s|%s)", numericalRegex, booleanRegex, stringRegex, charRegex);
        Pattern pattern = Pattern.compile(regex);

        for(String token : tokens) {
            if(keywords.contains(token)) {
                if(!parsedKeywords.contains(token))
                    parsedKeywords.add(token);
            } else if(operators.contains(token)) {
                if(!parsedOperators.contains(token))
                    parsedOperators.add(token);
            } else if(separators.contains(token)) {
                if(!parsedSeparators.contains(token))
                    parsedSeparators.add(token);
            } else if(pattern.matcher(token).matches()) {
                if(!parsedLiterals.contains(token))
                    parsedLiterals.add(token);
            } else {
                if(!parsedidentifiers.contains(token))
                    parsedidentifiers.add(token);
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
        String f = formatLine(line);
        if(extractedLiterals == null)
            return f.split(" ");

        int N = 0, index = f.indexOf(" ");
        while(index != -1) {
            N++;
            index = f.indexOf(" ", index + 1);
        }

        String[] sp = new String[N + extractedLiterals.size()];
        int pos = 0, offset = 0, idx = f.indexOf(" ");

        while(idx != -1) {
            sp[pos++] = f.substring(offset, idx);
            offset = idx + 1;
            idx = f.indexOf(" ", offset);
        }

        for(String s : extractedLiterals) 
            sp[pos++] = s;

        return sp;
    }

    private String formatLine(String line) {
        String numericalRegex = "[-]?([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false)";
        String stringRegex = "\".*\"";
        String charRegex = "\'.{1}\'";
        String regex = String.format("(%s|%s|%s|%s)", numericalRegex, booleanRegex, stringRegex, charRegex);    
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        extractedLiterals = new ArrayList<>();

        while(m.find()) {
            extractedLiterals.add(m.group());
        }

        String commentRegex = "//(.*)";

        line = line.replaceAll(regex, " ");
        line = line.replaceAll(commentRegex, "");
        line = line.replaceAll("\\s+", " ").trim();
        String f = "";
        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if(separators.contains(""+c) || operators.contains(""+c)) {
                f += String.format(" %c ", c);
            } else {
                f += c;
            }
        }

        // Fix any char literals that were modified 
        // Fix any operators that were modified
        f = f.replace("+  +", "++")
        .replace("-  -", "--")
        .replace("+  =", "+=")
        .replace("-  =", "-=")
        .replace("*  =", "*=")
        .replace("/  =", "/=")
        .replace("%  =", "%=")
        .replace("|  =", "|=")
        .replace("^  =", "^=")
        .replace("&  =", "&=")
        .replace("=  =", "==")
        .replace("!  =", "!=")
        .replace("<  =", "<=")
        .replace(">  =", ">=")
        .replace("<  <", "<<")
        .replace(">  >  >", ">>>")
        .replace(">  >", ">>");

        f = f.replaceAll("\\s+", " ").trim();
        return f;
    }
}