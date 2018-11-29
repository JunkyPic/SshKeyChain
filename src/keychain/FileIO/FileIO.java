package keychain.FileIO;

import java.io.*;

public class FileIO{
    public static void write(String toWrite, String fileName) {
        File f = new File(fileName);

        if (f.exists() && !f.isDirectory()) {
            try{
                FileOutputStream out = new FileOutputStream(fileName);

                out.write(toWrite.trim().getBytes());

                out.close();
            }catch (Exception e) {
                //
            }
        } else {
            try {
                f.createNewFile();
                try{
                    FileOutputStream out = new FileOutputStream(fileName);

                    out.write(toWrite.trim().getBytes());

                    out.close();
                }catch (Exception e) {
                    //
                }
            } catch (IOException e) {
                //
            }
        }
    }

}
