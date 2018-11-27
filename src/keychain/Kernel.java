package keychain;

import keychain.Config.Config;
import keychain.Config.Exceptions.InvalidConfigException;
import keychain.Config.Validator;
import keychain.Controllers.DefaultController;
import keychain.Views.DefaultView;

import javax.swing.*;
import java.io.IOException;

public class Kernel {
    public void run() {
        Config config = buildConfig();

        DefaultController defaultController = new DefaultController(config);
        defaultController.setMainFrame((new DefaultView()).create());

    }

    private Config buildConfig() {
        Config config = new Config();

        try{
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
