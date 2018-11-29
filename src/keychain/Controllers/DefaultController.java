package keychain.Controllers;

import keychain.Command.MacCommand;
import keychain.Command.OS;
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
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public void connectButtonClick(JButton button) {
        String btnName = button.getName();
        // Get the alias
        String alias = btnName.replace(Button.BUTTON_CONNECT_PREFIX, "");

        StringSelection selection = new StringSelection(keyChain.get(alias));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        if(config.getConfig().get("OPEN_TERMINAL_ON_ADDRESS_COPY").equals("true")) {
            // open a new terminal
            String os = OS.getOS();

            if(os.toLowerCase().contains("mac")) {
                MacCommand macCommand = new MacCommand();
                macCommand.openTerminal();
            } else {
                view.displayWarning("Unknown OS, only MAC OS works for now.");
            }
        }
    }

    public void deleteButtonClick(JButton button) {
        String btnName = button.getName();
        // Get the alias
        String alias = btnName.replace(Button.BUTTON_DELETE_PREFIX, "");

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null,
                "Are you sure you want to delete the address?" + System.lineSeparator() + "This action cannot be undone.",
                "Warning",
                dialogButton);

        if(dialogResult == JOptionPane.YES_OPTION){
            keyChain.remove(alias);

            // write the keychain to the keychain file
            KeyChainFileIO.write(keyChain, config);

            // redraw the canvas
            view.redraw(buttonModel, textFieldModel, keyChain);

            // Re-add listeners
            addListeners();
        }
    }

    public void addButtonClick() {
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
