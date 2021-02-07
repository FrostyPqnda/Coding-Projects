import java.util.HashMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class PasswordList {
    public void writeToPasswordList(String userName, byte[] userPassword)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("PasswordList.bsd", true));
            bw.write(userName + ":" + userPassword);
            bw.newLine();
            bw.close();
            System.out.println("\nData successfully written.");
        } catch(IOException io) {
            System.out.println("An error occured!");
			io.printStackTrace();
        }
    }

    public HashMap<String, String> getPasswordList() throws ArrayIndexOutOfBoundsException
    {
        HashMap<String, String> hm = new HashMap<String, String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("PasswordList.bsd"));
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":"); // Split the line by :
                String username = parts[0].trim(); // Username
                String password = parts[1].trim(); // Password
                
                if (!username.equals("") && !password.equals("")) 
                    hm.put(username, password); 
            }
        } catch(IOException io) {
            System.out.println("An error occured!!");
            io.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(Exception e) {
                    System.out.println("An error occured!!");
                    e.printStackTrace();
                }
            }
        }

        return hm;
    }

    public String getPassword(String username) throws Exception
    {
        CryptoPassword cp = new CryptoPassword();
        SecretKey secretKey = cp.generateKey("AES");
        Cipher cipher = Cipher.getInstance("AES");
        
        try {
            if(UserData.verifyUsername(username)) {
                return cp.decryptPassword(UserData.getEncryptedPassword(), secretKey, cipher);
            }
        }
        catch(BadPaddingException bpe) {
            bpe.printStackTrace();
        }

        return "Username not found!";
    }
}
