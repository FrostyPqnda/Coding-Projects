using System.Text.RegularExpressions;

public class Infix {
    public static void Main(string[] args) {
        Console.Write("Enter expression: ");
        string exp = Console.ReadLine();
        double eval = Evaluate(exp);
        Console.WriteLine($"Value = {eval}");
        Console.ReadKey();
    }
    
    /// <summary>
    /// Evaluates the arithmetic expression
    /// </summary>
    /// <param name="exp">The expression to be solved</param>
    /// <returns>The solution to the expression</returns>
    static double Evaluate(string exp) {
        List<string> tokens = Tokenize(exp);
        Stack<double> operands = new Stack<double>();
        Stack<char> operators = new Stack<char>();

        string pattern = @"[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)";
        Regex re = new Regex(pattern);

        foreach(string token in tokens) {
            if(re.IsMatch(token)) {
                double value = double.Parse(token);
                operands.Push(value);
            } else if(token.Equals("(")) {
                operators.Push(token[0]);
            } else if(token.Equals(")")) {
                while(operators.Peek() != '(') {
                    operands.Push(Operate(operands.Pop(), operands.Pop(), operators.Pop()));
                }
                operators.Pop();
            } else if(IsOperator(token)) {
                while(operators.Count > 0 && Precedence(token[0]) <= Precedence(operators.Peek())) {
                    operands.Push(Operate(operands.Pop(), operands.Pop(), operators.Pop()));
                }
                operators.Push(token[0]);
            }
        }

        while(operators.Count > 0) {
            operands.Push(Operate(operands.Pop(), operands.Pop(), operators.Pop()));
        }

        return operands.Pop();
    }

    /// <summary>
    /// Evaluate the values of two operands given
    /// an operator
    /// </summary>
    /// <param name="a">An operand</param>
    /// <param name="b">An operand</param>
    /// <param name="op">The operator</param>
    /// <returns>The evaluated value of the two operands</returns>
    static double Operate(double a, double b, char op) {
        double res = -1;

        switch(op) {
            case '+':
                res = (double)Math.Round((a + b) * 10) / 10;
                break;
            case '-':
                res = (double)Math.Round((b - a) * 10) / 10;
                break;
            case '*':
                res = (double)Math.Round((a * b) * 10) / 10;
                break;
            case '/':
                if(a == 0) 
                    throw new DivideByZeroException("Cannot divide by 0!");

                res = (double)Math.Round((b / a) * 10) / 10;
                break;
            case '^':
                res = (double)Math.Round(Math.Pow(b, a) * 10) / 10;
                break;
        }

        return res;
    }
    
    /// <summary>
    /// Parse the expression into a list of tokens
    /// </summary>
    /// <param name="expr">The expression to be parsed</param>
    /// <returns>A list of tokens parsed from the expression</returns>
    /// <exception cref="Exception">
    /// Thrown if any of the following conditions are met:
    /// 1. The operand could not be tokenized
    /// 2. The expression contains no operators
    /// 3. The operator count is greater than the operand count
    /// 4. The no. of '(' does not match the no. of ')'
    /// </exception>
    static List<string> Tokenize(string expr) {
        expr = Regex.Replace(expr, @"\s", string.Empty)
        .Replace("+-", "-")
        .Replace("-+", "-")
        .Replace("--", "+")
        .Replace("**", "^")
        .Replace(")(", ")*(");

        List<string> tokens = new List<string>();
        int i = 0;
        string pattern = @"[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)";
        Regex re = new Regex(pattern);

        int numOperators = 0;
        int numOperands = 0;
        int numOpen = 0;
        int numClosed = 0;

        while(i < expr.Length) {
            char c = expr[i];
            if(IsOperator("" + c)) {
                numOperators += 1;
                tokens.Add($"{c}");
            } else if(c == '(') {
                if(tokens.Count > 0 && re.IsMatch(tokens[tokens.Count - 1])) {
                    numOperators += 1;
                    tokens.Add("*");
                }
                numOpen++;
                tokens.Add($"{c}");
            } else if(c == ')') {
                numClosed++;
                tokens.Add($"{c}");
            } else {
                numOperands++;
                string value = $"{c}";
                while(i + 1 < expr.Length && !IsOperator("" + expr[i + 1]) && expr[i + 1] != '(' && expr[i + 1] != ')') {
                    value += expr[i + 1];
                    i += 1;
                }
                if(value.Count(t => t == '.') > 1) 
                    throw new Exception("Operand could not be parsed!"); 

                int size = tokens.Count;
                if(size == 1 && tokens[0].Equals("-")) {
                    numOperators--;
                    tokens[0] = $"-{value}";
                } else if(size >= 2 && (IsOperator(tokens[size - 2]) || tokens[size - 2].Equals("(")) && tokens[size - 1].Equals("-")) {
                    Console.WriteLine("APPLE");
                    tokens.ForEach(token => Console.WriteLine($"T = {token}"));
                    numOperators -= 1;
                    tokens[size - 1] = $"-{value}";
                } else {
                    tokens.Add(value);
                }
            }

            i += 1;
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
    static int Precedence(char op) {
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
    /// Check if the character is an arithmetic operator
    /// </summary>
    /// <param name="op">The character to be checked</param>
    /// <returns>True if the character is an arithmetic operator</returns>
    static bool IsOperator(string op) {
        return op.Equals("+") ||
        op.Equals("-") ||
        op.Equals("*") ||
        op.Equals("/") ||
        op.Equals("^");
    }
}
