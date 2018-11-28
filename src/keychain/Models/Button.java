package keychain.Models;

import javax.swing.*;
import java.util.HashMap;

public class Button implements IModel {
    public static final String BUTTON_ADD = "add";
    public static final String BUTTON_EDIT_PREFIX = "edit_";
    public static final String BUTTON_CONNECT_PREFIX = "connect_";
    public static final String BUTTON_DELETE_PREFIX = "delete_";
    public static final String BUTTON_SEARCH = "search_";

    private HashMap<String, JButton> buttons = new HashMap<>();

    public void put(String alias, JButton component) {
        buttons.put(alias, component);
    }

    public JButton get(String alias) {
        return buttons.get(alias);
    }

    public HashMap<String, JButton> getButtons() {
        return buttons;
    }
}
