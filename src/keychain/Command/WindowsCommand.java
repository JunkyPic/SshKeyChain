package keychain.Command;

import javax.swing.*;
import java.io.IOException;

public class WindowsCommand implements ICommand{
    public void openTerminal() {
        try{
            Runtime rt = Runtime.getRuntime();
            rt.exec(new String[]{"cmd.exe","/c","start"});
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to open command prompt");
        }
    }

    public void exec(String command){
        //
    }
}
