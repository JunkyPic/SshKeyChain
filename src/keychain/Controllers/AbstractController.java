package keychain.Controllers;

import keychain.Config.Config;
import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;
import keychain.Views.DefaultView;

abstract public class AbstractController {
    protected Config config;
    protected KeyChain keyChain;
    protected DefaultView view;
    protected Button buttonModel;
    protected TextField textFieldModel;

    public AbstractController(
            Config config,
            KeyChain keyChain,
            DefaultView view,
            Button button,
            TextField textField
    ) {
        this.view = view;
        this.buttonModel = button;
        this.textFieldModel = textField;
        this.config = config;
        this.keyChain = keyChain;
    }

    public KeyChain getKeyChain() {
        return keyChain;
    }

    public DefaultView getView() {
        return view;
    }

    public Button getButtonModel() {
        return buttonModel;
    }

    public TextField getTextFieldModel() {
        return textFieldModel;
    }
}
