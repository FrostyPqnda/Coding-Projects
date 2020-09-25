import java.util.Scanner;

public class TripleDigits extends TripleDigitFormatter
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter a number: ");
        int num = scan.nextInt();

        String str = tripleDigitFormatter(num);

        System.out.println(str);
        scan.close();
    }
}
