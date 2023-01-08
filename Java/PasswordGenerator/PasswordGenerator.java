import java.security.SecureRandom;

public class PasswordGenerator extends PasswordChecker
{
    static final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWER_ALPHA = UPPER_ALPHA.toLowerCase();
    static final String SPECIAL_CHARS = "\"\"'`~_=|({[.<>,/+#?!@$%^&*-]})";
    static final String NUMBER = "0123456789"; 
    static final String randString = UPPER_ALPHA + LOWER_ALPHA + SPECIAL_CHARS + NUMBER;

    public static void main(String[] args)
    {
        System.out.println(generatePassword(10));
    }

    // Creates a randomized password with uppercase and lowercase letters, numbers, and special characters
    static String generatePassword(int passwordLength)
    {
        SecureRandom secRand = new SecureRandom();
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

        return (checkPassword(passwordStr)) ? passwordStr : generatePassword(passwordLength);
    }
}
