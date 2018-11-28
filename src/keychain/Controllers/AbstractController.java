package keychain.Controllers;

import keychain.Config.Config;
import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;
import keychain.Views.DefaultView;

public class AbstractController {
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

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public KeyChain getKeyChain() {
        return keyChain;
    }

    public void setKeyChain(KeyChain keyChain) {
        this.keyChain = keyChain;
    }

}
