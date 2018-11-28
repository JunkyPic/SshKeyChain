package keychain;

import keychain.Config.Config;
import keychain.Config.Validator;
import keychain.Controllers.DefaultController;
import keychain.FileIO.KeyChainFileIO;
import keychain.Models.Button;
import keychain.Models.KeyChain;
import keychain.Models.TextField;
import keychain.Views.DefaultView;
import keychain.Config.Exceptions.InvalidConfigException;

import javax.swing.*;
import java.io.IOException;
import java.util.TreeMap;

public class Kernel {
    public void run() {
        Config config = buildConfig();
        KeyChain keyChain = buildKeychain(config);

        DefaultController defaultController = new DefaultController(
                config,
                keyChain,
                new DefaultView(),
                new Button(),
                new TextField()
        );

        defaultController.run();
    }

    private KeyChain buildKeychain(Config config) {
        try {
            return new KeyChain(KeyChainFileIO.toHashMap(config.getConfig().get("KEYCHAIN_FILE")));
        } catch (IOException ioEx) {
            JOptionPane.showMessageDialog(null, ioEx.getMessage());
            System.exit(0);
        }

        return new KeyChain(new TreeMap<>());
    }

    private Config buildConfig() {
        Config config = new Config();

        try {
            config.buildConfig();
            Validator configValidator = new Validator();
            configValidator.validate(config);
        } catch (InvalidConfigException | IOException e) {
            // Display whatever message here, it's a lot easier and it actually makes sense
            // Since this is a gui application and we want to display relevant shit
            JOptionPane.showMessageDialog(null, e.getMessage());

            // No option to exit yet since no frame is actually created
            System.exit(0); // Graceful exit...hopefully
        }

        return config;
    }
}
