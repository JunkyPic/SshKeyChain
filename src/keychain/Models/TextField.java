package keychain.Models;

import javax.swing.*;
import java.util.HashMap;

public class TextField implements IModel {
    public static final String TEXTFIELD_ALIAS_PREFIX = "alias_";
    public static final String TEXTFIELD_ADDRESS_PREFIX = "address_";
    public static final String TEXTFIELD_SEARCH_PREFIX = "search";

    private HashMap<String, JTextField> textFields = new HashMap<>();

    public void put(String alias, JTextField component) {
        textFields.put(alias, component);
    }

    public JTextField get(String alias) {
        return textFields.get(alias);
    }

    public HashMap<String, JTextField> getTextFields() {
        return textFields;
    }
}
