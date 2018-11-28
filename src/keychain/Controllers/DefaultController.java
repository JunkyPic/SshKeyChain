package keychain.Controllers;

import keychain.Config.Config;
import keychain.FileIO.KeyChainFileIO;
import keychain.Helpers.Validator;
import keychain.Listeners.ButtonListener;
import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;
import keychain.Views.DefaultView;
import keychain.Views.Errors.Errors;

import javax.swing.*;
import java.util.Map;

import static keychain.Models.Button.BUTTON_EDIT_PREFIX;

public class DefaultController extends AbstractController {

    public DefaultController(
            Config config,
            KeyChain keyChain,
            DefaultView view,
            Button button,
            TextField textField
    ) {
        super(config, keyChain, view, button, textField);
    }

    public void run() {
        view.buildHeader(buttonModel, textFieldModel);
        view.buildBody(buttonModel, textFieldModel, keyChain);
        view.buildFooter(buttonModel, textFieldModel, keyChain);

        addListeners();
    }

    private void addListeners() {
        if (!buttonModel.getButtons().isEmpty()) {
            for (Map.Entry<String, JButton> entry : buttonModel.getButtons().entrySet()) {
                entry.getValue().addActionListener(new ButtonListener(this));
            }
        }
    }

    public void delegateAction(JButton button) {
        String btnName = button.getName();

        if (btnName.equals(Button.BUTTON_ADD)) {
            addButtonClick();
        }else if (btnName.equals(Button.BUTTON_SEARCH)) {
            // TODO Implement this
            // Skip for now
        } else {
            // Here it's always going to be one of the Edit, Delete, or Connect
            // Each case will be handled separately
            // Don't match the button exactly since there's no point, we can do that later on
            if(btnName.contains(BUTTON_EDIT_PREFIX)) {
                editButtonClick(btnName);
                return;
            }
        }
    }

    private void editButtonClick(String btnName) {
        String hashMapKeyName = btnName.replace(Button.BUTTON_EDIT_PREFIX, "");

        JButton button = new JButton();
        JTextField aliasTextField = new JTextField();
        JTextField addressTextField = new JTextField();

        if(null != buttonModel.get(Button.BUTTON_EDIT_PREFIX + hashMapKeyName)) {
            button = buttonModel.get(Button.BUTTON_EDIT_PREFIX+ hashMapKeyName);
        }

        if(null != textFieldModel.get(TextField.TEXTFIELD_ADDRESS_PREFIX + hashMapKeyName)) {
            addressTextField = textFieldModel.get(TextField.TEXTFIELD_ADDRESS_PREFIX + hashMapKeyName);
        }

        if(null != textFieldModel.get(TextField.TEXTFIELD_ALIAS_PREFIX + hashMapKeyName)) {
            aliasTextField = textFieldModel.get(TextField.TEXTFIELD_ALIAS_PREFIX + hashMapKeyName);
            // Remove the old entry in the hashmap
            keyChain.remove(aliasTextField.getText());
        }

        // If it's editable, we can assume a new value was introduced
        // Even if it wasn't it's easier to remove the old value and add the new one
        if(aliasTextField.isEditable() && addressTextField.isEditable()) {
            if(!isInputValid(aliasTextField, addressTextField)) {
                return;
            }

            System.out.println(aliasTextField.getText());
            System.out.println(keyChain.getKeychain().entrySet());

            // Add the alias and address to the keychain
            keyChain.put(aliasTextField.getText(), addressTextField.getText());

            // write the keychain to the keychain file
            KeyChainFileIO.write(keyChain, config);

            // redraw the canvas
            view.redraw(buttonModel, textFieldModel, keyChain);

            // Re-add listeners
            addListeners();

            return;
        }

        view.toggleTextFieldEditability(aliasTextField);
        view.toggleTextFieldEditability(addressTextField);
        view.toggleButtonLabel(button);
    }

    private void addButtonClick() {
        JTextField headerAliasTextFieldBtn = textFieldModel.get(TextField.TEXTFIELD_ALIAS_PREFIX + "header_textfield");
        JTextField headerAddressTextFieldBtn = textFieldModel.get(TextField.TEXTFIELD_ADDRESS_PREFIX + "header_address");

        String alias = headerAliasTextFieldBtn.getText();
        String address = headerAddressTextFieldBtn.getText();

        if(!isInputValid(headerAliasTextFieldBtn, headerAddressTextFieldBtn )) {
            return;
        }

        // Add the alias and address to the keychain
        keyChain.put(alias, address);

        // write the keychain to the keychain file
        KeyChainFileIO.write(keyChain, config);

        // redraw the canvas
        view.redraw(buttonModel, textFieldModel, keyChain);

        // Re-add listeners
        addListeners();
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isInputValid(JTextField aliasText, JTextField addressText) {
        String alias = aliasText.getText();
        String address = addressText.getText();

        int aliasValidatorCode = Validator.isAliasValid(alias, getKeyChain());
        int addressValidatorCode = Validator.isAddressValid(address);

        if (Errors.errors.get(aliasValidatorCode) != null) {
            view.displayWarning(Errors.getErrorByCode(aliasValidatorCode));
            return false;
        }

        if (Errors.errors.get(addressValidatorCode) != null) {
            view.displayWarning(Errors.getErrorByCode(addressValidatorCode));
            return false;
        }

        return true;
    }

}
