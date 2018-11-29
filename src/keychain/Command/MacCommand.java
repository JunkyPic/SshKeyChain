package keychain.Command;

import javax.swing.*;
import java.io.IOException;

public class MacCommand implements ICommand {
    public void openTerminal() {
        try{
            Runtime.getRuntime().exec("/usr/bin/open -a Terminal");
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to open terminal");
        }
    }
}
