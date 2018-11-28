package keychain.Models;

import javax.swing.*;
import java.util.HashMap;

public class TextField implements IModel {
    private HashMap<String, JTextField> textFields = new HashMap<>();

    public void put(String alias, JTextField component) {
        textFields.put(alias, component);
    }

    public JTextField get(String alias) {
        return textFields.get(alias);
    }
}
