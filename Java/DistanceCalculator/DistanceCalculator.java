import java.util.Scanner;

public class DistanceCalculator
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter a number for X1: ");
        double x1 = scan.nextDouble();

        System.out.print("\nEnter a number for Y1: ");
        double y1 = scan.nextDouble();

        Point a = new Point(x1, y1);

        System.out.print("\nEnter a number for X2: ");
        double x2 = scan.nextDouble();

        System.out.print("\nEnter a number for Y2: ");
        double y2 = scan.nextDouble();

        Point b = new Point(x2, y2);

        double calcDistance = a.getDistance(b);
        System.out.println("\nThe distance between Point A: " + a +  " and Point B: " + b + " is " + calcDistance + " units.");

        scan.close();
    }
}
