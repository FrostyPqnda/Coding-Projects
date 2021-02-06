import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Java program that checks if a password is long
 * enough and meets the requirements
 * 
 * Requirements:
 * 
 * Has letters, numbers, and special characters.
 */
public class PasswordChecker 
{
	// checkPassword checks @param password is a valid password
	public static boolean checkPassword(String password)
	{
		// Contains at least one digit, letter (upper and lower), special character, no whitespaces, and has a length of 8 or higher.
		String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\"\"'`~_=|({[.,/+#?!@$%^&*-]})])(?=\\S+$).{8,}";
		Pattern pass = Pattern.compile(passwordRegex);
		Matcher pw = pass.matcher(password);
		return pw.find(); // return true if the length is greater than or equal to 8 and contains a letter, number, and special character
	}
}














