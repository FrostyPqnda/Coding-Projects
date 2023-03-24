using System;
using System.Collections.Generic;

public class Infix {
    static Stack<Double> operand;
    static Stack<Char> map;

    public static void Main(string[] args) {
        operand = new Stack<Double>();
        map = new Stack<Char>();

        Console.Write("Enter expression: ");
        string exp = Console.ReadLine();
        
        Console.WriteLine(Evaluate(exp));
        Console.ReadKey();
    }

    static double Evaluate(string exp) {
        string[] split = exp.Split(" ");
        for(int i = 0; i < split.Length; i++) {
            char c = split[i][0];
            if(Char.IsDigit(c)) {
                double x = Double.Parse(split[i]);
                operand.Push(x);
            } else if(c == '(' ) {
                map.Push(c);
            } else if(c == ')') {
                while(map.Peek() != '(') {
                    Operate();
                }
                map.Pop();
            } else if(IsOperator(c)) {
                while(map.Count > 0 && Order(c) <= Order(map.Peek())) {
                    Operate();
                }
                map.Push(c);
            }
        }

        while(map.Count > 0) {
            Operate();
        }   
        return operand.Pop();
    }

    static bool IsOperator(char c) {
        return c == '*' || c == '/' || c == '+' || c == '-' || c == '^';
    }

    static int Order(char c) {
        int prec = -1;

        if(c == '^') {
            prec = 3;
        } else if(c == '*' || c == '/') {
            prec = 2;
        } else if(c == '+' || c == '-') {
            prec = 1;
        }

        return prec;  
    }

    static void Operate() {
        double res = -1;
        double a = operand.Pop();
        double b = operand.Pop();
        char op = map.Pop();

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
                try {
                    res = (double)Math.Round((b / a) * 10) / 10;
                } catch(DivideByZeroException) {
                    Console.WriteLine("Division of {0} by zero.", b);
                }
                break;
            case '^':
                res = (double)Math.Round(Math.Pow(b, a) * 10) / 10;
                break;
        }

        operand.Push(res);
        return;
    }
}
