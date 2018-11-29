package keychain.Helpers;

import keychain.Controllers.DefaultController;
import keychain.Models.KeyChain;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class Filter {
    public void filter(DefaultController defaultController, JTextField textField) {
        String searchValue = textField.getText();

        // If the search value is empty, redraw the canvas
        if(searchValue.trim().isEmpty()) {
            defaultController.getView().redraw(defaultController.getButtonModel(), defaultController.getTextFieldModel(), defaultController.getKeyChain());
        } else {
            // Create a temporary map to redraw
            TreeMap<String, String> searchKeyChain = new TreeMap<>();

            for(Map.Entry<String, String> element: defaultController.getKeyChain().getKeychain().entrySet()) {
                if(element.getKey().contains(searchValue) || element.getValue().contains(searchValue)) {
                    searchKeyChain.put(element.getKey(), element.getValue());
                }
            }

            // redraw the canvas
            defaultController.getView().redraw(defaultController.getButtonModel(), defaultController.getTextFieldModel(), new KeyChain(searchKeyChain));
            defaultController.addButtonListeners();
        }
    }

}
