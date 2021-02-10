import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;


public class UserData 
{    
    private static String username; // Username variable
    private static String password; // Password variable
    private static Scanner scan; // Scanner input
    private static CryptoPassword cp = new CryptoPassword(); // CryptoPassword object
    private static PasswordList pl = new PasswordList(); // PasswordList object
    private static PasswordGenerator pg = new PasswordGenerator();

    public static void main(String[] args) throws Exception {
        //createUserData();

    
        /*try { 
            pl.writeToPasswordList(username, getEncryptedPassword()); 
        } catch(Exception e) { 
            System.out.println(e); 
        }*/
        
        //System.out.println(pl.getPassword("Brian Pham"));
        //pl.getPasswordList().forEach((elem) -> System.out.println(elem));
        //System.out.println(pl.getPassword(pl.passwordList(), "Brian Pham"));
    }

    static void createUserData() {
        scan = new Scanner(System.in);
        System.out.print("Enter an username: ");
        username = scan.nextLine();

        System.out.print("Enter a password length: ");
        int length = scan.nextInt();
        password = pg.generatePassword(length);
        System.out.println("\nYour generated password: " + password);
        scan.close();
    }

    public static boolean verifyUsername(String username)
    {
        boolean found = false;
        String tempUsername = "";

        try {
            scan = new Scanner(new File("PasswordList.bsd"));
            scan.useDelimiter("[:\n]");

            while(scan.hasNext() && !found) {
                tempUsername = scan.next();
                
                if(username != null) {
                    if(tempUsername.trim().equals(username.trim())) {
                        found = true;
                    }
                }
            }
            scan.close();
        } catch(IOException io) {
            System.out.println("An error occured!");
        }

        return found;
    }

    public static byte[] getEncryptedPassword() throws Exception
    {
        SecretKey secretKey = cp.generateKey("AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] encryptedPassword = cp.encryptPassword(password, secretKey, cipher);
        return encryptedPassword;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        UserData.username = username;
    }
}
