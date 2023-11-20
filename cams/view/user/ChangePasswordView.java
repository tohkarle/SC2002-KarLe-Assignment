package cams.view.user;

import cams.interfaces.Navigation;
import cams.interfaces.UI;
import cams.interfaces.View;
import cams.ui.auth.ChangePasswordUI;

public class ChangePasswordView implements View {

    private Navigation navigation;

    public ChangePasswordView(Navigation navigation) {
        this.navigation = navigation;
    }

    public void render() {
        UI changPasswordUI = new ChangePasswordUI(navigation);
        changPasswordUI.body();
    }
}
