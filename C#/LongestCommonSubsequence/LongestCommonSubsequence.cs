using System;

class LongestCommonSubsequence {
    public static void Main(string[] args) {
        string x;
        do {
            Console.Write("Enter first word: ");
            x = Console.ReadLine() ?? string.Empty;
        } while(string.IsNullOrEmpty(x));

        string y;
        do {
            Console.Write("Enter second word: ");
            y = Console.ReadLine() ?? string.Empty;
        } while(string.IsNullOrEmpty(y));

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

