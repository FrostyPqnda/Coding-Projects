class Program {
    public static void Main(string[] args) {
        string expr;
        do {
            Console.Write("Enter infix notation: ");
            ShuntingYard s = new ShuntingYard(expr = Console.ReadLine());
            string k = s.ToPostfix();
            Console.WriteLine(k);
        } while(!String.IsNullOrEmpty(expr));
        Console.ReadKey();
    }
}