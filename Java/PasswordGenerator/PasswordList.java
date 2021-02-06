import java.util.HashMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class PasswordList {
    public void writeToPasswordList(byte[] userName, byte[] userPassword)
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("PasswordList.txt", true));
            bw.write(userName + ": " + userPassword);
            bw.newLine();
            bw.close();
            System.out.println("\nData successfully written.");
        } catch(IOException io) {
            System.out.println("An error occured!");
			io.printStackTrace();
        }
    }

    public void readPasswordList()
    {
        try {
            HashMap<String, String> hm = new HashMap<String, String>();
            String line;

            BufferedReader br = new BufferedReader(new FileReader("PasswordList.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                if(parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    hm.put(key, value);
                } else {
                    System.out.println("Ignoring line: " + line);
                }
            }

            for(String key : hm.keySet())
                System.out.println(key + ": " + hm.get(key));
            
                br.close();
        } catch(IOException io) {
            System.out.println("An error occured!!");
            io.printStackTrace();
        }
    }
}
