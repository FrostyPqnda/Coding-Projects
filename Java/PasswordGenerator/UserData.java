import java.util.Scanner;
import java.util.InputMismatchException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;


public class UserData extends PasswordGenerator 
{    
    public static void main(String[] args) throws InputMismatchException
    {
        Scanner scan = new Scanner(System.in);
        CryptoPassword cp = new CryptoPassword();
        PasswordList pl = new PasswordList();

        System.out.print("Enter an username: ");
        String name = scan.nextLine();
        
        System.out.print("Enter a password length: ");
        int length = scan.nextInt();
        String password = generatePassword(length);

        //System.out.println("\nUsername: " + name);
        //System.out.println("Password: " + password);

        scan.close();
        
        try {
            SecretKey secretKey = cp.generateKey("AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte[] encryptedUsername = cp.encryptString(name, secretKey, cipher);
            byte[] encryptedPassword = cp.encryptString(password, secretKey, cipher);
            pl.writeToPasswordList(encryptedUsername, encryptedPassword);
            
            String decryptedUsername = cp.decryptString(encryptedUsername, secretKey, cipher);
            System.out.println("\nUsername: " + decryptedUsername);
            String decryptedPassword = cp.decryptString(encryptedPassword, secretKey, cipher);
            System.out.println("Password: " + decryptedPassword);
        } catch(Exception e) {
            System.out.println(e);
        }

        //pl.readPasswordList();
    }
}
