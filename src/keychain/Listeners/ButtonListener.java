package keychain.Listeners;

import keychain.Controllers.DefaultController;
import keychain.Models.Button;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static keychain.Models.Button.BUTTON_DELETE_PREFIX;

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
        }else if (btnName.equals(Button.BUTTON_SEARCH)) {
            // TODO Implement this
            // Skip for now
        } else {
            // Here it's always going to be one of the Edit, Delete, or Connect
            // Each case will be handled separately
            // Don't match the button exactly since there's no point, we can do that later on
            if(btnName.contains(BUTTON_DELETE_PREFIX)) {
                defaultController.deleteButtonClick(btn);
                return;
            }
        }
    }
}
