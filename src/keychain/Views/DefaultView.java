package keychain.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DefaultView {
    public JFrame create() {
        JFrame mainFrame = new JFrame("SSH KEYCHAIN");
        mainFrame.setSize(800, 400);
        mainFrame.setLayout(new GridLayout(2, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);

        return mainFrame;
    }
}
