/**
 * Short and simple Java program that rolls
 * two dice values [1 - 6] and counts how
 * many rolls it took to get snake eyes [1, 1]
 */
public class SnakeEyes
{
    public static void main(String[] args)
    {
        int count = 0; // Counting variable. Counts the amount of rolls it took to get snake eyes

        while(true)
        {
            count++;
            int rollOne = (int)(Math.random() * 6) + 1; // Dice 1 value from 1 - 6 inclusive
            int rollTwo = (int)(Math.random() * 6) + 1; // Dice 2 value from 1 - 6 inclusive
            
            System.out.println("Rolled: [" + rollOne + ", " + rollTwo + "]");
            
            if(rollOne == 1 && rollTwo == 1)
            {
                System.out.println("It took the program " + count + " rolls to get snake eyes.");
                break;
            }

            
        }
    }
}