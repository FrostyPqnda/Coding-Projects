class Program {
    public static void Main(string[] args) {
        ShuntingYard s = new ShuntingYard("12 + 3 * (3 ^ 2 - 3) ^ (4 + 10 * 5) - 5");
        string k = s.ToPostfix();
        Console.WriteLine(k);
        Console.ReadKey();
    }
}