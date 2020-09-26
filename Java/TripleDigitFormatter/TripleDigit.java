import java.util.Scanner;

public class TripleDigit extends TripleDigitFormatter
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        // Integer
        System.out.print("Enter a number [Must be at least 0]: ");
        int num = scan.nextInt();

        String str = tripleDigitFormatter(num);
        System.out.println(str);

        // Letters
        System.out.print("Enter a letter in the alphabet: ");
        char letter = scan.next().charAt(0);

        String alpha = tripleDigitFormatter(letter);
        System.out.println(alpha);
        
        scan.close();
    }
}
