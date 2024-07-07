import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

/**
 * Represents a LexicalAnalyzer that will be used 
 * to parse and analyze a given Java program.
 * 
 * @author Brian Pham
 */
public class LexicalAnalyzer {
    String file; // The file to be analyzed
    private List<String> keywords; // A list of Java keywords
    private List<String> operators; // A list of Java operators
    private List<String> separators; // A list of Java separators
    private List<String> extractedLiterals; // List of literals extracted from the file

    private List<String> parsedKeywords; // Parsed keywords, i.e class
    private List<String> parsedOperators; // Parsed operators, i.e +
    private List<String> parsedLiterals; // Parsed literals, i.e. true
    private List<String> parsedidentifiers; // Parsed variables, i.e. maxHeight
    private List<String> parsedSeparators; // Parsed delimiters., i.e. {} and ;

    /**
     * Initializes a newly created LexicalAnalyzer object
     * 
     * @param file A Java file
     */
    public LexicalAnalyzer(String file) {
        // Exit program if the object is not given a Java file
        if(!file.endsWith(".java")) {
            System.err.println("The file must have the .java extension!");
            System.exit(-1);
        }

        this.file = file;

        // Scan the files stored in the Java_Specification folder
        keywords = scanFile("Java_Specification/keywords.txt");
        operators = scanFile("Java_Specification/operators.txt");
        separators = scanFile("Java_Specification/separators.txt");

        // Initialized parsed list objects
        parsedKeywords = new ArrayList<>();
        parsedOperators = new ArrayList<>();
        parsedLiterals = new ArrayList<>();
        parsedidentifiers = new ArrayList<>();
        parsedSeparators = new ArrayList<>();
    }

