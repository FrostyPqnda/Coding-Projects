package PasswordChecker;
/*
 * This is a Java program that checks if the user's
 * password is long enough.
 */
public class PasswordChecker 
{
	/*
	 * Public static boolean method that takes
	 * in a string parameter and checks if the 
	 * length is long enough.
	 */
	public static boolean checkPassword(String password)
	{
	    String passwordChecker = "";
	    for(int i = 0; i < password.length(); i++)
	    {
	        char curr = password.charAt(i);
	        if(!Character.isLetter(curr) && !Character.isDigit(curr))
	        {
	            return false;
	        }
	    }
	    boolean passwordLength = password.length() >= 8;
	    return passwordLength;
	}
}














