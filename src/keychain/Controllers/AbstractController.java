package keychain.Controllers;

import keychain.Config.Config;

public class AbstractController {
    protected Config config;

    public AbstractController(Config config) {
        this.config = config;
    }
}