    /**
     * Performs a lexical analysis of the given Java 
     * program specified by the LexicalAnalyzer object
     */
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
            printReport();
            br.close();
        } catch(IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Print the report of the lexical analysis
     */
    private void printReport() {
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
    }

    /**
     * Get a list of keywords extracted from the file
     * 
     * @return A list of keywords extracted from the file
     */
    public List<String> getKeywords() {
        return parsedKeywords;
    }

    /**
     * Get a list of operators extracted from the file
     * 
     * @return A list of operators extracted from the file
     */
    public List<String> getOperators() {
        return parsedOperators;
    }

    /**
     * Get a list of identifiers extracted from the file
     * 
     * @return A list of identifiers extracted from the file
     */
    public List<String> getIdentifiers() {
        return parsedidentifiers;
    }

    /**
     * Get a list of literals extracted from the file
     * 
     * @return A list of literals extracted from the file
     */
    public List<String> getLiterals() {
        return parsedLiterals;
    }

    /**
     * Get a list of separators extracted from the file
     * 
     * @return A list of separators extracted from the file
     */
    public List<String> getSeparators() {
        return parsedSeparators;
    }

    /**
     * Parse the line and extract the tokens into appropriate the appropriate List objects, 
     * i.e. tokens that match a keyword will be added into the parsedKeywords list object.
     * 
     * @param line The line specified in a Java program will be parsed
     */
    private void parseLine(String line) {
        if(line.trim().equals(""))
            return;
        
        String numericalRegex = "[+-]?([0-9]+([.][0-9]+)?|[.][0-9]+)";
        String booleanRegex = "(true|false|null)";
        String stringRegex = "\"([^\"]*)\"";
        String charRegex = "\'.{1}\'";
        String litRegex = String.format("(%s|%s|%s|%s)", numericalRegex, booleanRegex, stringRegex, charRegex);
        Pattern pattern = Pattern.compile(litRegex);

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
                if(!parsedidentifiers.contains(token) && token.matches("([_$]{0,}[A-Za-z]+[0-9]{0,}|[_$]+[A-Za-z0-9]+)"))
                    parsedidentifiers.add(token);
            }
        }
    }

    /**
     * Scan the file and extract its content into a 
     * List object.
     * 
     * @param file File to be rrad
     * @return A List object containing data extracted from the file
     */
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

    /**
     * Tokenizes the given String into an
     * array of String objects. 
     * 
     * The delimer used will be the whitespace 
     * character.
     * 
     * @param line String to be tokenized
     * @return An array of tokens parsed from the given String object
     */
    private String[] tokenize(String line) {
        String f = formatLine(line);
        String[] tokens = f.split(" ");

        // Return the unmodified tokens if the line
        // never had any literals to extract.
        if(extractedLiterals == null)
            return tokens;

        // Create a temp copy of the tokens
        String[] copy = new String[tokens.length];
        System.arraycopy(tokens, 0, copy, 0, tokens.length);

        // Update the size of the tokens array
        tokens = new String[copy.length + extractedLiterals.size()];
        int index = 0;

        // Re-add all of the previous tokens back into the tokens array
        for(String data : copy) 
            tokens[index++] = data;

        // Add all of the extracted literals into the tokens
        for(String lit : extractedLiterals)
            tokens[index++] = lit;

        return tokens;
    }

    /**
     * Formats the given String by separating every separators, operators, keywords, 
     * literals, etc by a single space to allow the analyzer easier parsing of the file.
     * 
     * @param line The String object to be formatted
     * @return The formatted String
     */
    private String formatLine(String line) {
        if(line.trim().startsWith("import") && line.trim().contains("*")) {
            line = line.replace("*", "");
        }
 
        line = removeLiteralsAndComments(line);
        
        String f = "";
        for(int i = 0; i < line.length(); i++) {
            String c = String.format("%c", line.charAt(i));
            if(separators.contains(c) || operators.contains(c)) {
                f += String.format(" %s ", c);
            } else {
                f += c;
            }
        }

        // Fix any non-arithmetic operators that were modified and remove excess whitespaces
        f = fixOperators(f).replaceAll("\\s+", " ").trim();
        return f;
    }

    /**
     * Remove the every Java literals and comments from the
     * given String object.
     * 
     * @param line The string object to be modified
     * @return The string object after removing all literals
     */
    private String removeLiteralsAndComments(String line) {
        String singleLine = "//(.*)"; // Regex for single line comments such as // 
        String multiLine = "\\/\\*(.|)*?\\*\\/"; // Regex for multi-line comments on a single line such /* */
        String commentRegex = String.format("(%s|%s)", singleLine, multiLine); // XOR regex for single-line regex and multi-line regex
        line = line.replaceAll(commentRegex, ""); // Remove comments
        
        String negNumRegex = "[-]([0-9]+([.][0-9]+)?|[.][0-9]+)"; // Regex for negative values
        String booleanRegex = "(true|false|null)"; // Regex for boolean values
        String stringRegex = "\"([^\"]*)\""; // Regex for String values
        String charRegex = "\'.{1}\'"; // Regex for character values
        String litRegex = String.format("(%s|%s|%s|%s)", booleanRegex, stringRegex, charRegex, negNumRegex); 
        Pattern p = Pattern.compile(litRegex);
        Matcher m = p.matcher(line);
        extractedLiterals = new ArrayList<>();
        // Move the literals into a List object 
        while(m.find()) {
            extractedLiterals.add(m.group());
        }
        
        line = line.replaceAll(litRegex, " "); // Remove literals
        line = line.replaceAll("\\s+", " ").trim(); // Remove all excess whitespaces

        return line;
    }

    /**
     * Fix any non-arithmetic operators that were modified after
     * it was formatted.
     * 
     * @param line The String object to be modified
     * @return The modified String object
     */
    private String fixOperators(String line) {
        line = line.replaceAll("\\+\s+\\+", "++")
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
        .replaceAll("<\s+<\s+=", "<<=")
        .replaceAll("<\s+<", "<<")
        .replaceAll(">\s+>\s+>\s+=", ">>>=")
        .replaceAll(">\s+>\s+>", ">>>")
        .replaceAll(">\s+>\s+=", ">>=")
        .replaceAll(">\s+>", ">>")
        .replaceAll("\\|\s+\\|", "||")
        .replaceAll("&\s+&", "&&")
        .replaceAll("\\.\s{0,}\\.\s{0,}\\.", "...")
        .replace(":\s+:", "::");
        
        return line;
    }
}