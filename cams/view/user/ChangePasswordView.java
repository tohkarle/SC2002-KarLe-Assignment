package cams.view.user;

import cams.interfaces.Input;
import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.ChangePasswordUI;

public class ChangePasswordView implements View {

    private Navigation navigation;
    private Input getInput;

    public ChangePasswordView(Navigation navigation, Input getInput) {
        this.navigation = navigation;
        this.getInput = getInput;
    }

    public void render() {
        UI changPasswordUI = new ChangePasswordUI(navigation, getInput);
        changPasswordUI.body();
    }
}
