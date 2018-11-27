package keychain.Controllers;

import keychain.Config.Config;

import javax.swing.*;

public class DefaultController extends AbstractController{
    private JFrame mainFrame;

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public DefaultController(Config config) {
        super(config);
    }

}
