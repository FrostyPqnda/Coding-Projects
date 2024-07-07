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

    private List<String> extractedLiterals; // Extract the literals from the original file

    public LexicalAnalyzer(String file) {
        this.file = file;
        // Scan the files stored in the Java_Specification folder
        keywords = scanFile("Java_Specification/keywords.txt");
        operators = scanFile("Java_Specification/operators.txt");
        separators = scanFile("Java_Specification/separators.txt");

        parsedKeywords = new ArrayList<>();
        parsedOperators = new ArrayList<>();
        parsedLiterals = new ArrayList<>();
        parsedidentifiers = new ArrayList<>();
        parsedSeparators = new ArrayList<>();
    }

    public void analyze() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                // Skip multi-line comments
                if(line.trim().startsWith("/*")) {
                    while(!line.trim().endsWith("*/")) {
                        line = br.readLine();
                    }
                    if(line.trim().endsWith("*/")) {
                        line = br.readLine();
                    }
                }
                parseLine(line);
            }

            System.out.printf("Lexical Analysis of %s\n", file);

            System.out.println("Extracted keywords:");
            parsedKeywords.forEach(keyword -> System.out.printf("\t%s\n", keyword));

            System.out.println("Extracted operators:");
            parsedOperators.forEach(operator -> System.out.printf("\t%s\n", operator));

            System.out.println("Extracted identifiers:");
            parsedidentifiers.forEach(identifier -> System.out.printf("\t%s\n", identifier));

            System.out.println("Extracted literals:");
            parsedLiterals.forEach(literal -> System.out.printf("\t%s\n", literal));
            
            System.out.println("Extracted separators:");
            parsedSeparators.forEach(separator -> System.out.printf("\t%s\n", separator));

            br.close();
        } catch(IOException f) {
            f.printStackTrace();
        }
    }

    public void analyze(int lineNum) {
        System.out.printf("Lexical Analysis of %s, Line %d\n", file, lineNum);
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
        
        String numericalRegex = "[+-]?([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false)";
        String stringRegex = "\"([^\"]*)\"";
        String charRegex = "\'.{1}\'";
        String regex = String.format("(%s|%s|%s|%s)", numericalRegex, booleanRegex, stringRegex, charRegex);
        Pattern pattern = Pattern.compile(regex);

        String[] tokens = tokenize(line);
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
                if(!parsedidentifiers.contains(token) && token.matches("([_$]?[A-Za-z]+[0-9]+|[_$]+[A-Za-z0-9]+)"))
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

        String[] sp = new String[N + 1 + extractedLiterals.size()];
        int pos = 0, offset = 0, idx = f.indexOf(" ");

        while(idx != -1) {
            sp[pos++] = f.substring(offset, idx);
            offset = idx + 1;
            idx = f.indexOf(" ", offset);
        }
        sp[pos++] = f.substring(offset);

        for(String s : extractedLiterals) 
            sp[pos++] = s;

        return sp;  
    }

    private String formatLine(String line) {
        String negNumRegex = "[-]([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false|null)";
        String stringRegex = "\"([^\"]*)\"";
        String charRegex = "\'.{1}\'";
        
        String regex = String.format("(%s|%s|%s|%s)", booleanRegex, stringRegex, charRegex, negNumRegex);    
        
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        extractedLiterals = new ArrayList<>();

        while(m.find()) {
            extractedLiterals.add(m.group());
        }

        String singleLine = "//(.*)";
        String multiLine = "\\/\\*(.|)*?\\*\\/";
        String commentRegex = String.format("(%s|%s)", singleLine, multiLine);

        line = line.replaceAll(regex, " ");
        line = line.replaceAll("\\s+", " ").trim();
        line = line.replaceAll(commentRegex, "");
        String f = "";
        for(int i = 0; i < line.length(); i++) {
            String c = String.format("%c", line.charAt(i));
            if(separators.contains(c) || operators.contains(c)) {
                f += String.format(" %s ", c);
            } else {
                f += c;
            }
        }

        // Fix any non-arithmetic operators that were modified
        f = f.replaceAll("\\+\s+\\+", "++")
        .replaceAll("-\s+-", "--")
        .replaceAll("\\+\s+=", "+=")
        .replaceAll("-\s+=", "-=")
        .replaceAll("\\*\s+=", "*=")
        .replaceAll("/\s+=", "/=")
        .replaceAll("%\s+=", "%=")
        .replaceAll("\\|\s+=", "|=")
        .replaceAll("\\^\s+=", "^=")
        .replaceAll("&\s+=", "&=")
        .replaceAll("=\s+=", "==")
        .replaceAll("!\s+=", "!=")
        .replaceAll("<\s+=", "<=")
        .replaceAll(">\s+=", ">=")
        .replaceAll("<\s+<", "<<")
        .replaceAll(">\s+>\s+>", ">>>")
        .replaceAll(">\s+>", ">>");

        f = f.replaceAll("\\s+", " ").trim();
        return f;
    }
}