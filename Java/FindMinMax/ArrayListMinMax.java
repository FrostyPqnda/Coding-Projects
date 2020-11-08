import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ArrayListMinMax extends FindMinMax
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        do {
            try {
                System.out.print("Please enter an Integer value [Enter a non-Integer value to quit]: ");
                numbers.add(scan.nextInt());
            } catch(InputMismatchException e) {
                break;
            }
        } while(true);
        
        System.out.println("Maximum: " + findMaximum(numbers));
        scan.close();
    }
}
