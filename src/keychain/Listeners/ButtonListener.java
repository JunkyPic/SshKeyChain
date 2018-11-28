package keychain.Listeners;

import keychain.Controllers.DefaultController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonListener extends AbstractButton {
    private DefaultController defaultController;

    public ButtonListener(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        JButton hasMapButtonPointer = defaultController.getButtonModel().getButtons().get(btn.getName());

        if(null != hasMapButtonPointer) {
            defaultController.delegateAction(hasMapButtonPointer);
        }
    }
}
