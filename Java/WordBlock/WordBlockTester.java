import java.util.Scanner;

public class WordBlockTester 
{
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.print("Enter a word: ");
        String word = scan.nextLine();
        int row, col;

        row = readRow();
        col = readColumn();
        
        WordBlock wb = new WordBlock(word, row, col);
        System.out.println("\n" + wb);
    }

    static int readRow() 
    {
        int row;

        do {
            System.out.print("Enter a row value: ");
            row = scan.nextInt();

            if(row < 0) {
                System.out.println("Row value must be 0 or greater!");
            }
        } while(row < 0);

        return row;
    }

    static int readColumn()
    {
        int column;

        do {
            System.out.print("Enter a column value: ");
            column = scan.nextInt();

            if(column < 0) {
                System.out.println("Column value must be 0 or greater!");
            }
        } while(column < 0);

        return column;
    }
}
