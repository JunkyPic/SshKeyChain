package keychain.Listeners;

import keychain.Controllers.DefaultController;
import keychain.Helpers.Filter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TextFieldListener extends AbstractButton {
    private DefaultController defaultController;
    private Filter filter;

    public TextFieldListener(DefaultController defaultController) {
        this.defaultController = defaultController;
        this.filter = new Filter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filter.filter(defaultController, (JTextField) e.getSource());
    }
}