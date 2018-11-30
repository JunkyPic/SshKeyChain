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
    private JPanel bodyPanelPart;
    private JScrollPane bodyPanelScrollable;

    public DefaultView() {
        mainFrame = new JFrame("SSH KEYCHAIN");
        mainFrame.setSize(1000, 600);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void buildHeader(Button button, TextField textField) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JLabel aliasLabel = new JLabel("Alias");
        JLabel addressLabel = new JLabel("Address");

        JButton addButton = new JButton("Add");
        addButton.setName(Button.BUTTON_ADD);

        JTextField aliasTextField = new JTextField();
        aliasTextField.setColumns(10);
        aliasTextField.setName(TextField.TEXTFIELD_ALIAS_PREFIX + "header_textfield");

        JTextField addressTextField = new JTextField();
        addressTextField.setColumns(20);
        addressTextField.setName(TextField.TEXTFIELD_ADDRESS_PREFIX + "header_address");

        // Add buttons and textfields to models
        button.put(Button.BUTTON_ADD, addButton);
        textField.put(TextField.TEXTFIELD_ALIAS_PREFIX + "header_textfield", aliasTextField);
        textField.put(TextField.TEXTFIELD_ADDRESS_PREFIX + "header_address", addressTextField);

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
        this.bodyPanelScrollable = new JScrollPane();

        this.bodyPanelScrollable.setLayout(new ScrollPaneLayout());
        mainFrame.add(this.bodyPanelScrollable);
        JPanel bodyPanelPart = new JPanel();
        bodyPanelPart.setLayout(new GridLayout(keychain.size(), 1));
        this.bodyPanelPart = bodyPanelPart;

        if(!keychain.isEmpty()) {
            this.bodyPanelScrollable.setVisible(true);
            redraw(button, textField, keyChain);
        } else {
            this.bodyPanelScrollable.setVisible(false);
        }

        this.bodyPanelScrollable.setViewportView(bodyPanelPart);
        mainFrame.setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    public void redraw(Button button, TextField textField, KeyChain keyChain) {
        bodyPanelPart.removeAll();
        bodyPanelPart.revalidate();
        bodyPanelPart.repaint();

        bodyPanelPart.setLayout(new GridLayout(keyChain.getKeychain().size(), 1));

        for (Map.Entry<String, String> entry : keyChain.getKeychain().entrySet()) {
            JPanel part = new JPanel();
            part.setLayout(new FlowLayout());

            JLabel aliasLabel = new JLabel("Alias");
            JLabel addressLabel = new JLabel("Address");

            JTextField aliasTextField = new JTextField();
            aliasTextField.setName("alias_" + entry.getKey());
            aliasTextField.setEditable(false);
            aliasTextField.setText(entry.getKey());
            aliasTextField.setColumns(10);
            textField.put(TextField.TEXTFIELD_ALIAS_PREFIX + entry.getKey(), aliasTextField);

            JTextField addressTextField = new JTextField();
            addressTextField.setName("address_" + entry.getKey());
            addressTextField.setEditable(false);
            addressTextField.setText(entry.getValue());
            addressTextField.setColumns(20);
            textField.put(TextField.TEXTFIELD_ADDRESS_PREFIX + entry.getKey(), addressTextField);

            JButton deleteButton = new JButton("Delete");
            deleteButton.setName(Button.BUTTON_DELETE_PREFIX + entry.getKey());
            button.put(Button.BUTTON_DELETE_PREFIX + entry.getKey(), deleteButton);

            JButton connectButton = new JButton("Connect");
            connectButton.setName(Button.BUTTON_CONNECT_PREFIX + entry.getKey());
            button.put(Button.BUTTON_CONNECT_PREFIX + entry.getKey(), connectButton);

            part.add(aliasLabel);
            part.add(aliasTextField);

            part.add(addressLabel);
            part.add(addressTextField);

            part.add(deleteButton);
            part.add(connectButton);

            part.setVisible(true);

            bodyPanelPart.add(part);
        }

        bodyPanelScrollable.setVisible(true);
        bodyPanelPart.setVisible(true);
        mainFrame.setVisible(true);
    }

    public void buildFooter(TextField textField, KeyChain keyChain) {
        if(!keyChain.getKeychain().isEmpty()) {
            JPanel footerPanel = new JPanel();
            footerPanel.setLayout(new FlowLayout());

            JLabel searchLabel = new JLabel("Search");
            JTextField searchTextField = new JTextField();
            searchTextField.setColumns(30);
            searchTextField.setName(TextField.TEXTFIELD_SEARCH_PREFIX);

            textField.put(TextField.TEXTFIELD_SEARCH_PREFIX, searchTextField);

            footerPanel.add(searchLabel);
            footerPanel.add(searchTextField);

            mainFrame.add(footerPanel);
            mainFrame.setVisible(true);
        }
    }

    public void displayWarning(String warning) {
        JOptionPane.showMessageDialog(null, warning);
    }
}
