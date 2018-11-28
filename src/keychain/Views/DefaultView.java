package keychain.Views;

import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.TreeMap;

public class DefaultView implements IView{
    private JFrame mainFrame;

    public DefaultView() {
        mainFrame = new JFrame("SSH KEYCHAIN");
        mainFrame.setSize(100, 600);
        mainFrame.setLayout(new GridLayout(2, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    @SuppressWarnings("Duplicates")
    public void buildHeader(Button button, TextField textField) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JLabel aliasLabel = new JLabel("Alias");
        JLabel addressLabel = new JLabel("Address");

        JButton addButton = new JButton("Add");

        JTextField aliasTextField = new JTextField();
        aliasTextField.setColumns(10);

        JTextField addressTextField = new JTextField();
        addressTextField.setColumns(20);

        button.put("add_button", addButton);
        textField.put("header_alias", aliasTextField);
        textField.put("header_address", addressTextField);

        headerPanel.add(aliasLabel);
        headerPanel.add(aliasTextField);
        headerPanel.add(addressLabel);
        headerPanel.add(addressTextField);
        headerPanel.add(addButton);

        mainFrame.add(headerPanel);

        mainFrame.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    public void buildBody(Button button, TextField textField, KeyChain keyChain) {
        // Iterate thru the key chain, if any keys are present
        // Dynamically create whatever elements are in there
        // Only the alias and the value are present
        TreeMap<String, String> keychain = keyChain.getKeychain();
        if(!keychain.isEmpty()) {
            JScrollPane bodyPanelScrollable = new JScrollPane();

            for (Map.Entry<String, String> entry : keychain.entrySet()) {

                bodyPanelScrollable.setLayout(new FlowLayout());

                JPanel bodyPanelPart = new JPanel();
                bodyPanelPart.setLayout(new FlowLayout());

                JLabel aliasLabel = new JLabel("Alias");
                JLabel addressLabel = new JLabel("Address");

            }
        }
    }

    public void revalidate() {
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
