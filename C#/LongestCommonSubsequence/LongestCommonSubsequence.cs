using System;

class LongestCommonSubsequence {
    public static void Main(string[] args) {
        Console.Write("Enter first word: ");
        string x = Console.ReadLine() ?? String.Empty;  
        
        Console.Write("Enter second word: ");
        string y = Console.ReadLine() ?? String.Empty;

        if(string.IsNullOrEmpty(x) || string.IsNullOrEmpty(y))
            Environment.Exit(-1);

        int lcs = LCS(x, y);
        Console.WriteLine($"The longest common subsequence between {x} and {y} is {lcs}");
        Console.ReadKey();
    }

    // Return the length of the longest common subsequence
    // between [word1] and [word2].
    static int LCS(string word1, string word2) {
        int m = word1.Length, n = word2.Length;
        int[,] lcs = new int[m + 1, n + 1];

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1[i - 1] == word2[j - 1]) {
                    lcs[i, j] = lcs[i - 1, j - 1] + 1;
                } else {
                    lcs[i, j] = Math.Max(lcs[i - 1, j], lcs[i, j - 1]);
                }
            }
        }

        return lcs[m, n];
    }
}

