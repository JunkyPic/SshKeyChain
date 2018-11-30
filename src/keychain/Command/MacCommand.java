package keychain.Command;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MacCommand implements ICommand {
    public void openTerminal() {
        try{
            Runtime.getRuntime().exec("/usr/bin/open -a Terminal");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to open terminal");
        }
    }

    public void exec(String command) {
        try{
            final File file = new File(System.getProperty("user.dir") + "/src/keychain/Scripts/Connect.sh");
            if(!file.canExecute() || !file.canRead() || !file.canWrite()) {
                file.setReadable(true, false);
                file.setExecutable(true, false);
                file.setWritable(true, false);
            }

            ProcessBuilder pb = new ProcessBuilder(System.getProperty("user.dir") + "/src/keychain/Scripts/Connect.sh");
            Map<String, String> env = pb.environment();
            env.put("command", command);
            pb.start();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
