import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Java program that checks if a password is long
 * enough and meets the requirement
 * 
 * Requirements:
 * 
 * Has letters, numbers, and special characters.
 */
public class PasswordChecker 
{
	/**
	 * Method checkPassword checks @param password if it
	 * is a valid password and will return a boolean value
	 * of true or false.
	 */
	public static boolean checkPassword(String password)
	{
		// Special characters [$, @, #, etc] in the password string
		Pattern specialChars = Pattern.compile("[^A-Za-z0-9 ]");
		Matcher s = specialChars.matcher(password);
		
		// Alpha-numeric characters in the password string
		Pattern alphaNumericChars = Pattern.compile("[A-Za-z0-9]");
		Matcher a = alphaNumericChars.matcher(password);

		return password.length() >= 8 && a.find() && s.find();
	}
}














