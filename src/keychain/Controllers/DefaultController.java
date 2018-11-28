package keychain.Controllers;

import keychain.Config.Config;
import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;
import keychain.Views.DefaultView;

public class DefaultController extends AbstractController{

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
    }
}
