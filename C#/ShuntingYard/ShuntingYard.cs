using System.Text.RegularExpressions;

/// <summary>
/// C# class that implements the Shunting Yard 
/// algorithm, which converts an infix notation
/// into a postfix notation
/// </summary>
public class ShuntingYard {
    string expr;
    
    /// <summary>
    /// Constructor for the ShuntingYard class
    /// </summary>
    /// <param name="expr">The infix notation</param>
    public ShuntingYard(string expr) {
        this.expr = expr;
    }

    /// <summary>
    /// Convert expr to postfix notation
    /// </summary>
    /// <returns>The postfix notation of expr</returns>
    public string ToPostfix() {
        Stack<string> st = new Stack<string>();
        string postfix = "";
        List<string> tokens = Tokenize(expr);

        foreach(string token in tokens) {
            if(IsOperator(token[0])) {
                while(st.Count > 0 && Precedence(token[0]) <= Precedence(st.Peek()[0]) && Associativity(token[0]) == 'L') {
                    postfix += $" {st.Pop()} ";
                }
                st.Push(token);
            } else if(token.Equals("(")) {
                st.Push(token);
            } else if(token.Equals(")")) {
                while(st.Count > 0 && !st.Peek().Equals("(")) {
                    postfix += $" {st.Pop()} ";
                }
                st.Pop();
            } else {
                postfix += $" {token} ";
            }
        }

        while(st.Count > 0) {
            postfix += $" {st.Pop()} ";
        }

        postfix = Regex.Replace(postfix, @"\s\s+", " ").Trim();
        return postfix;
    }


    /// <summary>
    /// Parse the expression into a list of tokens
    /// </summary>
    /// <param name="expr">The expression to be parsed</param>
    /// <returns>A list of tokens parsed from the expression</returns>
    /// <exception cref="Exception">
    /// Thrown if any of the following conditions are met:
    /// 1. An invalid token was detected
    /// 2. The operand could not be tokenized
    /// 3. The expression contains no operators
    /// 4. The operator count is greater than the operand count
    /// 5. The no. of '(' does not match the no. of ')'
    /// </exception>
    private List<string> Tokenize(string expr) {
        expr = Regex.Replace(expr, @"\s", string.Empty)
        .Replace("(-", "(0-")
        .Replace("+-", "-")
        .Replace("-+", "-")
        .Replace("--", "+")
        .Replace("**", "^")
        .Replace(")(", ")*(");

        if(expr.StartsWith('-'))
            expr = "0" + expr;

        List<string> tokens = new List<string>();
        int numOperators = 0;
        int numOperands = 0;
        int numOpen = 0;
        int numClosed = 0;
        int numInvalid = 0;
        Regex re = new Regex(@"[0-9]+");

        for(int i = 0; i < expr.Length;) {
            char c = expr[i];
            if(IsOperator(c)) {
                numOperators++;
                tokens.Add($"{c}");
            } else if(c == '(') {
                numOpen++;
                tokens.Add($"{c}");
            } else if(c == ')') {
                numClosed++;
                tokens.Add($"{c}");
            } else if(re.IsMatch($"{c}")) {
                numOperands++;
                string value = $"{c}";
                while(i + 1 < expr.Length && !IsOperator(expr[i + 1]) && expr[i + 1] != '(' && expr[i + 1] != ')') {
                    value += expr[i + 1];
                    i++;
                }
                tokens.Add(value);
            } else {
                numInvalid++;
            }
            i++;
        }

        if(numInvalid != 0) {
            throw new Exception("Invalid token detected!");
        }
        
        
        if(numOperators <= 0) {
            throw new Exception("No arithmetic operators detected");
        }

        if(numOperators >= numOperands) {
            throw new Exception("Operator count >= Operand count");
        }

        if(numOpen != numClosed) {
            throw new Exception("Expression must be balanced");
        }

        return tokens;
    }

    /// <summary>
    /// Get the precedence value of the operator
    /// </summary>
    /// <param name="op">An arithmetic operator</param>
    /// <returns>The precedence value of the operator</returns>
    private int Precedence(char op) {
        int prec = -1;

        if(op == '^') {
            prec = 3;
        } else if(op == '*' || op == '/') {
            prec = 2;
        } else if(op == '+' || op == '-') {
            prec = 1;
        }

        return prec;  
    }

    /// <summary>
    /// Get the associtivity of an operator
    /// </summary>
    /// <param name="op">The operator</param>
    /// <returns>R if an operator is '^'</returns>
    private char Associativity(char op) {
        return (op == '^') ? 'R' : 'L';
    }

    /// <summary>
    /// Check if the character is an arithmetic operator
    /// </summary>
    /// <param name="op">The character to be checked</param>
    /// <returns>True if the character is an arithmetic operator</returns>
    private bool IsOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '^';
    }
}