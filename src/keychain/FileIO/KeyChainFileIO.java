package keychain.FileIO;

import keychain.Config.Config;
import keychain.Models.KeyChain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.TreeMap;

public class KeyChainFileIO extends FileIO {
    public static final String DELIMITER = "->";

    public static TreeMap<String, String> toHashMap(String fileName) throws IOException, InvalidPropertiesFormatException {
        TreeMap<String, String> keychain = new TreeMap<>();

        // Check if the file is empty, if it is return an empty treemap
        File f = new File(fileName);
        if(0 == f.length()) {
            return keychain;
        }

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            if(!line.contains(KeyChainFileIO.DELIMITER)) {
                throw new InvalidPropertiesFormatException("Unknown delimiter found when parsing keychain. Expecting \"" + KeyChainFileIO.DELIMITER + "\"");
            }
           String[] parts = line.split(KeyChainFileIO.DELIMITER);
           keychain.put(parts[0], parts[1]);
        }

        return keychain;
    }

    public static void write(KeyChain keyChain, Config config) {
        StringBuilder sr = new StringBuilder();

        for(Map.Entry<String, String> element: keyChain.getKeychain().entrySet()) {
            sr.append(element.getKey()).append(DELIMITER).append(element.getValue()).append(System.lineSeparator());
        }

        // Trim last System.lineSeparator() or it will keep appending new lines like there's no tomorrow
        FileIO.write(sr.toString().trim(), config.getConfig().get("KEYCHAIN_FILE"));
    }
}
