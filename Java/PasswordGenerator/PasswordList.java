import java.util.List;
import java.io.FileWriter;
//import java.io.FileReader;
//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
//import java.io.File;
//import java.nio.file.Path;
import java.nio.file.Paths;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class PasswordList extends UserData {

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

    public List<String> passwordList() throws ArrayIndexOutOfBoundsException
    {        
        List<String> passwordArrList = null;
        try {
            passwordArrList = Files.readAllLines(Paths.get("PasswordList.bsd"));
            return passwordArrList;
        } catch(IOException io) {
            System.out.println("An error occured!");
        }
        return passwordArrList;
    }

    public String getPassword(List<String> passwordList, String username) throws Exception
    {
        CryptoPassword cp = new CryptoPassword();
        SecretKey secretKey = cp.generateKey("AES");
        Cipher cipher = Cipher.getInstance("AES");

        for(int i = 0; i < passwordList.size(); i++) {
            if(verifyUsername(username)) {
                return cp.decryptPassword(UserData.getEncryptedPassword(), secretKey, cipher);
            }
        }
        return "Username not found!";
    }
}
