package keychain.Listeners;

import keychain.Controllers.DefaultController;
import keychain.Models.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static keychain.Models.Button.*;

public class ButtonListener extends AbstractButton {
    private DefaultController defaultController;

    public ButtonListener(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();

        String btnName = btn.getName();

        if (btnName.equals(Button.BUTTON_ADD)) {
            defaultController.addButtonClick();
        } else {
            // Here it's always going to be one of the Delete or Connect
            // Each case will be handled separately
            if(btnName.contains(BUTTON_DELETE_PREFIX)) {
                defaultController.deleteButtonClick(btn);
                return;
            }

            if(btnName.contains(BUTTON_COPY_PREFIX)) {
                defaultController.copyButtonClick(btn);
                return;
            }

            if(btnName.contains(BUTTON_CONNECT_PREFIX)) {
                defaultController.connectButtonClick(btn);
                return;
            }
        }
    }
}
