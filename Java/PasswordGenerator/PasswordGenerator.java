import java.security.SecureRandom;

public class PasswordGenerator extends PasswordChecker
{
    public static void main(String[] args)
    {
        System.out.println(generatePassword(10));
    }

    // Creates a randomized password with uppercase and lowercase letters, numbers, and special characters
    static String generatePassword(int passwordLength)
    {
        final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_ALPHA = UPPER_ALPHA.toLowerCase();
        final String SPECIAL_CHARS = "\"\"'`~_=|({[.<>,/+#?!@$%^&*-]})";
        final String NUMBER = "0123456789"; 
        final String randString = UPPER_ALPHA + LOWER_ALPHA + SPECIAL_CHARS + NUMBER;
        final SecureRandom secRand = new SecureRandom();

        String passwordStr = "";
        
        // Sets the length between 8 - 20 if it is out of bound
        if(passwordLength < 8 || passwordLength > 20)
        {
            passwordLength = (int)((Math.random() * 13) + 8);
        } 
        
        // Loops through the value of the password length and appends a random letter 
        // to the empty password string
        for(int i = 0; i < passwordLength; i++) 
        {
            int randCharIndex = secRand.nextInt(randString.length());
            // Appends the randomized letter/number/special character to the password string
            passwordStr += randString.charAt(randCharIndex);
        }

        if(checkPassword(passwordStr))
        {
            // Returns the password only if it is alpha-numeric and has at least 1 special character.
            return passwordStr; 
        }
        else
        {
            // If checkPassword is false, then run the function again until it is true.
            return generatePassword(passwordLength); 
        }
    }
}
