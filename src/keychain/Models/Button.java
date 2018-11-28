package keychain.Models;

import javax.swing.*;
import java.util.HashMap;

public class Button implements IModel {
    private HashMap<String, JButton> buttons = new HashMap<>();

    public void put(String alias, JButton component) {
        buttons.put(alias, component);
    }

    public JButton get(String alias) {
        return buttons.get(alias);
    }
}
