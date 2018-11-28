package keychain.FileIO;

import java.io.*;

public class FileIO{
    public static void write(String toWrite, String fileName) {
        File f = new File(fileName);
        FileWriter fw;

        if (f.exists() && !f.isDirectory()) {
            try {
                fw = new FileWriter(fileName);
                fw.write(toWrite + System.lineSeparator());
                fw.close();
            } catch (IOException ioe) {
                //
            }
        } else {
            try {
                f.createNewFile();
                try {
                    fw = new FileWriter(fileName);
                    fw.write(toWrite + System.lineSeparator());
                    fw.close();
                } catch (IOException ioe) {
                    //
                }
            } catch (IOException e) {
                //
            }
        }
    }

}
