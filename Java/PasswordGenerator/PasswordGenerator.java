import java.security.SecureRandom;

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
        
        // If password length is less than 8, set it to 8
        if(passwordLength < 8)
            passwordLength = 8; 
        
        for(int i = 0; i < passwordLength; i++) {
            int randCharIndex = secRand.nextInt(randString.length());
            char randChar = randString.charAt(randCharIndex);
            passwordStr += randChar; // Adds the random character to the password string
        }

        if(checkPassword(passwordStr))
            return passwordStr; // Returns the password only if it is alpha-numerica and has at least 1 special character.
        else
            return generatePassword(passwordLength); // If checkPassword is false, then run the method again recursively until checkPassword is true.
    }
}