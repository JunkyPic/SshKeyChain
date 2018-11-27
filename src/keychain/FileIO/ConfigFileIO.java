package keychain.FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigFileIO extends FileIO {
    public static ArrayList<String> toArrayByLine(String fileName) throws IOException {
        ArrayList<String> parts = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            parts.add(line);
        }

        return parts;
    }
}
