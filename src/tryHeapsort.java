import java.io.*;
import java.util.*;

public class tryHeapsort {

    static final int REPETITIONS = 30;

    public static void main(String[] args) {

    }
    // Read words from file, clean and return as array
    static String[] readWords(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            ArrayList<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                for (String w : line.trim().split("\\s+")) {
                    w = w.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (!w.isEmpty()) list.add(w);
                }
            }
            br.close();
            return list.toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Could not read file: " + filename);
            return new String[0];
        }
    }
}