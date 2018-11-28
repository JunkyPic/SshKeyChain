package keychain.Listeners;

import keychain.Controllers.DefaultController;

import java.awt.event.ActionEvent;

public class TextFieldListener extends AbstractButton {
    private DefaultController defaultController;

    public TextFieldListener(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}