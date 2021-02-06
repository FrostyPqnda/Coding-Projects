import java.security.SecureRandom;
import java.util.Scanner;
import java.util.InputMismatchException;

public class PasswordGenerator extends PasswordChecker
{
    // Credit to
    public static String generatePassword(int passwordLength)
    {
        final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_ALPHA = UPPER_ALPHA.toLowerCase();
        final String SPECIAL_CHARS = "\"\"'`~_=|({[.,/+#?!@$%^&*-]})";
        final String NUMBER = "0123456789"; 
        final String randString = UPPER_ALPHA + LOWER_ALPHA + SPECIAL_CHARS + NUMBER;
        final SecureRandom secRand = new SecureRandom();

        String passwordStr = "";

        if(passwordLength < 1) 
            throw new IllegalArgumentException();

        //StringBuilder sb = new StringBuilder(passwordLength);

        if(passwordLength < 8)
            passwordLength = 8;

        for(int i = 0; i < passwordLength; i++) {
            int randCharIndex = secRand.nextInt(randString.length());
            char randChar = randString.charAt(randCharIndex);
            //sb.append(randChar);
            passwordStr += randChar;
            System.out.println(passwordStr);
        }
        if(checkPassword(passwordStr))
            return passwordStr;
        else
            return generatePassword(passwordLength);
    }

    public static void main(String[] args) 
    { 
        Scanner scan = new Scanner(System.in);
        //boolean run = true;
        try {
            System.out.print("Enter a length: ");
            int input = scan.nextInt();
            String password = generatePassword(input);
            System.out.println("Your generated password: " + password);
            System.out.println(password.length());
        } catch(InputMismatchException e) {
            e.printStackTrace();
        }

        scan.close();
    } 
}